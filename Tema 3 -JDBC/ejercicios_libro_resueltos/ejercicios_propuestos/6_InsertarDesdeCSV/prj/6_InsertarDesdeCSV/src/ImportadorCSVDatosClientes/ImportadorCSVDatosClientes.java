package ImportadorCSVDatosClientes;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ImportadorCSVDatosClientes {

    static final String BASEDATOS = "libro_ad";
    static final String HOST = "localhost";
    static final String PORT = "3306";
    static final String PAR_ADIC = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    static final String CONNECTION_URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + BASEDATOS + PAR_ADIC;
    static final String USER = "libro_ad";
    static final String PWD = "libro_ad";
    static Connection con = null;

    static final String NOM_TABLA = "CLIENTES";
    static final int NUM_CAMPOS = 3;

    private static void muestraErrorSQL(SQLException e) {
        System.err.println("SQL ERROR mensaje: " + e.getMessage());
        System.err.println("SQL Estado: " + e.getSQLState());
        System.err.println("SQL código específico: " + e.getErrorCode());
    }

    /**
     * Solo se usa una conexión, que es una variable estática de clase, y se
     * crea la primera vez que se llama a este método.
     *
     * @return Conexión
     * @throws SQLException
     */
    private static Connection obtenConexion() throws SQLException {
        if (con == null) {
            con = DriverManager.getConnection(CONNECTION_URL, USER, PWD);
        }
        return con;
    }

    public static int insertaDatosClientes(String nombreFichero, String separadorCampos) throws IOException, SQLException {

        int nLin = 0;

        try (BufferedReader fbr = new BufferedReader(new FileReader(nombreFichero));
                Connection c = obtenConexion()) {
            String sqlInsUpd = "INSERT INTO CLIENTES(DNI,APELLIDOS,CP) VALUES(?,?,?) ON DUPLICATE KEY UPDATE APELLIDOS=?,CP=?";

            try (PreparedStatement sInsUpd = c.prepareStatement(sqlInsUpd)) {

                con.setAutoCommit(false);
                String linea;
                while ((linea = fbr.readLine()) != null) {
                    nLin++;
                    System.out.print("Leida línea " + nLin + ":" + linea + " ... ");
                    String campos[] = linea.split(separadorCampos);

                    for (int i = 0; i < campos.length; i++) {
                        System.out.print("Campo " + i + ":[" + campos[i] + "] ");
                    }

                    System.out.print(" ... ");

                    if (campos.length > NUM_CAMPOS) {
                        System.err.println("ERROR: línea " + nLin + " tiene " + campos.length + " campos en lugar de " + NUM_CAMPOS);
                        throw new SQLException("ERROR: línea " + nLin + " tiene " + campos.length + " campos en lugar de " + NUM_CAMPOS);
                    }

                    sInsUpd.setString(1, (campos[0].length()>0 ? campos[0] : null));  // DNI
                    sInsUpd.setString(2, (campos.length > 1 && campos[1].length()>0 ? campos[1] : null));  // APELLIDOS
                    sInsUpd.setString(3, (campos.length > 2 && campos[2].length()>0 ? campos[2] : null));  // CP
                    sInsUpd.setString(4, (campos.length > 1 && campos[1].length()>0 ? campos[1] : null));  // APELLIDOS
                    sInsUpd.setString(5, (campos.length > 2 && campos[2].length()>0 ? campos[2] : null));  // CP
                    sInsUpd.executeUpdate();
                    System.out.println("Grabada en base de datos.");
                }
                c.commit();
            } catch (SQLException e) {
                muestraErrorSQL(e);
                try {
                    con.rollback();
                    System.err.println("Se hace ROLLBACK");
                } catch (SQLException er) {
                    System.err.println("ERROR haciendo ROLLBACK");
                    muestraErrorSQL(er);
                }
                throw (e);
            }
        }
        return nLin;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        String sepCampos = "\\|";

        // Lee nombre de fichero desde línea de comandos y lee línea a línea del
        // fichero seleccionado. Igual que en programa de ejemplo de tema ficheros
        if (args.length < 1) {
            System.err.println("Indicar por favor nombre de fichero.");
            return;
        }
        String nomFich = args[0];

        try {
            insertaDatosClientes(nomFich, sepCampos);
        } catch (FileNotFoundException e) {
            System.err.println("No existe fichero " + nomFich);
        } catch (IOException e) {
            System.err.println("Error de E / S: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Error grabando datos en la base de datos");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
