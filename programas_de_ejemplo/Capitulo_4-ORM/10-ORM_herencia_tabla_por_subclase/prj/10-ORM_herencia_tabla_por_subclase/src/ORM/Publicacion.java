package ORM;

public class Publicacion implements java.io.Serializable {

  private Integer idPub;
  private String nomPub;

  public Publicacion() {
    }

  public Publicacion(String nomPub) {
    this.nomPub = nomPub;
    }

  public Integer getIdPub() {
    return this.idPub;
    }

  public void setIdPub(Integer idPub) {
    this.idPub = idPub;
    }
  
  public String getNomPub() {
    return this.nomPub;
    }

  public void setNomPub(Integer String) {
    this.nomPub = nomPub;
    }

  }

