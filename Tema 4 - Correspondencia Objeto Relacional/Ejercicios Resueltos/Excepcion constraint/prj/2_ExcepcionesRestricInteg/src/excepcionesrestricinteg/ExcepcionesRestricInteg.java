/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excepcionesrestricinteg;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

public class ExcepcionesRestricInteg {

    /**
     * Programa que crea una nueva sede, dos departamentos para esta nueva sede
     * y dos empleados para cada uno de estos departamentos.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Transaction t = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            t = s.beginTransaction();

            // Nueva sede
            ORM.Sede sede = new ORM.Sede();
            sede.setNomSede("ALBACETE");
            s.save(sede);

            // Dos departamentos para esa sede
            ORM.Departamento depto1 = new ORM.Departamento();
            depto1.setNomDepto("FINANZAS");
            depto1.setSede(sede);
            s.save(depto1);

            ORM.Departamento depto2 = new ORM.Departamento();
            depto2.setNomDepto("IT");
            depto2.setSede(sede);
            s.save(depto2);

            // Dos empleados para uno de estos departamentos
            ORM.Empleado emp1 = new ORM.Empleado();
            emp1.setDni("36925814G");
            emp1.setNomEmp("TORRALBA");
            emp1.setDepartamento(depto1);
            s.save(emp1);

            ORM.Empleado emp2 = new ORM.Empleado();
            emp2.setDni("48260482L");
            emp2.setNomEmp("MATESANZ");
            emp2.setDepartamento(depto1);
            s.save(emp2);

            ORM.Empleado emp3 = new ORM.Empleado();
            emp3.setDni("62840628M");
            emp3.setNomEmp("CAJAL");
            emp3.setDepartamento(depto2);
            s.save(emp3);

            ORM.Empleado emp4 = new ORM.Empleado();
            emp4.setDni("74185296S");
            emp4.setNomEmp("QUEVEDO");
            emp4.setDepartamento(depto2);
            s.save(emp4);

            t.commit();

//    } catch(ConstraintViolationException e) { // Esto no captura excepciones de este tipo
//      System.err.println("ERROR: " + e.getLocalizedMessage());
//      System.err.println("Error con sentencia SQL: " + e.getSQL());
//      System.err.println("Restricción violada: " + e.getConstraintName());
        } catch (Exception e) {
            System.err.println("---- Pila de excepciones INICIO ----");
            System.err.println("[superior]" + e.getClass().toString());
            Throwable th = e;
            int i=1;
            while ((th != null) && !(th instanceof ConstraintViolationException)) {
                th = th.getCause();
                System.err.println("[-" + (i++) + "]" + th.getClass().toString());
            }
            System.err.println("---- Pila de excepciones: FIN   ----");
            if (th instanceof ConstraintViolationException) {
                ConstraintViolationException cve = (ConstraintViolationException) th;
                System.err.println("===================================");
                System.err.println("Excepción de Hibernate de tipo " + cve.getClass().getName() + ": [" + cve.getMessage() + "]");
                System.err.println("Sentencia SQL: " + cve.getSQL());
                System.err.println("Restricción violada: " + cve.getConstraintName());
                System.err.print("Error de la excepción SQLException: ");
                System.err.println(cve.getSQLException().getMessage());
                System.err.println("===================================");
            } else {
                e.printStackTrace(System.err);
            }
            if (t != null) {
                t.rollback();
            }
        }
    }

}
