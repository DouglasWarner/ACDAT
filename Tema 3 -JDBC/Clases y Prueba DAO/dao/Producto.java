/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 *
 * @author douglas
 */
public class Producto {
    
    private int id_producto;
    private String ean;
    private String nom_producto;
    private double precio_unitario;

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public String getNom_producto() {
        return nom_producto;
    }

    public void setNom_producto(String nom_producto) {
        this.nom_producto = nom_producto;
    }

    public double getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(double precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public int getId_producto() {
        return id_producto;
    }

    public Producto() 
    {
        setEan("");
        setNom_producto("");
        setPrecio_unitario(0);
    }

    public Producto(String ean, String nom_producto, double precio_unitario) {
        setEan(ean);
        setNom_producto(nom_producto);
        setPrecio_unitario(precio_unitario);
    }
    
    public static Producto getProducto(int idProducto, Connection c)
    {
        String select = "select * from producto where id_producto = ?";
        Producto tmp = null;        
        
        try (PreparedStatement psGetPro = c.prepareStatement(select, PreparedStatement.RETURN_GENERATED_KEYS))
        {
            int i = 1;
            psGetPro.setInt(i++, idProducto);
            psGetPro.executeQuery();
            
            ResultSet rs = psGetPro.getResultSet();
            
            if(rs.next())
            {
                tmp = new Producto();
                System.out.println(rs.getInt("id_producto"));
                idProducto = rs.getInt("id_producto");
                System.out.println(rs.getInt("ean"));
                System.out.println(rs.getString("nom_producto"));
                System.out.println(rs.getDouble("precio_unitario"));
                        
                tmp.id_producto = idProducto;
                tmp.setEan(rs.getString("ean"));
                tmp.setNom_producto(rs.getString("nom_producto"));
                tmp.setPrecio_unitario(rs.getDouble("precio_unitario"));
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
    
    public boolean Save(Connection c)
    {
        String Insert = "insert into producto (ean, nom_producto, precio_unitario) values (?,?,?);";
        
      try (PreparedStatement insertarProducto = c.prepareStatement(Insert, PreparedStatement.RETURN_GENERATED_KEYS))
      {
          c.setAutoCommit(false);
          int i = 1;
          
          if(getEan() == "")
              insertarProducto.setNull(i++, Types.NULL);
          else
              insertarProducto.setString(i++, getEan());
          
          if(getNom_producto() == "")
              insertarProducto.setNull(i++, Types.NULL);
          else
              insertarProducto.setString(i++, getNom_producto());
          
          if(getPrecio_unitario() == 0)
             insertarProducto.setNull(i++, Types.NULL);
          else
              insertarProducto.setDouble(i++, getPrecio_unitario());
          
          insertarProducto.executeUpdate();
          
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
