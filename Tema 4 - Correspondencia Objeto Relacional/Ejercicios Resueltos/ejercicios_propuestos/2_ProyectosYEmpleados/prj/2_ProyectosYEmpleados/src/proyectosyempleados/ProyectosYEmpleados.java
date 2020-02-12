package proyectosyempleados;

import org.hibernate.Session;
import org.hibernate.Transaction;
import java.sql.Date;

public class ProyectosYEmpleados {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Transaction t = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            t = s.beginTransaction();

            // Nuevos empleados que serán jefes de proyecto
            ORM.Empleado jp1 = new ORM.Empleado();
            jp1.setDni("78901234X");
            jp1.setNomEmp("NADALES");
            s.save(jp1);

            ORM.Empleado jp2 = new ORM.Empleado();
            jp2.setDni("89012345E");
            jp2.setNomEmp("ROJAS");
            s.save(jp2);

            ORM.Empleado jp3 = new ORM.Empleado();
            jp3.setDni("09876543K");
            jp3.setNomEmp("LAMIQUIZ");
            s.save(jp3);
            
            // Nuevos proyectos

            ORM.Proyecto p1 = new ORM.Proyecto();
            p1.setNomProy("PAPEL ELECTRÓNICO");
            p1.setFInicio(Date.valueOf("2018-12-01"));
            p1.setEmpleado(jp1); // Es jefe de proyecto del proyecto anterior
            // Se podía haber hecho: jp1.getProyectos().add(p1);
            s.save(p1);

            ORM.Proyecto p2 = new ORM.Proyecto();
            p2.setNomProy("TINTA_HOLOGRÁFICA");
            p2.setFInicio(Date.valueOf("2021-10-01"));  // @Prueba1: error si se comenta esta línea
            p2.setEmpleado(jp2);                        // @Prueba2: error si se comenta esta línea
            s.save(p2);

            ORM.Proyecto p3 = new ORM.Proyecto();
            p3.setNomProy("RUEDAS CUADRADAS");
            p3.setFInicio(Date.valueOf("2021-12-28"));
            p3.setEmpleado(jp3);
            s.save(p3);

            t.commit();

        } catch (Exception e) {
            e.printStackTrace(System.err);
            if (t != null) {
                t.rollback();
            }
        }

    }

}
