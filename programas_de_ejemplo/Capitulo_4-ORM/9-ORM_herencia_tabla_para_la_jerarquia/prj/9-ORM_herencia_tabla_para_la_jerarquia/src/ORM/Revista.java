package ORM;

public class Revista extends Publicacion implements java.io.Serializable {

  private String issn;

  public Revista () {
    }

  public Revista(String nombre, String issn) {
    super(nombre);
    this.issn = issn;
    }

  public String getIssn() {
    return this.issn;
    }

  public void setIssn(String issn) {
    this.issn = issn;
    }

  }

