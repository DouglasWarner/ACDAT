package xPathConsulta;

import java.io.File;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPathConstants;

public class XPathConsulta {

  public static void main(String[] args) {

    String nomFich, xPath = "";
    File fXML;

    if (args.length < 1) {
      System.out.println("Por favor, indicar nombre de fichero.");
      return;
    } else {
      nomFich = args[0];
      System.out.println("Fichero XML: " + nomFich);
      if (args.length < 2) {
        System.out.print("Introducir XPath: ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
          xPath = br.readLine();
        } catch (Exception e) {
        }
      } else {
        xPath = args[1];
      }
    }

    fXML = new File(nomFich);
    if (!fXML.isFile()) {
      System.err.println("Fichero " + nomFich + " no existe.");
      return;
    }

    System.out.println("Fichero XML: " + fXML.getAbsolutePath() + " XPath: " + xPath);

    try {

      Document docXML = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(fXML);
      XPathExpression xPathExp = XPathFactory.newInstance().newXPath().compile(xPath);

      NodeList resultados = (NodeList) xPathExp.evaluate(docXML, XPathConstants.NODESET);

      for (int i = 0; i < resultados.getLength(); i++) {
        System.out.printf("[%d]: %s\n", i + 1, resultados.item(i).getTextContent());
      }
      if (resultados.getLength() < 1) {
        System.out.println("No se obtuvo ningún resultado.");
      }

    } catch (SAXException | XPathException e) {
      System.err.println(e.getMessage());
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

}
