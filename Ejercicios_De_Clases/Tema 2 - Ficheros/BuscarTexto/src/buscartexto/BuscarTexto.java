/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscartexto;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author douglas
 */
public class BuscarTexto 
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        if (args.length < 1) 
        {
          System.out.println("Indicar el nombre del fichero");
          return;
        }
        String nomFich = args[0];

        try (BufferedReader fbr = new BufferedReader(new FileReader(nomFich))) 
        {
          int i = 0;
          String linea = fbr.readLine();
          String palabra = "Málaga";
          
          while (linea != null) 
          {
              String[] columnaP = linea.split(" ");
              
                for (int j = 0; j < columnaP.length; j++) 
                {
                    if(columnaP[j].contains(palabra))
                    {
                        System.out.format("[%5d] %s", i, j+1);
                        System.out.println();
                    }
                }
                i++;                
                linea = fbr.readLine();            
          }
        } 
        catch (FileNotFoundException e) 
        {
          System.out.println("No existe fichero " + nomFich);
        } 
        catch (IOException e) 
        {
          System.out.println("Error de E/S: " + e.getMessage());
        } 
        catch (Exception e) 
        {
          e.printStackTrace();
        }

  }
    
}
