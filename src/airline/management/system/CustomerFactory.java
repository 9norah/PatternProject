package airline.management.system;

public class CustomerFactory {

    // Factory method to create customer objects
    public static Customer createCustomer(
        String type,
        String pnrNo,
        String address,
        String nationality,
        String name,
        String gender,
        String phoneNo,
        String passportNo,
        String flightCode
    ) {
        if (type.equalsIgnoreCase("regular")) {
            return new RegularCustomer(pnrNo, address, nationality, name, gender, phoneNo, passportNo, flightCode);
        }
        // You can add more types of customers in the future as VIP ...etc

        // Return null if no matching type is found
        return null;
    }
}
