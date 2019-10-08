package JDBC_ResultSet_actualizable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC_ResultSet_actualizable {

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
              Statement sConsulta = c.createStatement(
                      ResultSet.TYPE_SCROLL_INSENSITIVE,
                      ResultSet.CONCUR_UPDATABLE)) {

        ResultSet rs = sConsulta.executeQuery("SELECT * FROM CLIENTES WHERE CP IS NOT NULL");

        c.setAutoCommit(false);

        rs.last();  // Modifica último cliente
        rs.updateString("CP", "02568");
        rs.updateRow();

        rs.previous();  // Borrar penúltimo cliente
        rs.deleteRow();

        rs.moveToInsertRow();  // Inserta nueva fila
        rs.updateString("DNI", "24862486S");
        rs.updateString("APELLIDOS", "ZURITA");
        rs.updateString("CP", "33983");
        rs.insertRow();

        c.commit();

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
