package XQJ_Modificacion;

import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQExpression;

public class XQJ_Modificacion {

  private static String nomClaseDS = "net.xqj.exist.ExistXQDataSource";

  private static XQConnection obtenConexion() throws ClassNotFoundException, InstantiationException, IllegalAccessException, XQException {
    XQDataSource xqs = (XQDataSource) Class.forName(nomClaseDS).newInstance();
    xqs.setProperty("serverName", "localhost");
    xqs.setProperty("port", "8090");
    xqs.setProperty("user", "admin");
    xqs.setProperty("password", "admin");
    return xqs.getConnection();
  }

  private static void muestraErrorXQuery(XQException e) {
    System.err.println("XQuery ERROR mensaje: " + e.getMessage());
    System.err.println("XQuery ERROR causa: " + e.getCause());
    System.err.println("XQuery ERROR código: " + e.getVendorCode());
  }

  public static void main(String[] args) {
    XQConnection c = null;
    try {
      c = obtenConexion();

      String cad = "update insert "
              + "<cliente DNI=\"09876543K\">"
              + "<apellidos>LAMIQUIZ</apellidos>"
              + "<CP>43001</CP>"
              + "</cliente>"
              + " preceding doc('/db/pruebas/Clientes.xml')/clientes/cliente/apellidos[text()='SAMPER']/..";
      XQExpression xqe = c.createExpression();
      xqe.executeCommand(cad); // UPDATE según P. Lehti
    } catch (XQException e) {
      muestraErrorXQuery(e);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        if (c != null) {
          c.close();
        }
      } catch (XQException e) {
        e.printStackTrace();
      }
    }
  }

}