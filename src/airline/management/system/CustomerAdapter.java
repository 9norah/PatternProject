
package airline.management.system;

public class CustomerAdapter implements CustomerType {
    private Add_Customer addCustomer;
    private CustomerType customerType;

    public CustomerAdapter(Add_Customer addCustomer, CustomerType customerType) {
        this.addCustomer = addCustomer;
        this.customerType = customerType;
    }

    @Override
    public void setCustomerDetails(String name, String passportNo, String nationality, String address, String gender, String phoneNumber, String flightCode, String pnrNo) {
        customerType.setCustomerDetails(name, passportNo, nationality, address, gender, phoneNumber, flightCode, pnrNo);
        addCustomer.setCustomerDetails(name, passportNo, nationality, address, gender, phoneNumber, flightCode, pnrNo);
    }
}
