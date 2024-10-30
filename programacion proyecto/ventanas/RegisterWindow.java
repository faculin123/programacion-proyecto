package ventanas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import clases.DatabaseHandler;

public class RegisterWindow extends JFrame {
    private DatabaseHandler dbHandler;
    private JTextField nameField;
    private JTextField emailField;
    private JPasswordField passwordField;

    public RegisterWindow(DatabaseHandler dbHandler) {
        this.dbHandler = dbHandler;
        
        setTitle("Registro de Nuevo Usuario");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));
        add(panel);

        panel.add(new JLabel("Nombre:"));
        nameField = new JTextField();
        panel.add(nameField);

        panel.add(new JLabel("Correo electrónico:"));
        emailField = new JTextField();
        panel.add(emailField);

        panel.add(new JLabel("Contraseña:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        JButton registerButton = new JButton("Registrar");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });
        panel.add(registerButton);
    }

    private void registerUser() {
        String name = nameField.getText();
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean success = dbHandler.registerUser(name, email, password);
        if (success) {
            JOptionPane.showMessageDialog(this, "Usuario registrado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            dispose(); 
        } else {
            JOptionPane.showMessageDialog(this, "Error al registrar usuario", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        DatabaseHandler dbHandler = new DatabaseHandler();
        SwingUtilities.invokeLater(() -> {
            RegisterWindow registerWindow = new RegisterWindow(dbHandler);
            registerWindow.setVisible(true);
        });
    }
}

