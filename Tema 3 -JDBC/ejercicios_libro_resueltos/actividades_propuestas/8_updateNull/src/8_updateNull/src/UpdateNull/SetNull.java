package UpdateNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SetNull {

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

        try (
                Connection c = DriverManager.getConnection(urlConnection, user, pwd)) {
            try (
                    Statement sConsulta = c.createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE)) {

                ResultSet rs = sConsulta.executeQuery(
                        "SELECT CP,DNI FROM CLIENTES1 WHERE DNI='76543210S'");

                // Sería más claro consultar con SELECT * y hacer updateString("CP", ...),
                // Se hace de esta manera para hacerlo como se plantea en el enunciado,
                // aunque ambas formas son equivalentes.
                c.setAutoCommit(false);

                try {
                    rs.last();
                    rs.updateString(1, null);
                    rs.updateRow();
                    c.commit();
                    System.out.println("Hecho updateInt(1, null)");
                } catch (Exception e) {
                    c.rollback();
                    System.err.println("Error con updateInt(1, null)");
                    throw e;
                }

                try {
                    rs.last();             // Modifica cliente
                    rs.updateNull(1);
                    rs.updateRow();
                    c.commit();
                    System.out.println("Hecho updateNull(1)");
                } catch (Exception e) {
                    c.rollback();
                    System.err.println("Error con updateNull(1)");
                    throw e;
                }

            } catch (SQLException e) {
                muestraErrorSQL(e);
                try {
                    c.rollback();
                    System.err.println("Se hace ROLLBACK");
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
