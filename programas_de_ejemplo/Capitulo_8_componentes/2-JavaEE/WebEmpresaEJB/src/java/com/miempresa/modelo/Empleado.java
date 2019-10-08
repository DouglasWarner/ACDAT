/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miempresa.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "empleado")
@NamedQueries({
  @NamedQuery(name = "Empleado.findAll", query = "SELECT e FROM Empleado e")
  , @NamedQuery(name = "Empleado.findByDni", query = "SELECT e FROM Empleado e WHERE e.dni = :dni")
  , @NamedQuery(name = "Empleado.findByNomEmp", query = "SELECT e FROM Empleado e WHERE e.nomEmp = :nomEmp")})
public class Empleado implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 9)
  @Column(name = "dni")
  private String dni;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 40)
  @Column(name = "nom_emp")
  private String nomEmp;
  @OneToOne(cascade = CascadeType.ALL, mappedBy = "empleado", fetch = FetchType.LAZY)
  private EmpleadoDatosProf empleadoDatosProf;
  @JoinColumn(name = "id_depto", referencedColumnName = "id_depto")
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  private Departamento idDepto;

  public Empleado() {
  }

  public Empleado(String dni) {
    this.dni = dni;
  }

  public Empleado(String dni, String nomEmp) {
    this.dni = dni;
    this.nomEmp = nomEmp;
  }

  public String getDni() {
    return dni;
  }

  public void setDni(String dni) {
    this.dni = dni;
  }

  public String getNomEmp() {
    return nomEmp;
  }

  public void setNomEmp(String nomEmp) {
    this.nomEmp = nomEmp;
  }

  public EmpleadoDatosProf getEmpleadoDatosProf() {
    return empleadoDatosProf;
  }

  public void setEmpleadoDatosProf(EmpleadoDatosProf empleadoDatosProf) {
    this.empleadoDatosProf = empleadoDatosProf;
  }

  public Departamento getIdDepto() {
    return idDepto;
  }

  public void setIdDepto(Departamento idDepto) {
    this.idDepto = idDepto;
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
    if (!(object instanceof Empleado)) {
      return false;
    }
    Empleado other = (Empleado) object;
    if ((this.dni == null && other.dni != null) || (this.dni != null && !this.dni.equals(other.dni))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.miempresa.modelo.Empleado[ dni=" + dni + " ]";
  }
  
}
