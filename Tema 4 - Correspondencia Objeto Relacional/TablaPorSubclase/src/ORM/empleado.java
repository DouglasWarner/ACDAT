/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ORM;

/**
 *
 * @author metho
 */
public class empleado {
    
    private String dni;
    private String nomEmp;

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

    public empleado() {
    }

    public empleado(String dni, String nomEmp) {
        this.dni = dni;
        this.nomEmp = nomEmp;
    }
    
    
}
