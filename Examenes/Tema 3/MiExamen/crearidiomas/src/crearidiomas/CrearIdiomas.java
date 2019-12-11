/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crearidiomas;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;

/**
 *
 * @author douglas
 */
public class CrearIdiomas {
    
    public static void muestraErrorSQL(SQLException e) 
    {
        System.err.println("SQL ERROR mensaje: " + e.getMessage());
        System.err.println("SQL Estado: " + e.getSQLState());
        System.err.println("SQL código específico: " + e.getErrorCode());
    }
    
    public static void main(String[] args) 
    {        
        String separador = ",";
        String nomFichero;
        String basedatos = "traducciones";
        String host = "localhost";
        String port = "3306";
        String parAdic = "?useSSL=false";
        String urlConnection = "jdbc:mysql://" + host + ":" + port + "/" + basedatos + parAdic;
        String user = "traducciones";
        String pwd = "traducciones";
        
        if(args.length < 1)
        {
            System.err.println("Error: se debe de pasar un fichero.");
            return;
        }
        
        nomFichero = args[0];
        
        try (Connection c = DriverManager.getConnection(urlConnection, user, pwd))
        {
            InsertarIdiomas(nomFichero, separador, c);
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
    
    public static void InsertarIdiomas(String fichero, String separador, Connection c)
    {
        String insert = "insert into idioma(cod_iso,nom_idioma) values (?,?);";
        String[] idiomas;
        String linea;
        int numeroLinea = 0;
        int indice;
        
        try(BufferedReader bfr = new BufferedReader(new FileReader(fichero));
            PreparedStatement ps = c.prepareStatement(insert))
        {            
                while ((linea = bfr.readLine()) != null) 
                {
                    numeroLinea++;
                    System.out.println("Linea " + numeroLinea + ": " + linea);
                    idiomas = linea.split(separador);

                    System.out.print("Idioma ");
                    
                    for (int i = 0; i < idiomas.length; i++) {
                       System.out.print(i + ": " + idiomas[i] + " ");
                    }
                    
                    indice = 1;
                    ps.setString(indice++, (idiomas.length > 0 && idiomas[0].length()>0 ? idiomas[0] : null));
                    ps.setString(indice++, (idiomas.length > 1 && idiomas[1].length()>0 ? idiomas[1] : null));
                    ps.executeUpdate();
                    
                    System.out.println("Idioma insertado.");
                }
        } 
        
        catch (FileNotFoundException ex) 
        {
            System.err.println("No existe fichero " + fichero);
        } 
        catch (IOException ex) 
        {
            System.err.println(ex.getMessage());
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
        }
        
    }
}

        
        
    

