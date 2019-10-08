package ORM_executeUpdate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class ORM_ExecuteUpdate {

  public static void main(String[] args) {

    Transaction t = null;
    try (Session s = HibernateUtil.getSessionFactory().openSession()) {
      
      t = s.beginTransaction();

      Query q = s.createQuery("UPDATE Departamento SET nomDepto='I+D' WHERE nomDepto='Investigación y Desarrollo'");
      int numObj = q.executeUpdate();
      System.out.println(numObj + " objetos cambiados.");
      
      q = s.createQuery("DELETE Departamento WHERE nomDepto='ACTIVIDADES PARANORMALES'");
      numObj = q.executeUpdate();
      System.out.println(numObj + " objetos borrados.");
      
      t.commit();
      
    } catch (Exception e) {
      e.printStackTrace(System.err);
      if (t != null) {
        t.rollback();
      }
    } 
  }
}
