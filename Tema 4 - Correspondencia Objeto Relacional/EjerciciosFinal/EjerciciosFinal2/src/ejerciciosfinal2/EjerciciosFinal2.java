/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejerciciosfinal2;

import ORM.Empleado;
import ORM.Proyecto;
import java.time.Instant;
import java.util.Date;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author douglas
 */
public class EjerciciosFinal2 {

    public static void main(String[] args) {
        
        Transaction t = null;
        try(Session s = HibernateUtil.getSessionFactory().openSession())
        {
            t = s.beginTransaction();
            
            Empleado emp = s.get(Empleado.class, "30731678z");
            
            Proyecto pro = new Proyecto();
            pro.setEmpleado(emp);
            pro.setNomProy("Acceso a datos");
            pro.setFInicio(Date.from(Instant.now()));
            
            s.saveOrUpdate(emp);
            s.saveOrUpdate(pro);
            
            
            t.commit();            
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            if(t != null)
                t.rollback();
        }
    }
    
}
