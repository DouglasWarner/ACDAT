package ORM;
// Generated 15-abr-2019 12:17:26 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Proyecto generated by hbm2java
 */
public class Proyecto  implements java.io.Serializable {


     private Integer idProy;
     private EmpleadoPlantilla empleadoPlantilla;
     private String nomProy;
     private Date FInicio;
     private Date FFin;
     private Set asigProyectos = new HashSet(0);

    public Proyecto() {
    }

	
    public Proyecto(EmpleadoPlantilla empleadoPlantilla, String nomProy, Date FInicio) {
        this.empleadoPlantilla = empleadoPlantilla;
        this.nomProy = nomProy;
        this.FInicio = FInicio;
    }
    public Proyecto(EmpleadoPlantilla empleadoPlantilla, String nomProy, Date FInicio, Date FFin, Set asigProyectos) {
       this.empleadoPlantilla = empleadoPlantilla;
       this.nomProy = nomProy;
       this.FInicio = FInicio;
       this.FFin = FFin;
       this.asigProyectos = asigProyectos;
    }
   
    public Integer getIdProy() {
        return this.idProy;
    }
    
    public void setIdProy(Integer idProy) {
        this.idProy = idProy;
    }
    public EmpleadoPlantilla getEmpleadoPlantilla() {
        return this.empleadoPlantilla;
    }
    
    public void setEmpleadoPlantilla(EmpleadoPlantilla empleadoPlantilla) {
        this.empleadoPlantilla = empleadoPlantilla;
    }
    public String getNomProy() {
        return this.nomProy;
    }
    
    public void setNomProy(String nomProy) {
        this.nomProy = nomProy;
    }
    public Date getFInicio() {
        return this.FInicio;
    }
    
    public void setFInicio(Date FInicio) {
        this.FInicio = FInicio;
    }
    public Date getFFin() {
        return this.FFin;
    }
    
    public void setFFin(Date FFin) {
        this.FFin = FFin;
    }
    public Set getAsigProyectos() {
        return this.asigProyectos;
    }
    
    public void setAsigProyectos(Set asigProyectos) {
        this.asigProyectos = asigProyectos;
    }




}

