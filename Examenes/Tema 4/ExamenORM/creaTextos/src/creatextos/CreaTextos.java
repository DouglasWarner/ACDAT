/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creatextos;

import ORM.Texto;
import ORM.Traductor;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author douglas
 */
public class CreaTextos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Transaction t = null;
        try(Session s = HibernateUtil.getSessionFactory().openSession())
        {
            String[][] textos = new String[][]{{"EL QUIJOTE", "es"},
                                                    {"AS LUSIADAS", "pt"},
                                                    {"LA DIVINA COMEDIA", "it"}};
             
            for (int i = 0; i < textos.length; i++) 
            {
                 ORM.Texto insertarTexto = new Texto();
                for (int j = 0; j < textos[1].length; j++) 
                {
                    insertarTexto.setNumPalabras(textos[i][0].length());
                    insertarTexto.setIdioma(s.load(ORM.Idioma.class, textos[i][1]));
                    
                    t = s.beginTransaction();                    
                    s.save(insertarTexto);
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
