package ORM_Query_consulta_getSingleResult;

import org.hibernate.Session;
import org.hibernate.query.Query;
import javax.persistence.NoResultException;
import org.hibernate.NonUniqueResultException;

public class ORM_Query_consulta_getSingleResult {

  public static void main(String[] args) {

    Session s = HibernateUtil.getSessionFactory().openSession();
    try {

      Query q = s.createQuery("FROM EmpleadoDatosProf dp WHERE dp.sueldoBrutoAnual>=ALL(SELECT sueldoBrutoAnual FROM EmpleadoDatosProf)").setReadOnly(true);

      ORM.EmpleadoDatosProf datosProf = (ORM.EmpleadoDatosProf) q.getSingleResult();

      System.out.println("Empleado [" + datosProf.getDni() + "] "
              + "(" + datosProf.getEmpleado().getNomEmp() + ") "
              + " de departamento: " + datosProf.getEmpleado().getDepartamento().getNomDepto()
              + " de sede: " + datosProf.getEmpleado().getDepartamento().getSede().getNomSede()
              + ", con sueldo: " + datosProf.getSueldoBrutoAnual()
      );

    } catch (NoResultException e) {
      System.err.println("ERROR: No hay datos profesionales para ningún empleado");
    } catch (NonUniqueResultException e) {
      System.err.println("ERROR: Hay más de un empleado con el salario máximo");
    } catch (Exception e) {
      e.printStackTrace(System.err);
    }
  s.close();
  }
}
