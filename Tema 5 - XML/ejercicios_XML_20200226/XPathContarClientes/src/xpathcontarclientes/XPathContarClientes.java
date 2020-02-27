package xpathcontarclientes;

import java.io.File;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPathConstants;

public class XPathContarClientes {

  public static void main(String[] args) {

    File fXML;

    if (args.length < 1) {
      System.out.println("Por favor, indicar: nombre_fichero");
      System.exit(1);
    }
    String nomFich = args[0];

    fXML = new File(nomFich);
    if (!fXML.isFile()) {
      System.err.printf("ERROR: Fichero %s no existe.", nomFich);
      return;
    }

    String xPath = "/clientes/cliente";
    String xPathCuenta = "count(/clientes/cliente)";
    
    try {

      Document docXML = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(fXML);

      XPathExpression xPathExp = XPathFactory.newInstance().newXPath().compile(xPath);
      NodeList resultados = (NodeList) xPathExp.evaluate(docXML, XPathConstants.NODESET);

      int numClientes = resultados.getLength();
      System.out.printf("%d cliente%s recuperados con xpath %s.\n", numClientes,
              (numClientes == 1) ? "" : "s", xPath);
      
      XPathExpression xPathExpCount = XPathFactory.newInstance().newXPath().compile(xPathCuenta);
      Object resultadosCuenta = xPathExpCount.evaluate(docXML, XPathConstants.STRING);
      numClientes = Integer.parseInt(resultadosCuenta.toString());
      System.out.printf("%d cliente%s recuperados con xpath %s.\n", numClientes,
              (numClientes == 1) ? "" : "s", xPathCuenta);

    } catch (SAXException | XPathException e) {
      System.err.println(e.getMessage());
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

}
