/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo.dao;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
/**
 *
 * @author douglas
 */

public class Cliente {

    private int idCliente;
    private String dni;
    private String nom_cliente;
    
    public int getIdCliente() {
        return idCliente;
    }
    
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
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

    public Cliente()
    {
        setDni("");
        setNom_cliente("");
    }
    
    public Cliente(String dni, String nom_cliente) {
        setDni(dni);
        setNom_cliente(nom_cliente);
    }
 
    public static Cliente getCliente(int idCliente, Connection c)
    {
        String select = "select * from cliente where id_cliente = ?";
        Cliente tmp = null;        
        
        try (PreparedStatement psGetCli = c.prepareStatement(select, PreparedStatement.RETURN_GENERATED_KEYS))
        {
            int i = 1;
            psGetCli.setInt(i++, idCliente);
            psGetCli.executeQuery();
            
            ResultSet rs = psGetCli.getResultSet();
            
            if(rs.next())
            {
                tmp = new Cliente();
                System.out.println(rs.getInt("id_cliente"));
                idCliente = rs.getInt("id_cliente");
                System.out.println(rs.getInt("dni"));
                System.out.println(rs.getString("nom_cliente"));
                
                tmp.idCliente = idCliente;
                tmp.setDni(rs.getString("dni"));
                tmp.setNom_cliente(rs.getString("nom_cliente"));
            }
          
          return tmp;
        }
        catch (SQLException ex) {
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
        return null;
    }
    
    public boolean save(Connection c)
    {
        String Insert = "insert into cliente (dni, nom_cliente) values (?,?)";
        
      try (PreparedStatement insertarCliente = c.prepareStatement(Insert, PreparedStatement.RETURN_GENERATED_KEYS))
      {
          c.setAutoCommit(false);
          
          int i = 1;
          
          if(getDni() == "")
              insertarCliente.setNull(i++, Types.NULL);
          else
              insertarCliente.setString(i++, getDni());
          
          if(getNom_cliente() == "")
              insertarCliente.setNull(i++, Types.NULL);
          else
              insertarCliente.setString(i++, getNom_cliente());
          
          insertarCliente.executeUpdate();
          
          System.out.print(idCliente + " ");
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
