package escribeconnumerodelineas;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class EscribeConNumeroDeLineas {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    if (args.length < 1) {
      System.out.println("Indicar por favor nombre de fichero");
      return;
    }
    String nomFich = args[0];

    try (BufferedReader fbr = new BufferedReader(new FileReader(nomFich))) {
      int i = 0;
      String linea = fbr.readLine();
      while (linea != null) {
        System.out.format("[%5d] %s", i++, linea);
        System.out.println();
        linea = fbr.readLine();
      }
    } catch (FileNotFoundException e) {
      System.out.println("No existe fichero " + nomFich);
    } catch (IOException e) {
      System.out.println("Error de E/S: " + e.getMessage());
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

}
