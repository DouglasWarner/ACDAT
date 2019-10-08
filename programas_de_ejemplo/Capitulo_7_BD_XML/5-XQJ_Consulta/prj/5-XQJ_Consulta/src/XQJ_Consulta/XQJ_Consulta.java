package XQJ_Consulta;

import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQResultSequence;
import javax.xml.xquery.XQExpression;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamConstants;

public class XQJ_Consulta {

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
      String cad = "doc('/db/pruebas/Clientes.xml')/clientes/cliente/apellidos";
      XQExpression xqe = c.createExpression();
      XQResultSequence xqrs = xqe.executeQuery(cad);
      int i = 1;
      while (xqrs.next()) {
        System.out.println("[Resultado " + (i++) + "]");
        XMLStreamReader xsr = xqrs.getItemAsStream();
        while (xsr.hasNext()) {
          if (xsr.getEventType() == XMLStreamConstants.CHARACTERS) {
            System.out.println(xsr.getText());
          }
          xsr.next();
        }
      }
    } catch (XQException e) {
      muestraErrorXQuery(e);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        if (c != null) {
          c.close();
        }
      } catch (XQException xe) {
        xe.printStackTrace();
      }
    }
  }
}