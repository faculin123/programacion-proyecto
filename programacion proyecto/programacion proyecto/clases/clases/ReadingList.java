package clases;

import java.util.ArrayList;
import java.util.List;

public class ReadingList {
    private List<Book> readingList;

    public ReadingList() {
        readingList = new ArrayList<>();
    }

    // Método para agregar un libro a la lista de lectura
    public void addBook(Book book) {
        readingList.add(book);
    }

    // Método para eliminar un libro de la lista de lectura
    public void removeBook(Book book) {
        readingList.remove(book);
    }

    // Método para obtener la lista de lectura
    public List<Book> getReadingList() {
        return readingList;
    }

    // Método para verificar si un libro está en la lista de lectura
    public boolean isBookInReadingList(Book book) {
        return readingList.contains(book);
    }
}
