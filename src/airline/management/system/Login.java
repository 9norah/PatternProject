package airline.management.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Login extends JFrame implements LoginBehavior, ActionListener {

    TextField t1, t2;
    Label l1, l2;
    Button b2, b3, b4;
    GridBagLayout gbl;
    GridBagConstraints gbc;
    Font f1, f2;
    private boolean loginSuccessful = false;

    public Login() {
        super("Login");
        // Setup layout and components
        setBackground(Color.WHITE);
        f1 = new Font("TimesRoman", Font.BOLD, 20);
        f2 = new Font("TimesRoman", Font.BOLD, 15);

        gbl = new GridBagLayout();
        gbc = new GridBagConstraints();
        setLayout(gbl);

        l1 = new Label("Username");
        l1.setFont(f1);
        l2 = new Label("Password");
        l2.setFont(f1);

        t1 = new TextField(15);
        t2 = new TextField(15);
        t2.setEchoChar('*');

        b2 = new Button("Reset");
        b2.setFont(f2);
        b3 = new Button("Submit");
        b3.setFont(f2);
        b4 = new Button("Close");
        b4.setFont(f2);

        // Set up layout constraints for components
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbl.setConstraints(l1, gbc);
        add(l1);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbl.setConstraints(t1, gbc);
        add(t1);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbl.setConstraints(l2, gbc);
        add(l2);

        gbc.gridx = 2;
        gbc.gridy = 2;
        gbl.setConstraints(t2, gbc);
        add(t2);

        gbc.gridx = 0;
        gbc.gridy = 8;
        gbl.setConstraints(b2, gbc);
        add(b2);

        gbc.gridx = 2;
        gbc.gridy = 8;
        gbl.setConstraints(b3, gbc);
        add(b3);

        gbc.gridx = 4;
        gbc.gridy = 8;
        gbl.setConstraints(b4, gbc);
        add(b4);

        // Add action listeners
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);

        setVisible(true);
        setSize(400, 250);
        setLocation(400, 200);
    }

    // Implementation of the login method from the LoginBehavior interface
    @Override
    public void login(String username, String password) {
        try {
            conn c1 = new conn();
            String str = "select * from login where username = '" + username + "' and password = '" + password + "'";
            ResultSet rs = c1.s.executeQuery(str);

            if (rs.next()) {
                new Mainframe();
                setVisible(false);
                loginSuccessful = true;
            } else {
                JOptionPane.showMessageDialog(null, "Invalid Login");
                setVisible(false);
                loginSuccessful = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Getter method to check if the login was successful
    public boolean isLoginSuccessful() {
        return loginSuccessful;
    }

    // Handle button actions
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b2) {
            t1.setText("");
            t2.setText("");
        } else if (ae.getSource() == b4) {
            System.exit(0);
        } else if (ae.getSource() == b3) {
            String username = t1.getText();
            String password = t2.getText();

            // Use the LoginLogger decorator for the login operation
            LoginBehavior loginLogger = new LoginLogger(this);
            loginLogger.login(username, password);
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
