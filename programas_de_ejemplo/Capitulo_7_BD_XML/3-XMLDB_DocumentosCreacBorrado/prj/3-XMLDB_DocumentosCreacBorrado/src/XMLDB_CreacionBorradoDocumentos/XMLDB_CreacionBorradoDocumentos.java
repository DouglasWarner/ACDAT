package XMLDB_CreacionBorradoDocumentos;

import org.xmldb.api.base.*;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.modules.*;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import org.xml.sax.InputSource;
import org.xml.sax.ContentHandler;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import java.io.StringReader;
import java.io.StringWriter;


public class XMLDB_CreacionBorradoDocumentos {

    private static Collection obtenColeccion(String nomCol) throws Exception {
        Database dbDriver;
        Collection col;
        dbDriver = (Database) Class.forName("org.exist.xmldb.DatabaseImpl").newInstance();
        DatabaseManager.registerDatabase(dbDriver);
        col = DatabaseManager.getCollection(
                "xmldb:exist://localhost:8090/exist/xmlrpc/db" + nomCol,
                "admin", "admin");
        return col;
    }

    public static void main(String[] args) {
         Collection col = null;
        try {
            col = obtenColeccion("/pruebas");

            XMLResource recurso;

            // Crear documento a partir de String
            recurso = (XMLResource) col.createResource("Clientes.xml",
                    XMLResource.RESOURCE_TYPE);
            recurso.setContent("<clientes>\n"
                    + "<cliente DNI=\"78901234X\"><apellidos>NADALES</apellidos><CP>44126</CP></cliente>\n"
                    + "<cliente DNI=\"89012345E\"><apellidos>ROJAS</apellidos><validez estado=\"borrado\" timestamp=\"1528286082\"/></cliente>\n"
                    + "<cliente DNI=\"56789012B\">\n"
                    + "<apellidos>SAMPER</apellidos><CP>29730</CP></cliente></clientes>");
            col.storeResource(recurso);

            recurso = (XMLResource) col.createResource("Empresa_.xml",
                    XMLResource.RESOURCE_TYPE);
            recurso.setContent("<empresa CIF=\"A34246801\">MegaExport<sedes><sede>LEÓN</sede><sede>CÁCERES</sede></sedes></empresa>");
            col.storeResource(recurso);

            // Crear documento a partir de objeto SAX
            StringWriter out = new StringWriter();
            XMLOutputFactory xof = XMLOutputFactory.newInstance();
            XMLStreamWriter xsw;
            xsw = xof.createXMLStreamWriter(out);
            xsw.writeStartDocument();
            xsw.writeStartElement("empresa");
            xsw.writeAttribute("CIF", "A34246801");
            xsw.writeCharacters("MegaExport");
            xsw.writeStartElement("sedes");
            xsw.writeStartElement("sede");
            xsw.writeCharacters("LEÓN");
            xsw.writeEndElement();
            xsw.writeStartElement("sede");
            xsw.writeCharacters("CÁCERES");
            xsw.writeEndElement();
            xsw.writeEndElement();
            xsw.writeEndElement();
            xsw.writeEndDocument();
            xsw.flush();
            xsw.close();
            recurso = (XMLResource) col.createResource("DatosEmpresa",
                    XMLResource.RESOURCE_TYPE);
            ContentHandler ch = recurso.setContentAsSAX();
            XMLReader reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(ch);
            reader.parse(new InputSource(new StringReader(out.toString())));
            col.storeResource(recurso);

            col.removeResource(col.getResource("Empresa_.xml"));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (col != null) {
                    col.close();
                }
            } catch (XMLDBException xe) {
                xe.printStackTrace();
            }
        }
    }
}
