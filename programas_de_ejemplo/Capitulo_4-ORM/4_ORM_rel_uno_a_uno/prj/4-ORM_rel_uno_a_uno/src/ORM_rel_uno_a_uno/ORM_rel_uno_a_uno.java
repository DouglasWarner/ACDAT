package ORM_rel_uno_a_uno;

import java.math.BigDecimal;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ORM_rel_uno_a_uno {

  public static void main(String[] args) {

    try (Session s = HibernateUtil.getSessionFactory().openSession()) {
      Transaction t = null;

      try {
        t = s.beginTransaction();

        ORM.Departamento depto = s.get(ORM.Departamento.class, 1);

        ORM.Empleado emp = new ORM.Empleado();
        emp.setDni("76543210S");
        emp.setDepartamento(depto);
        emp.setNomEmp("SILVA");
        s.save(emp);

        ORM.EmpleadoDatosProf datosProf = new ORM.EmpleadoDatosProf();
        datosProf.setEmpleado(emp);
        datosProf.setCategoria("A");
        datosProf.setSueldoBrutoAnual(new BigDecimal(52000.0));
        s.save(datosProf);

        t.commit();

        s.refresh(emp);
        System.out.println("Categoría del nuevo empleado: " + emp.getEmpleadoDatosProf().getCategoria()
                + ", DNI: " + emp.getEmpleadoDatosProf().getDni());

      } catch (Exception e) {
        e.printStackTrace(System.err);
        if (t != null) {
          t.rollback();
        }
      }
    }
  }

}
