package orm_conexion;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class ORM_conexion {

  public static void main(String[] args) {

    try (Session s = HibernateUtil.getSessionFactory().openSession()) {
      Transaction t = null;
      
      try {
        t = s.beginTransaction();

        ORM.Sede sede = new ORM.Sede();
        sede.setNomSede("MÁLAGA");
        s.save(sede);

        ORM.Departamento depto = new ORM.Departamento();
        depto.setNomDepto("INVESTIGACIÓN Y DESARROLLO");
        depto.setSede(sede);
        s.save(depto);

        ORM.Empleado emp = new ORM.Empleado();
        emp.setDni("56789012B");
        emp.setNomEmp("SAMPER");
        emp.setDepartamento(depto);
        s.save(emp);

        t.commit();

      } catch (Exception e) {
        e.printStackTrace(System.err);
        if (t != null) {
          t.rollback();
        }
      }
    }
  }
  
}
