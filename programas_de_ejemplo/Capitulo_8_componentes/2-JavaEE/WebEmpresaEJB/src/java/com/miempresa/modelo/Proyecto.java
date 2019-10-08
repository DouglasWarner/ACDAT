/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miempresa.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author carlos
 */
@Entity
@Table(name = "proyecto")
@NamedQueries({
  @NamedQuery(name = "Proyecto.findAll", query = "SELECT p FROM Proyecto p")
  , @NamedQuery(name = "Proyecto.findByIdProy", query = "SELECT p FROM Proyecto p WHERE p.idProy = :idProy")
  , @NamedQuery(name = "Proyecto.findByFInicio", query = "SELECT p FROM Proyecto p WHERE p.fInicio = :fInicio")
  , @NamedQuery(name = "Proyecto.findByFFin", query = "SELECT p FROM Proyecto p WHERE p.fFin = :fFin")
  , @NamedQuery(name = "Proyecto.findByNomProy", query = "SELECT p FROM Proyecto p WHERE p.nomProy = :nomProy")})
public class Proyecto implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id_proy")
  private Integer idProy;
  @Basic(optional = false)
  @NotNull
  @Column(name = "f_inicio")
  @Temporal(TemporalType.DATE)
  private Date fInicio;
  @Column(name = "f_fin")
  @Temporal(TemporalType.DATE)
  private Date fFin;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 20)
  @Column(name = "nom_proy")
  private String nomProy;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "proyecto", fetch = FetchType.LAZY)
  private Set<ProyectoSede> proyectoSedeSet;

  public Proyecto() {
  }

  public Proyecto(Integer idProy) {
    this.idProy = idProy;
  }

  public Proyecto(Integer idProy, Date fInicio, String nomProy) {
    this.idProy = idProy;
    this.fInicio = fInicio;
    this.nomProy = nomProy;
  }

  public Integer getIdProy() {
    return idProy;
  }

  public void setIdProy(Integer idProy) {
    this.idProy = idProy;
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

  public String getNomProy() {
    return nomProy;
  }

  public void setNomProy(String nomProy) {
    this.nomProy = nomProy;
  }

  public Set<ProyectoSede> getProyectoSedeSet() {
    return proyectoSedeSet;
  }

  public void setProyectoSedeSet(Set<ProyectoSede> proyectoSedeSet) {
    this.proyectoSedeSet = proyectoSedeSet;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (idProy != null ? idProy.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Proyecto)) {
      return false;
    }
    Proyecto other = (Proyecto) object;
    if ((this.idProy == null && other.idProy != null) || (this.idProy != null && !this.idProy.equals(other.idProy))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.miempresa.modelo.Proyecto[ idProy=" + idProy + " ]";
  }
  
}
