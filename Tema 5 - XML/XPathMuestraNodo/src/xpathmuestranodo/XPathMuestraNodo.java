/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xpathmuestranodo;

import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathException;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author metho
 */
public class XPathMuestraNodo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String nomFich;
        String consultaXPath;
        File fichero;
        
        if(args.length < 1)
        {
            System.out.println("Indica el nombre del fichero XML");
            return;
        }
        
        nomFich = args[0];
        consultaXPath = "/*";
        
        fichero = new File(nomFich);
        if(!fichero.exists())
        {
            System.out.println("El fichero no existe");
            return;
        }
                
        try
        {
            Document docXML = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(fichero);
            XPathExpression xPathExp = XPathFactory.newInstance().newXPath().compile(consultaXPath);
            Object resultados = xPathExp.evaluate(docXML, XPathConstants.NODESET);

            System.out.println("Valor devuelto de tipo: " + resultados.getClass().toString());
            System.out.println("Valor devuelto (toString): " + resultados.toString());
            
            NodeList listResult = (NodeList) resultados;
            
            System.out.println("Modo: XPathConstants.NODESET");
            System.out.println("-----------------------------");
            for (int i = 0; i < listResult.getLength(); i++) {
                muestraNodo(listResult.item(i));
            }
            
            
            resultados = xPathExp.evaluate(docXML, XPathConstants.NODE);
            System.out.println("Modo: XPathConstants.NODE");
            System.out.println("-------------------------");
            muestraNodo((Node) resultados);
            
            
            resultados = xPathExp.evaluate(docXML, XPathConstants.STRING);
            System.out.println("Modo: XPathConstants.STRING");
            System.out.println("---------------------------");
            System.out.println(resultados);
            
            
            resultados = xPathExp.evaluate(docXML, XPathConstants.NUMBER);
            System.out.println("Modo: XPathConstants.NUMBER");
            System.out.println("---------------------------");
            System.out.println(resultados);
            
            
            resultados = xPathExp.evaluate(docXML, XPathConstants.BOOLEAN);
            System.out.println("Modo: XPathConstants.BOOLEAN");
            System.out.println("----------------------------");
            System.out.println(resultados);
            
            
            if(listResult.getLength() < 1)
                System.out.println("No existe un cliente con ese dni.");
        }
        catch (SAXException | XPathException e) 
        {
            System.err.println(e.getMessage());
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }    
    
    public static void muestraNodo(Node n)
   {                
        switch (n.getNodeType()) 
        {
            case Node.DOCUMENT_NODE:
                Document doc = (Document) n;
                System.out.println("Documento DOM, versión: " + doc.getXmlVersion()
                        + ", codificación: " + doc.getXmlEncoding());
                break;
            case Node.ELEMENT_NODE:
                System.out.println("<" + n.getNodeName()+">");
                break;
            case Node.TEXT_NODE:
                System.out.println(n.getNodeName() + "[" + n.getNodeValue() + "]");
                break;
        }
        
        NodeList nodos = n.getChildNodes();
        for (int i = 0; i < nodos.getLength(); i++) {
            Node nodoHijo = nodos.item(i);
            if(nodoHijo.getNodeType() == Node.TEXT_NODE)
            {
                String texto = nodoHijo.getNodeValue();
                if(texto.trim().length() == 0)
                    continue;
            }    
            muestraNodo(nodoHijo);       
       }
        
        
    }
}
