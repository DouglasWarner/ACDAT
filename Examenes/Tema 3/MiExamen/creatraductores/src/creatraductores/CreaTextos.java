/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creatraductores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

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
        
        try (Connection c = DriverManager.getConnection(urlConnection, user, pwd))
        {
            
            Texto t = new Texto();
            t.setNum_palabras(12584);
            t.setCod_idioma("es");
            int codTexto = t.save(c);
            
            ArrayList<HashMap> listaTrad = new ArrayList<HashMap>();
            HashMap asignar = new HashMap();
            
            asignar.put("cod_idioma_dest", "it");
            asignar.put("dni_nie_trad", "X5353636P");
            listaTrad.add(asignar);
            asignar.clear();
            asignar.put("cod_idioma_dest", "es");
            asignar.put("dni_nie_trad", "73563363W");
            
            listaTrad.add(asignar);
            
            t.asignaTraductores(listaTrad, c);
            
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
