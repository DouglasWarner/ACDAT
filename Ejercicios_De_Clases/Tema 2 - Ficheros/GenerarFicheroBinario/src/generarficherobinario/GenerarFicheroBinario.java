/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generarficherobinario;

import java.io.BufferedReader;
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
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        String fich = "C:\\basura\\Fichero.txt";
        File fichero = new File(fich);
        
        if(fichero.exists() && fichero.isFile())
            leerFichero(fichero);
        else
            System.out.println("El fichero no existe");
        
    }
    
    static void leerFichero(File fichero) throws FileNotFoundException, IOException
    {
        //String caracter;
        int caracter = 0;
        String hex = "";
        
        try(BufferedReader entrada = new BufferedReader(new FileReader(fichero));
            FileOutputStream salida = new FileOutputStream(new File(fichero.getName()+"binario.txt"))){

            while ((caracter = entrada.read()) != -1)
            {
                if(caracter != (int)' ')
                    hex += (char)caracter;
                
            }
            System.out.println(hex);
            for (int i = 0; i < hex.length(); i++) {
                if(hex.charAt(i) != ',')
                    System.out.println(hex.charAt(i));
            }
            
            /*while ((caracter = entrada.readLine()) != null)
            {                
                //salida.write(Integer.parseInt(caracter));
            }*/
        }
    }
}