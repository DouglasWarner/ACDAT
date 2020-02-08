package ORM;
// Generated 6 feb. 2020 21:57:31 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Empleado generated by hbm2java
 */
@Entity
@Table(name="empleado"
    ,catalog="gestionproyecto"
)
public class Empleado  implements java.io.Serializable {


     private String dni;
     private String nomEmp;
     private Datosprof datosprof;
     private Set proyectos = new HashSet(0);
     private Set asigproyectos = new HashSet(0);

    public Empleado() {
    }

	
    public Empleado(String dni, String nomEmp) {
        this.dni = dni;
        this.nomEmp = nomEmp;
    }
    public Empleado(String dni, String nomEmp, Datosprof datosprof, Set proyectos, Set asigproyectos) {
       this.dni = dni;
       this.nomEmp = nomEmp;
       this.datosprof = datosprof;
       this.proyectos = proyectos;
       this.asigproyectos = asigproyectos;
    }
   
     @Id 

    
    @Column(name="dni", unique=true, nullable=false, length=9)
    public String getDni() {
        return this.dni;
    }
    
    public void setDni(String dni) {
        this.dni = dni;
    }

    
    @Column(name="nom_Emp", nullable=false, length=200)
    public String getNomEmp() {
        return this.nomEmp;
    }
    
    public void setNomEmp(String nomEmp) {
        this.nomEmp = nomEmp;
    }

@OneToOne(fetch=FetchType.LAZY, mappedBy="empleado")
    public Datosprof getDatosprof() {
        return this.datosprof;
    }
    
    public void setDatosprof(Datosprof datosprof) {
        this.datosprof = datosprof;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="empleado")
    public Set getProyectos() {
        return this.proyectos;
    }
    
    public void setProyectos(Set proyectos) {
        this.proyectos = proyectos;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="empleado")
    public Set getAsigproyectos() {
        return this.asigproyectos;
    }
    
    public void setAsigproyectos(Set asigproyectos) {
        this.asigproyectos = asigproyectos;
    }




}


