package orm_colecciones_e_iteradores_2;

import java.util.Iterator;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ORM_colecciones_e_iteradores_2 {

  public static void main(String[] args) {

    try (Session s = HibernateUtil.getSessionFactory().openSession()) {
      Transaction t = null;

      try {
        t = s.beginTransaction();

        ORM.Sede sede = new ORM.Sede("VALENCIA");
        s.save(sede);

        ORM.Departamento depto1 = new ORM.Departamento(sede, "I+D");
        s.save(depto1);
        sede.getDepartamentos().add(depto1);

        ORM.Departamento depto2 = new ORM.Departamento(sede, "MARKETING");
        s.save(depto2);
        sede.getDepartamentos().add(depto2);

        ORM.Departamento depto3 = new ORM.Departamento(sede, "QA");
        s.save(depto3);
        sede.getDepartamentos().add(depto3);

        Iterator<ORM.Departamento> itDeptos = sede.getDepartamentos().iterator();

        System.out.println("Nueva sede [" + sede.getIdSede() + "] (" + sede.getNomSede() + ")");
        System.out.println("----------------");

        while (itDeptos.hasNext()) {
          ORM.Departamento unDepto = (ORM.Departamento) itDeptos.next();
          System.out.println("Depto: [" + unDepto.getIdDepto() + "] (" + unDepto.getNomDepto() + ")");
        }

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
