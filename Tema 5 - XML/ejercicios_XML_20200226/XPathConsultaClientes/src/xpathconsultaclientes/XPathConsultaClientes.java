package xpathconsultaclientes;

import java.io.File;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPathConstants;

public class XPathConsultaClientes {

  public static void main(String[] args) {

    File fXML;

    if (args.length < 2) {
      System.out.println("Por favor, indicar: nombre_fichero dni");
      System.exit(1);
    }
    String nomFich = args[0];
    String dni = args[1];

    fXML = new File(nomFich);
    if (!fXML.isFile()) {
      System.err.printf("ERROR: Fichero %s no existe.", nomFich);
      return;
    }

    System.out.printf("DNI de cliente: %s\n", dni);

    String xPath = "/clientes/cliente[@DNI=\"" + dni + "\"]/apellidos";

    try {

      Document docXML = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(fXML);

      XPathExpression xPathExp = XPathFactory.newInstance().newXPath().compile(xPath);
      NodeList resultados = (NodeList) xPathExp.evaluate(docXML, XPathConstants.NODESET);

      if (resultados.getLength() < 1) {
        System.out.printf("No existe ningún cliente con DNI %s.\n", dni);
      } else if (resultados.getLength() > 1) {
        System.out.printf("ERROR: más de un cliente con DNI %s.\n", dni); // Nunca debería pasar
      } else {

        System.out.printf("nombre: %s\n", resultados.item(0).getTextContent());

        String xPathCP = "/clientes/cliente[@DNI=\"" + dni + "\"]/CP";
        XPathExpression xPathExpCP = XPathFactory.newInstance().newXPath().compile(xPathCP);
        NodeList resCP = (NodeList) xPathExpCP.evaluate(docXML, XPathConstants.NODESET);

        if (resCP.getLength() == 1) {
          System.out.printf("CP: %s.\n", resCP.item(0).getTextContent());
        } else if (resCP.getLength() > 1) {
          System.out.printf("ERROR: más de un CP para cliente con DNI %s.\n", dni); // Nunca debería pasar
        } else {
          System.out.println("CP no informado");
        }
      }

    } catch (SAXException | XPathException e) {
      System.err.println(e.getMessage());
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

}
