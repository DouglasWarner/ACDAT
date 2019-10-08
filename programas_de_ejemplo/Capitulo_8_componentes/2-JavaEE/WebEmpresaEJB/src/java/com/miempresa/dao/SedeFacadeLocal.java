/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miempresa.dao;

import com.miempresa.modelo.Sede;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author carlos
 */
@Local
public interface SedeFacadeLocal {

  void create(Sede sede);

  void edit(Sede sede);

  void remove(Sede sede);

  Sede find(Object id);

  List<Sede> findAll();

  List<Sede> findRange(int[] range);

  int count();
  
}
