/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package muestratraductores;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author douglas
 */
public class MuestraTraductores {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Transaction t = null;
        try(Session s = HibernateUtil.getSessionFactory().openSession())
        {   
            t = s.beginTransaction();
            Query q = s.createQuery("from Traductor order by nomTrad").setReadOnly(true);
            
            List<ORM.Traductor> resultado = (List<ORM.Traductor>)q.getResultList();
            
            System.out.println(" ------ Traductores  ------ ");
            for (ORM.Traductor traductores : resultado) {
                ORM.Idioma idiomaMaterna = (ORM.Idioma) s.get(ORM.Idioma.class, traductores.getIdioma().getCodIso());
                System.out.printf(" [Traductor] DNI: %s ; Nombre: %s ; Lengua Materna: %s ; Número de lengua que traduce: %s %n", traductores.getDniNie()
                                                                                                , traductores.getNomTrad()
                                                                                                , idiomaMaterna.getNomIdioma()
                                                                                                , traductores.getIdiomas().size());
            }
            
            t.commit();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            if(t!=null)
                t.rollback();
        }
    }
    
}
