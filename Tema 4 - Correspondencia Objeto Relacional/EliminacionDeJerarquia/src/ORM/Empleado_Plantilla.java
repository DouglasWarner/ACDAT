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
public class Empleado_Plantilla extends Empleado implements java.io.Serializable{
    
    private Integer numEmp;

    public Empleado_Plantilla() {
    }

    public Empleado_Plantilla(String dni, String nomEmp, Integer numEmp) {
        super(dni, nomEmp);
        this.numEmp = numEmp;
    }

    public Integer getNumemp() {
        return numEmp;
    }

    public void setNumemp(Integer numEmp) {
        this.numEmp = numEmp;
    }
    
    
}
