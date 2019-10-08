package excepcionesdiversas;

public class ExcepcionesDiversas {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    // Rellena array con números variados
    int nums[][] = new int[2][3];
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 3; j++) {
        nums[i][j] = i + j;
      }
    }
    // Realiza cálculo para cada posición del array.
    // Provocará excepciones de diversos tipos
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        try {
          System.out.print("Segunda cifra de 5*nums["+i+"]["+j+"]/"+j+": ");
          System.out.println(String.valueOf(5 * nums[i][j] / j).charAt(1));
        } catch(ArithmeticException e) {
          System.out.println("ERROR: aritmético 5*"+nums[i][j]+"/"+j);
        } catch (ArrayIndexOutOfBoundsException e) {
          System.out.println("ERROR: No existe nums["+i+"]["+j+"]");
        } catch (Exception e) {
          System.out.println("ERROR: de otro tipo al calcular segunda cifra de: 5*"+nums[i][j]+"/"+j);
          System.out.println();
          e.printStackTrace();
        }
      }
    }
  }
}
