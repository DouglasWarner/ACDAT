/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excepcionesdivporcero;

/**
 *
 * @author carlos
 */
public class ExcepcionesDivPorCero {

  
  public int divide(int a, int b) {
    return a/b;
  }
  
  public static void main(String[] args) {
    
    int a,b;
    a=5; b=2;
    System.out.println(a+"/"+b+"="+a/b);
    b=0;
    System.out.println(a+"/"+b+"="+a/b);
    b=3;
    System.out.println(a+"/"+b+"="+a/b);
    
  }
  
}
