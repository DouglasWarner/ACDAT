/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.sql.ResultSet;

/**
 *
 * @author douglas
 */
public class Factura {
    
    static int id_autoIncrement = 0;
    private int num_factura;
    private int id_cliente;
    private Date fecha_factura;
    private List<LineaFactura> lineaFactura;

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

    public int getNum_factura() {
        return num_factura;
    }
    
    public Factura(Cliente id_cliente, Date fecha_factura) {
        id_autoIncrement++;
        this.num_factura = id_autoIncrement;
        setId_cliente(id_cliente.getId_cliente());
        setFecha_factura(fecha_factura);
    }
    
    public void addLineaFactura(LineaFactura linea)
    {
        this.lineaFactura.add(linea);
    }
    
    public boolean Save(Connection c)
    {
        String select = "select * from cliente where dni = '?';";
        String Insert = "insert into cliente (dni, nom_cliente) values (?,?)";
        String insertFilaFactura = "insert into linea_factura values (?,?,?,?);";
        String update = "update table cliente set nom_cliente = '?' where dni = '?';";
        
      try (PreparedStatement insertarFactura = c.prepareStatement(Insert, PreparedStatement.RETURN_GENERATED_KEYS);
              PreparedStatement insertarLinea = c.prepareStatement(insertFilaFactura))
      {
          c.setAutoCommit(false);
          int i = 1;
          
          insertarFactura.setInt(i++, getId_cliente());
          insertarFactura.setString(i++, getFecha_factura().toString());
          insertarFactura.executeUpdate();
          
          ResultSet rs = insertarFactura.getGeneratedKeys();
          rs.next();
          int numFactura = rs.getInt(1);
          
          int lineaFactura = 1;
          i = 1;
          
          insertarLinea.setInt(i++, numFactura);
          insertarLinea.setInt(i++, lineaFactura);
          insertarLinea.setInt(i++, this.lineaFactura.get(lineaFactura).getId_producto());
          insertarLinea.setInt(i++, this.lineaFactura.get(lineaFactura).getCantidad());
          
          lineaFactura = 1;
          i = 1;
          
          insertarLinea.setInt(i++, numFactura);
          insertarLinea.setInt(i++, lineaFactura);
          insertarLinea.setInt(i++, this.lineaFactura.get(lineaFactura).getId_producto());
          insertarLinea.setInt(i++, this.lineaFactura.get(lineaFactura).getCantidad());
          
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
