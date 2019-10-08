package ORM;

public class Libro extends Publicacion implements java.io.Serializable {

  private String isbn;
  private String autor;

  public Libro() {
    }

  public Libro(String titulo, String isbn, String autor) {
    super(titulo);
    this.isbn = isbn;
    this.autor = autor;
    }

  public String getIsbn() {
    return this.isbn;
    }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
    }

  public String getAutor() {
    return this.autor;
    }

  public void setAutor(String autor) {
    this.autor = autor;
    }

  }

