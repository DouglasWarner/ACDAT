/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miempresa.dao;

import com.miempresa.modelo.EmpleadoDatosProf;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author carlos
 */
@Local
public interface EmpleadoDatosProfFacadeLocal {

  void create(EmpleadoDatosProf empleadoDatosProf);

  void edit(EmpleadoDatosProf empleadoDatosProf);

  void remove(EmpleadoDatosProf empleadoDatosProf);

  EmpleadoDatosProf find(Object id);

  List<EmpleadoDatosProf> findAll();

  List<EmpleadoDatosProf> findRange(int[] range);

  int count();
  
}
