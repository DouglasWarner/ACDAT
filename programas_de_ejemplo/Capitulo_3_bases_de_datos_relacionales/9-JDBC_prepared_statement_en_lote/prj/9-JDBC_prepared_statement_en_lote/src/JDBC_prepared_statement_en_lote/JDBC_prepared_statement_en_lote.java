package JDBC_prepared_statement_en_lote;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBC_prepared_statement_en_lote {

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

    String[][] datosClientes = {
      {"13579135G", "MOYA", null},
      {"24680246G", "SILVA", "25865"},
      {"96307418R", "TORRES", "19273"}
    };

    try (
            Connection c = DriverManager.getConnection(urlConnection, user, pwd)) {
      try (
              PreparedStatement sInsert = c.prepareStatement("INSERT INTO CLIENTES1(DNI,APELLIDOS,CP) VALUES (?,?,?);")) {

        c.setAutoCommit(false);

        for (int nCli = 0; nCli < datosClientes.length; nCli++) {
          for (int i = 0; i < datosClientes[nCli].length; i++) {
            sInsert.setString(i + 1, datosClientes[nCli][i]);
          }
          sInsert.addBatch();
        }
        
        sInsert.executeBatch();

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
