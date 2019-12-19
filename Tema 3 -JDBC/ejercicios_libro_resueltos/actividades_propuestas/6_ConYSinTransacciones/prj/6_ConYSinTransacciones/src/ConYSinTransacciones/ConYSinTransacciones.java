package ConYSinTransacciones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConYSinTransacciones {

    public static void muestraErrorSQL(SQLException e) {
        System.err.println("SQL ERROR mensaje: " + e.getMessage());
        System.err.println("SQL Estado: " + e.getSQLState());
        System.err.println("SQL código específico: " + e.getErrorCode());
    }

    /**
     * Crea datos iniciales, de manera que al ejecutarse las sentencias se
     * produzca un error en la tercera sentencia, y no en las anteriores.
     *
     * @param c Conexión
     *
     */
    public static void inicializaDatos(Connection c) {
        try (
                PreparedStatement sDelete = c.prepareStatement("DELETE FROM CLIENTES1 WHERE DNI=?");
                PreparedStatement sInsert = c.prepareStatement("INSERT INTO CLIENTES1(DNI,APELLIDOS,CP) VALUES (?,?,?)")) {

            sDelete.setString(1, "54320198V");
            sDelete.executeUpdate();

            sDelete.setString(1, "76543210S");
            sDelete.executeUpdate();

            sDelete.setString(1, "90123456A");
            sDelete.executeUpdate();

            int i = 0;
            sInsert.setString(++i, "90123456A");
            sInsert.setString(++i, "SALINAS");
            sInsert.setString(++i, "50676");
            sInsert.executeUpdate();

        } catch (SQLException e) {
            System.err.println("ERROR inicializando datos");
            muestraErrorSQL(e);
        }
    }

    public static void insertaDatosConTransaccion(Connection c) {

        try (
                PreparedStatement sInsert = c.prepareStatement("INSERT INTO CLIENTES1(DNI,APELLIDOS,CP) VALUES (?,?,?)")) {

            c.setAutoCommit(false);

            int i = 0;
            sInsert.setString(++i, "54320198V");
            sInsert.setString(++i, "CARVAJAL");
            sInsert.setString(++i, "10109");
            sInsert.executeUpdate();

            sInsert.setString(i = 1, "76543210S");
            sInsert.setString(++i, "MARQUEZ");
            sInsert.setString(++i, "46987");
            sInsert.executeUpdate();

            sInsert.setString(i = 1, "90123456A");
            sInsert.setString(++i, "MOLINA");
            sInsert.setString(++i, "35153");
            sInsert.executeUpdate();

            c.commit();

        } catch (SQLException e) {
            System.err.println("ERROR insertando datos con transacciones");
            muestraErrorSQL(e);
            try {
                c.rollback();
                System.err.println("Se hace ROLLBACK");
            } catch (SQLException er) {
                System.err.println("ERROR haciendo ROLLBACK");
                muestraErrorSQL(er);
            }
        }
    }
    
    public static void insertaDatosSinTransaccion(Connection c) {

        try (
                PreparedStatement sInsert = c.prepareStatement("INSERT INTO CLIENTES1(DNI,APELLIDOS,CP) VALUES (?,?,?)")) {

            c.setAutoCommit(true);

            int i = 0;
            sInsert.setString(++i, "54320198V");
            sInsert.setString(++i, "CARVAJAL");
            sInsert.setString(++i, "10109");
            sInsert.executeUpdate();

            sInsert.setString(i = 1, "76543210S");
            sInsert.setString(++i, "MARQUEZ");
            sInsert.setString(++i, "46987");
            sInsert.executeUpdate();

            sInsert.setString(i = 1, "90123456A");
            sInsert.setString(++i, "MOLINA");
            sInsert.setString(++i, "35153");
            sInsert.executeUpdate();

        } catch (SQLException e) {
            System.err.println("ERROR insertando datos sin transacciones");
            muestraErrorSQL(e);
        }

    }

    public static void main(String[] args) {

        String basedatos = "libro_ad";
        String host = "localhost";
        String port = "3306";
        String parAdic = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String urlConnection = "jdbc:mysql://" + host + ":" + port + "/" + basedatos + parAdic;
        String user = "libro_ad";
        String pwd = "libro_ad";

        try (
                Connection c = DriverManager.getConnection(urlConnection, user, pwd)) {
            
            inicializaDatos(c);
            //insertaDatosConTransaccion(c);
            insertaDatosSinTransaccion(c);
            
        } catch (Exception e) {
            System.err.println("ERROR de conexión");
            e.printStackTrace(System.err);
        }
    }

}
