/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consultainversa;

import com.mysql.jdbc.ResultSetImpl;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author douglas
 */
public class ConsultaInversa {
    
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
            SelectInverso(c);
        } catch (SQLException ex) {
            muestraErrorSQL(ex);
        }
    }
    
    public static void SelectInverso(Connection c)
    {
        try (Statement s = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY); 
                ResultSet rs = s.executeQuery("select * from CLIENTES"))
        {
            int fila;
            int numCP;
            rs.last();
            fila = rs.getRow();
            do {
                rs.absolute(fila--);
                System.out.println("[" + fila +"]");
                System.out.println("DNI: " + rs.getString("DNI"));
                System.out.println("APELLIDOS: " + rs.getString("APELLIDOS"));
                numCP = rs.getInt("CP");
                System.out.println("CP: " + (rs.wasNull() ? "null" : numCP));                
            } while(!rs.isFirst());            
        } catch (SQLException e){
            muestraErrorSQL(e);
        }
    }
    
}
