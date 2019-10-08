/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miempresa.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author carlos
 */
@Embeddable
public class ProyectoSedePK implements Serializable {

  @Basic(optional = false)
  @NotNull
  @Column(name = "id_proy")
  private int idProy;
  @Basic(optional = false)
  @NotNull
  @Column(name = "id_sede")
  private int idSede;

  public ProyectoSedePK() {
  }

  public ProyectoSedePK(int idProy, int idSede) {
    this.idProy = idProy;
    this.idSede = idSede;
  }

  public int getIdProy() {
    return idProy;
  }

  public void setIdProy(int idProy) {
    this.idProy = idProy;
  }

  public int getIdSede() {
    return idSede;
  }

  public void setIdSede(int idSede) {
    this.idSede = idSede;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (int) idProy;
    hash += (int) idSede;
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof ProyectoSedePK)) {
      return false;
    }
    ProyectoSedePK other = (ProyectoSedePK) object;
    if (this.idProy != other.idProy) {
      return false;
    }
    if (this.idSede != other.idSede) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.miempresa.modelo.ProyectoSedePK[ idProy=" + idProy + ", idSede=" + idSede + " ]";
  }
  
}
