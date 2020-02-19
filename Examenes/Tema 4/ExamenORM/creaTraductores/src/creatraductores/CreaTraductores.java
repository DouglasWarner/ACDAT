/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creatraductores;

import ORM.Idioma;
import ORM.Traductor;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author douglas
 */
public class CreaTraductores {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Transaction t = null;
        try(Session s = HibernateUtil.getSessionFactory().openSession())
        {
            String[][] traductores = new String[][]{{"37515445G", "LOPEZ", "es"},
                                                    {"X5353636P", "ROSSI", "it"},
                                                    {"73453363W", "SCHMIDT", "de"}};
             
            for (int i = 0; i < traductores.length; i++) 
            {
                 ORM.Traductor insertarTraductor = new Traductor();
                for (int j = 0; j < traductores[1].length; j++) 
                {
                    insertarTraductor.setDniNie(traductores[i][0]);
                    insertarTraductor.setNomTrad(traductores[i][1]);
                    insertarTraductor.setIdioma(s.load(ORM.Idioma.class, traductores[i][2]));
                    
                    t = s.beginTransaction();                    
                    s.save(insertarTraductor);
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
