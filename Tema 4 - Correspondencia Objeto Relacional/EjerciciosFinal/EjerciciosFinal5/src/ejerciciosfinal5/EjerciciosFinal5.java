/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejerciciosfinal5;

import ORM.Asigproyecto;
import ORM.AsigproyectoId;
import ORM.Empleado;
import ORM.Proyecto;
import java.util.Calendar;
import java.util.Date;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Douglas
 */
public class EjerciciosFinal5 {

    public static void main(String[] args) {
        
        Transaction t = null;
        
        try(Session s = HibernateUtil.getSessionFactory().openSession())
        {
            t = s.beginTransaction();
            Calendar fecha = Calendar.getInstance();
            fecha.set(2020, 1, 10);            
            
            Empleado emp1 = new Empleado("25731678z", "Douglas");
            Empleado emp2 = new Empleado("30731678z", "Juan");
            Proyecto proy1 = new Proyecto(emp1, "Yo que se", Date.from(fecha.toInstant()));
            Proyecto proy2 = new Proyecto(emp2, "Yo que se segunda parte", Date.from(fecha.toInstant()));
            
            s.saveOrUpdate(emp1);
            s.saveOrUpdate(emp2);
            s.save(proy1);
            s.save(proy2);            
            
            fecha.set(2020, 1, 3);
            AsigproyectoId asi = new AsigproyectoId(Date.from(fecha.toInstant()), emp1.getDni(), proy1.getIdProy());
            fecha.set(2020, 1, 5);
            Asigproyecto asiProy = new Asigproyecto(asi,emp1,proy1, Date.from(fecha.toInstant()));
            
            Proyecto getProy = s.get(Proyecto.class, 1);
            fecha.set(2020, 1, 3);
            AsigproyectoId asi2 = new AsigproyectoId(Date.from(fecha.toInstant()), emp1.getDni(), getProy.getIdProy());
            Asigproyecto asiProy2 = new Asigproyecto(asi2,emp1,getProy);
            
            s.saveOrUpdate(asiProy);
            s.saveOrUpdate(asiProy2);
            
            System.out.println(" ------ Asignado Proyecto ------ ");
            System.out.printf(" [Empleado-Proyecto] Nombre empleado: %s ; Nombre proyecto: %s ; Fecha inicio: %s ; Fecha fin: %s  %n", asiProy.getEmpleado().getNomEmp()
                                                                                                                                    , asiProy.getProyecto().getNomProy()
                                                                                                                                    , asi.getFInicio(), asiProy.getFFin());           
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
