package ConsultaSentenciaPreparada;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultaSentenciaPreparada {

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
                PreparedStatement s = c.prepareStatement("SELECT * FROM CLIENTES WHERE DNI=?")) {

            // En lugar de repetir el código, se podrían introducir los DNIs en
            // un array e iterar sobre él para mostrar los datos para cada DNI.
            
            String dni = "89012345E";
            s.setString(1, dni);
            ResultSet rs = s.executeQuery();
            if (rs.next()) {
                System.out.print("Datos de cliente para DNI " + dni
                        + " => APELLIDOS: " + rs.getString("APELLIDOS"));
                Integer cp = rs.getInt("CP");
                if(!rs.wasNull()) System.out.print(", CP: " + cp);
                System.out.println("");
            } else {
                System.err.println("No existe cliente con DNI " + dni);
            }
            rs.close();
            
            dni = "74835834U";
            s.setString(1, dni);
            rs = s.executeQuery();
            if (rs.next()) {
                System.out.print("Datos de cliente para DNI " + dni
                        + " => APELLIDOS: " + rs.getString("APELLIDOS"));
                Integer cp = rs.getInt("CP");
                if(!rs.wasNull()) System.out.print(", CP: " + cp);
                System.out.println("");
            } else {
                System.err.println("No existe cliente con DNI " + dni);
            }
            rs.close();
            
            dni = "78901234X";
            s.setString(1, dni);
            rs = s.executeQuery();
            if (rs.next()) {
                System.out.print("Datos de cliente para DNI " + dni
                        + " => APELLIDOS: " + rs.getString("APELLIDOS"));
                Integer cp = rs.getInt("CP");
                if(!rs.wasNull()) System.out.print(", CP: " + cp);
                System.out.println("");
            } else {
                System.err.println("No existe cliente con DNI " + dni);
            }
            rs.close();

        } catch (SQLException e) {
            muestraErrorSQL(e);
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }

    }

}
