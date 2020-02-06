/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejerciciosfinal3;

import ORM.AsigProyecto;
import ORM.AsigProyectoId;
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
public class EjerciciosFinal3 {

    public static void main(String[] args) {
     
        Transaction t = null;
        try(Session s = HibernateUtil.getSessionFactory().openSession())
        {
            t = s.beginTransaction();
            
            Empleado emp = new Empleado("30731678z", "Juan");
            Proyecto pro = new Proyecto(emp, "Acceso de datos", Date.from(Instant.now()));
            
            AsigProyectoId asig = new AsigProyectoId();
            asig.setDniEmp("30731678z");
            asig.setIdProy(1);
            asig.setFInicio(Date.from(Instant.now()));
            
            AsigProyecto a = new AsigProyecto(asig, emp, pro);
            
            s.saveOrUpdate(emp);
            s.saveOrUpdate(pro);
            s.save(a);
            
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
