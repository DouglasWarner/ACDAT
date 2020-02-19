/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parsinsax;

import java.io.PrintStream;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 *
 * @author douglas
 */
public class ParsinSAX {

    /**
     * @param args the command line arguments
     */
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

class GestorEventos extends DefaultHandler
{
    String ident = " ";
    PrintStream ps;
    int nivel;
    
    private void Identa()
    {
        for (int i = 0; i < nivel; i++) {
            ps.print(ident);
        }
    }
    
    public GestorEventos(PrintStream ps) {
        this.ps = ps;
    }
    
    @Override
    public void startDocument() throws SAXException {
        nivel = 0; 
    }
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        nivel++;
        Identa();
        ps.print("<" + qName);
        for (int i = 0; i < attributes.getLength(); i++) {
            ps.print(" @" + attributes.getLocalName(i) + "[" + attributes.getValue(i) + "]");
        }
        ps.println(">");
    }
    

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        nivel--;
    }    

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String texto = new String(ch, start, length);
        if(texto.trim().length() == 0)
            return;
        nivel++;
        Identa();
        nivel--;
        ps.println("#text["+texto+"]");
        
    }

    @Override
    public void fatalError(SAXParseException e) throws SAXParseException {
        System.err.print(e);
        throw(e);
    }

    @Override
    public void error(SAXParseException e) throws SAXParseException {
        System.err.print(e);
        throw(e);
    }

    @Override
    public void warning(SAXParseException e) throws SAXParseException {
        System.err.print(e);
        throw(e);
    }
    
}
