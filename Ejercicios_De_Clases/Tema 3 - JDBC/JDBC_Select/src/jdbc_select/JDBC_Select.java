/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc_select;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 *
 * @author douglas
 */
public class JDBC_Select {

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

    try (Connection c = DriverManager.getConnection(urlConnection, user, pwd)) {
      System.out.println("Conexión realizada.");
        SelectOnTableClientes(c);
    } catch (SQLException e) {
      System.out.println("SQL mensaje: " + e.getMessage());
      System.out.println("SQL Estado: " + e.getSQLState());
      System.out.println("SQL código específico: " + e.getErrorCode());
    } catch (Exception e) {
      e.printStackTrace(System.err);
    }
  }
    
    // EXECUTA LA CONSULTA SELECT
  public static void SelectOnTableClientes(Connection c)
  {
      try (Statement s = c.createStatement(); ResultSet rs = s.executeQuery("select * from CLIENTES"))
      {
          int iFila = 1;
          
          while(rs.next())
          {
              System.out.println("["+ iFila++ + "]");
              System.out.println("DNI: " + rs.getString("DNI"));
              System.out.println("APELLIDOS: " + rs.getString("APELLIDOS"));
              int numCP = rs.getInt("CP");
              System.out.println("CP: " + (rs.wasNull() ? "null" : numCP));
                // Haciendo un getInt() y al ser null te devuelve un 0.
                // Al utilizar getString() te devolveria la cadena null.
                // Lo correcto para más utilizadad, sería utilizar el método wasNull(). Y comprobarlo con una condición ternaria.
          }
      }
      catch (SQLException ex) {
          muestraErrorSQL(ex);
      } catch (Exception e) {
          e.printStackTrace(System.err);
      }
  }
}
