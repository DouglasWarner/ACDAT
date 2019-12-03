package clasesconsavelistarelac;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.Calendar;
import java.util.ArrayList;

public class Proyecto {

    private Integer numProy = null;
    private String nombre = null;
    private String dniNifJefeProy = null;
    private Date fInicio = null;
    private Date fFin = null;

    public Proyecto() {
    }

    public Proyecto(Connection c, Integer numProy) throws SQLException {
        String sqlSel = "SELECT * FROM PROYECTOS WHERE NUM_PROY=?";
        try (PreparedStatement s = c.prepareStatement(sqlSel)) {
            s.setInt(1, numProy);
            ResultSet rs = s.executeQuery();
            if (rs.next()) {
                this.numProy = rs.getInt("NUM_PROY");
                this.nombre = rs.getString("NOMBRE");
                this.dniNifJefeProy = rs.getString("DNI_NIF_JEFE_PROY");
                this.fInicio = rs.getDate("F_INICIO");
                this.fFin = rs.getDate("F_FIN");
            } else {
                throw new SQLException("No existe proyecto con núm. proy: " + numProy);
            }
        } catch (Exception e) {
            System.err.println("Error al recuperar proyecto con número de proyecto: " + numProy);
            System.err.println("Sentencia: " + sqlSel);
            throw e;
        }
    }

    public Integer save(Connection c) throws SQLException {
        return (this.numProy == null) ? saveNew(c) : saveUpdate(c);
    }

    private Integer saveNew(Connection c) throws SQLException {
        this.numProy = null;
        String sqlIns = "INSERT INTO PROYECTOS(NOMBRE,DNI_NIF_JEFE_PROY,F_INICIO,F_FIN) "
                //                + " VALUES(?,?,?,?)";
                + " VALUES(?,?," + (this.fInicio != null ? "?" : "now()") + ",?)";
        try (PreparedStatement s = c.prepareStatement(sqlIns, Statement.RETURN_GENERATED_KEYS)) {
            int i = 0;
            s.setString(++i, this.nombre);
            s.setString(++i, this.dniNifJefeProy);
//            s.setDate(++i, this.fInicio, Calendar.getInstance());
            if (this.fInicio != null) {
                s.setDate(++i, this.fInicio, Calendar.getInstance());
            }
            s.setDate(++i, this.fFin, Calendar.getInstance());

            s.executeUpdate();
            ResultSet rsgk = s.getGeneratedKeys();
            rsgk.next();
            this.numProy = rsgk.getInt(1);
            if (this.fInicio == null) {  // Recuperar fecha de inicio creada por la base de datos con now()
                String sqlSelProy = "SELECT * FROM PROYECTOS WHERE NUM_PROY=?";
                PreparedStatement psSelProy = c.prepareStatement(sqlSelProy);
                psSelProy.setInt(1, this.numProy);
                ResultSet rsProy = psSelProy.executeQuery();
                if (rsProy.next()) {
                    this.fInicio = rsProy.getDate("F_INICIO");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al crear proyecto con nombre: " + nombre);
            System.err.println("Sentencia: " + sqlIns);
            throw e;
        }
        return this.numProy;
    }

    private Integer saveUpdate(Connection c) throws SQLException {
        Integer result = null;
//        String sqlUpd = "UPDATE PROYECTOS SET NOMBRE=?,DNI_NIF_JEFE_PROY=?,F_INICIO=?,F_FIN=?"
        String sqlUpd = "UPDATE PROYECTOS SET NOMBRE=?,DNI_NIF_JEFE_PROY=?,F_INICIO=" + (this.fInicio != null ? "?" : "now()") + ",F_FIN=?"
                + " WHERE NUM_PROY=?";
        try (PreparedStatement s = c.prepareStatement(sqlUpd)) {
            int i = 0;
            s.setString(++i, this.nombre);
            s.setString(++i, this.dniNifJefeProy);
//            s.setDate(++i, this.fInicio, Calendar.getInstance());
            if (this.fInicio != null) {
                s.setDate(++i, this.fInicio, Calendar.getInstance());
            }
            s.setDate(++i, this.fFin, Calendar.getInstance());
            s.setInt(++i, this.numProy);
            s.executeUpdate();
            result = this.numProy;
        } catch (SQLException e) {
            System.err.println("Error al modificar proyecto con número: " + this.numProy);
            System.err.println("Sentencia: " + sqlUpd);
            throw e;
        }
        return result;
    }

    @Override
    public String toString() {
        return "PROYECTO(NUM_PROY:" + numProy + ", NOMBRE: " + nombre + ", DNI_NIF_JEFE_PROY: " + dniNifJefeProy + ", F_INICIO: " + fInicio + ", F_FIN: " + fFin + ")";
    }

    public Integer getNumProy() {
        return numProy;
    }

    /*  // No tiene sentido un método getter para una clave autogenerada
    public void setNumProy(Integer numProy) {
        this.numProy = numProy;
    }
     */
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDniNifJefeProy() {
        return dniNifJefeProy;
    }

    public void setDniNifJefeProy(String dniNifJefeProy) {
        this.dniNifJefeProy = dniNifJefeProy;
    }

    public void setJefeProy(Empleado e) {
        this.dniNifJefeProy = e.getDniNif();
    }

    public Date getfInicio() {
        return fInicio;
    }

    public void setfInicio(Date fInicio) {
        this.fInicio = fInicio;
    }

    public Date getfFin() {
        return fFin;
    }

    public void setfFin(Date fFin) {
        this.fFin = fFin;
    }

    ArrayList<AsigEmpAProyecto> getListAsigEmpleados(Connection c) throws SQLException {
        ArrayList lEmpleadosAsig = new ArrayList();
        try (PreparedStatement sSelEmpProy = c.prepareStatement(
                "SELECT A.* FROM ASIG_PROYECTOS A JOIN EMPLEADOS E ON E.DNI_NIF=A.DNI_NIF_EMP WHERE A.NUM_PROY=? "
                + " AND A.F_INICIO<=NOW() AND (A.F_FIN IS NULL OR NOW()<=A.F_FIN)")) {  // Quizá mejor CURDATE()
            sSelEmpProy.setInt(1, this.numProy);
            ResultSet rs = sSelEmpProy.executeQuery();
            while (rs.next()) {
                AsigEmpAProyecto asigEmp = new AsigEmpAProyecto();
                asigEmp.setDniNifEmp(rs.getString("DNI_NIF_EMP"));
                asigEmp.setNumProy(rs.getInt("NUM_PROY"));
                asigEmp.setfInicio(rs.getDate("F_INICIO"));
                asigEmp.setfFin(rs.getDate("F_FIN"));
                lEmpleadosAsig.add(asigEmp);
            }
        } catch (SQLException e) {
            System.err.println("Error al seleccionar empleados para proyecto: " + this.toString());
            throw (e);
        }
        return (lEmpleadosAsig);
    }

}
