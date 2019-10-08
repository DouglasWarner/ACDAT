package jdbc_connection;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBC_Connection {

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

    //Class.forName("com.mysql.jdbc.Driver");    // No necesario desde SE 6.0
    //Class.forName("com.mysql.cj.jdbc.Driver"); // para MySQL 8.0, no necesario
    try (Connection c = DriverManager.getConnection(urlConnection, user, pwd)) {
      System.out.println("Conexión realizada.");        
    } catch (SQLException e) {
      System.out.println("SQL mensaje: " + e.getMessage());
      System.out.println("SQL Estado: " + e.getSQLState());
      System.out.println("SQL código específico: " + e.getErrorCode());
    } catch (Exception e) {
      e.printStackTrace(System.err);
    }
  }

}
