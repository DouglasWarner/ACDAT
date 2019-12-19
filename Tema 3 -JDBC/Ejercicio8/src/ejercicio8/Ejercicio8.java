/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Douglas
 */
public class Ejercicio8 {

    public static void muestraErrorSQL(SQLException e) 
    {
        System.err.println("SQL ERROR mensaje: " + e.getMessage());
        System.err.println("SQL Estado: " + e.getSQLState());
        System.err.println("SQL código específico: " + e.getErrorCode());
    }
    
    public static void main(String[] args) {
        
        String separador = ";";
        String nombreFichero;
        
        String basedatos = "ad";
        String host = "localhost";
        String port = "3306";
        String parAdic = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String urlConnection = "jdbc:mysql://" + host + ":" + port + "/" + basedatos + parAdic;
        String user = "ad";
        String pwd = "123";
        
        if(args.length < 1)
        {
            System.out.println("Error: Se debe pasar un fichero");
            return;
        }
        
        nombreFichero = args[0];
        
        try (Connection c = DriverManager.getConnection(urlConnection, user, pwd)) 
        {
            InsertarClienteCSV(nombreFichero, separador, c);
        } 
        catch (SQLException e) 
        {
            System.err.println("SQL mensaje: " + e.getMessage());
            System.err.println("SQL Estado: " + e.getSQLState());
            System.err.println("SQL código específico: " + e.getErrorCode());
        }
        catch (Exception e) 
        {
            e.printStackTrace(System.err);
        }
    }
    
    public static int InsertarClienteCSV(String nombreFichero, String separador, Connection c)
    {
        int numeroLinea = 0;
        String insert = "insert into clientes(DNI,APELLIDOS,CP) values (?,?,?) on duplicate key update APELLIDOS=?,CP=?;";
        String lineaLeida;
        String[] linea;
        
        try(BufferedReader bfr = new BufferedReader(new FileReader(nombreFichero)))
        {
            try(PreparedStatement ps = c.prepareStatement(insert))
            {
                c.setAutoCommit(false);
                
                while((lineaLeida = bfr.readLine()) != null)
                {
                    numeroLinea++;
                    linea = lineaLeida.split(separador);
                    
                    System.out.println("El cliente >> ");
                    for (int i = 0; i < linea.length; i++) {
                        System.out.print("Campo " + i + ":[" + linea[i] + "] ");
                    }
                    
                    ps.setString(1, (linea[0].length() > 0 ? linea[0] : null));
                    ps.setString(2, (linea[1].length() > 0 && linea.length > 1 ? linea[1] : null));
                    ps.setString(3, (linea[2].length() > 0 && linea.length > 2 ? linea[2] : null));
                    ps.setString(4, (linea[1].length() > 0 && linea.length > 1 ? linea[1] : null));
                    ps.setString(5, (linea[2].length() > 0 && linea.length > 2 ? linea[2] : null));
                    ps.addBatch();
                    
                    System.out.println(" >> Ha sido añadido a la lista para insertar.");
                }
                
                ps.executeBatch();
                
                c.commit();
            }
            catch (SQLException e)
            {
                muestraErrorSQL(e);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return numeroLinea;
    }
    
}
