package ventanas;
import javax.swing.*;

import clases.DatabaseHandler;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReadingListWindow extends JFrame {
    private JList<String> readingList;
    private DefaultListModel<String> listModel;
    private JButton markAsReadButton;
    private DatabaseHandler dbHandler;

    public ReadingListWindow(DatabaseHandler dbHandler) {
        this.dbHandler = dbHandler;
        setTitle("Lista de Lectura");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Modelo de la lista
        listModel = new DefaultListModel<>();
        readingList = new JList<>(listModel);
        readingList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(readingList), BorderLayout.CENTER);

        // Botón para marcar como leído
        markAsReadButton = new JButton("Marcar como Leído");
        markAsReadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                markAsRead();
            }
        });
        add(markAsReadButton, BorderLayout.SOUTH);

        // Cargar libros de la base de datos
        loadReadingList();
    }

    private void loadReadingList() {
        // Aquí debes obtener los libros de la base de datos
        ResultSet resultSet = dbHandler.getAllBooks(); // Asegúrate de tener este método implementado

        try {
            while (resultSet != null && resultSet.next()) {
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                // Agregar el libro al modelo de lista
                listModel.addElement(title + " - " + author);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void markAsRead() {
        int selectedIndex = readingList.getSelectedIndex();
        if (selectedIndex != -1) {
            String selectedBook = listModel.getElementAt(selectedIndex);
            // Aquí podrías implementar la lógica para mover el libro a una lista de leídos
            // Por ejemplo, actualizar la base de datos o cambiar el estado del libro

            // Por ahora, solo eliminamos de la lista de lectura
            listModel.remove(selectedIndex);
            JOptionPane.showMessageDialog(this, selectedBook + " ha sido marcado como leído.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona un libro para marcar como leído.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
