/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Date;
/**
 *
 * @author douglas
 */
public class EjemploDAO {

    public static void main(String[] args) {
        
        String basedatos = "facturacion";
        String host = "localhost";
        String port = "3306";
        String parAdic = "?useSSL=false";   // Para conexion sin SSL verification
        String urlConnection = "jdbc:mysql://" + host + ":" + port + "/" + basedatos + parAdic;
        String user = "facturacion";
        String pwd = "fact_pwd";
        
        try (Connection c = DriverManager.getConnection(urlConnection, user, pwd)) {
            System.out.println("Conexión realizada.");
            
            /*Cliente c1 = new Cliente("15236111X", "Adrian Perez");
            Cliente c2 = new Cliente("12345678Z", "Juan Benitez");
            Cliente c3 = new Cliente();
            c1.save(c);
            c2.save(c);
            c3.save(c);
            Cliente cTmp = Cliente.getCliente(1, c);
            
            Factura f1 = new Factura(cTmp, Date.valueOf("2008-09-11"));
            
            f1.Save(c);
            */
            Cliente cTmp = Cliente.getCliente(1, c);
            Factura fTmp = Factura.getFactura(1, c);
            
            /*Producto p1 = new Producto("214", "Adoquin", 1);
            Producto p2 = new Producto("200", "Papel", 1);
            p1.Save(c);
            p2.Save(c);*/
            
            Producto pTmp = Producto.getProducto(1, c);
            
            
            LineaFactura lf1 = new LineaFactura(fTmp, pTmp, 20);
            LineaFactura lf2 = new LineaFactura(fTmp, pTmp, 20);
            
            fTmp.addLineaFactura(lf1);
            fTmp.addLineaFactura(lf2);
            
            fTmp.Save(c);
        
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
}
