/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo.dao;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author douglas
 */

public class Cliente {

    static int id_autoIncrement = 0;
    private int id_cliente;
    private String dni;
    private String nom_cliente;
    
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        if(dni.length() > 8)
            this.dni = dni.substring(0, 8);
        else
            this.dni = dni;
    }

    public String getNom_cliente() {
        return nom_cliente;
    }

    public void setNom_cliente(String nom_cliente) {
        if(nom_cliente.length() > 50)
            this.nom_cliente = nom_cliente.substring(0,50);
        else
            this.nom_cliente = nom_cliente;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public Cliente()
    {
    }
    
    public Cliente(String dni, String nom_cliente) {
        id_autoIncrement++;
        id_cliente = id_autoIncrement;
        setDni(dni);
        setNom_cliente(nom_cliente);
    }
    
 
    public boolean Save(Connection c)
    {
        String select = "select * from cliente where dni = '?';";
        String Insert = "insert into cliente (dni, nom_cliente) values (?,?)";
        String update = "update table cliente set nom_cliente = '?' where dni = '?';";
        
      try (PreparedStatement insertarCliente = c.prepareStatement(Insert, PreparedStatement.RETURN_GENERATED_KEYS))
      {
          c.setAutoCommit(false);
          int i = 1;
          
          insertarCliente.setString(i++, getDni());
          insertarCliente.setString(i++, getNom_cliente());
          insertarCliente.executeUpdate();
          
          c.commit();
      } catch (SQLException ex) {
          muestraErrorSQL(ex);
          try
          {
              c.rollback();
              System.out.println("Haciendo rollback");
          } 
          catch (SQLException ex1) {
                System.out.println("Error haciendo rollback");
                muestraErrorSQL(ex1);
            }
      } 
      catch (Exception e) {
          System.out.println(e.getMessage());
      }
        return true;
    }
    
    public static void muestraErrorSQL(SQLException e) {
    System.err.println("SQL ERROR mensaje: " + e.getMessage());
    System.err.println("SQL Estado: " + e.getSQLState());
    System.err.println("SQL código específico: " + e.getErrorCode());
  }
}
