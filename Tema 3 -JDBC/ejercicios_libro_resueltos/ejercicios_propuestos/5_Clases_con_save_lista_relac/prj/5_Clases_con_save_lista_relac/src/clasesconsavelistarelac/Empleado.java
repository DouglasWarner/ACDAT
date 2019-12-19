package clasesconsavelistarelac;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Empleado {

    private String dniNif = null;
    private String nombre = null;

    public Empleado() {
    }

    public Empleado(Connection c, String dniNIF) throws SQLException {
        String sqlSel = "SELECT * FROM EMPLEADOS WHERE DNI_NIF=?";
        try (PreparedStatement s = c.prepareStatement(sqlSel)) {
            s.setString(1, dniNIF);
            ResultSet rs = s.executeQuery();
            if (rs.next()) {
                this.dniNif = rs.getString("DNI_NIF");
                this.nombre = rs.getString("NOMBRE");
            } else {
                throw new SQLException("No existe empleado con DNI: " + dniNif);
            }
        }
        catch(Exception e) {
            System.err.println("Error al recuperar empleado con DNI: "+dniNif);
            System.err.println("Sentencia: "+sqlSel);
            throw e;
        }
    }

    public boolean save(Connection c) throws SQLException {
        boolean result = false;
        String sqlIns = "INSERT INTO EMPLEADOS(DNI_NIF,NOMBRE) VALUES(?,?) "
                + " ON DUPLICATE KEY UPDATE NOMBRE=?";
        try (PreparedStatement s = c.prepareStatement(sqlIns)) {
            int i=0;
            s.setString(++i, this.dniNif);
            s.setString(++i, this.nombre);
            s.setString(++i, this.nombre);
            s.executeUpdate();
            result = true;
        }
        catch(SQLException e) {
            System.err.println("Error al grabar empleado con DNI: "+this.dniNif);
            System.err.println("Sentencia: "+sqlIns);
            throw e;
        }
        return result;
    }

    public String getDniNif() {
        return dniNif;
    }

    public void setDniNif(String dniNif) {
        this.dniNif = dniNif;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    @Override
    public String toString() {
        return "EMPLEADO(DNI:"+dniNif+", NOMBRE: "+nombre+")";
    }

}
