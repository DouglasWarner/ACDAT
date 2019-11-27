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
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author douglas
 */
public class Factura {
    
    private int numFactura;
    private int id_cliente;
    private Date fecha_factura;
    private List<LineaFactura> linea_Factura = new ArrayList<>();

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
        setId_cliente(java.sql.Types.NULL);
        setFecha_factura(java.sql.Date.valueOf(LocalDate.now()));
        
    }
    public Factura(Cliente id_cliente, Date fecha_factura) {
        setId_cliente(id_cliente.getIdCliente());
        setFecha_factura(fecha_factura);
    }
    
    public void addLineaFactura(LineaFactura linea)
    {
        this.linea_Factura.add(linea);
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
        String insertFilaFactura = "insert into linea_factura values (?,?,?,?);";
        String numLineaFact = "select ifnull(max(num_linea_factura), 0)+1 from linea_factura where num_factura = ?";
        
      try (PreparedStatement insertarFactura = c.prepareStatement(Insert, PreparedStatement.RETURN_GENERATED_KEYS);
              PreparedStatement insertarLinea = c.prepareStatement(insertFilaFactura);
              PreparedStatement selectLinea = c.prepareStatement(numLineaFact))
      {
          c.setAutoCommit(false);
          int i = 1;
          
          insertarFactura.setInt(i++, getId_cliente());
          insertarFactura.setDate(i++, getFecha_factura());
          insertarFactura.executeUpdate();
          
          ResultSet rs = insertarFactura.getGeneratedKeys();
          rs.next();
          numFactura = rs.getInt(1);
          
          // Select
          
          selectLinea.setInt(1, numFactura);
          selectLinea.executeQuery();
          ResultSet rsLinea = selectLinea.getResultSet();
          
          int lineaFactura = rsLinea.getInt(1);
          i = 1;
          
          if(this.linea_Factura != null)
          {
            for (LineaFactura linea : this.linea_Factura)
            {   
              insertarLinea.setInt(i++, numFactura);
              insertarLinea.setInt(i++, lineaFactura++);
              insertarLinea.setInt(i++, linea.getId_producto());
              insertarLinea.setInt(i++, linea.getCantidad());

              System.out.print("Agregado linea factura");
              
              i = 1;         
            }
          }
          
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
