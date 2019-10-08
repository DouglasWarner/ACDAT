package plantillasxsl;

import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import javax.xml.transform.Source;
import javax.xml.transform.Result;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.TransformerException;

public class PlantillasXSL {

  public static void main(String[] args) {

    String fXML = "clientes.xml";
    String fXSLHTML = "clientes_HTML.xsl";
    String fXSLCSV = "clientes_CSV.xsl";
    String fHTML = "clientes.html";
    String fCSV = "clientes.csv";
    
    try {
     
      Source sXML = new StreamSource(fXML);  // Documento fuente XML
      
      Source sXSLHTML = new StreamSource(fXSLHTML);  // Plantilla para HTML
      Result osHTML = new StreamResult(new FileOutputStream(fHTML));  // Salida en HTML
      
      Transformer tHTML = TransformerFactory.newInstance().newTransformer(sXSLHTML);
      tHTML.transform(sXML, osHTML);
      
      Source sXSLCSV = new StreamSource(fXSLCSV);  // Plantilla para CSV
      Result osCSV = new StreamResult(new FileOutputStream(fCSV));  // Salida en CSV
      
      Transformer tCSV = TransformerFactory.newInstance().newTransformer(sXSLCSV);
      tCSV.transform(sXML, osCSV);

    } catch (FileNotFoundException | TransformerException e) {
      System.err.println(e.getMessage());
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

}

