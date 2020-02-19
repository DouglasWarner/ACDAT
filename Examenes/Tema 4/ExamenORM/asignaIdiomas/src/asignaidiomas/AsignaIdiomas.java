/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asignaidiomas;

import ORM.AsigTraduccion;
import ORM.AsigTraduccionId;
import ORM.Idioma;
import ORM.Traductor;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author douglas
 */
public class AsignaIdiomas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Transaction t = null;
        try(Session s = HibernateUtil.getSessionFactory().openSession())
        {        
            t = s.beginTransaction();
            ORM.Traductor traduc = new ORM.Traductor();
            traduc = s.get(ORM.Traductor.class, "37515445G");
            
            ORM.Idioma getItaliano = s.get(ORM.Idioma.class, "it");
            ORM.Idioma getAleman = s.get(ORM.Idioma.class, "de");
            
//            Set<ORM.Idioma> idiomas = new HashSet<ORM.Idioma>();
            traduc.getIdiomas().add(getAleman);
            traduc.getIdiomas().add(getItaliano);
            
            
            System.out.printf("Asignado idiomas al traductor %s %n", traduc.getDniNie());
            System.out.printf("[Idioma] Cod_iso: %s , Nombre: %s %n", getAleman.getCodIso(), getAleman.getNomIdioma());
            System.out.printf("[Idioma] Cod_iso: %s , Nombre: %s", getItaliano.getCodIso(), getItaliano.getNomIdioma());
            
            s.saveOrUpdate(traduc);
            
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
