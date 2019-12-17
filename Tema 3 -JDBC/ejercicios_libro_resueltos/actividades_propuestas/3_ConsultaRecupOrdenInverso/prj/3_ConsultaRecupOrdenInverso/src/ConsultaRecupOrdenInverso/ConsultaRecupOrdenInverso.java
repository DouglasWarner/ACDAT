package ConsultaRecupOrdenInverso;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultaRecupOrdenInverso {

    public static void muestraErrorSQL(SQLException e) {
        System.err.println("SQL ERROR mensaje: " + e.getMessage());
        System.err.println("SQL Estado: " + e.getSQLState());
        System.err.println("SQL código específico: " + e.getErrorCode());
    }

    public static void main(String[] args) {

        String basedatos = "libro_ad";
        String host = "localhost";
        String port = "3306";
        String parAdic = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String urlConnection = "jdbc:mysql://" + host + ":" + port + "/" + basedatos + parAdic;
        String user = "libro_ad";
        String pwd = "libro_ad";

        try (
                Connection c = DriverManager.getConnection(urlConnection, user, pwd);
                //Statement s = c.createStatement();  // Con MySQL 8.0, se puede crear así
                Statement s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = s.executeQuery("SELECT * FROM CLIENTES")) {

            /*
            System.out.print("Tipo de cursor: ");
            switch (rs.getType()) {
                case ResultSet.TYPE_FORWARD_ONLY:
                    System.out.println("TYPE_FORWARD_ONLY");
                    break;
                case ResultSet.TYPE_SCROLL_INSENSITIVE:
                    System.out.println("TYPE_SCROLL_INSENSITIVE");
                    break;
                case ResultSet.TYPE_SCROLL_SENSITIVE:
                    System.out.println("TYPE_SCROLL_SENSITIVE");
                    break;
            }

            System.out.print("Opciones de concurrencia: ");
            switch (rs.getConcurrency()) {
                case ResultSet.CONCUR_READ_ONLY:
                    System.out.println("CONCUR_READ_ONLY");
                    break;
                case ResultSet.CONCUR_UPDATABLE:
                    System.out.println("CONCUR_UPDATABLE");
                    break;
            }

            System.out.println("------------");
            System.out.println("De delante a atrás:");
            int i_ = 1;
            // rs.beforeFirst();
            while (rs.next()) {
                System.out.println("[" + (i_++) + "]");
                System.out.println("DNI: " + rs.getString("DNI"));
                System.out.println("Apellidos: " + rs.getString("APELLIDOS"));
                System.out.println("CP: " + rs.getString("CP"));
            }
            System.out.println("------------");
             */
            
            System.out.println("De atrás a delante:");
            int i = 1;
            rs.afterLast();
            while (rs.previous()) {
                System.out.println("[" + (i++) + " por la cola]");
                System.out.println("DNI: " + rs.getString("DNI"));
                System.out.println("Apellidos: " + rs.getString("APELLIDOS"));
                System.out.println("CP: " + rs.getString("CP"));
            }
        } catch (SQLException e) {
            muestraErrorSQL(e);
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }

    }

}
