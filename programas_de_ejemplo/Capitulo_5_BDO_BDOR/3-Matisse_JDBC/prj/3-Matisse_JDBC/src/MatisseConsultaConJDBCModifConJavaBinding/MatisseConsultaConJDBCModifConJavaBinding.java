package MatisseConsultaConJDBCModifConJavaBinding;

import com.matisse.MtDatabase;
import com.matisse.MtException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import gest_proyectos.*;

public class MatisseConsultaConJDBCModifConJavaBinding {

  public static void muestraErrorSQL(SQLException e) {
    System.err.println("SQL ERROR mensaje: " + e.getMessage());
    System.err.println("SQL Estado: " + e.getSQLState());
    System.err.println("SQL código específico: " + e.getErrorCode());
  }

  public static void main(String[] args) {
    try (
      MtDatabase db = new MtDatabase("localhost", "AcDat_BDO")) {
      db.open();
      db.startTransaction();
      Empleado e = new Empleado(db);
      e.setDni("65432109F");
      e.setNom_emp("LUQUE");
      try (
        Connection jdbcCon = db.getJDBCConnection();
        Statement stmt = jdbcCon.createStatement()) {
          String commandText = "SELECT REF(p) FROM gest_proyectos.Proyecto p WHERE p.jefe_proyecto.dni='78901234X'";
          try (ResultSet rset = stmt.executeQuery(commandText)) {
              Proyecto p;
              while (rset.next()) {
                  p = (Proyecto) rset.getObject(1);
                  System.out.println("Proyecto: "+p.getNom_proy()+
                          ", jefe: ["+p.getJefe_proyecto().getDni()+"] "+
                          p.getJefe_proyecto().getNom_emp());
                  if(p.getJefe_proyecto().getDni().equals("78901234X")) {
                      p.appendTiene_asignado(e);
                      System.out.println("Asignado nuevo empleado.");
                  }
              }
          }
        db.commit();
      } catch (SQLException sqle) {
        muestraErrorSQL(sqle);
      }
    } catch (MtException mte) {
      System.out.println("MtException : " + mte.getMessage());
    }
  }
}

