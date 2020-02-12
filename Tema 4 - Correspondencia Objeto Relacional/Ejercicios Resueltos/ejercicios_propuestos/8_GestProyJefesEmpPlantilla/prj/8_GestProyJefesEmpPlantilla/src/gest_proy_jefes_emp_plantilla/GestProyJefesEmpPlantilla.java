package gest_proy_jefes_emp_plantilla;

import org.hibernate.Session;
import org.hibernate.Transaction;
import java.sql.Date;
import java.util.List;
import java.util.Set;
import org.hibernate.query.Query;

public class GestProyJefesEmpPlantilla {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Transaction t = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            t = s.beginTransaction();

            // Nuevos empleados
            ORM.Empleado e1 = new ORM.Empleado();
            e1.setDni("31415926L");
            e1.setNomEmp("TORRES");
            s.save(e1);

            ORM.EmpleadoPlantilla jp1 = new ORM.EmpleadoPlantilla();
            jp1.setDni("16180339T");
            jp1.setNomEmp("LACIERVA");
            jp1.setNumEmp(823475);
            s.save(jp1);
            
            // Nuevos proyectos

            ORM.Proyecto p1 = new ORM.Proyecto();
            p1.setNomProy("ULTRABATIDORA");
            p1.setFInicio(Date.valueOf("2019-12-28"));
            p1.setEmpleadoPlantilla(jp1);
            s.save(p1);
            
            ORM.EmpleadoPlantilla jp2 = s.load(ORM.EmpleadoPlantilla.class, "89012345E");
            ORM.Proyecto p2 = new ORM.Proyecto();
            p2.setNomProy("DIVÁN HIPERBÁRICO");
            p2.setFInicio(Date.valueOf("2019-04-05"));
            p2.setEmpleadoPlantilla(jp2);
            s.save(p2);
            
            // Asignación de e1 (no de plantilla) a p1
            ORM.AsigProyectoId ap1Id = new ORM.AsigProyectoId(
                    e1.getDni(), p1.getIdProy(), Date.valueOf("2018-12-10")
            );
            ORM.AsigProyecto ap1 = new ORM.AsigProyecto();
            ap1.setId(ap1Id);
            ap1.setFFin(Date.valueOf("2020-03-20"));
            s.save(ap1);
            
            // Asignación de e3 (empleado de plantilla recuperado) a p1
            ORM.Empleado e3 = s.load(ORM.Empleado.class, "23571379J");
            ORM.AsigProyectoId ap2Id = new ORM.AsigProyectoId(
                    e3.getDni(), p1.getIdProy(), Date.valueOf("2018-09-20")
            );
            ORM.AsigProyecto ap2 = new ORM.AsigProyecto();
            ap2.setId(ap2Id);
            ap2.setFFin(Date.valueOf("2020-03-20"));
            s.save(ap2);
            
            // Asignación de e4 (empleado de plantilla recuperado a p2)
            ORM.Empleado e4 = s.load(ORM.Empleado.class, "09876543K");
            ORM.AsigProyectoId ap3Id = new ORM.AsigProyectoId(
                    e4.getDni(), p2.getIdProy(), Date.valueOf("2020-01-12")
            );
            ORM.AsigProyecto ap3 = new ORM.AsigProyecto();
            ap3.setId(ap3Id);
            s.save(ap3);
  
            t.commit();

        } catch (Exception e) {
            e.printStackTrace(System.err);
            if (t != null) {
                t.rollback();
            }
        }
        
        // Lista todos los proyectos con sus empleados asignados.
        // Lista todos los empleados con sus asignaciones a proyectos.
        //   (para cada uno indica si es de plantilla, con instanceof)
        // Lista todos los empleados de plantilla con los proyectos de los que
        //   son jefes de proyecto.
        
        // Para que las siguientes consultas con Query funcionen, deben hacerse
        // en una nueva sesión, aunque se haya hecho commit.

        try (Session s = HibernateUtil.getSessionFactory().openSession()) {

            // Mostrar asignaciones de empleados para cada proyecto
            Query qp = s.createQuery("select p from Proyecto p").setReadOnly(true);
            List<ORM.Proyecto> listaProy = qp.getResultList();
            for (ORM.Proyecto unProy : listaProy) {
                System.out.println("Proyecto " + unProy.getIdProy() + "(" + unProy.getNomProy() + "), "
                        + "JEFE[" + unProy.getEmpleadoPlantilla().getDni() + "(" + unProy.getEmpleadoPlantilla().getNomEmp() + ")]: ");
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

            // Mostrar proyectos de los que cada empleado de plantilla es jefe
            Query qep = s.createQuery("select ep from EmpleadoPlantilla ep").setReadOnly(true);
            List<ORM.EmpleadoPlantilla> listaEmpPlant = qep.getResultList();
            for (ORM.EmpleadoPlantilla unEmpPlant : listaEmpPlant) {
                System.out.println("Empleado de plantilla " + unEmpPlant.getDni() + "(" + unEmpPlant.getNomEmp() + ")");
                for (ORM.Proyecto unProy : (Set<ORM.Proyecto>) unEmpPlant.getProyectos()) {
                    System.out.println("  Jefe de proyecto " + unProy.getIdProy() + ": " + unProy.getNomProy());
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
