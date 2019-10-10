/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ficheroaleatorio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author douglas
 */
public class FicheroAleatorio {    static File ficheroAlea;
    static final int LONG_REG = 16;
    
    public FicheroAleatorio(File fich)
    {
        ficheroAlea = fich;
    }
    
    public static void main(String[] args) {
        File fich = new File("/home/douglas/Escritorio/ficheroAleatorio.txt");
        
        FicheroAleatorio fa = new FicheroAleatorio(fich);
        
        System.out.println(fa.Leer(2));
        
        fa.Insertar(2, "Douglas");
        fa.Insertar(1, "Infiltrado");
        fa.Insertar(4, "nombre");
        for (int i = 1; i < 6; i++) {
           System.out.format(" pos%d: %s", i, fa.Leer(i));
        }
    }
    /*  Inserta el nombre en la posición dada (posición de registro, 
        es decir, si se indica pos=4, 
        se insertará a partir del byte LONG_REG*(4-1)=48). Utilizar seek, read.*/
    public void Insertar(int posicion, String nombre)
    {
        try(RandomAccessFile fichLeer = new RandomAccessFile(ficheroAlea,"rws")) {
            
            for(int i=nombre.length()+1; i<=LONG_REG; i++) {
                nombre += " ";
            }
            
            fichLeer.seek((posicion-1)*LONG_REG);
            
            fichLeer.write(nombre.getBytes("UTF-8"),0,LONG_REG);
            
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        
    }
    // obtiene el nombre en la posición dada. Utilizar seek, write.
    public String Leer(int pos)
    {
        String leido = "";
        byte[] tmp = new byte[LONG_REG];
        
        try(RandomAccessFile fichLeer = new RandomAccessFile(ficheroAlea,"rws")) {
            fichLeer.seek((pos-1)*LONG_REG);
            fichLeer.read(tmp);
            leido = new String(tmp,"UTF-8");
            
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        finally
        {
            return leido;
        }
    }
}
