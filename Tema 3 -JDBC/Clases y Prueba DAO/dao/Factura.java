/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Types;

/**
 *
 * @author douglas
 */
public class Factura {
    
    private int numFactura;
    private int id_cliente;
    private Date fecha_factura;
    private List<LineaFactura> linea_Factura;

    public int getNumFactura() {
        return numFactura;
    }
    
    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public Date getFecha_factura() {
        return fecha_factura;
    }

    public void setFecha_factura(Date fecha_factura) {
        this.fecha_factura = fecha_factura;
    }
    
    public Factura()
    {
        this.numFactura++;
        setId_cliente(0);
        setFecha_factura(null);
        this.linea_Factura = new ArrayList<>();
        
    }
    public Factura(Cliente id_cliente, Date fecha_factura) {
        this.numFactura++;
        setId_cliente(id_cliente.getIdCliente());
        setFecha_factura(fecha_factura);
        this.linea_Factura = new ArrayList<>();
    }
    
    public void addLineaFactura(Producto p, int cantidad, Connection c)
    {
        LineaFactura linea = new LineaFactura(this, p, cantidad);
        
        String insertFilaFactura = "insert into linea_factura values (?,?,?,?);";
        String numLineaFact = "select ifnull(max(num_linea_factura), 0)+1 from linea_factura where num_factura = ?";
        String selectProducto = "select count(id_producto) from linea_factura where num_factura = ? and id_producto = ?";
        
        try(PreparedStatement insertarLinea = c.prepareStatement(insertFilaFactura);
            PreparedStatement selectLinea = c.prepareStatement(numLineaFact);
            PreparedStatement selectIdProducto = c.prepareStatement(selectProducto))
        {
            c.setAutoCommit(false);
            // Select linea_factura
          int i = 1;
          
          selectIdProducto.setInt(i++, getNumFactura());
          selectIdProducto.setInt(i++, p.getId_producto());
          selectIdProducto.executeQuery();
          ResultSet rsProducto = selectIdProducto.getResultSet();
          
          rsProducto.next();
          
          if(rsProducto.getInt(1) > 0)
          {
              muestraErrorSQL(new SQLException());
              return;
          }
              
          i = 1;
          
          selectLinea.setInt(1, getNumFactura());
          selectLinea.executeQuery();
          ResultSet rsLinea = selectLinea.getResultSet();
          rsLinea.next();
          int lineaFactura = rsLinea.getInt(1);
                     
            insertarLinea.setInt(i++, getNumFactura());
            insertarLinea.setInt(i++, lineaFactura++);
            if(linea.getId_producto() == 0)
                insertarLinea.setNull(i++, Types.NULL);
            else
                insertarLinea.setInt(i++, linea.getId_producto());

            if(linea.getCantidad() == 0)
                insertarLinea.setNull(i++, Types.NULL);
            else
                insertarLinea.setInt(i++, linea.getCantidad());

            insertarLinea.executeUpdate();
            System.out.print("Agregado linea factura");

            i = 1;
            
          this.linea_Factura.add(linea);
          
          c.commit();
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
      catch (Exception e) {
          System.out.println(e.getMessage());
      }
        
    }
    
    public List<LineaFactura> getListLineaFactura()
    {
        return this.linea_Factura;
    }
    
    public static Factura getFactura(int idFactura, Connection c)
    {
        String select = "select * from factura where num_factura = ?;";
        Factura tmp = null;       
        
        try (PreparedStatement psGetFact = c.prepareStatement(select, PreparedStatement.RETURN_GENERATED_KEYS))
        {
            int i = 1;
            psGetFact.setInt(i++, idFactura);
            psGetFact.executeQuery();
            
            ResultSet rs = psGetFact.getResultSet();
            
            if(rs.next())
            {
                tmp = new Factura(); 
                System.out.println(rs.getInt("num_factura"));
                System.out.println(rs.getInt("id_cliente"));
                System.out.println(rs.getDate("fecha_factura"));
                
                tmp.numFactura = idFactura;
                tmp.setId_cliente(rs.getInt("id_cliente"));
                tmp.setFecha_factura(rs.getDate("fecha_factura"));
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
        String Insert = "insert into factura (id_cliente, fecha_factura) values (?,?)";
        
        
      try (PreparedStatement insertarFactura = c.prepareStatement(Insert, PreparedStatement.RETURN_GENERATED_KEYS);)
      {
          c.setAutoCommit(false);
          int i = 1;
          
          if(getId_cliente() == 0)
              insertarFactura.setNull(i++, Types.NULL);
          else
              insertarFactura.setInt(i++, getId_cliente());
          
          if(getFecha_factura() == null)
              insertarFactura.setNull(i++, Types.NULL);
          else
              insertarFactura.setDate(i++, getFecha_factura());
          
          insertarFactura.executeUpdate();
          
          ResultSet rs = insertarFactura.getGeneratedKeys();
          rs.next();
          numFactura = rs.getInt(1);
          
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
