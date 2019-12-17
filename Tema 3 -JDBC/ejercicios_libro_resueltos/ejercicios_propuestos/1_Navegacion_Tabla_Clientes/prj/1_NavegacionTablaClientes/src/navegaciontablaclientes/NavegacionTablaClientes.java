package navegaciontablaclientes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class NavegacionTablaClientes {

    public static void muestraErrorSQL(SQLException e) {
        System.err.println("SQL ERROR mensaje: " + e.getMessage());
        System.err.println("SQL Estado: " + e.getSQLState());
        System.err.println("SQL código específico: " + e.getErrorCode());
    }

    /**
     *
     * @param rs
     */
    public static void muestraRegistro(ResultSet rs) throws SQLException {
        System.out.println("[" + rs.getRow() + "]");
        System.out.println("DNI: " + rs.getString("DNI"));
        System.out.println("Apellidos: " + rs.getString("APELLIDOS"));
        System.out.println("CP: " + rs.getString("CP"));
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        String basedatos = "ad";
        String host = "localhost";
        String port = "3306";
        String parAdic = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String urlConnection = "jdbc:mysql://" + host + ":" + port + "/" + basedatos + parAdic;
        String user = "ad";
        String pwd = "123";

        try (
                Connection c = DriverManager.getConnection(urlConnection, user, pwd);
                Statement s = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = s.executeQuery("SELECT * FROM CLIENTES");
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));) {

            boolean fin = false;

            if (rs.next()) {
                muestraRegistro(rs);
            } else {
                System.out.println("Tabla vacía");
                fin = true;
            }

            rs.last();
            int numReg = rs.getRow();  // Número de filas en ResultSet
            rs.first();
            while (!fin) {
                System.out.print("Introduzca comando (? para ayuda)>>");
                String comando = br.readLine();

                if (comando.equals("?")) {
                    System.out.println(". para terminar");
                    System.out.println("k para ir a siguiente fila");
                    System.out.println("d para ir a anterior fila");
                    System.out.println("un número para ir a la fila en la posición indicada por el número");
                } else if (comando.equals(".")) {
                    System.out.println("Adiós ...");
                    fin = true;
                } else if (comando.equals("d")) {
                    if (!rs.isFirst() && !rs.isBeforeFirst()) {
                        rs.previous();
                        muestraRegistro(rs);
                    } else {
                        System.out.println("Se está en el primer registro. No se puede ir al anterior.");
                    }
                } else if (comando.equals("k")) {
                    if (!rs.isLast() && !rs.isAfterLast()) {
                        rs.next();
                        muestraRegistro(rs);
                    } else {
                        System.out.println("Se está en último registro. No se puede ir al siguiente.");
                    }
                } else {  // Quizá sea un número. Si no, comando no válido    
                    int p = -1;
                    try {
                        p = Integer.parseInt(comando);
                    } catch (NumberFormatException e) {
                        p = -1;
                    }
                    if (p < 0) {
                        System.out.println("Comando no válido: " + comando);
                    } else if (p < 1) {
                        System.out.println("Posición debe ser mayor o igual que 1");
                    } else if (p > numReg) {
                        System.out.println("Posición mayor que la del último registro(" + numReg + ")");
                    }
                    else {
                        rs.absolute(p);
                        muestraRegistro(rs);
                    }
                }
            }
        } catch (SQLException e) {
            muestraErrorSQL(e);
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }

    }

}
