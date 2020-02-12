package muestraasignacionesvigentes;

import org.hibernate.Session;
import org.hibernate.query.Query;
import java.sql.Date;
import java.util.List;

public class MuestraAsignacionesVigentes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            // Mostrar asignaciones de empleados para cada proyecto
            Query qp = s.createQuery(
                    "select ap from AsigProyecto ap where ap.id.FInicio <= sysdate()"
                    +" and (ap.FFin is null or sysdate() <= ap.FFin)"
                    +" order by ap.proyecto.nomProy,ap.empleado.nomEmp"
            ).
            setReadOnly(true);
            List<ORM.AsigProyecto> listaAsigProy = qp.getResultList();
            for (ORM.AsigProyecto unaAsigProy : listaAsigProy) {
                System.out.println(
                        "Proyecto [" + unaAsigProy.getProyecto().getNomProy() + "], "
                        + "Empleado [" + unaAsigProy.getEmpleado().getNomEmp() + "], "
                        + "desde " + unaAsigProy.getId().getFInicio()
                        + (unaAsigProy.getFFin() != null
                        ? " hasta " + unaAsigProy.getFFin() : " y sin fecha de fin")
                );
            }

        } catch (Exception e) {
            e.printStackTrace(System.err);
        }

    }

}
