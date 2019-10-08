package JDBC_prepared_statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class JDBC_prepared_statement {

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
            PreparedStatement sInsert = c.prepareStatement("INSERT INTO CLIENTES1(DNI,APELLIDOS,CP) VALUES (?,?,?);")) {

      sInsert.setString(1, "78901234X");
      sInsert.setString(2, "NADALES");
      sInsert.setInt(3, 44126);

      sInsert.executeUpdate();

      int i=1;
      sInsert.setString(i++, "89012345E");
      sInsert.setString(i++, "ROJAS");
      sInsert.setNull(i++, Types.INTEGER);

      sInsert.executeUpdate();

      sInsert.setString(i=1, "56789012B");
      sInsert.setString(i++, "SAMPER");
      sInsert.setInt(i++, 29730);

      sInsert.executeUpdate();

    } catch (SQLException e) {
      muestraErrorSQL(e);
    } catch (Exception e) {
      e.printStackTrace(System.err);
    }
  }

}
