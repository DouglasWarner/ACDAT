/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saveorupdate;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class SaveOrUpdate {

    /**
     * Programa que, para un DNI, crea un empleado si no existe y lo modifica si
     * existe, utilizando saveOrUpdate(). Como el primer programa de ejemplo. Es
     * necesario crear una nueva sede y un nuevo departamento dentro de ella
     * para poder asignar un departamento al empleado. Si no, no se puede
     * grabar. (columna id_depto integer not null en tabla empleado).
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Transaction t = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            t = s.beginTransaction();

            ORM.Sede sede = new ORM.Sede();
            sede.setNomSede("BARCELONA");
            s.save(sede);
            ORM.Departamento depto = new ORM.Departamento();
            depto.setNomDepto("INVESTIGACIÓN Y DESARROLLO");
            depto.setSede(sede);
            s.save(depto);

            ORM.Empleado emp = new ORM.Empleado();
            emp.setDni("56789012B");
            emp.setNomEmp("SAMPEDRO");   // Cambia nombre
            emp.setDepartamento(depto);  // Asigna departamento
            s.saveOrUpdate(emp);

            t.commit();

            System.out.println("Empleado creado o actualizado.");
            System.out.println("DNI: " + emp.getDni());
            System.out.println("Nombre: " + emp.getNomEmp());
            System.out.println("Depto: " + emp.getDepartamento().getNomDepto()
                    +" en sede: " + emp.getDepartamento().getSede().getNomSede());

        } catch (Exception e) {
            e.printStackTrace(System.err);
            if (t != null) {
                t.rollback();
            }
        }
    }

}
