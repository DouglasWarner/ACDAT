package jaxbclientscrear;

import java.io.File;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.JAXBException;

public class JAXBClientsCrear {

  public static void main(String[] args) {

    File f = null;
    if (args.length < 1) {
      System.out.println("Indicar por favor nombre de fichero");
      return;
    } else {
      String nomFich = args[0];
      f = new File(nomFich);
      if (f.isFile()) {
        System.err.println("Fichero " + nomFich + " existe, no se hace nada");
        return;
      }
    }

    // Crea objeto Clientes con datos de varios clientes
    clientes_JAXB.Clientes clientes = new clientes_JAXB.Clientes();
    List<clientes_JAXB.Cliente> listaClientes = clientes.getCliente();

    clientes_JAXB.Cliente cliente = new clientes_JAXB.Cliente();
    cliente.setDNI("78901234X");
    cliente.setApellidos("NADALES");
    cliente.setCP("44126");

    listaClientes.add(cliente);

    cliente = new clientes_JAXB.Cliente();
    cliente.setDNI("89012345E");
    cliente.setApellidos("ROJAS");

    clientes_JAXB.Validez validez = new clientes_JAXB.Validez();
    validez.setEstado(clientes_JAXB.TipoEstado.VIGENTE);
    validez.setTimestamp("1528286082");
    cliente.setValidez(validez);

    listaClientes.add(cliente);

    try {
      // Crea contexto para objeto de tipo Clientes
      JAXBContext contextoClientes = JAXBContext.newInstance(
              clientes_JAXB.Clientes.class
      );

      Marshaller m = contextoClientes.createMarshaller();  // Crea marshaller

      // Configura marshaller: salida formateada
      m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

      m.marshal(clientes, f);  // Marshalling en objeto

      System.out.println("Generado fichero XML: " + f.getAbsolutePath());

    } catch (JAXBException e) {
      System.err.println(e.getMessage());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}