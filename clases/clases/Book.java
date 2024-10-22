package clases;
public class Book {
    private String title;
    private String author;
    private String genre;
    private String synopsis;

    public Book(String title, String author, String genre, String synopsis) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.synopsis = synopsis;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }
}
