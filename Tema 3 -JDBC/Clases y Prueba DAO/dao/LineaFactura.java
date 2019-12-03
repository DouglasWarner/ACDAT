/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo.dao;

/**
 *
 * @author douglas
 */
public class LineaFactura {
        
    private int num_factura;
    private int id_producto;
    private int cantidad;

    public int getNum_factura() {
        return num_factura;
    }

    public void setNum_factura(int num_factura) {
        this.num_factura = num_factura;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public LineaFactura()
    {
        setNum_factura(0);
        setId_producto(0);
        setCantidad(0);
    }
    public LineaFactura(Factura num_factura, Producto id_producto, int cantidad) {
        setNum_factura(num_factura.getNumFactura());
        setId_producto(id_producto.getId_producto());
        setCantidad(cantidad);
    }    
}
