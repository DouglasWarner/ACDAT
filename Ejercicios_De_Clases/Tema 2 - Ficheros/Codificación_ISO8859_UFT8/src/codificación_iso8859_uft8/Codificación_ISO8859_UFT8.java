/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codificación_iso8859_uft8;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 *
 * @author douglas
 */
public class Codificación_ISO8859_UFT8 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        if (args.length < 1) 
        {
          System.out.println("Indicar el nombre del fichero");
          return;
        }
        String nomFichero = args[0];
        File nomFichTemp = File.createTempFile(nomFichero, "");
    }
    
}
