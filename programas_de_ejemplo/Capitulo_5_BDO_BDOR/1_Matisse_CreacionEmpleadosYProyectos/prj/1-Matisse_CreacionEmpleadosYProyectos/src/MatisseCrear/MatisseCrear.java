package MatisseCrear;

import com.matisse.MtDatabase;
import com.matisse.MtException;
import gest_proyectos.*;

public class MatisseCrear {
  public static void main(String[] args) {
    try(MtDatabase db = new MtDatabase("localhost", "AcDat_BDO")) {
      db.open();
      db.startTransaction();
      Proyecto p1 = new Proyecto(db);
      p1.setNom_proy("PAPEL ELECTRÓNICO");
      p1.setF_inicio(new java.util.GregorianCalendar(2018,12,01));
      EmpleadoPlantilla jp1 = new EmpleadoPlantilla(db);
      jp1.setDni("78901234X");
      jp1.setNom_emp("NADALES");
      jp1.setNum_emp("604202");      
      p1.setJefe_proyecto(jp1);
      Empleado e1=new Empleado(db);
      e1.setDni("56789012B");
      e1.setNom_emp("SAMPER");
      p1.appendTiene_asignado(e1);
      EmpleadoPlantilla e2=new EmpleadoPlantilla(db);
      e2.setDni("76543210S");
      e2.setNom_emp("SILVA");
      e2.setNum_emp("753014");
      DatosProfesionales dp2 = new DatosProfesionales(db);
      dp2.setDni("76543210S");
      dp2.setCategoria("B1");
      dp2.setSueldo_bruto_anual((float) 45200.00);
      e2.setTiene_datos_prof (dp2);
      p1.appendTiene_asignado(e2);
      Empleado e3=new Empleado(db);
      e3.setDni("89012345E");
      e3.setNom_emp("ROJAS");
      db.commit();
    }
    catch (MtException mte)
    {
      System.out.println("MtException : " + mte.getMessage());
    }
  }
}
