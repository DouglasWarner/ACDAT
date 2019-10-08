package excepcionesconthrows;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

public class ExcepcionesConThrows {

  public File creaFicheroTempConCar(String prefNomFich, char car, int numRep) throws IOException {
    File f = File.createTempFile(prefNomFich, "");
    FileWriter fw = new FileWriter(f);
    for (int i = 0; i < numRep; i++) fw.write(car);
    fw.close();
    return f;
  }

  public static void main(String[] args) {

    try {
      File ft = new ExcepcionesConThrows().creaFicheroTempConCar("AAAA_", 'A', 20);
      System.out.println("Creado fichero: " + ft.getAbsolutePath());
      ft.delete();
    } catch (IOException e) {
      System.err.println(e.getMessage());
    }

  }

}
