/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cambiar_a_e_a;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author douglas
 */
public class Cambiar_A_E_A {

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
        File nomfich = new File(nomFichero);
        File nomFichTemp = File.createTempFile(nomFichero, "");
        
        try ())
        {
        }
    }
    
}
