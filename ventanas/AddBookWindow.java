package ventanas;
import javax.swing.*;

import clases.Book;
import clases.DatabaseHandler;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddBookWindow extends JFrame {
    private JTextField titleField;
    private JTextField authorField;
    private JTextField genreField;
    private JTextArea synopsisArea;
    private JButton addButton;
    private DatabaseHandler dbHandler;

    public AddBookWindow(DatabaseHandler dbHandler) {
        this.dbHandler = dbHandler;
        setTitle("Agregar Libro");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(5, 2));

        add(new JLabel("Título:"));
        titleField = new JTextField();
        add(titleField);

        add(new JLabel("Autor:"));
        authorField = new JTextField();
        add(authorField);

        add(new JLabel("Género:"));
        genreField = new JTextField();
        add(genreField);

        add(new JLabel("Sinopsis:"));
        synopsisArea = new JTextArea();
        add(new JScrollPane(synopsisArea));

        addButton = new JButton("Agregar Libro");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBook();
            }
        });
        add(addButton);
    }

    private void addBook() {
        String title = titleField.getText();
        String author = authorField.getText();
        String genre = genreField.getText();
        String synopsis = synopsisArea.getText();

        if (title.isEmpty() || author.isEmpty() || genre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Book newBook = new Book(title, author, genre, synopsis);
        
        dbHandler.addBook(newBook);

        JOptionPane.showMessageDialog(this, "Libro agregado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        titleField.setText("");
        authorField.setText("");
        genreField.setText("");
        synopsisArea.setText("");
    }
}

