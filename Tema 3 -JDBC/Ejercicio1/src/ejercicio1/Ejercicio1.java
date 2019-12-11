/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Douglas
 */
public class Ejercicio1 {

    public static void muestraErrorSQL(SQLException e) 
    {
        System.err.println("SQL ERROR mensaje: " + e.getMessage());
        System.err.println("SQL Estado: " + e.getSQLState());
        System.err.println("SQL código específico: " + e.getErrorCode());
    }
    
    public static void main(String[] args) throws IOException, SQLException {
        
        String basedatos = "ad";
        String host = "localhost";
        String port = "3306";
        String parAdic = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String urlConnection = "jdbc:mysql://" + host + ":" + port + "/" + basedatos + parAdic;
        String user = "ad";
        String pwd = "123";
        
        int nFilas = 0;
        int primero = 0;
        boolean tabla = true;
        String comando;
        
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) 
        {
            System.out.print("Introduzca nombre de tabla: ");
            String nomTabla = br.readLine();

            try (Connection c = DriverManager.getConnection(urlConnection, user, pwd);
                 Statement s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                 ResultSet rs = s.executeQuery("select * from " + nomTabla)) 
            {
                if (rs.next()) {
                  MostrarResult(rs);
                } else {
                  System.out.println("Tabla " + nomTabla + " vacía");
                  tabla = false;
                }
                
                rs.last();
                nFilas = rs.getRow();
                rs.first();
                primero = rs.getRow();
                
                System.out.println("La tabla tiene " + nFilas + " filas.");

                while(tabla)
                {            
                    comando = br.readLine();

                    switch(comando)
                    {
                        case ".":
                            return;
                        case "k":
                            if(!rs.isLast())
                            {
                                rs.next();
                                MostrarResult(rs);
                            }
                            else
                                System.out.println("No hay mas datos a listar");
                            break;
                        case "d":
                            if(!rs.isFirst())
                            {
                                rs.previous();
                                MostrarResult(rs);
                            }                            
                            else
                                System.out.println("Este es el primer dato a listar");
                            break;
                        default:
                            if(comando.matches("^[0-9]+$"))
                                if(Integer.parseInt(comando) <= nFilas && Integer.parseInt(comando) >= primero)
                                 {
                                     rs.absolute(Integer.parseInt(comando));
                                     MostrarResult(rs);
                                 }                                    
                            break;
                    }
                }
            }
            catch (SQLException e) 
            {
                muestraErrorSQL(e);
            } 
            catch (Exception e) 
            {
                System.err.println(e.getMessage());
            }
        }        
        catch (Exception e)
        {
            System.err.println(e.getMessage());
        }
    }
    
    static void MostrarResult(ResultSet rs) throws SQLException
        {
            String nombreCol;
            Object columnaResultado;
            String CampoValor;
            int nColumna;
            ResultSetMetaData rsmd = rs.getMetaData();
            
            System.out.println("[" + rs.getRow() + "]");
            nColumna = rsmd.getColumnCount();
            for (int i = 1; i <= nColumna; i++) 
            {
              nombreCol = rsmd.getColumnName(i);
              columnaResultado = rs.getObject(i);
              CampoValor = (columnaResultado == null) ? "NULL" : columnaResultado.toString();
              System.out.println(nombreCol + ": " + CampoValor);
            }
        }
}
