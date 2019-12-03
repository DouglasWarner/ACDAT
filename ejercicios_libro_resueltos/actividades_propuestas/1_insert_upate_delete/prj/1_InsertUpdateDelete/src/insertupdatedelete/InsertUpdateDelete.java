package insertupdatedelete;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

public class InsertUpdateDelete {

    public static void muestraErrorSQL(SQLException e) {
        System.err.println("SQL ERROR mensaje: " + e.getMessage());
        System.err.println("SQL Estado: " + e.getSQLState());
        System.err.println("SQL código específico: " + e.getErrorCode());
    }

    /**
     * @param args the command line arguments
     */
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
                Statement s = c.createStatement()) {
            int nFil = 0;
            nFil += s.executeUpdate("UPDATE CLIENTES SET APELLIDOS='ROJAS' WHERE DNI='89012345E'");
            nFil += s.executeUpdate("DELETE FROM CLIENTES WHERE DNI='09876543K'");
            System.out.println("Cambios realizados. " + nFil + " Filas afectadas.");

        } catch (SQLException e) {
            muestraErrorSQL(e);
            System.err.println("SQL código específico: " + e.getErrorCode());
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }

    }

}
