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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "departamento")
@NamedQueries({
  @NamedQuery(name = "Departamento.findAll", query = "SELECT d FROM Departamento d")
  , @NamedQuery(name = "Departamento.findByIdDepto", query = "SELECT d FROM Departamento d WHERE d.idDepto = :idDepto")
  , @NamedQuery(name = "Departamento.findByNomDepto", query = "SELECT d FROM Departamento d WHERE d.nomDepto = :nomDepto")})
public class Departamento implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id_depto")
  private Integer idDepto;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 32)
  @Column(name = "nom_depto")
  private String nomDepto;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDepto", fetch = FetchType.LAZY)
  private Set<Empleado> empleadoSet;
  @JoinColumn(name = "id_sede", referencedColumnName = "id_sede")
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  private Sede idSede;

  public Departamento() {
  }

  public Departamento(Integer idDepto) {
    this.idDepto = idDepto;
  }

  public Departamento(Integer idDepto, String nomDepto) {
    this.idDepto = idDepto;
    this.nomDepto = nomDepto;
  }

  public Integer getIdDepto() {
    return idDepto;
  }

  public void setIdDepto(Integer idDepto) {
    this.idDepto = idDepto;
  }

  public String getNomDepto() {
    return nomDepto;
  }

  public void setNomDepto(String nomDepto) {
    this.nomDepto = nomDepto;
  }

  public Set<Empleado> getEmpleadoSet() {
    return empleadoSet;
  }

  public void setEmpleadoSet(Set<Empleado> empleadoSet) {
    this.empleadoSet = empleadoSet;
  }

  public Sede getIdSede() {
    return idSede;
  }

  public void setIdSede(Sede idSede) {
    this.idSede = idSede;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (idDepto != null ? idDepto.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Departamento)) {
      return false;
    }
    Departamento other = (Departamento) object;
    if ((this.idDepto == null && other.idDepto != null) || (this.idDepto != null && !this.idDepto.equals(other.idDepto))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.miempresa.modelo.Departamento[ idDepto=" + idDepto + " ]";
  }
  
}
