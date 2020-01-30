/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ORM;

/**
 *
 * @author douglas
 */
public class Empleado implements java.io.Serializable{
    private String dni;
    private String nomEmp;

    public Empleado() {
    }

    public Empleado(String dni, String nomEmp) {
        this.dni = dni;
        this.nomEmp = nomEmp;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNomEmp() {
        return nomEmp;
    }

    public void setNomEmp(String nomEmp) {
        this.nomEmp = nomEmp;
    }
    
    
}
