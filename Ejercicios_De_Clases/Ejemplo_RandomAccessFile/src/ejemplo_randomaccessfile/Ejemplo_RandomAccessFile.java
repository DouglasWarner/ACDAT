/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo_randomaccessfile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author douglas
 */

public class Ejemplo_RandomAccessFile {

    /**
     * @param args the command line arguments
     */    
    static long offset = 0;
    
    public static void main(String[] args) {
        String[] nombres = {"Juan", "Pepito", "Don José", "Popeye", "Paco"};
        File fich = new File("/home/douglas/Escritorio/nombres.txt");
        
        if(fich.exists())
            fich.delete();
        
        try (RandomAccessFile raf = new RandomAccessFile(fich, "rws")) {
            
            for(String nombre : nombres)
            {
                raf.seek(offset);
                raf.write(nombre.getBytes("UTF-8"));
                offset += 16;
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        finally
        {
            System.out.println("Generado y escrito en fichero");
        }
    }
    
}