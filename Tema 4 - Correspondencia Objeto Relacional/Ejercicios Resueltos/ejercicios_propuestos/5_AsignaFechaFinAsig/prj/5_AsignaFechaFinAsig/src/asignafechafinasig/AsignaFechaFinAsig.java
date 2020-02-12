package asignafechafinasig;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.sql.Date;

public class AsignaFechaFinAsig {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Transaction t = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {

            t = s.beginTransaction();

            Query q = s.createQuery(
                    "select ap from AsigProyecto ap where "
                    + " ap.proyecto.nomProy=:nomProy AND ap.empleado.dni=:dni "
                    + " and ap.id.FInicio=:FInicio"
            );
            
            q.setParameter("nomProy", "PAPEL ELECTRÓNICO").
                    setParameter("dni", "23571379J").
                    setParameter("FInicio", Date.valueOf("2019-01-02"));
            ORM.AsigProyecto ap1 = (ORM.AsigProyecto) q.getSingleResult();
            ap1.setFFin(Date.valueOf("2019-04-07"));
            s.update(ap1);
            
            q.setParameter("nomProy", "AVIÓN A PEDALES").
                    setParameter("dni", "97531975S").
                    setParameter("FInicio", Date.valueOf("2019-03-17"));
            ORM.AsigProyecto ap2 = (ORM.AsigProyecto) q.getSingleResult();
            ap2.setFFin(Date.valueOf("2019-03-28"));
            s.update(ap2);

            t.commit();

        } catch (Exception e) {
            e.printStackTrace(System.err);
            if (t != null) {
                t.rollback();
            }
        }

    }

}
