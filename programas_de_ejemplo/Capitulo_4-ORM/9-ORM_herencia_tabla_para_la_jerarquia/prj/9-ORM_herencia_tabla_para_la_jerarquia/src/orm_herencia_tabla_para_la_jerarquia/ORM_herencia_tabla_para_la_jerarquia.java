package orm_herencia_tabla_para_la_jerarquia;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class ORM_herencia_tabla_para_la_jerarquia {

  public static void main(String[] args) {

    Transaction t = null;
    try(Session s = HibernateUtil.getSessionFactory().openSession()) {

      t = s.beginTransaction();

      ORM.Publicacion pub = new ORM.Publicacion("FOLLETO INDEFINIDO");
      s.save(pub);
      Integer idPub = pub.getIdPub();

      ORM.Libro libro = new ORM.Libro("CORRESPONDENCIA OBJETO-RELACIONAL", "9789901234567", "LOMAS, ANTONIO");
      s.save(libro);
      Integer idLibro = libro.getIdPub();

      ORM.Revista rev = new ORM.Revista("PERSISTENCIA DE OBJETOS", "6789012X05");
      s.save(rev);
      Integer idRev = rev.getIdPub();

      pub = (ORM.Publicacion) s.load(ORM.Publicacion.class, idPub);
      libro = (ORM.Libro) s.load(ORM.Libro.class, idLibro);
      rev = (ORM.Revista) s.load(ORM.Revista.class, idRev);
      
      System.out.println("Publicación [" + pub.getIdPub() + "]" + pub.getNomPub());
      System.out.println("Libro [" + libro.getIdPub() + "] (" + libro.getNomPub() + "; " + libro.getIsbn() + ";" + libro.getAutor() + ")");
      System.out.println("Revista [" + rev.getIdPub() + "] (" + rev.getNomPub() + "; " + rev.getIssn() + ")");

      t.commit();

    } catch (Exception e) {
      e.printStackTrace(System.err);
      if (t != null) {
        t.rollback();
      }
    }
  }

}
