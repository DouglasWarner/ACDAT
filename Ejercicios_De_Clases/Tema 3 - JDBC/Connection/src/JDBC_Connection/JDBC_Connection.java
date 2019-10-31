package JDBC_Connection;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC_Connection {

  public static void muestraErrorSQL(SQLException e) {
    System.err.println("SQL ERROR mensaje: " + e.getMessage());
    System.err.println("SQL Estado: " + e.getSQLState());
    System.err.println("SQL código específico: " + e.getErrorCode());
  }
  
  public static void main(String[] args) {

    String basedatos = "ad";
    String host = "localhost";
    String port = "3306";
    String parAdic = "?useSSL=false";   // Para conexion sin SSL verification
    String urlConnection = "jdbc:mysql://" + host + ":" + port + "/" + basedatos + parAdic;
    String user = "ad";
    String pwd = "123";

    //Class.forName("com.mysql.jdbc.Driver");    // No necesario desde SE 6.0
    //Class.forName("com.mysql.cj.jdbc.Driver"); // para MySQL 8.0, no necesario
    try (Connection c = DriverManager.getConnection(urlConnection, user, pwd)) {
      System.out.println("Conexión realizada.");
      
        // CreateTableClientes(c);      CREA LA TABLA CLIENTES
        // InsertTableClientes(c);      INSERTA EN LA TABLA CLIENTES
        // ModificarTableClientes(c);   MODIFICA (ELIMINA Y ACTUALIZA) EN LA TABLA CLIENTES
        
    } catch (SQLException e) {
      System.out.println("SQL mensaje: " + e.getMessage());
      System.out.println("SQL Estado: " + e.getSQLState());
      System.out.println("SQL código específico: " + e.getErrorCode());
    } catch (Exception e) {
      e.printStackTrace(System.err);
    }
  }
  
  // CREA LA TABLA CLIENTES
  public static void CreateTableClientes(Connection c)
  {
      try (Statement s = c.createStatement()) {
          s.execute("CREATE TABLE CLIENTES (DNI CHAR(9) NOT NULL, APELLIDOS VARCHAR(100) NOT NULL, CP CHAR(5)"
                  + ", PRIMARY KEY (DNI))");
      } catch (SQLException ex) {
          muestraErrorSQL(ex);
      } catch (Exception e) {
          e.printStackTrace(System.err);
      }
      
  }
  
  // INSERTA EN LA TABLA CLIENTES
  public static void InsertTableClientes(Connection c)
  {
      try (Statement s = c.createStatement())
      {
          int nFil = s.executeUpdate("insert into CLIENTES values "
                  + "('78901234X','NADALES','44126'),"
                  + "('89012345E','HOJAS','null'),"
                  + "('56789012B','SAMPER','29730'),"
                  + "('09876543K','LAMIQUIZ','null');");
          System.out.println(nFil + " Filas insertadas.");
      } catch (SQLException ex) {
          muestraErrorSQL(ex);
      } catch (Exception e) {
          e.printStackTrace(System.err);
      }
  }
  
  // MODIFICA (ELIMINA Y ACTUALIZA) EN LA TABLA CLIENTES
  public static void ModificarTableClientes(Connection c)
  {
      try (Statement s = c.createStatement())
      {
          int nFilUpdate = s.executeUpdate("update CLIENTES set APELLIDOS = 'ROJAS' where DNI = '89012345E';");
          int nFilDelete = s.executeUpdate("delete from CLIENTES where DNI = '09876543K';");
          System.out.println(nFilUpdate + " Filas Actualizadas");
          System.out.println(nFilDelete + " Filas Eliminadas");
      } catch (SQLException ex) {
          muestraErrorSQL(ex);
      } catch (Exception e) {
          e.printStackTrace(System.err);
      }
  }
}