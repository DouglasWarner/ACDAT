/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo_02.pkg10;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author douglas
 */
public class Ejemplo_0210 {

    static File f = null;
    static FileWriter fw = null;

    public File creaFicheroTempConCar(String prefNomFich, char car, int numRep)
    throws IOException {
        f = File.createTempFile(prefNomFich, "");
        fw = new FileWriter(f);
        for (int i = 0; i < numRep; i++) {
            fw.write(car);
        }
        fw.close();
        return f;
    }
    
    public static void main(String[] args) {
        try
        {
            File ft = new Ejemplo_0210().creaFicheroTempConCar("AAAA_", 'A', 20);
            System.out.println("Creando fichero: " + ft.getAbsolutePath());
            ft.delete();
        } 
        catch (IOException e) 
        {
            System.err.println(e.getMessage());
        }
        finally 
        {
            if(fw != null)
            {
                try
                {
                    fw.close();
                }
                catch(IOException e)
                {
                    System.err.println("Error al cerrar fichero: "+e.getMessage());
                }
            }
        }
    }
}
