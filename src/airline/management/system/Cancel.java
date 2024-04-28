package airline.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Cancel extends JFrame {
    private JTextField passengerNoField, cancellationNoField, cancellationDateField, ticketIdField, flightCodeField;
    private JButton cancelButton;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new Cancel());
    }

    public Cancel() {
        initialize();
    }

    private void initialize() {
        setTitle("Cancellation");
        getContentPane().setBackground(Color.WHITE);
        setBounds(100, 100, 801, 472);
        setLayout(null);

        JLabel cancellationLabel = new JLabel("CANCELLATION");
        cancellationLabel.setFont(new Font("Tahoma", Font.PLAIN, 31));
        cancellationLabel.setBounds(185, 24, 259, 38);
        add(cancellationLabel);

        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("airline/management/system/icon/cancel.png"));
        Image image = icon.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon scaledIcon = new ImageIcon(image);
        JLabel iconLabel = new JLabel(scaledIcon);
        iconLabel.setBounds(470, 100, 250, 250);
        add(iconLabel);

        addLabeledTextField("PASSENGER NO", 100, passengerNoField);
        addLabeledTextField("CANCELLATION NO", 150, cancellationNoField);
        addLabeledTextField("CANCELLATION DATE", 200, cancellationDateField);
        addLabeledTextField("TICKET_ID", 250, ticketIdField);
        addLabeledTextField("FLIGHT_CODE", 300, flightCodeField);

        cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        cancelButton.setBackground(Color.BLACK);
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setBounds(250, 350, 150, 30);
        add(cancelButton);

        cancelButton.addActionListener(this::cancelTicket);

        setSize(860, 500);
        setVisible(true);
        setLocation(400, 200);
    }

    private void addLabeledTextField(String label, int yPosition, JTextField textField) {
        JLabel textLabel = new JLabel(label);
        textLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
        textLabel.setBounds(60, yPosition, 150, 27);
        add(textLabel);

        textField = new JTextField();
        textField.setBounds(250, yPosition, 150, 27);
        add(textField);
    }

    private void cancelTicket(ActionEvent event) {
        // Retrieve input values
        String passengerNo = passengerNoField.getText();
        String cancellationNo = cancellationNoField.getText();
        String cancellationDate = cancellationDateField.getText();
        String ticketId = ticketIdField.getText();
        String flightCode = flightCodeField.getText();

        // Get the singleton instance of the Conn class
        Conn connInstance = Conn.getInstance();
        Connection connection = connInstance.getConnection();

        try {
            // Prepare and execute SQL statement
            String query = "INSERT INTO cancellation (passenger_no, cancellation_no, cancellation_date, ticket_id, flight_code) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, passengerNo);
                preparedStatement.setString(2, cancellationNo);
                preparedStatement.setString(3, cancellationDate);
                preparedStatement.setString(4, ticketId);
                preparedStatement.setString(5, flightCode);

                preparedStatement.executeUpdate();
                JOptionPane.showMessageDialog(this, "Ticket Canceled");
                setVisible(false);
            }
        } catch (SQLException e) {
            // Handle SQL exception
            JOptionPane.showMessageDialog(this, "Failed to cancel ticket: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
