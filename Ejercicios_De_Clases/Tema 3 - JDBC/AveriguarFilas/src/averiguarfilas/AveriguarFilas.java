/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package averiguarfilas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author douglas
 */
public class AveriguarFilas {

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
            NFilas(c);
        } catch (SQLException ex) {
            muestraErrorSQL(ex);
        }
    }
    
    public static void NFilas(Connection c)
    {
        try (Statement s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY); 
                ResultSet rs = s.executeQuery("select * from CLIENTES"))
        {
            int count = 0;
            if(rs.last())
                count = rs.getRow();
            System.out.println("Número de filas: " + count);
        } catch (SQLException e){
            muestraErrorSQL(e);
        }
    }
}
