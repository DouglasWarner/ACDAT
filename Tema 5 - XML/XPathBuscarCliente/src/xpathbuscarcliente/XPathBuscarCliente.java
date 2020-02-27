/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xpathbuscarcliente;

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
public class XPathBuscarCliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String nomFich;
        String consultaDNI;
        File fichero;
        
        if(args.length < 1)
        {
            System.out.println("Indica el nombre del fichero XML");
            return;
        }
        if(args.length < 2)
        {
            System.out.println("Indica el dni del cliente a buscar");
            return;
        }
        
        nomFich = args[0];
        consultaDNI = "//cliente[@DNI=\""+args[1]+"\"]";
        
        fichero = new File(nomFich);
        if(!fichero.exists())
        {
            System.out.println("El fichero no existe");
            return;
        }
        
        System.out.printf("DNI de cliente: %s\n", args[1]);
        
        try
        {
           Document docXML = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(fichero);
           XPathExpression xPathExp = XPathFactory.newInstance().newXPath().compile(consultaDNI);
           NodeList resultados = (NodeList) xPathExp.evaluate(docXML, XPathConstants.NODESET);
           
           System.out.println(resultados.item(0).getTextContent());
           
           if(resultados.getLength() < 1)
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
    
    
}
