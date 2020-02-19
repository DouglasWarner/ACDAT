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
                        
            GetNCliente(docDOM);
            
            // Otra manera de obtener la cantidad de clientes que hay
            //System.out.println(docDOM.getElementsByTagName("cliente").getLength());
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
    
   public static void GetNCliente(Node n)
   {
       int numClientes = 0;
       
       for (int i = 0; i < n.getChildNodes().getLength(); i++) 
            {
                Node nodo = n.getChildNodes().item(i);
                
                if(nodo.getNodeType() == Node.ELEMENT_NODE)
                {
                    if(nodo.getNodeName() == "cliente")
                    {
                        System.out.print("Cliente: [" + ++numClientes + "] Atributos: ");
                        NamedNodeMap atributos = nodo.getAttributes();
                        for (int j = 0; j < atributos.getLength(); j++) {
                            System.out.print(atributos.item(j).getNodeName() + "[ "+ atributos.item(j).getNodeValue() + " ]");
                        }
                        System.out.println();
                    }
                }
                
                GetNCliente(nodo);
            }
   }
    
}
