// Parsing con SAX
package sax_parser;

import java.io.PrintStream;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;
import org.xml.sax.XMLReader;
import org.xml.sax.SAXException;
import org.xml.sax.Attributes;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.SAXParser;

class GestorEventos extends DefaultHandler {

    private static final String INDENT_NIVEL = "  ";  // Para indentación
    private final PrintStream ps;
    private int nivel;

    private void indenta() {
        for (int i = 0; i < nivel; i++) {    // Indentación
            ps.print(INDENT_NIVEL);
        }
    }

    public GestorEventos(PrintStream ps) {
        this.ps = ps;
    }

    @Override
    public void startDocument() {
        nivel = 0;
    }

    @Override
    public void startElement(String uri, String nombreLocal, String nombreCualif, Attributes atributos) {
        nivel++;
        indenta();
        ps.print("<" + nombreCualif);
        for (int i = 0; i < atributos.getLength(); i++) {
            ps.print(" @" + atributos.getLocalName(i) + "[" + atributos.getValue(i) + "]");
        }
        ps.println(">");
    }

    @Override
    public void endElement(String uri, String nombreLocal, String nombreCualif) {
        nivel--;
    }

    @Override
    public void characters(char[] cars, int inicio, int longitud) {
        String texto = new String(cars, inicio, longitud);
        if (texto.trim().length() == 0) {
            return;
        }
        nivel++;
        indenta();
        nivel--;
        ps.println("#text[" + texto + "]");
    }
}

public class SAX_Parser {

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
            GestorEventos gestorEventos = new GestorEventos(System.out);
            parserSAX.setContentHandler(gestorEventos);
            parserSAX.parse(nomFich);
        } catch (SAXException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
