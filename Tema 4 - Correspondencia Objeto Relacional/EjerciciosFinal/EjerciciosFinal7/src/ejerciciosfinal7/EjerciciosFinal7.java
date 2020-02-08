/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejerciciosfinal7;

import ORM.Asigproyecto;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author Douglas
 */
public class EjerciciosFinal7 {

    public static void main(String[] args) {
        
        Transaction t = null;
        
        try(Session s = HibernateUtil.getSessionFactory().openSession())
        {
            t = s.beginTransaction();
            
            Query q = s.createQuery("FROM Asigproyecto where f_Inicio < current_date and f_Fin is null or f_Fin >= current_date").setReadOnly(true);
            
            List<Asigproyecto> resultado = (List<Asigproyecto>)q.getResultList();
            
            System.out.println(" ------ Empleado asignado a proyecto ------ ");
            for (Asigproyecto asigproyecto : resultado) {
                System.out.printf(" [Empleado-Proyecto] Nombre empleado: %s ; Nombre proyecto: %s ; Fecha inicio: %s ; Fecha fin: %s  %n", asigproyecto.getEmpleado().getNomEmp()
                                                                                                                                    , asigproyecto.getProyecto().getNomProy()
                                                                                                                                    , asigproyecto.getId().getFInicio(), asigproyecto.getFFin());
            }
            
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
