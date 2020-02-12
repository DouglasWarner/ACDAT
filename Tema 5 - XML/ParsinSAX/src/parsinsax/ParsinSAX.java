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
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author douglas
 */
public class ParsinSAX {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
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
        super.startDocument(); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes); //To change body of generated methods, choose Tools | Templates.
    }
    

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName); //To change body of generated methods, choose Tools | Templates.
    }    

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length); //To change body of generated methods, choose Tools | Templates.
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
