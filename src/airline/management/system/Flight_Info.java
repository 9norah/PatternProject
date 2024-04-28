package airline.management.system;

import net.proteanit.sql.DbUtils;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class Flight_Info extends JFrame {
    private JTable table;
    private JTextField textField;

    public static void main(String[] args) {
        new Flight_Info().setVisible(true);
    }

    public Flight_Info() {
        setTitle("Flight Information");
        getContentPane().setBackground(Color.WHITE);
        getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 13));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(860, 523);
        setLayout(null);

        JLabel flightDetailsLabel = new JLabel("FLIGHT INFORMATION");
        flightDetailsLabel.setFont(new Font("Tahoma", Font.PLAIN, 31));
        flightDetailsLabel.setForeground(new Color(100, 149, 237));
        flightDetailsLabel.setBounds(50, 20, 570, 35);
        add(flightDetailsLabel);

        JLabel flightCodeLabel = new JLabel("FLIGHT CODE");
        flightCodeLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
        flightCodeLabel.setBounds(50, 100, 200, 30);
        add(flightCodeLabel);

        textField = new JTextField();
        textField.setBounds(220, 100, 200, 30);
        add(textField);

        JButton showButton = new JButton("SHOW");
        showButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        showButton.setBounds(220, 150, 120, 30);
        add(showButton);

        showButton.addActionListener(this::showFlightInfo);

        table = new JTable();
        table.setBackground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(23, 250, 800, 300);
        scrollPane.setBackground(Color.WHITE);
        add(scrollPane);

        addTableLabels();

        setSize(900, 650);
        setVisible(true);
        setLocation(400, 200);
    }

    private void addTableLabels() {
        // Add table labels
        addLabel("FLIGHT CODE", 23, 220);
        addLabel("FLIGHT NAME", 145, 220);
        addLabel("SOURCE", 275, 220);
        addLabel("DESTINATION", 367, 220);
        addLabel("CAPACITY", 497, 220);
        addLabel("CLASS CODE", 587, 220);
        addLabel("CLASS NAME", 700, 220);
    }

    private void addLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Tahoma", Font.PLAIN, 13));
        label.setBounds(x, y, 120, 14);
        add(label);
    }

    private void showFlightInfo(ActionEvent ae) {
        String code = textField.getText();

        try {
            // Retrieve the singleton instance of Conn
            Conn connInstance = Conn.getInstance();
            Connection connection = connInstance.getConnection();

            // Prepare SQL query
            String query = "SELECT f_code, f_name, src, dst, capacity, class_code, class_name FROM flight INNER JOIN sector ON f_code = ?";

            // Prepare a statement
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, code);

            // Execute query and update table
            ResultSet resultSet = preparedStatement.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(resultSet));

            // Close the statement and the result set
            preparedStatement.close();
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
