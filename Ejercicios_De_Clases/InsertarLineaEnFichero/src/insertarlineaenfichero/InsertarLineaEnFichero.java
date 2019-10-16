/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insertarlineaenfichero;

import com.sun.source.tree.Tree;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author douglas
 */
public class InsertarLineaEnFichero {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String nomFichero = args[0];
        File fichOriginal = new File(nomFichero);
                
        if(args.length < 1)
        {
            System.out.println("Indicar un fichero.");
            return;
        }
        
        if(!fichOriginal.exists())
        {
            System.out.println("El fichero no existe.");
            return;
        }
        
        try(BufferedReader bfr = new BufferedReader(new FileReader(fichOriginal)))
        {
            File fichTemp = File.createTempFile(nomFichero, "");
            BufferedWriter bfw = new BufferedWriter(new FileWriter(fichTemp));
            
            String linea = bfr.readLine();
            while(linea != null)
            {
                bfw.write(linea);
                bfw.newLine();
                bfw.write("----");
                bfw.newLine();
                linea = bfr.readLine();
            }
            bfw.close();
            
            String nombFicheroTemp = nomFichero + "." + new SimpleDateFormat("yyyyMMdd").format(new Date()) + ".bak";
            
            if(fichOriginal.renameTo(new File(nombFicheroTemp)))
            {
                System.out.println("Creado fichero backup.");
                if(fichTemp.renameTo(new File(nomFichero)))
                {
                    System.out.println("Creado fichero nuevo.");
                }
            }
            
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        finally
        {
            
        }
    }
    
}
