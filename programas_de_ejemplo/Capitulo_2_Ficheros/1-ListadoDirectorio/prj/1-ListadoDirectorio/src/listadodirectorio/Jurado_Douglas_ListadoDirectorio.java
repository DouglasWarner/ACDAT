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
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String[] unidades = {"byte", "k", "m", "g"};
		

                for(File f : ficheros) {
                    String textoDescr=f.isDirectory() ? "/" :
                            f.isFile() ? "_" : "?";
		    int longitud = 0;
		    long tamanio = f.length() / 1024;
    
		    while(tamanio > 0)
		    {
			tamanio = f.length() / 1024;
			longitud ++;
   		    }

                    System.out.println(String.format("(%s) %s %s %s %s %d %s &s",textoDescr, f.CanRead() ? "r" : "-", f.CanWrite() ? "w" : "-", f.CanExecute() ? "x" : "-", f.getName(), f.length(), unidades[longitud], formato.format(f.lastModified())));
                }
            }
        }
    }
}
