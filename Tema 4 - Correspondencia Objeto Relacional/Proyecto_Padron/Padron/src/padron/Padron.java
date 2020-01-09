/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package padron;

import ORM_Padron.Comunidad;
import ORM_Padron.Localidad;
import ORM_Padron.Provincia;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author douglas
 */
public class Padron {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        String ficheroComunidad = "/home/douglas/Escritorio/Proyecto_Padron/csv/comunidades.csv";
        String ficheroProvincia = "/home/douglas/Escritorio/Proyecto_Padron/csv/provincias.csv";
        String[] localidades = {"Torremolinos","Benalmadena","Fuengirola","Marbella"};
        
        Transaction t = null;
        
        try(Session s = HibernateUtil.getSessionFactory().openSession())
        {
            t = s.beginTransaction();
            
            LeerCamposComunidad(ficheroComunidad, s);
            //LeerCamposProvincia(ficheroProvincia, s);
            //InsertarLocalidades(localidades, s);
            
            t.commit();            
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            if(t != null)
                t.rollback();
        }
    }

    public static void LeerCamposComunidad(String ficheroComunidad, Session s) 
    {
        String linea;
        try(BufferedReader bf = new BufferedReader(new FileReader(ficheroComunidad)))
        {
            while((linea = bf.readLine()) != null)
            {
                String[] campos = linea.split(";");
                
                InsertarComunidad(campos, s);
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Padron.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Padron.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void LeerCamposProvincia(String ficheroProvincia, Session s) 
    {
        String linea;
        try(BufferedReader bf = new BufferedReader(new FileReader(ficheroProvincia)))
        {
            while((linea = bf.readLine()) != null)
            {
                String[] campos = linea.split(";");
                
                InsertarProvincia(campos, s);
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Padron.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Padron.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static boolean InsertarComunidad(String[] campos, Session s)
    {        
        ORM_Padron.Comunidad com = new Comunidad();
        com.setNomCom(campos[0]);

        s.save(com);        
        
        return true;
    }
    
    public static boolean InsertarProvincia(String[] campos, Session s)
    {        
        ORM_Padron.Provincia pro = new Provincia();
        
        pro.setCodigoPostal(campos[0]);
        pro.setNomProv(campos[1]);
        pro.setComunidad(new ORM_Padron.Comunidad(campos[2]));
        
        s.save(pro);        
        
        return true;
    }
    
    public static boolean InsertarLocalidades(String[] campos, Session s)
    {        
        ORM_Padron.Localidad loc = new Localidad();
        
        loc.setNomLoc("Torremolinos");
        loc.setProvincia(new ORM_Padron.Provincia());
        
        s.save(loc);        
        
        return true;
    }
}
