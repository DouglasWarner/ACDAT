package ConexionXQJ;

import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQMetaData;

public class ConexionXQJ {

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
            XQMetaData xqmd = c.getMetaData();
            System.out.println("Conexión establecida como: " + xqmd.getUserName());
            System.out.println(
                    //                "Producto: " +xqmd.getProductName()+", "+"versión: " +xqmd.getXQJMajorVersion()+"."+xqmd.getXQJMinorVersion()+".\n"+
                    "Soporte para transacciones: " + (xqmd.isTransactionSupported() ? "Sí" : "No") + ".\n"
            //               + "Validación con esquemas: "+ (xqmd.isSchemaValidationFeatureSupported() ? "Sí" : "No")+".\n"+
            //               + "Serialización: "+ (xqmd.isSerializationFeatureSupported() ? "Sí" : "No")+".\n"
            );
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
