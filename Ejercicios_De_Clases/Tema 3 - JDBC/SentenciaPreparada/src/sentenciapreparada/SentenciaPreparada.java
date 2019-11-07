/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sentenciapreparada;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.sql.ResultSet;
/**
 *
 * @author douglas
 */
public class SentenciaPreparada {
    
    public static void muestraErrorSQL(SQLException e)
    {
        System.err.println("SQL ERROR mensaje: " + e.getMessage());
        System.err.println("SQL Estado: " + e.getSQLState());
        System.err.println("SQL código específico: " + e.getErrorCode());
    }
    
    public static void main(String[] args) {
        String basedatos = "ad";
        String host = "localhost";
        String port = "3306";
        String parAdic = "?useSSL=false";
        String urlConnection = "jdbc:mysql://" + host + ":" +
                                port + "/" + basedatos + parAdic;
        String user = "ad";
        String pwd = "123";
        
        try (Connection c = DriverManager.getConnection(urlConnection, user, pwd))
        {
            System.out.println("Conexión realizada.");
            // CreateTableClientes1(c);
            // Insert(c);
            
            ConsultaPreparada(c);
            
        } catch (SQLException ex) {
            muestraErrorSQL(ex);
        }
    }
    
    public static void CreateTableClientes1(Connection c)
    {
        try (Statement s = c.createStatement()) {
             s.execute("CREATE TABLE CLIENTES1 (DNI CHAR(9) NOT NULL, APELLIDOS VARCHAR(100) NOT NULL, CP CHAR(5)"
                  + ", PRIMARY KEY (DNI))");
        } catch (SQLException ex) {
          muestraErrorSQL(ex);
        } catch (Exception e) {
          e.printStackTrace(System.err);
        }
    }
    
    public static void Insert(Connection c)
    {
        try(PreparedStatement sInsertar = c.prepareStatement("insert into CLIENTES1 values (?,?,?)"))
        {
            int i = 1;
            sInsertar.setString(i++, "78901234X");
            sInsertar.setString(i++, "NADALES");
            sInsertar.setInt(i++, 44126);
            sInsertar.executeUpdate();
            i = 1;
            sInsertar.setString(i++, "89012345E");
            sInsertar.setString(i++, "ROJAS");
            sInsertar.setNull(i++, Types.INTEGER);
            sInsertar.executeUpdate();
            i = 1;
            sInsertar.setString(i++, "56789012B");
            sInsertar.setString(i++, "SAMPER");
            sInsertar.setInt(i++, 29730);
            sInsertar.executeUpdate();
            
        }   catch (SQLException ex) {
            muestraErrorSQL(ex);
        }
    }
    public static void ConsultaPreparada(Connection c)
    {
        try(PreparedStatement ps = c.prepareStatement("select * from CLIENTES1 where DNI = ?"))
        {
            ps.setString(1, "89012345E");
            ResultSet rs = ps.executeQuery();
            rs.beforeFirst();
            while(rs.next())
            {
                System.out.println(rs.getString("DNI") + " " + rs.getString("APELLIDOS") + " " + rs.getString("CP"));
            }
            
        } catch (SQLException ex)
        {
            muestraErrorSQL(ex);
        }
    }
    
}

