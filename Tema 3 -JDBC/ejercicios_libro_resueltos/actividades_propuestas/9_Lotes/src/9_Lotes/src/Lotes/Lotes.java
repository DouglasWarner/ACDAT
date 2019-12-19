package Lotes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Lotes {

    public static void muestraErrorSQL(SQLException e) {
        System.err.println("SQL ERROR mensaje: " + e.getMessage());
        System.err.println("SQL Estado: " + e.getSQLState());
        System.err.println("SQL código específico: " + e.getErrorCode());
    }

    public static void main(String[] args) {

        String basedatos = "ad";
        String host = "localhost";
        String port = "3306";
        String parAdic = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String urlConnection = "jdbc:mysql://" + host + ":" + port + "/" + basedatos + parAdic;
        String user = "ad";
        String pwd = "123";

        String[][] datosClientes = {
            {"13579134G", "MOYA", null},
            {"24680247G", "SILVA", "25865"},
            {"96307416R", "TORRES", "19273"}
        };

        try (
                Connection c = DriverManager.getConnection(urlConnection, user, pwd)) {
            try (
                    PreparedStatement sInsert = c.prepareStatement("INSERT INTO CLIENTES1(DNI,APELLIDOS,CP) VALUES (?,?,?)")) {

                boolean soportaLotes = c.getMetaData().supportsBatchUpdates();
                System.out.println("Soporte para lotes: "+(soportaLotes ? "Sí" : "No"));

                c.setAutoCommit(false);
                for (int nCli = 0; nCli < datosClientes.length; nCli++) {
                    for (int i = 0; i < datosClientes[nCli].length; i++) {
                        sInsert.setString(i + 1, datosClientes[nCli][i]);
                    }
                    if (soportaLotes) {
                        sInsert.addBatch();
                    }
                    else sInsert.executeUpdate();
                }
                if (soportaLotes) {
                    int[] numActLote = sInsert.executeBatch();
                    int i=1;
                    for(int numActSent: numActLote) {
                        System.out.println("Sentencia " + (i++) + ": "
                                + numActSent + " filas afectadas.");
                    }
                }
                c.commit();

            } catch (SQLException e) {
                muestraErrorSQL(e);
                try {
                    c.rollback();
                } catch (Exception er) {
                    System.err.println("ERROR haciendo ROLLBACK");
                    er.printStackTrace(System.err);
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR de conexión");
            e.printStackTrace(System.err);
        }

    }

}
