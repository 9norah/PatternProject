package airline.management.system;

import java.sql.*;

public class RegularCustomer extends Customer {

    public RegularCustomer(String pnrNo, String address, String nationality, String name, String gender, String phoneNo, String passportNo, String flightCode) {
        this.pnrNo = pnrNo;
        this.address = address;
        this.nationality = nationality;
        this.name = name;
        this.gender = gender;
        this.phoneNo = phoneNo;
        this.passportNo = passportNo;
        this.flightCode = flightCode;
    }

    // Implementation of save method
    @Override
    public void save() throws SQLException {
        // Get the database connection
        Conn databaseConnection = Conn.getInstance();
        Statement statement = databaseConnection.getConnection().createStatement();

        // Create the SQL insert statement
        String query = String.format(
            "INSERT INTO passenger (pnr_no, address, nationality, name, gender, ph_no, passport_no, flight_code) " +
            "VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')",
            pnrNo, address, nationality, name, gender, phoneNo, passportNo, flightCode
        );

        // Execute the query
        statement.executeUpdate(query);
    }
}
