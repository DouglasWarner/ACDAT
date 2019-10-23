/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generarficherobinario;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author douglas
 */
public class GenerarFicheroBinario {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        String fich = "/home/douglas/Escritorio/Fichero.txt";
        
        leerFichero(fich);
        
    }
    
    static void leerFichero(String fichero) throws FileNotFoundException, IOException
    {
        String caracter;
        
        try(BufferedReader entrada = new BufferedReader(new FileReader(fichero));
            FileOutputStream salida = new FileOutputStream(new File(fichero+"binario.txt"))){

            while ((caracter = entrada.readLine()) != null)
            {
                String hex = String.format("%X", Integer.parseInt(caracter));
                
                salida.write(Integer.parseInt(hex));                
            }
            
        }
    }
    
}
