package datosprofesionales;

import java.math.BigDecimal;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.sql.Date;
import java.util.List;
import java.util.Set;

public class DatosProfesionales {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Transaction t = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            
            t = s.beginTransaction();

            // Se crea un empleado y se le asignan datos profesionales.
            
            ORM.Empleado e1 = new ORM.Empleado();
            e1.setDni("27182818S");
            e1.setNomEmp("LUQUE");
            s.save(e1);
            ORM.DatosProf dp1 = new ORM.DatosProf();
            dp1.setCategoria("A3");
            dp1.setSueldoBrutoAnual(new BigDecimal(32000.0));
            dp1.setEmpleado(e1);
            s.save(dp1);
            
            // Se recupera un empleado y se le asignan datos profesionales.
            // Se asume que existe y por eso se usa load(). Si no existiera,
            // se produciría una excepción.
            ORM.Empleado e2 = s.load(ORM.Empleado.class, "78901234X");
            ORM.DatosProf dp2 = new ORM.DatosProf();
            dp2.setCategoria("A1");
            dp2.setSueldoBrutoAnual(new BigDecimal(35543.20));
            dp2.setEmpleado(e2);
            s.save(dp2);
            
            t.commit();

        } catch (Exception e) {
            e.printStackTrace(System.err);
            if (t != null) {
                t.rollback();
            }
        }

    }

}
