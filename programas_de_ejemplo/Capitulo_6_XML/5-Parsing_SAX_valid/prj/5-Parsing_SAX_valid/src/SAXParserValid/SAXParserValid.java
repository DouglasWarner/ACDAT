// Parsing SAX con validación
package SAXParserValid;

import java.io.File;
import java.io.PrintStream;
import javax.xml.XMLConstants;
import org.xml.sax.XMLReader;
import org.xml.sax.SAXException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.validation.SchemaFactory;

class GestorEventos extends DefaultHandler {

    private static final String INDENT_NIVEL = "  ";  // Para indentación
    private final PrintStream ps;
    private int nivel;

    private void indenta() {
        for (int i = 0; i < nivel; i++) {
            ps.print(INDENT_NIVEL);
        }
    }

    public GestorEventos(PrintStream ps) {
        this.ps = ps;
    }

    @Override
    public void startDocument() {
        nivel = 0;
        ps.println("(DOCUMENTO)");
    }

    @Override
    public void startElement(String uri, String nombreLocal, String nombreCualif, Attributes atributos) {
        nivel++;
        indenta();
        ps.println("ELEMENTO(" + nombreCualif + ")");
    }

    @Override
    public void endElement(String uri, String nombreLocal, String nombreCualif) {
        nivel--;
    }

    @Override
    public void characters(char[] cars, int inicio, int longitud) {
        String texto = new String(cars, inicio, longitud);
        nivel++;
        indenta();
        nivel--;
        ps.println("TEXTO(" + texto + ")");
    }

    @Override
    public void error(SAXParseException e) throws SAXParseException {
        System.err.println("Error recuperable: " + e.toString());
        throw (e);
    }

    @Override
    public void fatalError(SAXParseException e) throws SAXParseException {
        System.err.println("Error no recuperable: " + e.toString());
        throw (e);
    }

    @Override
    public void warning(SAXParseException e) throws SAXParseException {
        System.err.println("Aviso: " + e.toString());
        throw (e);
    }
}

public class SAXParserValid {

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
        try {
            SAXParserFactory spf = SAXParserFactory.newInstance();
            System.out.println("Fichero XML: " + f.getAbsolutePath());
            if (fEsq != null) {    // Validar con esquema
                System.out.println("Validación con esquema: "
                        + fEsq.getAbsolutePath()
                );
                try {
                    spf.setSchema(
                            SchemaFactory.newInstance(
                                    XMLConstants.W3C_XML_SCHEMA_NS_URI).newSchema(fEsq)
                    );
                } catch (SAXException e) {
                    System.err.println(e.getMessage());
                    return;
                }
            } else { // Validar con DTD
                System.out.println("Validación con DTD");
                spf.setValidating(true);
            }

            SAXParser parserSAX = spf.newSAXParser();
            XMLReader xmlReader = parserSAX.getXMLReader();
            GestorEventos gestorEventos = new GestorEventos(System.out);
            xmlReader.setContentHandler(gestorEventos);
            xmlReader.setErrorHandler(gestorEventos);
            xmlReader.parse(f.getAbsolutePath());

        } catch (SAXException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
