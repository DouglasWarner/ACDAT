package jaxbclientes;

import java.util.List;
import java.io.File;
import javax.xml.XMLConstants;
import javax.xml.validation.SchemaFactory;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.JAXBException;
import javax.xml.bind.UnmarshalException;
import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;

public class JAXBClientes {

    public static void main(String[] args) {

        File f = null, fEsq = null;

        if (args.length < 1) {
            System.out.println("Indicar por favor nombre de fichero");
            return;
        } else {
            String nomFich = args[0];
            f = new File(nomFich);
            if (!f.isFile()) {
                System.err.println("Fichero " + nomFich + " no existe.");
                return;
            }
            if (args.length >= 2) {
                String nomFichEsquema = args[1];
                fEsq = new File(nomFichEsquema);
                if (!fEsq.isFile()) {
                    System.err.println("Fichero " + nomFichEsquema + " no existe.");
                    return;
                }
            }
        }

        System.out.println("Fichero XML: " + f.getAbsolutePath());
        if (fEsq != null) {    // Validar con esquema
            System.out.println("Validación con esquema: "
                    + fEsq.getAbsolutePath()
            );
        }

        try {
            JAXBContext contextoClientes = JAXBContext.newInstance(clientes_JAXB.Clientes.class);

            Unmarshaller u = contextoClientes.createUnmarshaller();

            u.setSchema(SchemaFactory.newInstance( // Esquema para validación
                    XMLConstants.W3C_XML_SCHEMA_NS_URI).newSchema(fEsq));
            u.setEventHandler(new GestorEventos());

            // Unmarshalling en objeto, incluyendo parsing y validación
            clientes_JAXB.Clientes clientes = (clientes_JAXB.Clientes) u.unmarshal(f);

            // Iteración sobre objeto para mostrar los contenidos
            List<clientes_JAXB.Cliente> listaClientes = clientes.getCliente();

            for (clientes_JAXB.Cliente cliente : listaClientes) {
                System.out.println("=> DNI: " + cliente.getDNI());
                System.out.println("Apellidos: " + cliente.getApellidos());
                String cp = cliente.getCP();
                if (cp != null) {
                    System.out.println("CP: " + cp);
                }
                clientes_JAXB.Validez validez = cliente.getValidez();
                if (validez != null) {
                    System.out.println("Validez: " + validez.getEstado());
                    String timestamp = validez.getTimestamp();
                    if (timestamp.length() > 0) {
                        System.out.println("timestamp: " + timestamp);
                    }
                }
            }

        } catch (UnmarshalException e) {
            System.err.println(e.getMessage());
        } catch (JAXBException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

class GestorEventos implements ValidationEventHandler {

    @Override
    public boolean handleEvent(ValidationEvent e) {
        System.err.println("Evento de validación (" + e.getSeverity() + ")"
                + "[línea: " + e.getLocator().getLineNumber() + ", "
                + "columna: " + e.getLocator().getColumnNumber() + "]: "
                + e.getMessage()
        );
        return (e.getSeverity() != ValidationEvent.FATAL_ERROR);
    }

}
