/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miempresa.dao;

import com.miempresa.modelo.ProyectoSede;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author carlos
 */
@Local
public interface ProyectoSedeFacadeLocal {

  void create(ProyectoSede proyectoSede);

  void edit(ProyectoSede proyectoSede);

  void remove(ProyectoSede proyectoSede);

  ProyectoSede find(Object id);

  List<ProyectoSede> findAll();

  List<ProyectoSede> findRange(int[] range);

  int count();
  
}
