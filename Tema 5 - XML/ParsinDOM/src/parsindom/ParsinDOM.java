/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parsindom;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author douglas
 */
public class ParsinDOM {

    /**
     * CONTAR CLIENTES EN EL DOCUMENTOS XML
     */
    public static void main(String[] args) {
        String nomFichero;
        if(args.length < 1)
        {
            System.out.println("Indica el nombre del fichero XML");
            return;
        }
        
        nomFichero = args[0];
        
        DocumentBuilderFactory documento = DocumentBuilderFactory.newInstance();
        documento.setIgnoringComments(true);
        documento.setIgnoringElementContentWhitespace(true);
        
        try
        {
            DocumentBuilder db = documento.newDocumentBuilder();
            Document docDOM = db.parse(new File(nomFichero));
            int numClientes = 0;
            NodeList nodos = docDOM.getChildNodes();
            
            for (int i = 0; i < nodos.getLength(); i++) {
            if(docDOM.getNodeType() == Node.ELEMENT_NODE)
            {
                if(docDOM.getNodeName() == "cliente")
                {
                    System.out.print("Cliente: [" + numClientes++ + "] Atributos: ");
                    NamedNodeMap atributos = docDOM.getAttributes();
                    for (int j = 0; j < atributos.getLength(); j++) {
                        System.out.print(atributos.item(j).getNodeName() + "[ "+ atributos.item(j).getNodeValue() + " ]");
                    }
                    System.out.println();
                }
            }
            }
            
            System.out.println(docDOM.getElementsByTagName("cliente").getLength());
        }
        catch (FileNotFoundException | ParserConfigurationException | SAXException e)
        {
            System.err.println(e.getMessage());
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
        }
    }
    
    private static final String indent_nivel = " ";
    public static void MuestraNodo(Node nodo, int nivel, PrintStream ps)
    {
        if (nodo.getNodeType() == Node.TEXT_NODE) {
            if(nodo.getNodeValue().trim().length() == 0) {
                ;
            }            
        }
        
        for (int i = 0; i < nivel; i++) {
            ps.print(indent_nivel);
        }
        
        int numClientes = 0;
        if(nodo.getNodeType() == Node.ELEMENT_NODE)
        {
            if(nodo.getNodeName() == "cliente")
            {
                ps.print("Cliente: [" + numClientes++ + "] Atributos: ");
                NamedNodeMap atributos = nodo.getAttributes();
                for (int i = 0; i < atributos.getLength(); i++) {
                    ps.print(atributos.item(i).getNodeName() + "[ "+ atributos.item(i).getNodeValue() + " ]");
                }
                ps.println();
            }
        }
        switch(nodo.getNodeType())
        {
            case Node.DOCUMENT_NODE:
                Document doc = (Document)nodo;
                ps.println("Documento DOM, versión: "+doc.getXmlVersion()+", codificacion: "+ doc.getXmlEncoding());
                break;
            case Node.ELEMENT_NODE:
                ps.print("<" + nodo.getNodeName());
                NamedNodeMap atributos = nodo.getAttributes();
                for (int i = 0; i < atributos.getLength(); i++) {
                    ps.print(atributos.item(i).getNodeName() + "[ "+ atributos.item(i).getNodeValue() + " ]");
                }
                ps.println(">");
                break;
            case Node.TEXT_NODE:
                ps.println(nodo.getNodeName() + " [ " + nodo.getNodeValue() + " ] ");
                break;
            default:
                ps.println("Nodo de tipo: " + nodo.getNodeType());
                break;
        }        
    }
    
}
