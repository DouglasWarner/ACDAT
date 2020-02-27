// Parsing con SAX
package parsingsaxcontarclientes;

import java.io.PrintStream;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.XMLReader;
import org.xml.sax.SAXException;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.XMLReaderFactory;

class GestorEventos extends DefaultHandler {

  int numClientes;

  public int getNumClientes() {
    return numClientes;
  }

  public GestorEventos() {
  }

  @Override
  public void startDocument() {
    numClientes = 0;
  }

  @Override
  public void startElement(String uri, String nombreLocal, String nombreCualif, Attributes atributos) {
    if (nombreCualif.equals("cliente")) {
      numClientes++;
    }
  }

//    @Override
//    public void endElement(String uri, String nombreLocal, String nombreCualif) {
//    }
//    @Override
//    public void characters(char[] cars, int inicio, int longitud) {
//    }
}

public class ParsingSAXContarClientes {

  public static void main(String[] args) {
    String nomFich;
    if (args.length < 1) {
      System.out.println("Indicar por favor nombre de fichero");
      return;
    } else {
      nomFich = args[0];
    }
    try {
      XMLReader parserSAX = XMLReaderFactory.createXMLReader();
      GestorEventos gestorEventos = new GestorEventos();
      parserSAX.setContentHandler(gestorEventos);
      parserSAX.parse(nomFich);
      System.out.printf("Número de clientes: %d.\n", gestorEventos.getNumClientes());
    } catch (SAXException e) {
      System.err.println(e.getMessage());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
