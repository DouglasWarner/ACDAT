/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fichaccesoalea_estructuraderegistro;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author douglas
 */
public class FichAccesoAlea_EstructuraDeRegistro {

    private final File f;
    private final List<Pair<String, Integer>> campos;
    private long longReg;
    private long numReg = 0;

    FichAccesoAlea_EstructuraDeRegistro(String nomFich, List<Pair<String, Integer>> campos) throws IOException {
        this.campos = campos;
        this.f = new File(nomFich);
        longReg = 0;
        for (Pair<String, Integer> campo : campos) {
            this.longReg += campo.getValue();
        }
        if (f.exists()) {
            this.numReg = f.length() / this.longReg;
        }
    }
    
    public long getNumReg() {
        return numReg;
    }

    public void insertar(Map<String, String> reg) throws IOException {
        insertar(reg, this.numReg++);
    }

    public void insertar(Map<String, String> reg, long pos) throws IOException {
        try (RandomAccessFile faa = new RandomAccessFile(f, "rws")) {
            faa.seek(pos * this.longReg);
            for (Pair<String, Integer> campo : this.campos) {
                String nomCampo = campo.getKey();
                Integer longCampo = campo.getValue();
                String valorCampo = reg.get(nomCampo);
                if (valorCampo == null) {
                    valorCampo = "";
                }
                String valorCampoForm = String.format("%1$-" + longCampo + "s",valorCampo);
                faa.write(valorCampoForm.getBytes("UTF-8"), 0, longCampo);
            }
        }
    }
    /*public String buscar(int pos, String nomCampo) throws IOException {
        try (RandomAccessFile faa = new RandomAccessFile(f, "rws")) {
            int offsetCampo = 0;
            int longCampo = 0;
            int valorCampo = 0;
            for (Pair<String, Integer> campo : this.campos) {
                if(campo.getKey() == nomCampo)
                {
                    valorCampo = campo.getValue();
                }
                longCampo += campo.getValue();
            }
            
            //byte[] buffer = new byte[valorCampo];
            //int i = faa.read(buffer, offsetCampo, valorCampo);
            
            //String valorFormat = new String(buffer, "UTF-8");
            //return valorFormat;
        }
    }*/
    public String buscar(String valorClave, String nomCampo) throws IOException
    {
        int longClave;
        int offsetCampo = 0;
        int longCampo = 0;
        int offset = 0;
        boolean primeraIt = true;
        for(Pair<String, Integer> campo : this.campos)
        {
            if(primeraIt) {
                longClave = campo.getValue();
            } 
            else if(campo.getKey() == nomCampo) 
            {
                  offsetCampo = longCampo;
            }
            offset += campo.getValue();
            longCampo = offset;
            primeraIt = false;
        }
            
        try (RandomAccessFile faa = new RandomAccessFile(f, "rws"))
        {
            byte[] buffer = new byte[offsetCampo];
            faa.seek(offsetCampo);
            faa.read(buffer);
            String result = new String(buffer, "UTF-8");
            return result;
        }
    }

    public static void main(String[] args) {

        List campos = new ArrayList();
        campos.add(new Pair("numeroEmpleado", 6));
        campos.add(new Pair("dni", 9));
        campos.add(new Pair("nombre", 32));

        try {
            FichAccesoAlea_EstructuraDeRegistro faa = new FichAccesoAlea_EstructuraDeRegistro("fic_acceso_aleat.dat", campos);
            Map reg = new HashMap();
            reg.put("numeroEmpleado", "001");
            reg.put("dni", "56789012B");
            reg.put("nombre", "SAMPER");
            faa.insertar(reg);
            reg.clear();
            reg.put("numeroEmpleado", "002");
            reg.put("dni", "89012345E");
            reg.put("nombre", "DOUGLAS");
            faa.insertar(reg);
            reg.clear();
            reg.put("numeroEmpleado", "003");
            reg.put("dni", "23456789D");
            reg.put("nombre", "DORCE");            
            faa.insertar(reg);
            reg.clear();
            reg.put("numeroEmpleado", "004");
            reg.put("dni", "78901234X");
            reg.put("nombre", "NADALES");          
            faa.insertar(reg, 1);
            
            String buscado = faa.buscar("nombre","NADALES");
            System.out.println(buscado);
    //     faa.insertar(reg,25);  // Probarlo, interesante
        } catch (IOException e) {
            System.err.println("Error de E/S: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

// Pair class
class Pair<U, V>
{
	public final U key;	// first field of a Pair
	public final V value; 	// second field of a Pair

	// Constructs a new Pair with specified values
	public Pair(U key, V value)
	{
		this.key = key;
		this.value = value;
	}

	@Override
	// Checks specified object is "equal to" current object or not
	public boolean equals(Object o)
	{
		if (this == o)
			return true;

		if (o == null || getClass() != o.getClass())
			return false;

		Pair<?, ?> pair = (Pair<?, ?>) o;

		// call equals() method of the underlying objects
		if (!key.equals(pair.key))
			return false;
		return value.equals(pair.value);
	}

	@Override
	// Computes hash code for an object to support hash tables
	public int hashCode()
	{
		// use hash codes of the underlying objects
		return 31 * key.hashCode() + value.hashCode();
	}

	@Override
	public String toString()
	{
		return "(" + key + ", " + value + ")";
	}
        
        // Devuelve la clave
	public U getKey()
        {
            return this.key;
        }
        
        // Devuelve el valor
        public V getValue()
        {
            return this.value;
        }
}


