package airline.management.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Add_Customer extends JFrame {

    // Declare UI components as private instance variables
    private JTextField passportField, pnrField, addressField, nationalityField, nameField, flightCodeField, phoneField;
    private JRadioButton maleRadioButton, femaleRadioButton;
    private JButton saveButton;

    // Singleton instance of database connection
    private static Conn databaseConnection;

    public Add_Customer() {
        // Initialize the singleton instance of the database connection
        if (databaseConnection == null) {
            databaseConnection = Conn.getInstance();
        }

        // Set up the JFrame properties
        setTitle("Add Customer Details");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(800, 500);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setLocationRelativeTo(null);
        
        // Create UI components
        createUIComponents();
        
        // Add action listeners
        addActionListeners();

        // Set the JFrame to be visible
        setVisible(true);
    }

    private void createUIComponents() {
        // Add title label
        JLabel titleLabel = new JLabel("Add Customer Details");
        titleLabel.setForeground(Color.BLUE);
        titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 31));
        titleLabel.setBounds(420, 24, 442, 35);
        add(titleLabel);

        // Add labels and text fields using a helper method
        addLabeledTextField("Passport No", 80, passportField);
        addLabeledTextField("PNR No", 120, pnrField);
        addLabeledTextField("Address", 170, addressField);
        addLabeledTextField("Nationality", 220, nationalityField);
        addLabeledTextField("Name", 270, nameField);
        addLabeledTextField("Flight Code", 30, flightCodeField);
        addLabeledTextField("Phone No", 370, phoneField);

        // Add gender radio buttons
        JLabel genderLabel = new JLabel("Gender");
        genderLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
        genderLabel.setBounds(60, 320, 150, 27);
        add(genderLabel);
        
        maleRadioButton = new JRadioButton("Male");
        maleRadioButton.setBounds(200, 320, 70, 27);
        add(maleRadioButton);
        
        femaleRadioButton = new JRadioButton("Female");
        femaleRadioButton.setBounds(280, 320, 70, 27);
        add(femaleRadioButton);
        
        // Group the radio buttons
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);

        // Add Save button
        saveButton = new JButton("Save");
        saveButton.setBounds(200, 420, 150, 30);
        saveButton.setBackground(Color.BLACK);
        saveButton.setForeground(Color.WHITE);
        add(saveButton);
    }
    
    private void addLabeledTextField(String labelText, int yPosition, JTextField textField) {
        // Create and add label
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Tahoma", Font.PLAIN, 17));
        label.setBounds(60, yPosition, 150, 27);
        add(label);
        
        // Create and add text field
        textField = new JTextField();
        textField.setBounds(200, yPosition, 150, 27);
        add(textField);
    }
    
    private void addActionListeners() {
        // Add action listener to Save button
        saveButton.addActionListener(event -> saveCustomer());
    }

    private void saveCustomer() {
        // Retrieve values from text fields
        String passportNo = passportField.getText();
        String pnrNo = pnrField.getText();
        String address = addressField.getText();
        String nationality = nationalityField.getText();
        String name = nameField.getText();
        String flightCode = flightCodeField.getText();
        String phoneNo = phoneField.getText();
        
        // Determine gender
        String gender = maleRadioButton.isSelected() ? "Male" : "Female";

        // Prepare and execute the SQL insert statement
        try {
            Statement statement = databaseConnection.getConnection().createStatement();
            String query = String.format(
                "INSERT INTO passenger (pnr_no, address, nationality, name, gender, ph_no, passport_no, flight_code) " +
                "VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')",
                pnrNo, address, nationality, name, gender, phoneNo, passportNo, flightCode
            );
            
            statement.executeUpdate(query);
            
            // Display success message
            JOptionPane.showMessageDialog(this, "Customer Added Successfully");
            setVisible(false);
        } catch (SQLException e) {
            // Handle SQL exceptions
            JOptionPane.showMessageDialog(this, "Failed to add customer: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new Add_Customer());
    }
}
