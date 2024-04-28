package airline.management.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conn {
    private static Conn instance;
    private Connection connection;

    // Private constructor to prevent instantiation from other classes
    private Conn() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql:///project4", "root", "");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Database connection error: " + e.getMessage());
        }
    }

    // Public method to provide access to the single instance
    public static synchronized Conn getInstance() {
        if (instance == null) {
            instance = new Conn();
        }
        return instance;
    }

    // Method to get the connection
    public Connection getConnection() {
        return connection;
    }
}
