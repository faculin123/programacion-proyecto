package herramientas;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import clases.Book;
import clases.User;

public class FileManager {
    
    public static void saveBooksToFile(List<Book> books, String filePath) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(books);
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Book> loadBooksFromFile(String filePath) {
        if (!fileExists(filePath)) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (List<Book>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar libros: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static void saveUsersToFile(List<User> users, String filePath) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(users);
        }
    }

    @SuppressWarnings("unchecked")
    public static List<User> loadUsersFromFile(String filePath) {
        if (!fileExists(filePath)) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (List<User>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar usuarios: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static void addBook(Book book, String filePath) {
        List<Book> books = loadBooksFromFile(filePath);
        books.add(book);
        try {
            saveBooksToFile(books, filePath);
        } catch (IOException e) {
            System.err.println("Error al guardar el libro: " + e.getMessage());
        }
    }

    public static void addUser(User user, String filePath) {
        List<User> users = loadUsersFromFile(filePath);
        users.add(user);
        try {
            saveUsersToFile(users, filePath);
        } catch (IOException e) {
            System.err.println("Error al guardar el usuario: " + e.getMessage());
        }
    }

    public static void removeBook(Book book, String filePath) {
        List<Book> books = loadBooksFromFile(filePath);
        books.remove(book);
        try {
            saveBooksToFile(books, filePath);
        } catch (IOException e) {
            System.err.println("Error al eliminar el libro: " + e.getMessage());
        }
    }

    public static void removeUser(User user, String filePath) {
        List<User> users = loadUsersFromFile(filePath);
        users.remove(user);
        try {
            saveUsersToFile(users, filePath);
        } catch (IOException e) {
            System.err.println("Error al eliminar el usuario: " + e.getMessage());
        }
    }

    private static boolean fileExists(String filePath) {
        File file = new File(filePath);
        return file.exists() && file.isFile();
    }
}
