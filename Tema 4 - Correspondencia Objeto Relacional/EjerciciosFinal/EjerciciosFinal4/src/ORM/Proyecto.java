package ORM;
// Generated 6 feb. 2020 21:57:31 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Proyecto generated by hbm2java
 */
@Entity
@Table(name="proyecto"
    ,catalog="gestionproyecto"
)
public class Proyecto  implements java.io.Serializable {


     private Integer idProy;
     private Empleado empleado;
     private String nomProy;
     private Date FInicio;
     private Date FFin;
     private Set asigproyectos = new HashSet(0);

    public Proyecto() {
    }

	
    public Proyecto(Empleado empleado, String nomProy, Date FInicio) {
        this.empleado = empleado;
        this.nomProy = nomProy;
        this.FInicio = FInicio;
    }
    public Proyecto(Empleado empleado, String nomProy, Date FInicio, Date FFin, Set asigproyectos) {
       this.empleado = empleado;
       this.nomProy = nomProy;
       this.FInicio = FInicio;
       this.FFin = FFin;
       this.asigproyectos = asigproyectos;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id_Proy", unique=true, nullable=false)
    public Integer getIdProy() {
        return this.idProy;
    }
    
    public void setIdProy(Integer idProy) {
        this.idProy = idProy;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="dni_Jefe_Proy", nullable=false)
    public Empleado getEmpleado() {
        return this.empleado;
    }
    
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    
    @Column(name="nom_Proy", nullable=false, length=200)
    public String getNomProy() {
        return this.nomProy;
    }
    
    public void setNomProy(String nomProy) {
        this.nomProy = nomProy;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="f_Inicio", nullable=false, length=10)
    public Date getFInicio() {
        return this.FInicio;
    }
    
    public void setFInicio(Date FInicio) {
        this.FInicio = FInicio;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="f_Fin", length=10)
    public Date getFFin() {
        return this.FFin;
    }
    
    public void setFFin(Date FFin) {
        this.FFin = FFin;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="proyecto")
    public Set getAsigproyectos() {
        return this.asigproyectos;
    }
    
    public void setAsigproyectos(Set asigproyectos) {
        this.asigproyectos = asigproyectos;
    }




}


