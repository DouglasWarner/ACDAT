/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejerciciosfinal8;

import ORM.Empleado;
import ORM.Proyecto;
import ORM.emp_plantilla;
import java.time.Instant;
import java.util.Date;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Douglas
 */
public class EjerciciosFinal8 {

    public static void main(String[] args) {
        
        Transaction t = null;
        
        try(Session s = HibernateUtil.getSessionFactory().openSession())
        {
            t = s.beginTransaction();            
            
            ORM.Empleado emp = new Empleado("25731678S","Jose");
            
            ORM.emp_plantilla empPla = new emp_plantilla(25, "20731678S", "Pepe");
            
            ORM.Proyecto proy = new Proyecto();
            
            s.save(emp);
            s.save(empPla);
            
            String dniEmp = emp.getDni();            
            String dniEmpPla = empPla.getDni();
            
            emp = (ORM.Empleado) s.load(ORM.Empleado.class, dniEmp);
            empPla = (ORM.emp_plantilla) s.load(ORM.emp_plantilla.class, dniEmpPla);
            
            //  ASIGNAR JEFE EN UN PROYECTO
            proy.setEmpleado_plantilla(empPla);
            proy.setNomProy("-- Proyeto 1 --");
            proy.setFInicio(Date.from(Instant.now()));
            
            s.save(proy);
            
            System.out.println("Empleado ["+emp.getDni() + "] "+ emp.getNomEmp());
            System.out.println("Empleado Plantilla ["+empPla.getDni() + "] ("+ empPla.getNomEmp() +"; "+empPla.getNum_Emp()+")");
            System.out.println("Proyecto ["+proy.getIdProy()+ "] ("+ proy.getNomProy()+"; "+proy.getFInicio()+ "; "+ proy.getEmpleado_plantilla().getDni() +")");
            
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
