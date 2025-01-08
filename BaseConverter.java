import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BaseConverter extends JFrame {

    private class BaseConverter {
        public static int baseToDecimal(String number, int base) {
            return Integer.parseInt(number, base);
        }
        public static String decimalToBase(int decimal, int base) {
            return Integer.toString(decimal, base).toUpperCase();
        }
    }

    private JTextField numberInput;
    private JTextField baseInput;
    private JTextField targetBaseInput;
    private JLabel resultLabel;

    public BaseConverter() {
        // Set up the frame
        setTitle("Base Converter");
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(240, 248, 255));
        setLayout(new GridBagLayout());
    
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
    
        // Create components
        JLabel numberLabel = createLabel("Enter Number:");
        numberInput = createTextField();
    
        JLabel baseLabel = createLabel("Base of the Number:");
        baseInput = createTextField();
    
        JLabel targetBaseLabel = createLabel("Target Base:");
        targetBaseInput = createTextField();
    
        JButton convertButton = new JButton("Convert");
        convertButton.setFont(new Font("Poppins", Font.BOLD, 14));
        convertButton.setBackground(new Color(30, 144, 255));
        convertButton.setForeground(Color.WHITE);
        convertButton.setFocusPainted(false);
        convertButton.setBorder(BorderFactory.createLineBorder(new Color(30, 144, 255), 2));
        convertButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        convertButton.addActionListener(new ConvertButtonListener());
    
        resultLabel = new JLabel("Result: ");
        resultLabel.setFont(new Font("Poppins", Font.BOLD, 16));
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
    
        // Add components to layout
        gbc.gridx = 0; gbc.gridy = 0; add(numberLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 0; add(numberInput, gbc);
        gbc.gridx = 0; gbc.gridy = 1; add(baseLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 1; add(baseInput, gbc);
        gbc.gridx = 0; gbc.gridy = 2; add(targetBaseLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 2; add(targetBaseInput, gbc);
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2; add(convertButton, gbc);
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2; add(resultLabel, gbc);
    }
    

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Poppins", Font.BOLD, 14));
        return label;
    }

    private JTextField createTextField() {
        JTextField textField = new JTextField();
        textField.setFont(new Font("Poppins", Font.PLAIN, 14));
        textField.setBorder(BorderFactory.createLineBorder(new Color(169, 169, 169), 1));
        textField.setPreferredSize(new Dimension(200, 25)); // Set preferred size
        return textField;
    }
    

    private class ConvertButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String number = numberInput.getText().trim();
                int base = Integer.parseInt(baseInput.getText().trim());
                int targetBase = Integer.parseInt(targetBaseInput.getText().trim());

                if (base < 2 || base > 16 || targetBase < 2 || targetBase > 16) {
                    throw new IllegalArgumentException("Bases must be between 2 and 16.");
                }

                int decimal = BaseConverter.baseToDecimal(number, base);
                String result = BaseConverter.decimalToBase(decimal, targetBase);

                resultLabel.setText("Result: " + result);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(BaseConverter.this,
                        "Invalid input. Please enter valid numbers and bases.");
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(BaseConverter.this, ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BaseConverter frame = new BaseConverter();
            frame.setVisible(true);
        });
    }
}