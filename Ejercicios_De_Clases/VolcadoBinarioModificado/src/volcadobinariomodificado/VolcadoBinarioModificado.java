/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package volcadobinariomodificado;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.HashMap;

/**
 *
 * @author douglas
 */
public class VolcadoBinarioModificado {

    static int TAM_FILA = 32;
    static int MAX_BYTES = 2048;
    InputStream is = null;
    static StringBuilder salida = new StringBuilder();

    public VolcadoBinarioModificado(InputStream is) {
      this.is = is;
    }

    public void volcar() throws IOException {
      byte buffer[] = new byte[TAM_FILA];
      int bytesLeidos;
      int offset = 0;
     
      do {
          bytesLeidos = is.read(buffer);
          System.out.format("[%5d]", offset);
          salida.append(String.format("[%5d]", offset));
          for (int i = 0; i < bytesLeidos; i++) {
              salida.append(" ");
              System.out.format(" %2x", buffer[i]);
              salida.append(String.format(" %2x", buffer[i]));              
          }
          salida.append("\n");
          offset += bytesLeidos;
          System.out.println();
      } while (bytesLeidos == TAM_FILA && offset < MAX_BYTES);
    }

    public static void main(String[] args) throws IOException {
      if (args.length < 1) {
          System.out.println("No se ha indicado ningún fichero");
          return;
      }
       // PrintStream
      String nomFich = args[0];
      File fichTemp = File.createTempFile(nomFich," ");
      
      try (FileInputStream fis = new FileInputStream(nomFich); PrintStream pst = new PrintStream(fichTemp)) 
      {
        VolcadoBinarioModificado vbm = new VolcadoBinarioModificado(fis);
        System.out.println("Volcado binario de "+nomFich);
        vbm.volcar();
          
        pst.print(salida);
          
        if(fichTemp.renameTo(new File(nomFich+"Nuevo.txt")))
        {
            System.out.println("Creado nuevo fichero con el volcado binario");
        }
          
      } catch (FileNotFoundException e) {
          System.err.println("ERROR: no existe fichero " + nomFich);
      } catch (IOException e) {
          System.err.println("ERROR de E/S: " + e.getMessage());
      } catch (Exception e) {
          e.printStackTrace();
      }
    }    
}
