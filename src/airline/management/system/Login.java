package airline.management.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton resetButton;
    private JButton submitButton;
    private JButton closeButton;

    public Login() {
        super("Login");

        setBackground(Color.WHITE);

        setLayout(new GridLayout(4, 2, 5, 5));

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
        add(usernameLabel);

        usernameField = new JTextField();
        add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
        add(passwordLabel);

        passwordField = new JPasswordField();
        add(passwordField);

        resetButton = new JButton("Reset");
        resetButton.addActionListener(this);
        add(resetButton);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);
        add(submitButton);

        closeButton = new JButton("Close");
        closeButton.addActionListener(this);
        add(closeButton);

        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == resetButton) {
            usernameField.setText("");
            passwordField.setText("");
        } else if (e.getSource() == closeButton) {
            System.exit(0);
        } else if (e.getSource() == submitButton) {
            try {
                // Retrieve singleton instance of Conn
                Conn connInstance = Conn.getInstance();
                Connection connection = connInstance.getConnection();

                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Use a PreparedStatement to execute the query
                String query = "SELECT * FROM login WHERE username = ? AND password = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    new Mainframe();
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Login");
                }

                // Close resources
                resultSet.close();
                preparedStatement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "An error occurred during login. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
