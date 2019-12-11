package ORM;
// Generated 11 Dec 2019, 12:24:46 by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ProyectoSedeId generated by hbm2java
 */
@Embeddable
public class ProyectoSedeId  implements java.io.Serializable {


     private int idProy;
     private int idSede;

    public ProyectoSedeId() {
    }

    public ProyectoSedeId(int idProy, int idSede) {
       this.idProy = idProy;
       this.idSede = idSede;
    }
   


    @Column(name="id_proy", nullable=false)
    public int getIdProy() {
        return this.idProy;
    }
    
    public void setIdProy(int idProy) {
        this.idProy = idProy;
    }


    @Column(name="id_sede", nullable=false)
    public int getIdSede() {
        return this.idSede;
    }
    
    public void setIdSede(int idSede) {
        this.idSede = idSede;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof ProyectoSedeId) ) return false;
		 ProyectoSedeId castOther = ( ProyectoSedeId ) other; 
         
		 return (this.getIdProy()==castOther.getIdProy())
 && (this.getIdSede()==castOther.getIdSede());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getIdProy();
         result = 37 * result + this.getIdSede();
         return result;
   }   


}


