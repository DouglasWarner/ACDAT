/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo_proyectoorm;

import ORM.Departamento;
import ORM.Empleado;
import ORM.Sede;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
/**
 *
 * @author metho
 */
public class Ejemplo_ProyectoORM {
    
    public static void main(String[] args) {
        Transaction t = null;
        
        try(Session s = NewHibernateUtil.getSessionFactory().openSession())
        {
            t = s.beginTransaction();
            
            ORM.Sede sede = new Sede("Málaga");
            s.save(sede);
            
            ORM.Departamento depart1 = new Departamento();
            depart1.setNomDepto("Recursos Humanos");
            depart1.setSede(sede);
            
            s.save(depart1);
            
            ORM.Departamento depart2 = new Departamento();
            depart2.setNomDepto("Recursos Humanos");
            depart2.setSede(sede);
            
            s.save(depart2);
            
            ORM.Empleado emp1_depart1 = new Empleado();
            ORM.Empleado emp2_depart1 = new Empleado();
            emp1_depart1.setDni("26871412J");
            emp2_depart1.setDni("24536874L");
            emp1_depart1.setNomEmp("Juan");
            emp2_depart1.setNomEmp("Felipe");
            emp1_depart1.setDepartamento(depart1);
            emp2_depart1.setDepartamento(depart1);
            
            s.save(emp1_depart1);
            s.save(emp2_depart1);
            
            ORM.Empleado emp1_depart2 = new Empleado();
            ORM.Empleado emp2_depart2 = new Empleado();
            emp1_depart2.setDni("25314725P");
            emp2_depart2.setDni("24713658X");
            emp1_depart2.setNomEmp("Jhon");
            emp2_depart2.setNomEmp("Maria");
            emp1_depart2.setDepartamento(depart2);
            emp2_depart2.setDepartamento(depart2);
            
            s.save(emp1_depart2);
            s.save(emp2_depart2);
            
            t.commit();
        }
        catch (Exception e)
        {
            //e.getCause();
            //ConstraintViolationException ex;
            e.printStackTrace(System.err);
            if(t != null)
                t.rollback();
        }
    }
    
}
