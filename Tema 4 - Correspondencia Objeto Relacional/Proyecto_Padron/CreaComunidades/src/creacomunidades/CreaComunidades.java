/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creacomunidades;

import ORM_Padron.Comunidad;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author douglas
 */
public class CreaComunidades {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        String ficheroComunidad = "./../csv/comunidades.csv";
        
        Transaction t = null;
        
        try(Session s = HibernateUtil.getSessionFactory().openSession())
        {
            t = s.beginTransaction();
            
            LeerCamposComunidad(ficheroComunidad, s);
            
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
        try(BufferedReader bf = new BufferedReader(new FileReader(new File(ficheroComunidad))))
        {
            while((linea = bf.readLine()) != null)
            {                
                InsertarComunidad(linea, s);
            }
            
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static boolean InsertarComunidad(String campo, Session s)
    {
        ORM_Padron.Comunidad com = new Comunidad();
        com.setNomCom(campo);        
        
        s.saveOrUpdate(com);
        
        System.out.printf("\nCreada comunidad: %d %s", com.getIdCom(), com.getNomCom());
        
        return true;
    }
}
