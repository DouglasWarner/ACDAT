/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package muestratralenguamat;

import ORM.Idioma;
import java.util.Iterator;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author douglas
 */
public class MuestraTradLenguaMat {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        if(args.length < 0)
        {
            System.err.println("Error: Se debe de pasar un codido de idioma");
            return;
        }
        
        Transaction t = null;
        try(Session s = HibernateUtil.getSessionFactory().openSession())
        {        
            ORM.Idioma traduc = new Idioma();
            traduc = s.get(ORM.Idioma.class, args[0]);
            
            Iterator<ORM.Traductor> listaTraductorIdioma = (Iterator<ORM.Traductor>) traduc.getTraductors().iterator();
            
            System.out.println(" ------ Traductores con el idioma [" + traduc.getCodIso() +", " + traduc.getNomIdioma()+"] ------ ");
            while (listaTraductorIdioma.hasNext()) 
            {
                ORM.Traductor traductorIdioma = listaTraductorIdioma.next();
                System.out.printf(" [Traductor] DNI: %s ; Nombre: %s %n", traductorIdioma.getDniNie(), traductorIdioma.getNomTrad());
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
