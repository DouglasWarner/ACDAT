/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parsinsaxbuscarcliente;

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
public class ParsinSAXBuscarCliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String nomFich;
        String dni;
        if (args.length < 2) {
            System.out.println("Indicar por favor nombre de fichero");
            return;
        } else {
            nomFich = args[0];
            dni = args[1];
        }
        try {
            XMLReader parserSAX = XMLReaderFactory.createXMLReader();
            GestorEventos gestorEventos = new GestorEventos(System.out, args[1]);
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
    private final PrintStream ps;
    private int numClientes;
    private String dniCliente;
        
    public GestorEventos(PrintStream ps, String dni) {
        this.ps = ps;
        dniCliente = dni;
    }
    
    @Override
    public void startDocument() throws SAXException {
        numClientes = 0; 
    }
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        
        if(qName.equals("cliente"))
        {
            String tmp = attributes.getValue("DNI");
            if(tmp.equals(dniCliente))
                System.out.println("Encontrado cliente => " +tmp);
        }
        
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println(numClientes);
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
