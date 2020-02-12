package asignacionesproyectos;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.sql.Date;
import java.util.List;
import java.util.Set;

public class AsignacionesProyectos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Transaction t = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            t = s.beginTransaction();

            // Se recuperan empleados ya existentes para asignarlos como jefes
            // de proyecto y como empleados asignados a proyectos.
            // Se recupera un proyecto y se crea un proyecto.
            // Se asume que existen empleados con esos DNI y por eso se usa load() y no
            // get(). Si no existieran, se produciría una excepción ObjectNotFoundException
            ORM.Empleado jp1 = s.load(ORM.Empleado.class, "78901234X");
            ORM.Empleado e1 = s.load(ORM.Empleado.class, "89012345E");

            ORM.Empleado e2 = new ORM.Empleado();
            e2.setDni("97531975S");
            e2.setNomEmp("SERRA");
            s.save(e2);

            ORM.Empleado e3 = new ORM.Empleado();
            e3.setDni("23571379J");
            e3.setNomEmp("MARES");
            s.save(e3);

            // Se recupera un proyecto por nombre. Se asume que existe uno y solo un
            // proyecto con ese nombre. Si no, se produciría una excepción NoResultException
            // o NonUniqueResultException. Una posibilidad interesante sería crear un
            // índice único de proyectos por nombre: 
            //   create unique index i_proyecto_nombre on proyecto(nom_proy)
            // Entonces sería imposible que produjera una excepción NonUniqueResultException.    
            Query q = s.createQuery(
                    "select p from Proyecto p where p.nomProy='PAPEL ELECTRÓNICO'"
            );
            ORM.Proyecto p1 = (ORM.Proyecto) q.getSingleResult();
            p1.setFFin(Date.valueOf("2020-06-09"));
            s.update(p1);   // Ya existe, se ha recuperado y modificado

            ORM.Proyecto p2 = new ORM.Proyecto();
            p2.setNomProy("AVIÓN A PEDALES");
            p2.setFInicio(Date.valueOf("2019-02-20"));
            p2.setEmpleado(jp1); // Es jefe de proyecto del proyecto anterior
            s.save(p2);

            // Asignación de e1 a p1
            ORM.AsigProyectoId ap1Id = new ORM.AsigProyectoId(
                    e1.getDni(), p1.getIdProy(), Date.valueOf("2018-12-10")
            );
            ORM.AsigProyecto ap1 = new ORM.AsigProyecto();
            // Existen AsigProyecto.setEmpleado() y AsigProyect.setProyecto(),
            // pero no hay ningún método para asignarle fecha de inicio
            ap1.setId(ap1Id);
            ap1.setFFin(Date.valueOf("2019-03-20"));
            s.save(ap1);

            // Asignación de e3 a p1
            ORM.AsigProyectoId ap2Id = new ORM.AsigProyectoId(
                    e3.getDni(), p1.getIdProy(), Date.valueOf("2019-01-02")
            );
            ORM.AsigProyecto ap2 = new ORM.AsigProyecto();
            ap2.setId(ap2Id);
            ap2.setFFin(Date.valueOf("2019-05-16"));
            s.save(ap2);

            // Asignación de e2 a p2
            ORM.AsigProyecto ap3 = new ORM.AsigProyecto();
            ORM.AsigProyectoId ap3Id = new ORM.AsigProyectoId(
                    e2.getDni(), p2.getIdProy(), Date.valueOf("2019-03-17")
            );
            ap3.setId(ap3Id);
            s.save(ap3);

            // Asignación de jp1 a p2
            ORM.AsigProyecto ap4 = new ORM.AsigProyecto();
            ORM.AsigProyectoId ap4Id = new ORM.AsigProyectoId(
                    e3.getDni(), p2.getIdProy(), Date.valueOf("2020-03-23")
            );
            ap4.setId(ap4Id);
            s.save(ap4);

            t.commit();

        } catch (Exception e) {
            e.printStackTrace(System.err);
            if (t != null) {
                t.rollback();
            }
        }

        // Para que las siguientes consultas con Query funcionen, deben hacerse
        // en una nueva sesión, aunque se haya hecho commit.
        
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            // Mostrar asignaciones de empleados para cada proyecto
            Query qp = s.createQuery("select p from Proyecto p").setReadOnly(true);
            List<ORM.Proyecto> listaProy = qp.getResultList();
            for (ORM.Proyecto unProy : listaProy) {
                System.out.println("Proyecto " + unProy.getIdProy() + "(" + unProy.getNomProy() + "), "
                        + "JEFE[" + unProy.getEmpleado().getDni() + "(" + unProy.getEmpleado().getNomEmp() + ")]: ");
                for (ORM.AsigProyecto unaAsig : (Set<ORM.AsigProyecto>) unProy.getAsigProyectos()) {
                    ORM.Empleado unEmpAsig = unaAsig.getEmpleado();
                    System.out.println("  Asignado empleado " + unEmpAsig.getDni() + ": " + unEmpAsig.getNomEmp());
                }
            }

            // Mostrar asignaciones a proyectos para cada empleado
            Query qe = s.createQuery("select e from Empleado e").setReadOnly(true);
            List<ORM.Empleado> listaEmp = qe.getResultList();
            for (ORM.Empleado unEmp : listaEmp) {
                System.out.println("Empleado " + unEmp.getDni() + "(" + unEmp.getNomEmp() + ")");
                for (ORM.AsigProyecto unaAsig : (Set<ORM.AsigProyecto>) unEmp.getAsigProyectos()) {
                    ORM.Proyecto unProyAsig = unaAsig.getProyecto();
                    System.out.println("  Asignado a proyecto " + unProyAsig.getIdProy() + ": " + unProyAsig.getNomProy());
                }
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
            if (t != null) {
                t.rollback();
            }
        }

    }

}
