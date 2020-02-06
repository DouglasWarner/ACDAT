/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablaporsubclase;

import ORM.empleado;
import ORM.empleado_plantilla;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author metho
 */
public class TablaPorSubclase {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Transaction t = null;
        
        try(Session s = HibernateUtil.getSessionFactory().openSession())
        {
            t = s.beginTransaction();
            
            ORM.empleado emp = new empleado("25731678S","Jose");
            s.save(emp);
            String dniEmp = emp.getDni();
            
            ORM.empleado_plantilla empPla = new empleado_plantilla(25, "20731678S", "Pepe");
            s.save(empPla);
            String dniEmpPla = empPla.getDni();
            
            emp = (ORM.empleado) s.load(ORM.empleado.class, dniEmp);
            empPla = (ORM.empleado_plantilla) s.load(ORM.empleado_plantilla.class, dniEmpPla);
            
            System.out.println("Empleado ["+emp.getDni() + "] "+ emp.getNomEmp());
            System.out.println("Empleado Plantilla ["+empPla.getDni() + "] ("+ empPla.getNomEmp() +"; "+empPla.getNumEmp()+")");
            
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
