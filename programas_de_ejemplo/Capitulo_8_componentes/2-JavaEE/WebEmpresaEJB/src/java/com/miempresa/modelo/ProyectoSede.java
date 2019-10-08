/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miempresa.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author carlos
 */
@Entity
@Table(name = "proyecto_sede")
@NamedQueries({
  @NamedQuery(name = "ProyectoSede.findAll", query = "SELECT p FROM ProyectoSede p")
  , @NamedQuery(name = "ProyectoSede.findByIdProy", query = "SELECT p FROM ProyectoSede p WHERE p.proyectoSedePK.idProy = :idProy")
  , @NamedQuery(name = "ProyectoSede.findByIdSede", query = "SELECT p FROM ProyectoSede p WHERE p.proyectoSedePK.idSede = :idSede")
  , @NamedQuery(name = "ProyectoSede.findByFInicio", query = "SELECT p FROM ProyectoSede p WHERE p.fInicio = :fInicio")
  , @NamedQuery(name = "ProyectoSede.findByFFin", query = "SELECT p FROM ProyectoSede p WHERE p.fFin = :fFin")})
public class ProyectoSede implements Serializable {

  private static final long serialVersionUID = 1L;
  @EmbeddedId
  protected ProyectoSedePK proyectoSedePK;
  @Basic(optional = false)
  @NotNull
  @Column(name = "f_inicio")
  @Temporal(TemporalType.DATE)
  private Date fInicio;
  @Column(name = "f_fin")
  @Temporal(TemporalType.DATE)
  private Date fFin;
  @JoinColumn(name = "id_proy", referencedColumnName = "id_proy", insertable = false, updatable = false)
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  private Proyecto proyecto;
  @JoinColumn(name = "id_sede", referencedColumnName = "id_sede", insertable = false, updatable = false)
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  private Sede sede;

  public ProyectoSede() {
  }

  public ProyectoSede(ProyectoSedePK proyectoSedePK) {
    this.proyectoSedePK = proyectoSedePK;
  }

  public ProyectoSede(ProyectoSedePK proyectoSedePK, Date fInicio) {
    this.proyectoSedePK = proyectoSedePK;
    this.fInicio = fInicio;
  }

  public ProyectoSede(int idProy, int idSede) {
    this.proyectoSedePK = new ProyectoSedePK(idProy, idSede);
  }

  public ProyectoSedePK getProyectoSedePK() {
    return proyectoSedePK;
  }

  public void setProyectoSedePK(ProyectoSedePK proyectoSedePK) {
    this.proyectoSedePK = proyectoSedePK;
  }

  public Date getFInicio() {
    return fInicio;
  }

  public void setFInicio(Date fInicio) {
    this.fInicio = fInicio;
  }

  public Date getFFin() {
    return fFin;
  }

  public void setFFin(Date fFin) {
    this.fFin = fFin;
  }

  public Proyecto getProyecto() {
    return proyecto;
  }

  public void setProyecto(Proyecto proyecto) {
    this.proyecto = proyecto;
  }

  public Sede getSede() {
    return sede;
  }

  public void setSede(Sede sede) {
    this.sede = sede;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (proyectoSedePK != null ? proyectoSedePK.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof ProyectoSede)) {
      return false;
    }
    ProyectoSede other = (ProyectoSede) object;
    if ((this.proyectoSedePK == null && other.proyectoSedePK != null) || (this.proyectoSedePK != null && !this.proyectoSedePK.equals(other.proyectoSedePK))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.miempresa.modelo.ProyectoSede[ proyectoSedePK=" + proyectoSedePK + " ]";
  }
  
}
