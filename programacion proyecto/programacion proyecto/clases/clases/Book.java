package clases;
public class Book {                                             //atributos privados
    private String titulo;
    private String autor;
    private String genero;
    private String sinopsis;
    private int paginas;
    private int publicationYear;

    public Book(String title, String author, String genre, String synopsis, int publicationYear, int pages) {           //metodo constructor
        this.titulo = title;
        this.autor = author;
        this.genero = genre;
        this.sinopsis = synopsis;
        this.publicationYear = publicationYear;
        this.paginas = pages;
    }

    public Book(String title, String author, String genre, String synopsis) {
    }

    public String getTitle() {                             //metodo getter
        return titulo;
    }

    public String getAuthor() {
        return autor;
    }

    public String getGenre() {
        return genero;
    }

    public String getSynopsis() {
        return sinopsis;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public int getPages() {
        return paginas;
    }

    public void setTitle(String title) {                    //metodo setter
        if (title != null && !title.isEmpty()) {
            this.titulo = title;
        }
    }

    public void setAuthor(String author) {
        if (author != null && !author.isEmpty()) {
            this.autor = author;
        }
    }
    
    public void setGenre(String genre) {
        if (genre != null && !genre.isEmpty()) {
            this.genero = genre;
        }
    }

    public void setSynopsis(String synopsis) {
        if (synopsis != null && !synopsis.isEmpty()) {
            this.sinopsis = synopsis;
        }
    }

    public void setPublicationYear(int publicationYear) {
        if (publicationYear > 0) {
            this.publicationYear = publicationYear;
        }
    }

    public void setPages(int pages) {
        if (pages > 0) {
            this.paginas = pages;
        }
    }

    @Override                                                       //metodo toString
    public String toString() {
        return "Book{" +
                "title='" + titulo + '\'' +
                ", author='" + autor + '\'' +
                ", genre='" + genero + '\'' +
                ", synopsis='" + sinopsis + '\'' +
                ", publicationYear=" + publicationYear +
                ", pages=" + paginas +
                '}';
    }

    @Override                                                               //metodo equals
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (publicationYear != book.publicationYear) return false;
        if (paginas != book.paginas) return false;
        if (!titulo.equals(book.titulo)) return false;
        if (!autor.equals(book.autor)) return false;
        if (!genero.equals(book.genero)) return false;
        return sinopsis.equals(book.sinopsis);
    }

    @Override                                                   //metodo hashcode
    public int hashCode() {
        int result = titulo.hashCode();
        result = 31 * result + autor.hashCode();
        result = 31 * result + genero.hashCode();
        result = 31 * result + sinopsis.hashCode();
        result = 31 * result + publicationYear;
        result = 31 * result + paginas;
        return result;
    }

    public void printBookInfo() {
        System.out.println(this.toString());
    }
}