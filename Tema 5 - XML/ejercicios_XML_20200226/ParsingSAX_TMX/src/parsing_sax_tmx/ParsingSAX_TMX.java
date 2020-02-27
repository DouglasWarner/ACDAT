package parsing_sax_tmx;

/**
 * 
 * El programa se puede desarrollar a partir del programa de ejemplo de parsing con SAX.
 * En los métodos manejadores de eventos se detecta el contexto en que se ha llamado
 * al método (fundamentalmente, nombre de elemento), y se va recopilando y guardando
 * en atributos de la clase toda la información relevante según el contexto. Cuando
 * se llega al final de un elemento de tipo tuv, toda ls información correspondiente
 * a una traducción está disponible y no hay mas que mostrarla en el formato en que se pide.
 * Se lleva la cuenta de cuando se han leido 500 traducciones (segmentos tu), y se
 * termina la ejecución del programa con System.exit(0). Hay mejores formas de terminar
 * el proceso de parsing. Lo que no hay es ninguna instrucción que permita parar el parsing,
 * y esto es por el propio funcionamiento de un parser SAX. Este consiste en un XMLReader
 * que va leyendo y analizando el documento y lanzando eventos. Un programa cliente solo
 * se puede responder a estos eventos en los métodos manejadores de eventos, que tienen
 * una limitada visión del proceso y ningún control sobre él. La mejor manera terminar el
 * proceso de parsing es lanzando una excepción de una clase nueva creada como clase 
 * hija de SAXParseException. Esta excepción acabará llegando en una llamada a a un método
 * manejador de eventos. Al ser de una clase específica, se puede reconocer y gestionar por separado.
 * Este programa asume que los únicos idiomas que pueden aparecer son el español
 * y el inglés. No asume que la lengua de origen sea ninguna de ellas. Sería relativamente
 * sencillo generalizarlo y hacerlo funcionar en el caso en que puedan estar presentes otros
 * idiomas y en el caso en que para el mismo texto en el idioma original estén disponibles
 * las traducciones a varios otros idiomas. Para guardar los textos en varios idiomas
 * se podrían utilizar arrays o HashMaps.
 * 
 */

// Parsing con SAX
import java.io.PrintStream;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;
import org.xml.sax.XMLReader;
import org.xml.sax.SAXException;
import org.xml.sax.Attributes;

class GestorEventos extends DefaultHandler {

  private final PrintStream ps;
  private int cuentaSeg;
  private String textoEN;
  private String textoES;
  private String idioma;
  private boolean enSegmento;
  private String idiomaOrigen;
  private final static int MAX_SEGMENTOS = 500;

  public GestorEventos(PrintStream ps) {
    this.ps = ps;
    this.cuentaSeg = 0;
    this.idioma = null;
    this.enSegmento = false;
    this.idiomaOrigen = null;
  }

  @Override
  public void startDocument() {
    cuentaSeg = 0;
  }

  @Override
  public void startElement(String uri, String nombreLocal, String nombreCualif, Attributes atributos) {

    if (nombreLocal.equals("header")) {
      this.idiomaOrigen = atributos.getValue("srclang");
    } else if (nombreLocal.equals("tu")) {
      this.textoEN = null;
      this.textoES = null;
    } else if (nombreLocal.equals("tuv")) {
      this.idioma = atributos.getValue("xml:lang");
    } else if (nombreLocal.equals("seg")) {  // Idioma está en variable idioma
      this.enSegmento = true;
    }
    if (cuentaSeg > MAX_SEGMENTOS) {
      System.exit(0);
    }
  }

  // Se escribe todo, una vez recopilado
  @Override
  public void endElement(String uri, String nombreLocal,
           String nombreCualif
  ) {
    if (nombreLocal.equals("tu")) {
      System.out.println("[" + this.cuentaSeg + "]");
      System.out.println(((this.idiomaOrigen.equals("en")) ? "*" : "") + "en=>" + this.textoEN);
      System.out.println(((this.idiomaOrigen.equals("es")) ? "*" : "") + "es=>" + this.textoES);
      cuentaSeg++;
    } else if (nombreLocal.equals("seg")) {
      this.enSegmento = false;
    }
  }

  @Override
  public void characters(char[] cars, int inicio, int longitud
  ) {
    String texto = new String(cars, inicio, longitud);
    if (texto.trim().length() == 0) {
      return;
    }
    if (this.enSegmento) {
      if (this.idioma.equals("en")) {
        this.textoEN = texto;
      } else if (this.idioma.equals("es")) {
        this.textoES = texto;
      }
    }
  }
}

public class ParsingSAX_TMX {

  public static void main(String[] args) {
    String nomFich;
    if (args.length < 1) {
      System.out.println("Indicar por favor nombre de fichero");
      return;
    } else {
      nomFich = args[0];
    }
    try {
      GestorEventos gestorEventos = new GestorEventos(System.out);
      XMLReader parserSAX = XMLReaderFactory.createXMLReader();
    parserSAX.setContentHandler(gestorEventos);
    parserSAX.parse(nomFich);
    } catch (SAXException e) {
      System.err.println(e.getMessage());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
