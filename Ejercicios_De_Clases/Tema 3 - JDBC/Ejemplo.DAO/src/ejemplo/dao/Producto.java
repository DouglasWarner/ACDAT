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
public class Producto {
    
    static int id_autoIncrement = 0;
    private int id_producto;
    private String ean;
    private String nom_producto;
    private double precio_unitario;

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        if(ean.length() > 13)
            this.ean = ean.substring(0,13);
        else
            this.ean = ean;
    }

    public String getNom_producto() {
        return nom_producto;
    }

    public void setNom_producto(String nom_producto) {
        if(nom_producto.length() > 64)
            this.nom_producto = nom_producto.substring(0,64);
        else
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

    public Producto() {
    }

    public Producto(String ean, String nom_producto, double precio_unitario) {
        id_autoIncrement++;
        id_producto = id_autoIncrement;
        setEan(ean);
        setNom_producto(nom_producto);
        setPrecio_unitario(precio_unitario);
    }
    
}
