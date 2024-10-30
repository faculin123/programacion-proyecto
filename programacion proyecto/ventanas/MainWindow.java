package ventanas;

import javax.swing.*;
import clases.DatabaseHandler;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {
    private DatabaseHandler dbHandler;

    public MainWindow(DatabaseHandler dbHandler) {
        this.dbHandler = dbHandler;
        setTitle("Catálogo de Libros");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla

        // Crear el menú
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Opciones");
        JMenuItem addBookMenuItem = new JMenuItem("Agregar Libro");

        // Agregar ActionListener al menú "Agregar Libro"
        addBookMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openAddBookWindow();
            }
        });

        menu.add(addBookMenuItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);
    }

    private void openAddBookWindow() {
        AddBookWindow addBookWindow = new AddBookWindow(dbHandler);
        addBookWindow.setVisible(true);
    }

    public static void main(String[] args) {
        DatabaseHandler dbHandler = new DatabaseHandler();

        MainWindow mainWindow = new MainWindow(dbHandler);
        mainWindow.setVisible(true);
    }
}
