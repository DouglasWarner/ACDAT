package clasesconsavelistarelac;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class ClasesConSaveListaRelac {

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
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try (Connection c = ClasesConSaveListaRelac.obtenConexion()) {
            
            try {
                c.setAutoCommit(false);
                
                int nEmp = 0, nProy = 0, nAsig = 0;

                // nuevoEmpleado(c, "78901234X", "NADALES")
                String DNI1 = "78901234X", nombre1_ = "NATALES", nombre1 = "NADALES";
                Empleado e1 = new Empleado();
                e1.setDniNif(DNI1);
                e1.setNombre(nombre1_);
                if (e1.save(c)) {
                    nEmp++;
                    Empleado e1_ = new Empleado(c, DNI1);
                    System.out.println("Empleado creado: " + e1_.toString());
                }
                e1.setNombre(nombre1);
                if (e1.save(c)) {
                    Empleado e1_ = new Empleado(c, DNI1);
                    System.out.println("Empleado modificado: " + e1_.toString());
                }

                // nuevoProyecto(c, "PAPEL ELECTRÓNICO", "78901234X", null, null);
                String nombreP1_ = "PAPEL ETÉREO", nombreP1 = "PAPEL ELECTRÓNICO";                
                Proyecto p1 = new Proyecto();
                p1.setNombre(nombreP1_);
                p1.setDniNifJefeProy(DNI1);
                p1.setfInicio(null);
                p1.setfFin(null);
                Integer numProy1 = p1.save(c); 
                if(p1.save(c) != null) {   // Se asigna número de proyecto, que se puede recuperar con getNumProy()
                    nProy++;
                    System.out.println("Proyecto creado: " + p1.toString());  // Debe mostrar número de proyecto y fecha inicio asignada
                }
                p1.setNombre(nombreP1);
                if (p1.save(c) != null) {
                    Proyecto p1_ = new Proyecto(c, numProy1);
                    System.out.println("Proyecto modificado: " + p1_.toString());
                }
                
                // nuevoEmpleado(c, "56789012B", "SAMPER")
                String DNI2 = "56789012B", nombre2 = "SAMPER";
                Empleado e2 = new Empleado();
                e2.setDniNif(DNI2);
                e2.setNombre(nombre2);
                if (e2.save(c)) {
                    nEmp++;
                    System.out.println("Empleado creado: " + e2.toString());
                }

                // asignaEmpAProyecto(c, "56789012B", numProy1, null, null)
                
                Date fIni = null;
                AsigEmpAProyecto aep1 = new AsigEmpAProyecto();
                aep1.setEmpleado(e2);
                aep1.setProyecto(p1);
                aep1.setfInicio(fIni);
                if(aep1.save(c)) {
                    nAsig++;  // Se asigna fecha de inicio
                    System.out.println("Asignación a proyecto creada: " + aep1.toString());
                }
                
                // nuevoEmpleado(c, "76543210S", "SILVA")
                
                String DNI3 = "76543210S", nombre3 = "SILVA";
                Empleado e3 = new Empleado();
                e3.setDniNif(DNI3);
                e3.setNombre(nombre3);
                if (e3.save(c)) {
                    nEmp++;
                    System.out.println("Empleado creado: " + e3.toString());
                }

                // asignaEmpAProyecto(c, "76543210S", numProy1, Date.valueOf("2020-01-06"), Date.valueOf("2020-11-25"))
                
                fIni = Date.valueOf("2020-01-06");
                Date fFin = Date.valueOf("2020-11-25");
                AsigEmpAProyecto aep2 = new AsigEmpAProyecto();
                aep2.setEmpleado(e3);
                aep2.setProyecto(p1);
                aep2.setfInicio(fIni);
                aep2.setfFin(fFin);
                if(aep2.save(c)) {
                    nAsig++;
                    System.out.println("Asignación a proyecto creada: " + aep2.toString());
                }
                
                // nuevoEmpleado(c, "89012345E", "ROJAS")

                String DNI4 = "89012345E", nombre4 = "ROJAS";
                Empleado e4 = new Empleado();
                e4.setDniNif(DNI4);
                e4.setNombre(nombre4);
                if (e4.save(c)) {
                    nEmp++;
                    System.out.println("Empleado creado: " + e4.toString());
                }
                
                // nuevoProyecto(c, "TINTA_HOLOGRÁFICA", "89012345E", Date.valueOf("2021-10-01"), Date.valueOf("2025-11-10"))
                
                String nombreP2 = "TINTA_HOLOGRÁFICA";
                Proyecto p2 = new Proyecto();
                p2.setNombre(nombreP2);
                p2.setJefeProy(e4);
                p2.setfInicio(Date.valueOf("2018-10-01"));
                p2.setfFin(null);
                if(p2.save(c) != null) {
                    nProy++;
                    System.out.println("Proyecto creado: " + p2.toString());  // Debe mostrar número de proyecto y fecha inicio asignada
                }
                p2.setfFin(Date.valueOf("2025-11-10"));
                if (p2.save(c) != null) {
                    System.out.println("Proyecto modificado: " + p2.toString());
                }
                
                
                // asignaEmpAProyecto(c, "76543210S", numProy2, Date.valueOf("2022-01-16"), Date.valueOf("2022-12-31"))
                
                fIni = Date.valueOf("2019-01-16");
                fFin = null;
                AsigEmpAProyecto aep3 = new AsigEmpAProyecto();
                aep3.setEmpleado(e3);
                aep3.setProyecto(p2);
                aep3.setfInicio(fIni);
                aep3.setfFin(fFin);
                if(aep3.save(c)) {
                    nAsig++;
                    System.out.println("Asignación a proyecto creada: " + aep3.toString());
                }
                aep3.setfFin(Date.valueOf("2022-12-31"));
                if(aep3.save(c)) {
                    nAsig++;
                    System.out.println("Asignación a proyecto modificada: " + aep3.toString());
                }
                
                // nuevoEmpleado(c, "09876543K", "LAMIQUIZ")
                
                Empleado e5 = new Empleado();
                e5.setDniNif("09876543K");
                e5.setNombre("LAMIQUIZ");
                if (e5.save(c)) {
                    nEmp++;
                    System.out.println("Empleado creado: " + e5.toString());
                }

                // nuevoProyecto(c, "RUEDAS CUADRADAS", "09876543K", null, Date.valueOf("2021-12-30"));

                Proyecto p3 = new Proyecto();
                p3.setNombre("RUEDAS CUADRADAS");
                p3.setJefeProy(e5);
                p3.setfInicio(null);
                p3.setfFin(Date.valueOf("2021-12-30"));
                if(p3.save(c) != null) {
                    nProy++;
                    System.out.println("Proyecto creado: " + p3.toString());  // Debe mostrar número de proyecto y fecha inicio asignada
                }
                
                c.commit();
                
                System.out.println(nEmp + " empleados creados.");
                System.out.println(nProy + " proyectos creados.");
                System.out.println(nAsig + " asignaciones de empleados a proyectos creadas.");
                
                List<AsigEmpAProyecto> lEmpP1 = p1.getListAsigEmpleados(c);
                List<AsigEmpAProyecto> lEmpP2 = p2.getListAsigEmpleados(c);
                List<AsigEmpAProyecto> lEmpP3 = p3.getListAsigEmpleados(c);
                
                System.out.println(p1.toString());
                System.out.println("Lista de empleados :");
                for(AsigEmpAProyecto unaAsigEmp: lEmpP1) {
                    System.out.println(unaAsigEmp.toString());
                }
                
                System.out.println(p2.toString());
                System.out.println("Lista de empleados :");
                for(AsigEmpAProyecto unaAsigEmp: lEmpP2) {
                    System.out.println(unaAsigEmp.toString());
                }
                
                System.out.println(p3.toString());
                System.out.println("Lista de empleados :");
                for(AsigEmpAProyecto unaAsigEmp: lEmpP3) {
                    System.out.println(unaAsigEmp.toString());
                }

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
