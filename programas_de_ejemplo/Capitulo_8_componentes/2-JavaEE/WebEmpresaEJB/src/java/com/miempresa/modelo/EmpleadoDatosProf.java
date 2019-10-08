/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miempresa.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author carlos
 */
@Entity
@Table(name = "empleado_datos_prof")
@NamedQueries({
  @NamedQuery(name = "EmpleadoDatosProf.findAll", query = "SELECT e FROM EmpleadoDatosProf e")
  , @NamedQuery(name = "EmpleadoDatosProf.findByDni", query = "SELECT e FROM EmpleadoDatosProf e WHERE e.dni = :dni")
  , @NamedQuery(name = "EmpleadoDatosProf.findByCategoria", query = "SELECT e FROM EmpleadoDatosProf e WHERE e.categoria = :categoria")
  , @NamedQuery(name = "EmpleadoDatosProf.findBySueldoBrutoAnual", query = "SELECT e FROM EmpleadoDatosProf e WHERE e.sueldoBrutoAnual = :sueldoBrutoAnual")})
public class EmpleadoDatosProf implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 9)
  @Column(name = "dni")
  private String dni;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 2)
  @Column(name = "categoria")
  private String categoria;
  // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
  @Column(name = "sueldo_bruto_anual")
  private BigDecimal sueldoBrutoAnual;
  @JoinColumn(name = "dni", referencedColumnName = "dni", insertable = false, updatable = false)
  @OneToOne(optional = false, fetch = FetchType.LAZY)
  private Empleado empleado;

  public EmpleadoDatosProf() {
  }

  public EmpleadoDatosProf(String dni) {
    this.dni = dni;
  }

  public EmpleadoDatosProf(String dni, String categoria) {
    this.dni = dni;
    this.categoria = categoria;
  }

  public String getDni() {
    return dni;
  }

  public void setDni(String dni) {
    this.dni = dni;
  }

  public String getCategoria() {
    return categoria;
  }

  public void setCategoria(String categoria) {
    this.categoria = categoria;
  }

  public BigDecimal getSueldoBrutoAnual() {
    return sueldoBrutoAnual;
  }

  public void setSueldoBrutoAnual(BigDecimal sueldoBrutoAnual) {
    this.sueldoBrutoAnual = sueldoBrutoAnual;
  }

  public Empleado getEmpleado() {
    return empleado;
  }

  public void setEmpleado(Empleado empleado) {
    this.empleado = empleado;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (dni != null ? dni.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof EmpleadoDatosProf)) {
      return false;
    }
    EmpleadoDatosProf other = (EmpleadoDatosProf) object;
    if ((this.dni == null && other.dni != null) || (this.dni != null && !this.dni.equals(other.dni))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.miempresa.modelo.EmpleadoDatosProf[ dni=" + dni + " ]";
  }
  
}
