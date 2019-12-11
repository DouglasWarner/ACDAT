/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creatraductores;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author douglas
 */
public class Texto {
    
    private int id_texto;
    private int num_palabras;
    private String cod_idioma;

    public int getId_texto() {
        return id_texto;
    }
    
    public int getNum_palabras() {
        return num_palabras;
    }

    public void setNum_palabras(int num_palabras) {
        this.num_palabras = num_palabras;
    }

    public String getCod_idioma() {
        return cod_idioma;
    }

    public void setCod_idioma(String cod_idioma) {
        this.cod_idioma = cod_idioma;
    }
    
    public Texto()
    {
        this.id_texto++;
        setCod_idioma("");
        setNum_palabras(0);
    }
    
    public Texto(int codTexto ,Connection c)
    {
        String select = "select * from texto where id_texto = ?;";
        Texto t = null;
        
        try (PreparedStatement psGetTexto = c.prepareStatement(select, PreparedStatement.RETURN_GENERATED_KEYS))
        {
            int i = 1;
            psGetTexto.setInt(i++, codTexto);
            psGetTexto.executeQuery();
            
            ResultSet rs = psGetTexto.getResultSet();
            
            if(rs.next())
            {
                this.id_texto = rs.getInt("id_texto");
                setNum_palabras(rs.getInt("num_palabras"));
                setCod_idioma(rs.getString("cod_idioma"));
            }
            
        }
        catch (SQLException ex) 
        {
          muestraErrorSQL(ex);
          try
          {
              c.rollback();
              System.out.println("Haciendo rollback");
          } 
          catch (SQLException ex1) 
          {
                System.out.println("Error haciendo rollback");
                muestraErrorSQL(ex1);
            }
        }
    }
    
    public Texto(String idioma, int numPalabras)
    {
        this.id_texto++;
        setCod_idioma(idioma);
        setNum_palabras(numPalabras);
    }
    
    public int save(Connection c)
    {        
        String Insert = "insert into texto (num_palabras, cod_idioma) values (?,?)";
        int codTexto = 0;
        
      try (PreparedStatement insertar = c.prepareStatement(Insert, PreparedStatement.RETURN_GENERATED_KEYS);)
      {
          c.setAutoCommit(false);
          int i = 1;
          
          if(getNum_palabras() == 0)
              insertar.setNull(i++, Types.NULL);
          else
              insertar.setInt(i++, getNum_palabras());
          
          insertar.setString(i++, getCod_idioma());
          
          insertar.executeUpdate();
          
          ResultSet rs = insertar.getGeneratedKeys();
          rs.next();
          codTexto = rs.getInt(1);
          
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
      
      return codTexto;
    }
    
    public void asignaTraductores(ArrayList<HashMap> listaTrad, Connection c)
    {
        String insertar = "insert into asig_traduccion(id_texto, cod_idioma_dest, dni_nie_trad) values (?,?,?);";
        int i = 0;
        
        try(PreparedStatement ps = c.prepareStatement(insertar))
        {
            c.setAutoCommit(false);
            
            for (HashMap<String,String> trad : listaTrad) 
            {                
                ps.setInt(i++, getId_texto());
                ps.setString(i++, trad.get("cod_idioma_dest"));
                ps.setString(i++, trad.get("dni_nie_trad"));
                ps.executeUpdate();
            }
            
            c.commit();
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
      catch (Exception e) {
          System.out.println(e.getMessage());
      }
    }
            
    public static void muestraErrorSQL(SQLException e) {
    System.err.println("SQL ERROR mensaje: " + e.getMessage());
    System.err.println("SQL Estado: " + e.getSQLState());
    System.err.println("SQL código específico: " + e.getErrorCode());
    }
}
