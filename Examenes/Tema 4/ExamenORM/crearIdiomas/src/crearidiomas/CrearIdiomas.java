/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crearidiomas;

import ORM.Idioma;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author douglas
 */
public class CrearIdiomas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Transaction t = null;
        try(Session s = HibernateUtil.getSessionFactory().openSession())
        {
            String[][] idiomas = new String[][]{{"en", "ENGLISH"},
                                                {"es", "ESPAÑOL"},
                                                {"de", "DEUTSCH"},
                                                {"it", "ITALIANO"},
                                                {"fr", "FRANCES"},
                                                {"pt", "PORTUGUES"}};            
             
            for (int i = 0; i < idiomas.length; i++) 
            {
                 ORM.Idioma insertarIdiomas = new Idioma();
                for (int j = 0; j < idiomas[1].length; j++) 
                {
                    insertarIdiomas.setCodIso(idiomas[i][0]);
                    insertarIdiomas.setNomIdioma(idiomas[i][1]);
                    
                    t = s.beginTransaction();                    
                    s.save(insertarIdiomas);
                    t.commit();
                }
   
            }
            
             
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            if(t!=null)
                t.rollback();
        }
    }    
}
