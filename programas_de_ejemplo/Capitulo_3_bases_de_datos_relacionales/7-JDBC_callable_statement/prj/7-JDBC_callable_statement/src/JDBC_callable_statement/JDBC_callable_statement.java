package JDBC_callable_statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBC_callable_statement {

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
            Connection c = DriverManager.getConnection(urlConnection, user, pwd)) {
      try (
              PreparedStatement sInsert = c.prepareStatement("INSERT INTO CLIENTES1(DNI,APELLIDOS,CP) VALUES (?,?,?);")) {

        c.setAutoCommit(false);

        int i = 0;
        sInsert.setString(++i, "54320198V");
        sInsert.setString(++i, "CARVAJAL");
        sInsert.setString(++i, "10109");
        sInsert.executeUpdate();

        sInsert.setString(i = 1, "76543210S");
        sInsert.setString(++i, "MARQUEZ");
        sInsert.setString(++i, "46987");
        sInsert.executeUpdate();

        sInsert.setString(i = 1, "90123456A");
        sInsert.setString(++i, "MOLINA");
        sInsert.setString(++i, "35153");
        sInsert.executeUpdate();

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