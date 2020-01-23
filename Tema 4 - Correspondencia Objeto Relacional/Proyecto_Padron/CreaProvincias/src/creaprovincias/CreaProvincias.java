/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creaprovincias;

import ORM_Padron.Localidad;
import ORM_Padron.Provincia;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author douglas
 */
public class CreaProvincias {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        String ficheroProvincia = "./../csv/provincias.csv";
        
        Transaction t = null;
        
        try(Session s = HibernateUtil.getSessionFactory().openSession())
        {
            t = s.beginTransaction();
            
            LeerCamposProvincia(ficheroProvincia, s);
            
            t.commit();
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            if(t != null)
                t.rollback();
        }
    }
    
    public static void LeerCamposProvincia(String ficheroProvincia, Session s) 
    {
        String linea;
        try(BufferedReader bf = new BufferedReader(new FileReader(new File(ficheroProvincia))))
        {
            while((linea = bf.readLine()) != null)
            {
                String[] campos = linea.split(";");
                
                InsertarProvincia(campos, s);
            }
            
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static boolean InsertarProvincia(String[] campos, Session s)
    {        
        ORM_Padron.Provincia pro = new Provincia();
        ORM_Padron.Comunidad com = s.get(ORM_Padron.Comunidad.class, Integer.parseInt(campos[2]));                
        
        if(com == null)
            return false;
        
        pro.setCodigoPostal(campos[0]);
        pro.setNomProv(campos[1]);
        pro.setComunidad(com);        
        
        s.saveOrUpdate(pro);
        
        System.out.printf("\nCreada comunidad: %s %s %d", pro.getCodigoPostal(), pro.getNomProv(), pro.getComunidad().getIdCom());
        
        return true;
    }
}
