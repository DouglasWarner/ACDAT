<<<<<<< HEAD
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miempresa.dao;

import com.miempresa.modelo.Sede;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;  // [@AD] Eliminado
import javax.persistence.Persistence;           // [@AD] Añadido

/**
 *
 * @author carlos
 */
@Stateless
public class SedeFacade extends AbstractFacade<Sede> implements SedeFacadeLocal {

//  @PersistenceContext(unitName = "WebEmpresaEJBPU")  // [@AD] Eliminado
  private EntityManager em;

  @Override
  protected EntityManager getEntityManager() {
    em = Persistence.createEntityManagerFactory("WebEmpresaEJBPU").createEntityManager();  // [@AD] Añadido
    return em;
  }

  public SedeFacade() {
    super(Sede.class);
  }
  
}
=======
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miempresa.dao;

import com.miempresa.modelo.Sede;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;  // [@AD] Eliminado
import javax.persistence.Persistence;           // [@AD] Añadido

/**
 *
 * @author carlos
 */
@Stateless
public class SedeFacade extends AbstractFacade<Sede> implements SedeFacadeLocal {

//  @PersistenceContext(unitName = "WebEmpresaEJBPU")  // [@AD] Eliminado
  private EntityManager em;

  @Override
  protected EntityManager getEntityManager() {
    em = Persistence.createEntityManagerFactory("WebEmpresaEJBPU").createEntityManager();  // [@AD] Añadido
    return em;
  }

  public SedeFacade() {
    super(Sede.class);
  }
  
}
>>>>>>> origin/master
