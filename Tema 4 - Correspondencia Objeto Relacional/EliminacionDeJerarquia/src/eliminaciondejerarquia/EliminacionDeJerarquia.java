/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eliminaciondejerarquia;

import ORM.Empleado;
import ORM.Empleado_Plantilla;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author douglas
 */
public class EliminacionDeJerarquia {

    public static void main(String[] args) {
        
        Transaction t = null;
        try(Session s = HibernateUtil.getSessionFactory().openSession())
        {
            t = s.beginTransaction();
            
            ORM.Empleado emp = new Empleado("25731678S","Jose");
            s.save(emp);
            String dniEmp = emp.getDni();
            String nomEmp = emp.getNomEmp();
            
            ORM.Empleado_Plantilla empPla = new Empleado_Plantilla(dniEmp, nomEmp, 25);
            s.save(empPla);
            String dniEmpPla = empPla.getDni();
            
            emp = (ORM.Empleado) s.load(ORM.Empleado.class, dniEmp);
            empPla = (ORM.Empleado_Plantilla) s.load(ORM.Empleado_Plantilla.class, dniEmpPla);
            
            System.out.println("Empleado ["+emp.getDni() + "] "+ emp.getNomEmp());
            System.out.println("Empleado Plantilla ["+empPla.getDni() + "] ("+ empPla.getNomEmp() +"; "+empPla.getNumemp()+")");
            
            t.commit();
        }catch(Exception e)
        {
            e.printStackTrace(System.err);
            if(t != null)
                t.rollback();
        }
        
    }
    
}
