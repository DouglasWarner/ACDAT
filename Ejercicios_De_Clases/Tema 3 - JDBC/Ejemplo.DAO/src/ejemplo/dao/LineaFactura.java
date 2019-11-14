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
    
    static int num_linea = 0;
    private int num_linea_factura;
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

    public LineaFactura(Factura num_factura, Producto id_producto, int cantidad) {
        num_linea++;
        this.num_linea_factura = num_linea;
        setId_producto(id_producto.getId_producto());
        setNum_factura(num_factura.getId_factura());
        setCantidad(cantidad);
    }
    
    
}
