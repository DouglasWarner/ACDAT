package listadodirectorio;

import java.io.File;

public class ListadoDirectorio {
  
    public static void main(String[] args) {        
        String ruta=".";
        if(args.length>=1) ruta=args[0];
        File fich=new File(ruta);        
        if(!fich.exists()) {
            System.out.println("No existe el fichero o directorio ("+ruta+").");
        }
        else {
            if(fich.isFile()) {
                System.out.println(ruta+" es un fichero.");
            }
            else {
                System.out.println(ruta+" es un directorio. Contenidos: ");
                File[] ficheros=fich.listFiles(); // Ojo, ficheros o directorios
                for(File f : ficheros) {
                    String textoDescr=f.isDirectory() ? "/" :
                            f.isFile() ? "_" : "?";
                    System.out.println("("+textoDescr+") "+f.getName());
                }
            }
        }
    }
}
