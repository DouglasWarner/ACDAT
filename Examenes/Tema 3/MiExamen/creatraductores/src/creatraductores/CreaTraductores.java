/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creatraductores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author douglas
 */
public class CreaTraductores {
    
    public static void muestraErrorSQL(SQLException e) 
    {
        System.err.println("SQL ERROR mensaje: " + e.getMessage());
        System.err.println("SQL Estado: " + e.getSQLState());
        System.err.println("SQL código específico: " + e.getErrorCode());
    }
    
    public static void main(String[] args) 
    {
        String basedatos = "traducciones";
        String host = "localhost";
        String port = "3306";
        String parAdic = "?useSSL=false";
        String urlConnection = "jdbc:mysql://" + host + ":" + port + "/" + basedatos + parAdic;
        String user = "traducciones";
        String pwd = "traducciones";
        
        String[][] datosTraductores = { {"37515445G", "LOPÉZ", "es"},
                                        {"X5353626P", "ROSSI", "it"},
                                        {"73453363W", "SCHMIDT", "de"},
        };
        
        try (Connection c = DriverManager.getConnection(urlConnection, user, pwd))
        {
            insertarTraductores(datosTraductores, c);
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
    
    public static void insertarTraductores(String[][] traductor, Connection c)
    {
        String insert = "insert into traductor(dni_nie, nom_trad, id_lengua_nativa) values (?,?,?);";
        
        try (PreparedStatement sInsert = c.prepareStatement(insert))
        {
            
            c.setAutoCommit(false);

            for (int j = 0; j < traductor.length; j++) 
            {
                for (int i = 0; i < traductor[j].length; i++) 
                {
                    System.out.print(traductor[j][i] + ", ");
                    sInsert.setString(i + 1, traductor[j][i]);
                }
                                
                sInsert.executeUpdate();
                
                System.out.println("Insertado traductor.");
                                
                c.commit();
            }
        }
        catch (SQLException e) 
        {
                muestraErrorSQL(e);
                try 
                {
                    c.rollback();
                } 
                catch (Exception er) 
                {
                    System.err.println("ERROR haciendo ROLLBACK");
                    er.printStackTrace(System.err);
                }
            }
    }
}
