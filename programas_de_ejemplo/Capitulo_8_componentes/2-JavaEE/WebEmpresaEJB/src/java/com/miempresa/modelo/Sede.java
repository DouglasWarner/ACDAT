/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miempresa.modelo;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author carlos
 */
@Entity
@Table(name = "sede")
@NamedQueries({
  @NamedQuery(name = "Sede.findAll", query = "SELECT s FROM Sede s")
  , @NamedQuery(name = "Sede.findByIdSede", query = "SELECT s FROM Sede s WHERE s.idSede = :idSede")
  , @NamedQuery(name = "Sede.findByNomSede", query = "SELECT s FROM Sede s WHERE s.nomSede = :nomSede")})
public class Sede implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id_sede")
  private Integer idSede;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 20)
  @Column(name = "nom_sede")
  private String nomSede;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "sede", fetch = FetchType.LAZY)
  private Set<ProyectoSede> proyectoSedeSet;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSede", fetch = FetchType.LAZY)
  private Set<Departamento> departamentoSet;

  public Sede() {
  }

  public Sede(Integer idSede) {
    this.idSede = idSede;
  }

  public Sede(Integer idSede, String nomSede) {
    this.idSede = idSede;
    this.nomSede = nomSede;
  }

  public Integer getIdSede() {
    return idSede;
  }

  public void setIdSede(Integer idSede) {
    this.idSede = idSede;
  }

  public String getNomSede() {
    return nomSede;
  }

  public void setNomSede(String nomSede) {
    this.nomSede = nomSede;
  }

  public Set<ProyectoSede> getProyectoSedeSet() {
    return proyectoSedeSet;
  }

  public void setProyectoSedeSet(Set<ProyectoSede> proyectoSedeSet) {
    this.proyectoSedeSet = proyectoSedeSet;
  }

  public Set<Departamento> getDepartamentoSet() {
    return departamentoSet;
  }

  public void setDepartamentoSet(Set<Departamento> departamentoSet) {
    this.departamentoSet = departamentoSet;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (idSede != null ? idSede.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Sede)) {
      return false;
    }
    Sede other = (Sede) object;
    if ((this.idSede == null && other.idSede != null) || (this.idSede != null && !this.idSede.equals(other.idSede))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.miempresa.modelo.Sede[ idSede=" + idSede + " ]";
  }
  
}
