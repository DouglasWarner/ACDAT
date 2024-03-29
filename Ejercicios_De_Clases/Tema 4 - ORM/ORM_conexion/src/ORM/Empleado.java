package ORM;
// Generated 11 Dec 2019, 12:24:46 by Hibernate Tools 4.3.1


<<<<<<< HEAD
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
=======
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
>>>>>>> origin/master

/**
 * Empleado generated by hbm2java
 */
@Entity
@Table(name="empleado"
    ,catalog="proyecto_orm"
)
public class Empleado  implements java.io.Serializable {


     private String dni;
     private Departamento departamento;
     private String nomEmp;
     private EmpleadoDatosProf empleadoDatosProf;

    public Empleado() {
    }

	
    public Empleado(String dni, Departamento departamento, String nomEmp) {
        this.dni = dni;
        this.departamento = departamento;
        this.nomEmp = nomEmp;
    }
    public Empleado(String dni, Departamento departamento, String nomEmp, EmpleadoDatosProf empleadoDatosProf) {
       this.dni = dni;
       this.departamento = departamento;
       this.nomEmp = nomEmp;
       this.empleadoDatosProf = empleadoDatosProf;
    }
   
     @Id 

    
    @Column(name="dni", unique=true, nullable=false, length=9)
    public String getDni() {
        return this.dni;
    }
    
    public void setDni(String dni) {
        this.dni = dni;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_depto", nullable=false)
    public Departamento getDepartamento() {
        return this.departamento;
    }
    
    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    
    @Column(name="nom_emp", nullable=false, length=40)
    public String getNomEmp() {
        return this.nomEmp;
    }
    
    public void setNomEmp(String nomEmp) {
        this.nomEmp = nomEmp;
    }

@OneToOne(fetch=FetchType.LAZY, mappedBy="empleado")
    public EmpleadoDatosProf getEmpleadoDatosProf() {
        return this.empleadoDatosProf;
    }
    
    public void setEmpleadoDatosProf(EmpleadoDatosProf empleadoDatosProf) {
        this.empleadoDatosProf = empleadoDatosProf;
    }




}


