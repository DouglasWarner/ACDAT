/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejerciciosfinal4;

import ORM.Datosprof;
import ORM.Empleado;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Douglas
 */
public class EjerciciosFinal4 {

    public static void main(String[] args) {
        
        Transaction t = null;
        
        try(Session s = HibernateUtil.getSessionFactory().openSession())
        {
            t = s.beginTransaction();
            
            Empleado emp1 = new Empleado("25731678z", "Douglas");
            Empleado emp2 = new Empleado("30731678z", "Juan");
            Datosprof datosProfesionales = new Datosprof(emp2, "A", 20000);
            
            s.saveOrUpdate(emp1);
            s.saveOrUpdate(emp2);
            s.saveOrUpdate(datosProfesionales);
            
            Empleado getEmp1 = s.get(Empleado.class, "25731678z");
            Datosprof dP = new Datosprof(getEmp1, "B", 20000);
            s.saveOrUpdate(dP);
            
            Empleado getEmp2 = s.get(Empleado.class, "30731678z");
            
            System.out.println(" ------ Añadido ------ ");
            System.out.printf(" [Empleado] DNI: %s ; Nombre: %s ; Datos: %s ; Sueldo: %s  %n", getEmp1.getDni(), getEmp1.getNomEmp()
                                                                            , getEmp1.getDatosprof().getCategoria(), getEmp1.getDatosprof().getSueldoBrutoAnual());
            System.out.printf(" [Empleado] DNI: %s ; Nombre: %s ; Datos: %s ; Sueldo: %s  %n", getEmp2.getDni(), getEmp2.getNomEmp()
                                                                            , getEmp2.getDatosprof().getCategoria(), getEmp2.getDatosprof().getSueldoBrutoAnual());            
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
