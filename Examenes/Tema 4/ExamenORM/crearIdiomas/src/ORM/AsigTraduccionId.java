package ORM;
// Generated 13 Feb 2020, 12:09:36 by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * AsigTraduccionId generated by hbm2java
 */
@Embeddable
public class AsigTraduccionId  implements java.io.Serializable {


     private int idTexto;
     private String codIdiomaDest;

    public AsigTraduccionId() {
    }

    public AsigTraduccionId(int idTexto, String codIdiomaDest) {
       this.idTexto = idTexto;
       this.codIdiomaDest = codIdiomaDest;
    }
   


    @Column(name="id_texto", nullable=false)
    public int getIdTexto() {
        return this.idTexto;
    }
    
    public void setIdTexto(int idTexto) {
        this.idTexto = idTexto;
    }


    @Column(name="cod_idioma_dest", nullable=false, length=2)
    public String getCodIdiomaDest() {
        return this.codIdiomaDest;
    }
    
    public void setCodIdiomaDest(String codIdiomaDest) {
        this.codIdiomaDest = codIdiomaDest;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof AsigTraduccionId) ) return false;
		 AsigTraduccionId castOther = ( AsigTraduccionId ) other; 
         
		 return (this.getIdTexto()==castOther.getIdTexto())
 && ( (this.getCodIdiomaDest()==castOther.getCodIdiomaDest()) || ( this.getCodIdiomaDest()!=null && castOther.getCodIdiomaDest()!=null && this.getCodIdiomaDest().equals(castOther.getCodIdiomaDest()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getIdTexto();
         result = 37 * result + ( getCodIdiomaDest() == null ? 0 : this.getCodIdiomaDest().hashCode() );
         return result;
   }   


}


