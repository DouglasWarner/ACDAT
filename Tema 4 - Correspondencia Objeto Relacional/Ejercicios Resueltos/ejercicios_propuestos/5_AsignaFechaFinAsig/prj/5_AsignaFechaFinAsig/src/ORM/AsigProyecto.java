package ORM;
// Generated 12-abr-2019 0:08:50 by Hibernate Tools 4.3.1


import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * AsigProyecto generated by hbm2java
 */
@Entity
@Table(name="asig_proyecto"
    ,catalog="orm_gestion_proyectos"
)
public class AsigProyecto  implements java.io.Serializable {


     private AsigProyectoId id;
     private Empleado empleado;
     private Proyecto proyecto;
     private Date FFin;

    public AsigProyecto() {
    }

	
    public AsigProyecto(AsigProyectoId id, Empleado empleado, Proyecto proyecto) {
        this.id = id;
        this.empleado = empleado;
        this.proyecto = proyecto;
    }
    public AsigProyecto(AsigProyectoId id, Empleado empleado, Proyecto proyecto, Date FFin) {
       this.id = id;
       this.empleado = empleado;
       this.proyecto = proyecto;
       this.FFin = FFin;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="dniEmp", column=@Column(name="dni_emp", nullable=false, length=9) ), 
        @AttributeOverride(name="idProy", column=@Column(name="id_proy", nullable=false) ), 
        @AttributeOverride(name="FInicio", column=@Column(name="f_inicio", nullable=false, length=10) ) } )
    public AsigProyectoId getId() {
        return this.id;
    }
    
    public void setId(AsigProyectoId id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="dni_emp", nullable=false, insertable=false, updatable=false)
    public Empleado getEmpleado() {
        return this.empleado;
    }
    
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_proy", nullable=false, insertable=false, updatable=false)
    public Proyecto getProyecto() {
        return this.proyecto;
    }
    
    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="f_fin", length=10)
    public Date getFFin() {
        return this.FFin;
    }
    
    public void setFFin(Date FFin) {
        this.FFin = FFin;
    }




}


