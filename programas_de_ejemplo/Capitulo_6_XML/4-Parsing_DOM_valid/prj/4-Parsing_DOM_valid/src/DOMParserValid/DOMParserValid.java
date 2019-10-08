/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DOMParserValid;

import java.io.File;
import java.io.PrintStream;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.validation.SchemaFactory;
import javax.xml.XMLConstants;

/**
 *
 * @author carlos
 */
public class DOMParserValid {

  static final String INDENT_NIVEL = "  ";  // Para indentación

  public static void muestraNodo(Node nodo, int nivel, PrintStream ps) {

    for (int i = 0; i < nivel; i++) {
      ps.print(INDENT_NIVEL);
    }

    switch (nodo.getNodeType()) {    // Escribe información de nodo según tipo
      case Node.DOCUMENT_NODE:    // Documento
        ps.println("DOCUMENTO");
        break;
      case Node.ELEMENT_NODE:    // Elemento
        ps.println("ELEMENTO(" + nodo.getNodeName() + ")");
        break;
      case Node.TEXT_NODE:   // Texto
        ps.println(nodo.getNodeName() + "[" + nodo.getNodeValue() + "]");
        break;
    }
    NodeList nodosHijos = nodo.getChildNodes();    // Nodos hijos
    for (int i = 0; i < nodosHijos.getLength(); i++) {
      muestraNodo(nodosHijos.item(i), nivel + 1, ps);
    }
  }

  public static void main(String[] args) {

    File f = null, fEsq = null;

    if (args.length < 1) {
      System.out.println("Indicar por favor nombre de fichero");
      return;
    } else {
      String nomFich = args[0];
      f = new File(nomFich);
      if (!f.isFile()) {
        System.err.println("Fichero " + nomFich + " no existe.");
        return;
      }
      if (args.length >= 2) {
        String nomFichEsquema = args[1];
        fEsq = new File(nomFichEsquema);
        if (!fEsq.isFile()) {
          System.err.println("Fichero " + nomFichEsquema + " no existe.");
          return;
        }
      }
    }

    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    dbf.setIgnoringComments(true);
    dbf.setIgnoringElementContentWhitespace(true);

    System.out.println("Fichero XML: " + f.getAbsolutePath());
    if (fEsq != null) {    // Validar con esquema
      System.out.println("Validación con esquema: " + fEsq.getAbsolutePath());
      try {
        dbf.setSchema(SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI).newSchema(fEsq));
      } catch (SAXException e) {
        System.err.println(e.getMessage());
        return;
      }
    } else {    // Validar con DTD
      System.out.println("Validación con DTD");
      dbf.setValidating(true);
    }

    try {
      DocumentBuilder db = dbf.newDocumentBuilder();
      db.setErrorHandler(new GestorEventos());  // Cosa de SAX, pero conveniente

      Document domDoc = db.parse(f);

      muestraNodo(domDoc, 0, System.out);
    } catch (ParserConfigurationException e) {
      System.err.println(e.getMessage());
    } catch (SAXParseException e) {
      /*
      System.err.println("----getMessage()--------");
      System.err.println(e.getMessage());
      System.err.println("----getLineNumber()-----");
      System.err.println(e.getLineNumber());
      System.err.println("----getColumnNumber-----");
      System.err.println(e.getColumnNumber());
      System.err.println("----getSystemId---------");
      System.err.println(e.getSystemId());
      System.err.println("----getPublicId()-------");
      System.err.println(e.getPublicId());
      System.err.println("----toString()-------");
      System.err.println(e.toString());
       */
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

}

class GestorEventos extends DefaultHandler {

  @Override
  public void error(SAXParseException e) throws SAXParseException {
    /*
    System.err.println("=== ERROR de parsing ===");
    System.err.println("----getMessage()--------");
    System.err.println(e.getMessage());
    System.err.println("----getLineNumber()-----");
    System.err.println(e.getLineNumber());
    System.err.println("----getColumnNumber-----");
    System.err.println(e.getColumnNumber());
    System.err.println("----getSystemId---------");
    System.err.println(e.getSystemId());
    System.err.println("----getPublicId()-------");
    System.err.println(e.getPublicId());
    System.err.println("----toString()-------");
    System.err.println("========================");
     */
    System.err.println("Error recuperable: " + e.toString());
    throw (e);
  }

  @Override
  public void fatalError(SAXParseException e) throws SAXParseException {
    /*
    System.err.println("=== ERROR FATAL de parsing ===");
    System.err.println("----getMessage()--------");
    System.err.println(e.getMessage());
    System.err.println("----getLineNumber()-----");
    System.err.println(e.getLineNumber());
    System.err.println("----getColumnNumber-----");
    System.err.println(e.getColumnNumber());
    System.err.println("----getSystemId---------");
    System.err.println(e.getSystemId());
    System.err.println("----getPublicId()-------");
    System.err.println(e.getPublicId());
    System.err.println("----toString()-------");
    System.err.println("========================");
     */
    System.err.println("Error no recuperable: " + e.toString());
    throw (e);
  }

  @Override
  public void warning(SAXParseException e) throws SAXParseException {
    /*
    System.err.println("=== AVISO de parsing ===");
    System.err.println("----getMessage()--------");
    System.err.println(e.getMessage());
    System.err.println("----getLineNumber()-----");
    System.err.println(e.getLineNumber());
    System.err.println("----getColumnNumber-----");
    System.err.println(e.getColumnNumber());
    System.err.println("----getSystemId---------");
    System.err.println(e.getSystemId());
    System.err.println("----getPublicId()-------");
    System.err.println(e.getPublicId());
    System.err.println("----toString()-------");
    System.err.println("========================");
     */
    System.err.println("Aviso: " + e.toString());

    throw (e);
  }

}
