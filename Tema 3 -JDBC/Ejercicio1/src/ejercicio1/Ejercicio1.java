/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author metho
 */
public class Ejercicio1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        String[] cliente = new String[10];
        
        int maxColumn = cliente.length;
        int indice = 0;
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String comando = null;
        System.out.println("Contenido " + indice);
        
        while(true)
        {            
            comando = br.readLine();
            
            switch(comando)
            {
                case ".":
                    return;
                case "k":
                    if(indice > maxColumn-1)
                        System.out.println("No hay mas datos a listar");
                    else
                        System.out.println("Contenido " + ++indice);
                    break;
                case "d":
                    if(indice < 1)
                        System.out.println("Este es el primer dato a listar");
                    else
                        System.out.println("Contenido " + --indice);
                    break;
                default:
                    if(comando.matches("^[0-9]+$"))
                        if(Integer.parseInt(comando) < maxColumn && Integer.parseInt(comando) > 0)
                            System.out.println("Contenido " + comando);
                    break;
            }
        }
    }
    
}
