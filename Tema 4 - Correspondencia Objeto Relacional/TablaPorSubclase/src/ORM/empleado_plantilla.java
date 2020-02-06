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
public class empleado_plantilla extends empleado{
 
    private Integer numEmp;

    public Integer getNumEmp() {
        return numEmp;
    }

    public void setNumEmp(Integer numEmp) {
        this.numEmp = numEmp;
    }

    public empleado_plantilla() {
    }

    public empleado_plantilla(Integer numEmp) {
        this.numEmp = numEmp;
    }

    public empleado_plantilla(Integer numEmp, String dni, String nomEmp) {
        super(dni, nomEmp);
        this.numEmp = numEmp;
    }
    
    
}
