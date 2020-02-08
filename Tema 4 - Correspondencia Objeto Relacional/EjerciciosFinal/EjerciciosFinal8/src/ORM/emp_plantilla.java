/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ORM;

import java.util.Set;

/**
 *
 * @author metho
 */
public class emp_plantilla extends Empleado{
    
    private Integer num_Emp;

    public Integer getNum_Emp() {
        return num_Emp;
    }

    public void setNum_Emp(Integer num_Emp) {
        this.num_Emp = num_Emp;
    }

    public emp_plantilla() {
    }

    public emp_plantilla(Integer num_Emp) {
        this.num_Emp = num_Emp;
    }

    public emp_plantilla(Integer num_Emp, String dni, String nomEmp) {
        super(dni, nomEmp);
        this.num_Emp = num_Emp;
    }

    public emp_plantilla(Integer num_Emp, String dni, String nomEmp, Datosprof datosprof, Set proyectos, Set asigproyectos) {
        super(dni, nomEmp, datosprof, proyectos, asigproyectos);
        this.num_Emp = num_Emp;
    }
    
    
}
