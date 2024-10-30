package clases;
import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler {
    private List<Book> books;
    private List<User> users;

    // constructor sin parametros
    public DatabaseHandler() {
        books = new ArrayList<>();
        users = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public List<Book> getBooks() {
        return books;
    }

   
    public void addUser(User user) {
        users.add(user);
    }

    public User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    private Connection connection;

    public DatabaseHandler(String dbUrl) {
        try {
            connection = DriverManager.getConnection(dbUrl);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }





    public ResultSet getAllBooks() {
        String sql = "SELECT * FROM books";
        try {
            Statement stmt = connection.createStatement();
            return stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean connect() {
        throw new UnsupportedOperationException("Unimplemented method 'connect'");
    }

    public boolean registerUser(String name, String email, String password) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'registerUser'");
    }
}

