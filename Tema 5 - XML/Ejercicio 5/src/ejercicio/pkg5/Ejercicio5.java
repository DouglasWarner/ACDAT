/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio.pkg5;

import java.io.PrintStream;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 *
 * @author metho
 */
public class Ejercicio5 {

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
    private final PrintStream ps;
    private final static int MAX_SEGMENTOS = 20;
    private int segmentoActual = 0;
    private String textoES;
    private String textoEN;
    private LinkedHashMap<String, String> idiomas;
    private String idiomaOriginal;
    private String idiomaActual;
    private boolean enSegmento;
    
    public GestorEventos(PrintStream ps) {
        this.ps = ps;
        idiomas = new LinkedHashMap<>();
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        
        if(localName.equals("tu"))
        {
            System.out.println("["+ ++segmentoActual +"]");
            for (Entry<String, String> idioma : idiomas.entrySet()) {
                //System.out.println(idioma.getKey() + " " + idioma.getValue());
                System.out.println((idiomaOriginal.equals(idioma.getKey()) ? "*" : "") + idioma.getKey() + "=>" + idioma.getValue());
            }
            
            idiomas = new LinkedHashMap<>();
        }
        
        if(localName.equals("seg"))
            enSegmento = !enSegmento;
        
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String texto = new String(ch,start,length);
        if(texto.trim().length() == 0)
            return;
        
        if(enSegmento)
        {
            idiomas.put(idiomaActual, texto);
        }
    }
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if(localName.equals("header"))
            idiomaOriginal = attributes.getValue("srclang");
        
        if(localName.equals("tuv"))
            idiomaActual = attributes.getValue("xml:lang");
                    
        if(localName.equals("seg"))
            enSegmento = !enSegmento;
        
        if(segmentoActual == MAX_SEGMENTOS)
            throw(new FinDeConsulta("Fin de consulta"));   
    }
    
    @Override
    public void startDocument() throws SAXException {
        segmentoActual = 0;
    }

    @Override
    public void fatalError(SAXParseException e) throws SAXException {
        System.err.print(e);
        throw(e);
    }

    @Override
    public void error(SAXParseException e) throws SAXException {
        System.err.print(e);
        throw(e);
    }

    @Override
    public void warning(SAXParseException e) throws SAXException {
        System.err.print(e);
        throw(e);
    }
}


class FinDeConsulta extends SAXException
{
    public FinDeConsulta(String message) {
        super(message);
    }
}