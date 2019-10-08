package ORM_Query_SQL;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;

public class ORM_Query_SQL {

  public static void main(String[] args) {

    Session s = HibernateUtil.getSessionFactory().openSession();

    try {
      List<Object[]> empleados = s.createNativeQuery(
              "SELECT e.dni, e.id_depto, e.nom_emp, dp.categoria FROM empleado e LEFT OUTER JOIN empleado_datos_prof dp ON dp.dni=e.dni")
              .addScalar("dni", StringType.INSTANCE)
              .addScalar("id_depto", IntegerType.INSTANCE)
              .addScalar("nom_emp", StringType.INSTANCE)
              .addScalar("categoria", StringType.INSTANCE)
              .list();
      for (Object[] objetos : empleados) {
        System.out.println("Empleado [dni:" + (String) objetos[0]
                + ", id_depto: " + (Integer) objetos[1]
                + ", nom_emp: " + (String) objetos[2]
                + ", categoria: " + (String) objetos[3]
                + "]");
      }

    } catch (Exception e) {
      e.printStackTrace(System.err);
    }
  }
}
