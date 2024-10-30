package ventanas;

import javax.swing.*;
import java.awt.*;
import clases.DatabaseHandler;

public class MainApp extends JFrame {
    private DatabaseHandler dbHandler;

    public MainApp(DatabaseHandler dbHandler) {
        this.dbHandler = dbHandler;
        
        setTitle("Main Application");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        createMenu();
        createToolBar();

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        add(mainPanel);

        JLabel label = new JLabel("Bienvenido a la aplicación");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(label, BorderLayout.CENTER);

        // Prueba la conexión a la base de datos al iniciar la aplicación
        testDatabaseConnection();
    }

    private void createMenu() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("Archivo");
        JMenuItem exitMenuItem = new JMenuItem("Salir");
        exitMenuItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitMenuItem);

        JMenu helpMenu = new JMenu("Ayuda");
        JMenuItem aboutMenuItem = new JMenuItem("Acerca de");
        aboutMenuItem.addActionListener(e -> showAboutDialog());
        helpMenu.add(aboutMenuItem);

        menuBar.add(fileMenu);
        menuBar.add(helpMenu);

        setJMenuBar(menuBar);
    }

    private void createToolBar() {
        JToolBar toolBar = new JToolBar();

        JButton connectButton = new JButton("Conectar");
        connectButton.addActionListener(e -> testDatabaseConnection());

        toolBar.add(connectButton);
        add(toolBar, BorderLayout.NORTH);
    }

    private void showAboutDialog() {
        JOptionPane.showMessageDialog(this, "Aplicación de gestión de datos\nVersión 1.0", "Acerca de", JOptionPane.INFORMATION_MESSAGE);
    }

    private void testDatabaseConnection() {
        boolean connected = false;
        
        try {
            connected = dbHandler.connect();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al conectar a la base de datos: " + ex.getMessage(), "Error de conexión", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();  // Ayuda a depurar posibles errores
        }

        if (connected) {
            JOptionPane.showMessageDialog(this, "Conexión exitosa a la base de datos", "Conexión", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Error al conectar a la base de datos", "Conexión", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        DatabaseHandler dbHandler = new DatabaseHandler();

        SwingUtilities.invokeLater(() -> {
            MainApp app = new MainApp(dbHandler);
            app.setVisible(true);
        });
    }
}
