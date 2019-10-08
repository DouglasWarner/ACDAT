/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miempresa.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import com.miempresa.modelo.Sede;

/**
 *
 * @author carlos
 */
@Stateless
public class GestorSedes {
  
  private final SedeFacade sedeFacade = new SedeFacade();

  public int createSede(String nomSede) {
    Sede entidadSede = new Sede();
    entidadSede.setNomSede(nomSede);
    EntityManager em = sedeFacade.getEntityManager();
    EntityTransaction tx = em.getTransaction();
    try {
      tx.begin();
      sedeFacade.create(entidadSede);
      tx.commit();
      return entidadSede.getIdSede();
    } catch (Exception e) {
      try {
        tx.rollback();
      } catch (Exception e1) {
        e1.printStackTrace();
      }
      e.printStackTrace();
      return -1;
    }

  }

  // Add business logic below. (Right-click in editor and choose
  // "Insert Code > Add Business Method")
}
