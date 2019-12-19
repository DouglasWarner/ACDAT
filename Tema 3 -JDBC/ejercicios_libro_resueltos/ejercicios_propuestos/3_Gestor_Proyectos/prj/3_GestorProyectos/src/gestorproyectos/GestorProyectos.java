package gestorproyectos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.SQLException;

public class GestorProyectos {

    static final String BASEDATOS = "libro_ad";
    static final String HOST = "localhost";
    static final String PORT = "3306";
    static final String PAR_ADIC = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    static final String CONNECTION_URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + BASEDATOS + PAR_ADIC;
    static final String USER = "libro_ad";
    static final String PWD = "libro_ad";

    private static void muestraErrorSQL(SQLException e) {
        System.err.println("SQL ERROR mensaje: " + e.getMessage());
        System.err.println("SQL Estado: " + e.getSQLState());
        System.err.println("SQL código específico: " + e.getErrorCode());
    }

    private static Connection obtenConexion() throws SQLException {
        return DriverManager.getConnection(CONNECTION_URL, USER, PWD);
    }

    /**
     *
     * @param c Conexión con la base de datos
     * @param dniNif DNI o NIF de empleado
     * @param nombre Nombre de empleado
     * @return true si se creó empleado correctamente, false en caso contrario
     * @throws SQLException
     */
    public static boolean nuevoEmpleado(Connection c, String dniNif, String nombre) throws SQLException {
        boolean result = false;
        try (PreparedStatement sInsert = c.prepareStatement("INSERT INTO EMPLEADOS(DNI_NIF,NOMBRE) VALUES(?,?)")) {
            int i = 0;
            sInsert.setString(++i, dniNif);
            sInsert.setString(++i, nombre);
            sInsert.executeUpdate();
            result = true;
        } catch (SQLException e) {  // Se informa de ella pero se vuelve a lanzar
            System.err.println("Error al crear empleado (" + dniNif + "," + nombre + ")");
            throw e;
        }
        return result;
    }

    /**
     *
     * @param c Conexión con la base de datos
     * @param nombre Nombre del proyecto
     * @param dniNifJefeProy DNI jefe proyecto
     * @param fInicio Fecha de inicio. Si null, se asigna la fecha actual del
     * servidor
     * @param fFin Fecha de fin
     * @return Número de proyecto
     * @throws SQLException
     */
    public static Integer nuevoProyecto(Connection c, String nombre, String dniNifJefeProy, Date fInicio, Date fFin) throws SQLException {
        Integer result = null;
        String sentInsert
                = "INSERT INTO PROYECTOS(NOMBRE,DNI_NIF_JEFE_PROY,F_FIN,F_INICIO) "
                + " VALUES(?,?,?," + (fInicio == null ? "now()" : "?") + ")";
        try (PreparedStatement sInsert = c.prepareStatement(sentInsert,
                PreparedStatement.RETURN_GENERATED_KEYS)) {
            int i = 0;
            sInsert.setString(++i, nombre);
            sInsert.setString(++i, dniNifJefeProy);
            sInsert.setDate(++i, fFin);
            if (fInicio != null) {
                sInsert.setDate(++i, fInicio);
            }
            //  Para asignar fecha en máquina cliente:
            //  sInsert.setDate(++i, ((fInicio!=null) ? fInicio : new Date(System.currentTimeMillis())));
            sInsert.executeUpdate();
            ResultSet rsgk = sInsert.getGeneratedKeys();
            rsgk.next();
            result = rsgk.getInt(1);
        } catch (SQLException e) {  // Se informa de ella pero se vuelve a lanzar
            System.err.println("Error al crear proyecto (" + nombre + "," + dniNifJefeProy + "," + fInicio + "," + fFin + ")");
            throw e;
        }
        return result;
    }

    /**
     *
     * @param c Conexión
     * @param dniNifEmp DNI o NIF de empleado
     * @param numProy Número de proyecto
     * @param fInicio Fecha de inicio asignación
     * @param fFin Fecha de fin asignación
     * @return true si se creó la asignación, false en caso contrario
     * @throws java.sql.SQLException
     */
    public static boolean asignaEmpAProyecto(Connection c, String dniNifEmp, Integer numProy, Date fInicio, Date fFin) throws SQLException {
        boolean result = false;
        String sentInsert
                = "INSERT INTO ASIG_PROYECTOS(DNI_NIF_EMP,NUM_PROY,F_FIN,F_INICIO) "
                + "VALUES(?,?,?," + (fInicio == null ? "now()" : "?") + ")";
        try (PreparedStatement sInsert = c.prepareStatement(sentInsert)) {
            int i = 0;
            sInsert.setString(++i, dniNifEmp);
            sInsert.setInt(++i, numProy);
            sInsert.setDate(++i, fFin);
            if (fInicio != null) {
                sInsert.setDate(++i, fInicio);
            }
            //  Para asignar fecha en máquina cliente:
            //  sInsert.setDate(++i, fInicio != null ? fInicio : new Date(System.currentTimeMillis()));
            sInsert.executeUpdate();
            result = true;
        } catch (SQLException e) {  // Se informa de ella pero se vuelve a lanzar
            System.err.println("Error al asignar empleado a proyecto (" + dniNifEmp + "," + numProy + "," + fInicio + "," + fFin + ")");
            throw e;
        }
        return result;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try (
                Connection c = GestorProyectos.obtenConexion()) {
            try {
                c.setAutoCommit(false);

                int numEmp = 0, numProy = 0, numAsig = 0;

                if (GestorProyectos.nuevoEmpleado(c, "78901234X", "NADALES")) {
                    numEmp++;
                }

                Integer numProy1 = GestorProyectos.nuevoProyecto(c, "PAPEL ELECTRÓNICO", "78901234X", null, null);
                if (numProy1 != null) {
                    numProy++;
                }

                if (GestorProyectos.nuevoEmpleado(c, "56789012B", "SAMPER")) {
                    numEmp++;
                }

                if (GestorProyectos.asignaEmpAProyecto(c, "56789012B", numProy1, null, null)) {
                    numAsig++;
                }

                if (GestorProyectos.nuevoEmpleado(c, "76543210S", "SILVA")) {
                    numEmp++;
                }

                if (GestorProyectos.asignaEmpAProyecto(c, "76543210S", numProy1, Date.valueOf("2020-01-06"), null)) {
                    numAsig++;
                }

                if (GestorProyectos.nuevoEmpleado(c, "89012345E", "ROJAS")) {
                    numEmp++;
                }

                Integer numProy2 = GestorProyectos.nuevoProyecto(c, "TINTA_HOLOGRÁFICA", "89012345E", Date.valueOf("2021-10-01"), null);
                if (numProy2 != null) {
                    numProy++;
                }

                if (GestorProyectos.asignaEmpAProyecto(c, "76543210S", numProy2, Date.valueOf("2022-01-16"), Date.valueOf("2022-12-31"))) {
                    numAsig++;
                }

                if (GestorProyectos.nuevoEmpleado(c, "09876543K", "LAMIQUIZ")) {
                    numEmp++;
                }

                Integer numProy3 = GestorProyectos.nuevoProyecto(c, "RUEDAS CUADRADAS", "09876543K", Date.valueOf("2021-12-28"), Date.valueOf("2021-12-30"));
                if (numProy3 != null) {
                    numProy++;
                }

                c.commit();

                System.out.println(numEmp + " empleados creados.");
                System.out.println(numProy + " proyectos creados.");
                System.out.println(numAsig + " asignaciones de empleados a proyectos creadas.");

            } catch (SQLException e) {
                muestraErrorSQL(e);
                try {
                    c.rollback();
                    System.err.println("Se hace ROLLBACK");
                } catch (SQLException er) {
                    System.err.println("ERROR haciendo ROLLBACK");
                    muestraErrorSQL(er);
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR de conexión");
            e.printStackTrace(System.err);
        }
    }

}
