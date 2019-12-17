package clasesconsavelistarelac;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.Calendar;

public class AsigEmpAProyecto {

    private String dniNifEmp = null;
    private Integer numProy = null;
    private Date fInicio = null;
    private Date fFin = null;

    public AsigEmpAProyecto() {
    }

    public AsigEmpAProyecto(Connection c, String dniNifEmp, Integer numProy, Date fInicio) throws SQLException {
        String sqlSel = "SELECT * FROM ASIG_PROYECTOS WHERE DNI_NIF_EMP=? AND NUM_PROY=? AND F_INICIO=?";
        try (PreparedStatement s = c.prepareStatement(sqlSel)) {
            int i = 0;
            s.setString(++i, dniNifEmp);
            s.setInt(++i, numProy);
            s.setDate(++i, fInicio, Calendar.getInstance());
            ResultSet rs = s.executeQuery();
            if (rs.next()) {
                this.dniNifEmp = rs.getString("DNI_NIF_EMP");
                this.numProy = rs.getInt("NUM_PROY");
                this.fInicio = rs.getDate("F_FIN");
                this.fFin = rs.getDate("F_FIN");
            } else {
                throw new SQLException("No existe asignación a proyecto con DNI emp.: " + dniNifEmp + ", núm. proy.: " + numProy + ", F. inicio:" + fInicio);
            }
        } catch (Exception e) {
            System.err.println("Error al recuperar asignación a proyecto con DNI emp.: " + dniNifEmp + ", núm. proy.: " + numProy + ", F. inicio:" + fInicio);
            System.err.println("Sentencia: " + sqlSel);
            throw e;
        }
    }

    public AsigEmpAProyecto(Connection c, Empleado e, Proyecto p, Date fInicio) throws SQLException {
        this(c, e.getDniNif(), p.getNumProy(), fInicio);
    }

    public boolean save(Connection c) throws SQLException {
        boolean result = false;

        // Si no se da fecha de inicio para nueva asignación, se asigna la actual del servidor de base de datos, NOW()
        // Pero hay que gestionar duplicidades (ON DUPLICATE KEY).
        // La dificultad cuando se da valor nulo para fecha de inicio es que hay que seleccionar la
        // mayor fecha de entre las que son menores o iguales que NOW(), porque puede cambiar la fecha entre el
        // momento en que se intenta insertar y el momento en que se intenta actualizar

        String sqlIns = "INSERT INTO ASIG_PROYECTOS(DNI_NIF_EMP,NUM_PROY,F_INICIO,F_FIN) VALUES(?,?," + (this.fInicio != null ? "?" : "now()") + ",?) "
                //        String sqlIns = "INSERT INTO ASIG_PROYECTOS(DNI_NIF_EMP,NUM_PROY,F_INICIO,F_FIN) VALUES(?,?,?,?) "
                + " ON DUPLICATE KEY UPDATE F_FIN=?";

        try (PreparedStatement s = c.prepareStatement(sqlIns)) {
            int i = 0;
            s.setString(++i, this.dniNifEmp);
            s.setInt(++i, this.numProy);
            if (this.fInicio != null) {
                s.setDate(++i, this.fInicio, Calendar.getInstance());
            }
//            s.setDate(++i, this.fInicio, Calendar.getInstance());
            s.setDate(++i, this.fFin, Calendar.getInstance());
            s.setDate(++i, this.fFin, Calendar.getInstance());

            s.executeUpdate();

            // Si se ha grabado nueva asignación y se había dado null como fInicio,
            // recuperar la fecha que se ha asignado. Salvo casos muy excepcionales,
            // será null. Y en esos casos, se genera excepción.

            if (this.fInicio == null) {
                String sqlfInicio = "SELECT F_INICIO FROM ASIG_PROYECTOS "
                        + " WHERE DNI_NIF_EMP=? AND NUM_PROY=? AND F_INICIO=CURDATE()";
                PreparedStatement sFIni = c.prepareStatement(sqlfInicio);
                i = 0;
                sFIni.setString(++i, this.dniNifEmp);
                sFIni.setInt(++i, this.numProy);
                ResultSet rsFIni = sFIni.executeQuery();
                if (rsFIni.next()) {
                    this.fInicio = rsFIni.getDate(1);
                } else {
                    throw new SQLException("Error al crear asignación a proyecto con fecha de inicio null, no se pudo recuperar fecha de inicio asignada");
                }
            }
            result = true;
        } catch (SQLException e) {
            System.err.println("Error al grabar asignación de empleado a proyecto con DNI de empleado: " + this.dniNifEmp + ", núm. proyecto: " + this.numProy + ", f. inicio: " + this.fInicio + ", f. fin: " + this.fFin);
            System.err.println("Sentencia: " + sqlIns);
            throw e;
        }
        return result;
    }

    @Override
    public String toString() {
        return "ASIG_PROYECTO(DNI_NIF_EMP: " + dniNifEmp + ", NUM_PROY: " + numProy + ", F_INICIO: " + fInicio + ", F_FIN: " + fFin + ")";
    }

    public String getDniNifEmp() {
        return dniNifEmp;
    }

    public void setDniNifEmp(String dniNifEmp) {
        this.dniNifEmp = dniNifEmp;
    }

    public void setEmpleado(Empleado emp) {
        setDniNifEmp(emp.getDniNif());
    }

    public Integer getNumProy() {
        return numProy;
    }

    public void setNumProy(Integer numProy) {
        this.numProy = numProy;
    }

    public void setProyecto(Proyecto p) {
        setNumProy(p.getNumProy());
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

}
