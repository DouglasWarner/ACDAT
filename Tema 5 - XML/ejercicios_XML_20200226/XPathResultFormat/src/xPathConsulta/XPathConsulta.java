package xPathConsulta;

/**
 * 
 * En este programa se obtiene la lista de resultados como un NodeSet
 * (con XPathConstants.NODESET) y se utiliza un método muestraNodo para mostrar
 * todos los contenidos de cada nodo obtenido por un XPath.
 * Se incluye comentado el código para las otras formas de obtener los resultados
 * (XPathConstants.NODE, XPathConstants.STRING, XPathConstants.NUMBER,
 * XPathConstants.BOOLEAN) y para mostrar los resultados.
 * 
 */

import java.io.File;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPathConstants;

public class XPathConsulta {

    private static final String INDENT_NIVEL = "  ";  // Para indentación

    public static void muestraNodo(Node nodo, int nivel, boolean noTextoVacio, PrintStream ps) {
        for (int i = 0; i < nivel; i++) {
            ps.print(INDENT_NIVEL);
        }
        switch (nodo.getNodeType()) {  // Escribe información de nodo según tipo
            case Node.DOCUMENT_NODE:     // Documento
                ps.println("DOCUMENTO");
                break;
            case Node.ELEMENT_NODE:      // Elemento
                ps.println("ELEMENTO(" + nodo.getNodeName() + ")");
                break;
            case Node.TEXT_NODE:         // Texto
                ps.println(nodo.getNodeName() + "[" + nodo.getNodeValue() + "]");
                break;
        }
        NodeList nodosHijos = nodo.getChildNodes();    // Nodos hijos
        for (int i = 0; i < nodosHijos.getLength(); i++) {
            Node nodoHijo = nodosHijos.item(i);
            if (noTextoVacio && nodoHijo.getNodeType() == Node.TEXT_NODE) {
                String text = nodoHijo.getNodeValue();
                if (text.trim().length() == 0) {
                    continue;
                }
            }
            muestraNodo(nodoHijo, nivel + 1, noTextoVacio, ps);
        }
    }

    public static void main(String[] args) {

        String nomFich, xPath = "";
        File fXML;

        if (args.length < 1) {
            System.out.println("Por favor, indicar nombre de fichero.");
            return;
        } else {
            nomFich = args[0];
            System.out.println("Fichero XML: " + nomFich);
            if (args.length < 2) {
                System.out.print("Introducir XPath: ");
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                try {
                    xPath = br.readLine();
                } catch (Exception e) {
                }
            } else {
                xPath = args[1];
            }
        }

        fXML = new File(nomFich);
        if (!fXML.isFile()) {
            System.err.println("Fichero " + nomFich + " no existe.");
            return;
        }

        System.out.println("Fichero XML: " + fXML.getAbsolutePath() + " XPath: " + xPath);

        javax.xml.namespace.QName modo = XPathConstants.NODESET;
//        javax.xml.namespace.QName modo = XPathConstants.NODE;
//        javax.xml.namespace.QName modo = XPathConstants.STRING;
//        javax.xml.namespace.QName modo = XPathConstants.NUMBER;
//        javax.xml.namespace.QName modo = XPathConstants.BOOLEAN;
        
        try {

            Document docXML = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(fXML);
            XPathExpression xPathExp = XPathFactory.newInstance().newXPath().compile(xPath);

            Object resultados = xPathExp.evaluate(docXML, modo);

            if (modo == XPathConstants.NODESET) {
                System.out.println("Modo: XPathConstants.NODESET");
                System.out.println("-----------------------------");
            } else if (modo == XPathConstants.NODE) {
                System.out.println("Modo: XPathConstants.NODE");
                System.out.println("-------------------------");
            } else if (modo == XPathConstants.STRING) {
                System.out.println("Modo: XPathConstants.STRING");
                System.out.println("---------------------------");
            } else if (modo == XPathConstants.NUMBER) {
                System.out.println("Modo: XPathConstants.NUMBER");
                System.out.println("---------------------------");
            } else if (modo == XPathConstants.BOOLEAN) {
                System.out.println("Modo: XPathConstants.BOOLEAN");
                System.out.println("----------------------------");
            }

            if (resultados == null) {
                System.out.println("Devuelto valor nulo");
                return;
            }

            System.out.println("Valor devuelto de tipo: " + resultados.getClass().toString());
            System.out.println("Valor devuelto (toString): " + resultados.toString());
            System.out.println("-------- RESULTADOS --------");

            if (modo == XPathConstants.NODESET) {
                NodeList nodeListResult = (NodeList) resultados;
                for (int i = 0; i < nodeListResult.getLength(); i++) {
                    // System.out.printf("[%d]: %s\n", i + 1, resultados.item(i).getTextContent());
                    System.out.printf("[%d]\n", i + 1);
                    muestraNodo(nodeListResult.item(i), 0, true, System.out);
                }
                if (nodeListResult.getLength() < 1) {
                    System.out.println("No se obtuvo ningún resultado.");
                }
            } else if (modo == XPathConstants.NODE) {
                muestraNodo((Node) resultados, 0, true, System.out);
            } else if (modo == XPathConstants.STRING) {
                System.out.println(resultados);
            } else if (modo == XPathConstants.NUMBER) {
                System.out.println(resultados);
            } else if (modo == XPathConstants.BOOLEAN) {
                System.out.println(resultados);
            }

        } catch (SAXException | XPathException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
