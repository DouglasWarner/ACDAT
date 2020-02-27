package parsingcomcontarclientes;

import java.io.File;
import java.io.FileNotFoundException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ParsingDOMContarClientes {

  public static int numClientes(Node nodo) {
    
    int numClientes = 0;
    
    if (nodo.getNodeType() == Node.ELEMENT_NODE && nodo.getNodeName().equals("cliente")) {
      numClientes++;
    }

    NodeList nodosHijos = nodo.getChildNodes();    // Muestra nodos hijos
    for (int i = 0; i < nodosHijos.getLength(); i++) {
      numClientes += numClientes(nodosHijos.item(i));
    }
    
    return numClientes;
    
  }

  public static void main(String[] args) {
    String nomFich;
    if (args.length < 1) {
      System.out.println("Indicar por favor nombre de fichero");
      return;
    } else {
      nomFich = args[0];
    }

    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    dbf.setIgnoringComments(true);
    dbf.setIgnoringElementContentWhitespace(true);

    try {
      DocumentBuilder db = dbf.newDocumentBuilder();
      Document domDoc = db.parse(new File(nomFich));
      int numClientes = numClientes(domDoc);
      System.out.printf("%d clientes en documento.\n", numClientes);
    } catch (FileNotFoundException | ParserConfigurationException
            | SAXException e) {
      System.err.println(e.getMessage());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
