package airline.management.system;

import java.sql.SQLException;

public abstract class Customer {

    protected String pnrNo;
    protected String address;
    protected String nationality;
    protected String name;
    protected String gender;
    protected String phoneNo;
    protected String passportNo;
    protected String flightCode;

    // Abstract method to save the customer data
    public abstract void save() throws SQLException;
}
