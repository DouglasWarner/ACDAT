/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crealocalidades;

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
public class CreaLocalidades {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        String ficheroLocalidades = "./../csv/localidades.csv";
        
        Transaction t = null;
        
        try(Session s = HibernateUtil.getSessionFactory().openSession())
        {
            t = s.beginTransaction();
            
            LeerCamposLocalidades(ficheroLocalidades, s);
            
            t.commit();
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            if(t != null)
                t.rollback();
        }
    }
    
    public static void LeerCamposLocalidades(String ficheroLocalidades, Session s) 
    {
        String linea;
        try(BufferedReader bf = new BufferedReader(new FileReader(new File(ficheroLocalidades))))
        {
            while((linea = bf.readLine()) != null)
            {
                String[] campos = linea.split(";");
                
                InsertarLocalidades(campos, s);
            }
            
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static boolean InsertarLocalidades(String[] campos, Session s)
    {   
        ORM_Padron.Localidad loc = new Localidad();
        ORM_Padron.Provincia pro = s.get(ORM_Padron.Provincia.class, campos[1]);

        if(pro == null)
            return false;

        loc.setNomLoc(campos[0]);
        loc.setProvincia(pro);

        s.saveOrUpdate(loc);

        System.out.printf("\nCreada comunidad: %d %s %s", loc.getIdLoc(), loc.getNomLoc(), loc.getProvincia().getCodigoPostal());               
        
        return true;
    }
}
