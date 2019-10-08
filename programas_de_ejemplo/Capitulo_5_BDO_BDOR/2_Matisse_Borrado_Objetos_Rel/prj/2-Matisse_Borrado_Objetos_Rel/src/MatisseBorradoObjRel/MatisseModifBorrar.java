package MatisseBorradoObjRel;

import com.matisse.MtDatabase;
import com.matisse.MtException;
import com.matisse.MtObjectIterator;
import gest_proyectos.*;

public class MatisseModifBorrar {
  
  public static void muestraProyecto(Proyecto p) {
    System.out.println("Proyecto "+p.getNom_proy()+
      "[OID: "+p.getMtOidToHexString()+"]");
    System.out.println("---------");
    System.out.println("Jefe proyecto: DNI: "+p.getJefe_proyecto().getDni()+
      ", Nombre: "+p.getJefe_proyecto().getNom_emp());
    System.out.println("Empleados:");
    MtObjectIterator<Empleado> itEmp = p.tiene_asignadoIterator();
    while(itEmp.hasNext()) {
      Empleado e = itEmp.next();
      System.out.println("DNI: "+e.getDni()+", Nombre: "+e.getNom_emp());
    }
  }
  
  public static void main(String[] args) {
    try(MtDatabase db = new MtDatabase("localhost", "AcDat_BDO")) {
      db.open();
      db.startTransaction(); 
      Proyecto p = new Proyecto(db);
      p.setNom_proy("TINTA_HOLOGRÁFICA");
      p.setF_inicio(new java.util.GregorianCalendar(2018,12,28));
      EmpleadoPlantilla ep  =                                     // NADALES
        EmpleadoPlantilla.lookupEmpleadoPlantilla_i_dni(db, "78901234X");
      p.setJefe_proyecto(ep);                                     
      Empleado e1 = Empleado.lookupEmpleado_pk(db, "89012345E");  // ROJAS
      e1.setNom_emp("ROSAS");
      e1.appendAsignado_a(p);
      Empleado e2 = Empleado.lookupEmpleado_pk(db, "76543210S");  // SILVA
      e2.getTiene_datos_prof().remove();
      e2.remove();
      Empleado e3 = Empleado.lookupEmpleado_pk(db, "56789012B");  // SAMPER
      e3.clearAsignado_a();
      MtObjectIterator<Proyecto> itProy = Proyecto.instanceIterator(db);
      while(itProy.hasNext()) {
        Proyecto unProy = itProy.next();
        muestraProyecto(unProy);
      }
      db.commit();
    }
    catch (MtException mte)
    {
      System.out.println("MtException : " + mte.getMessage());
    }
  }
}
