package FuncionAlmacenada;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.CallableStatement;
import java.sql.SQLException;

public class FuncionAlmacenada {

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
                CallableStatement s = c.prepareCall("{?=call apellidos_cliente(?)}")) {

            s.registerOutParameter(1, java.sql.Types.VARCHAR);
            s.setString(2, "78901234X");

            s.execute();

            String apellidos = s.getString(1);

            System.out.println("Apellidos: " + apellidos);

        } catch (SQLException e) {
            muestraErrorSQL(e);
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }

    }

}
