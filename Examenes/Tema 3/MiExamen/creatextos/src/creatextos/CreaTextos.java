/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creatextos;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author douglas
 */
public class CreaTextos {

    public static void muestraErrorSQL(SQLException e) 
    {
        System.err.println("SQL ERROR mensaje: " + e.getMessage());
        System.err.println("SQL Estado: " + e.getSQLState());
        System.err.println("SQL código específico: " + e.getErrorCode());
    }
    
    public static void main(String[] args) {
        
        String basedatos = "traducciones";
        String host = "localhost";
        String port = "3306";
        String parAdic = "?useSSL=false";
        String urlConnection = "jdbc:mysql://" + host + ":" + port + "/" + basedatos + parAdic;
        String user = "traducciones";
        String pwd = "traducciones";
        
        try (java.sql.Connection c = DriverManager.getConnection(urlConnection, user, pwd))
        {
            Texto t = new Texto();
            t.setNum_palabras(12584);
            t.setCod_idioma("es");
            int codTexto = t.save(c);
            
            Texto t1 = new Texto(6, c);
            System.out.print("Texto " + t1.getId_texto() + ", " + t1.getNum_palabras() + " palabras en " + t1.getCod_idioma() + "\n");
            
        }
        catch (SQLException ex) 
        {
            muestraErrorSQL(ex);
        } 
        catch (Exception e)
        {
            System.err.println(e.getMessage());
        }  
        
    }
    
}
