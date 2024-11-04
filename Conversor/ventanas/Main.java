import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class UnitConverterGUI extends JFrame {

    private final JComboBox<String> conversionOptions;
    private final JTextField inputField;
    private final JLabel resultLabel;
    private File historialFile;

    public UnitConverterGUI() {
        setTitle("Conversor de Unidades");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());


        getContentPane().setBackground(new Color(60, 63, 65));


        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(42, 46, 48));
        JLabel titleLabel = new JLabel("Conversor de Unidades", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel);


        JPanel centerPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        centerPanel.setBackground(new Color(60, 63, 65));

        conversionOptions = new JComboBox<>(new String[] { "cm a metros", "metros a km", "km a millas", "millas a km" });
        conversionOptions.setFont(new Font("Arial", Font.PLAIN, 14));
        inputField = new JTextField();
        inputField.setFont(new Font("Arial", Font.PLAIN, 14));
        inputField.setHorizontalAlignment(JTextField.CENTER);


        JPanel bottomPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        bottomPanel.setBackground(new Color(60, 63, 65));

        JButton convertButton = new JButton("Convertir");
        convertButton.setFont(new Font("Arial", Font.BOLD, 14));
        convertButton.setBackground(new Color(0, 123, 255));
        convertButton.setForeground(Color.WHITE);
        convertButton.setFocusPainted(false);
        convertButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        convertButton.addActionListener(e -> convert());

        JButton clearHistoryButton = new JButton("Limpiar Historial");
        clearHistoryButton.setFont(new Font("Arial", Font.BOLD, 14));
        clearHistoryButton.setBackground(new Color(255, 0, 0));
        clearHistoryButton.setForeground(Color.WHITE);
        clearHistoryButton.setFocusPainted(false);
        clearHistoryButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        clearHistoryButton.addActionListener(e -> clearHistory());

        resultLabel = new JLabel("Resultado: ", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));
        resultLabel.setForeground(Color.WHITE);


        centerPanel.add(new JLabel("Selecciona una conversión:", SwingConstants.CENTER));
        centerPanel.add(conversionOptions);
        centerPanel.add(inputField);


        bottomPanel.add(convertButton);
        bottomPanel.add(clearHistoryButton);
        bottomPanel.add(resultLabel);


        add(titlePanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);


        initializeHistoryFile();

        setVisible(true);
    }

    private void initializeHistoryFile() {
        // Crear la carpeta Historial si no existe
        File historyDir = new File("Historial");
        if (!historyDir.exists()) {
            historyDir.mkdir();
        }

        // Crear o abrir el archivo Historial.txt
        historialFile = new File(historyDir, "Historial.txt");
        try {
            if (!historialFile.exists()) {
                historialFile.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void convert() {
        try {
            double input = Double.parseDouble(inputField.getText());
            double result = 0.0;
            String selectedConversion = (String) conversionOptions.getSelectedItem();
            String conversionOperation = "";

            switch (selectedConversion) {
                case "cm a metros":
                    result = cmToMeters(input);
                    conversionOperation = input + " cm a " + result + " metros";
                    break;
                case "metros a km":
                    result = metersToKm(input);
                    conversionOperation = input + " metros a " + result + " km";
                    break;
                case "km a millas":
                    result = kmToMiles(input);
                    conversionOperation = input + " km a " + result + " millas";
                    break;
                case "millas a km":
                    result = milesToKm(input);
                    conversionOperation = input + " millas a " + result + " km";
                    break;
            }

            resultLabel.setText("Resultado: " + result);
            appendToHistory(conversionOperation);

        } catch (NumberFormatException e) {
            resultLabel.setText("Por favor ingresa un número válido.");
        }
    }

    private void appendToHistory(String operation) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(historialFile, true))) {
            writer.write(operation);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void clearHistory() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(historialFile))) {
            writer.write(""); // Limpiar el contenido del archivo
            resultLabel.setText("Historial limpiado.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public double cmToMeters(double cm) {
        return cm / 100;
    }

    public double metersToKm(double meters) {
        return meters / 1000;
    }

    public double kmToMiles(double km) {
        return km * 0.621371;
    }

    public double milesToKm(double miles) {
        return miles / 0.621371;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(UnitConverterGUI::new);
    }
}
