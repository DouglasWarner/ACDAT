package volcadobinario;

import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class VolcadoBinario {

  static int TAM_FILA = 32;
  static int MAX_BYTES = 2048;
  InputStream is = null;

  public VolcadoBinario(InputStream is) {
    this.is = is;
  }

  public void volcar() throws IOException {
    byte buffer[] = new byte[TAM_FILA];
    int bytesLeidos;
    int offset = 0;
    do {
      bytesLeidos = is.read(buffer);
      System.out.format("[%5d]", offset);
      for (int i = 0; i < bytesLeidos; i++) {
        System.out.format(" %2x", buffer[i]);
      }
      offset += bytesLeidos;
      System.out.println();
    } while (bytesLeidos == TAM_FILA && offset < MAX_BYTES);
  }

  public static void main(String[] args) {
    if (args.length < 1) {
      System.out.println("No se ha indicado ningún fichero");
      return;
    }

    String nomFich = args[0];

    try (FileInputStream fis = new FileInputStream(nomFich)) {
      VolcadoBinario vb = new VolcadoBinario(fis);
      System.out.println("Volcado binario de "+nomFich);
      vb.volcar();
    } catch (FileNotFoundException e) {
      System.err.println("ERROR: no existe fichero " + nomFich);
    } catch (IOException e) {
      System.err.println("ERROR de E/S: " + e.getMessage());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
