/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excepcionesuniqueindex;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

public class ExcepcionesUniqueIndex {

    /**
     * 1) Se crea nueva sede con nombre que ya existe. 2) Se crea sede con
     * nombre que no existe. 3) Se crea departamento para sede creada en el paso
     * anterior. 4) Se crea departamento para sede creada en el paso anterior,
     * con el mismo nombre.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Transaction t = null;

        ORM.Sede sede;
        ORM.Departamento depto;

        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            try {
                t = s.beginTransaction();

                sede = new ORM.Sede();
                sede.setNomSede("ALBACETE");
                s.save(sede);

                t.commit();
            } catch (Exception e) {
                System.err.println("---- Pila de excepciones INICIO ----");
                System.err.println("[superior]" + e.getClass().toString());
                Throwable th = e;
                int i = 1;
                while ((th != null) && !(th instanceof ConstraintViolationException)) {
                    th = th.getCause();
                    System.err.println("[-" + (i++) + "]" + th.getClass().toString());
                }
                System.err.println("---- Pila de excepciones: FIN   ----");
                if (th instanceof ConstraintViolationException) {
                    ConstraintViolationException cve = (ConstraintViolationException) th;
                    System.err.println("===============(1)=================");
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

            sede = null;
            try {
                t = s.beginTransaction();

                sede = new ORM.Sede();
                sede.setNomSede("CIUDAD REAL");
                s.save(sede);

                t.commit();
            } catch (Exception e) {
                System.err.println("---- Pila de excepciones INICIO ----");
                System.err.println("[superior]" + e.getClass().toString());
                Throwable th = e;
                int i = 1;
                while ((th != null) && !(th instanceof ConstraintViolationException)) {
                    th = th.getCause();
                    System.err.println("[-" + (i++) + "]" + th.getClass().toString());
                }
                System.err.println("---- Pila de excepciones: FIN   ----");
                if (th instanceof ConstraintViolationException) {
                    ConstraintViolationException cve = (ConstraintViolationException) th;
                    System.err.println("===============(2)=================");
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

            if (sede != null) {
                try {
                    t = s.beginTransaction();

                    depto = new ORM.Departamento();
                    depto.setNomDepto("RECURSOS HUMANOS");
                    depto.setSede(sede);
                    s.save(depto);

                    t.commit();
                } catch (Exception e) {
                    System.err.println("---- Pila de excepciones INICIO ----");
                    System.err.println("[superior]" + e.getClass().toString());
                    Throwable th = e;
                    int i = 1;
                    while ((th != null) && !(th instanceof ConstraintViolationException)) {
                        th = th.getCause();
                        System.err.println("[-" + (i++) + "]" + th.getClass().toString());
                    }
                    System.err.println("---- Pila de excepciones: FIN   ----");
                    if (th instanceof ConstraintViolationException) {
                        ConstraintViolationException cve = (ConstraintViolationException) th;
                    System.err.println("===============(3)=================");
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

            if (sede != null) {
                try {
                    t = s.beginTransaction();

                    depto = new ORM.Departamento();
                    depto.setNomDepto("RECURSOS HUMANOS");
                    depto.setSede(sede);
                    s.save(depto);

                    t.commit();
                } catch (Exception e) {
                    System.err.println("---- Pila de excepciones INICIO ----");
                    System.err.println("[superior]" + e.getClass().toString());
                    Throwable th = e;
                    int i = 1;
                    while ((th != null) && !(th instanceof ConstraintViolationException)) {
                        th = th.getCause();
                        System.err.println("[-" + (i++) + "]" + th.getClass().toString());
                    }
                    System.err.println("---- Pila de excepciones: FIN   ----");
                    if (th instanceof ConstraintViolationException) {
                        ConstraintViolationException cve = (ConstraintViolationException) th;
                    System.err.println("===============(4)=================");
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

    }

}
