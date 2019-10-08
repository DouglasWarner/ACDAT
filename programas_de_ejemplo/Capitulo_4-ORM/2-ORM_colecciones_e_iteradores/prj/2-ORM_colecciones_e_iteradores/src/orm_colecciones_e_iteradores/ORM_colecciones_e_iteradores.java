package orm_colecciones_e_iteradores;

import java.util.Iterator;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ORM_colecciones_e_iteradores {

  public static void main(String[] args) {

    try (Session s = HibernateUtil.getSessionFactory().openSession()) {

      Transaction t = null;
      try {

        t = s.beginTransaction();

        ORM.Sede sede = new ORM.Sede("TERUEL");
        s.save(sede);

        ORM.Departamento depto = new ORM.Departamento();
        depto.setNomDepto("I+D");
        depto.setSede(sede);
        s.save(depto);

        depto = new ORM.Departamento(sede, "MARKETING");
        s.save(depto);

        depto = new ORM.Departamento(sede, "QA");
        s.save(depto);

        s.refresh(sede);

        Iterator itDeptos = sede.getDepartamentos().iterator();

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
