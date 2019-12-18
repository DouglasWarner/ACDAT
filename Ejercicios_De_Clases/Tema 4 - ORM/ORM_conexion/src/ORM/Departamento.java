package ORM;
// Generated 11 Dec 2019, 12:24:46 by Hibernate Tools 4.3.1


<<<<<<< HEAD
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
=======
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
>>>>>>> origin/master

/**
 * Departamento generated by hbm2java
 */
@Entity
@Table(name="departamento"
    ,catalog="proyecto_orm"
)
public class Departamento  implements java.io.Serializable {


     private Integer idDepto;
     private Sede sede;
     private String nomDepto;
     private Set empleados = new HashSet(0);

    public Departamento() {
    }

	
    public Departamento(Sede sede, String nomDepto) {
        this.sede = sede;
        this.nomDepto = nomDepto;
    }
    public Departamento(Sede sede, String nomDepto, Set empleados) {
       this.sede = sede;
       this.nomDepto = nomDepto;
       this.empleados = empleados;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id_depto", unique=true, nullable=false)
    public Integer getIdDepto() {
        return this.idDepto;
    }
    
    public void setIdDepto(Integer idDepto) {
        this.idDepto = idDepto;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_sede", nullable=false)
    public Sede getSede() {
        return this.sede;
    }
    
    public void setSede(Sede sede) {
        this.sede = sede;
    }

    
    @Column(name="nom_depto", nullable=false, length=32)
    public String getNomDepto() {
        return this.nomDepto;
    }
    
    public void setNomDepto(String nomDepto) {
        this.nomDepto = nomDepto;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="departamento")
    public Set getEmpleados() {
        return this.empleados;
    }
    
    public void setEmpleados(Set empleados) {
        this.empleados = empleados;
    }




}


