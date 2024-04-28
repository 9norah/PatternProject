package airline.management.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Add_Customer extends JFrame {
    private JTextField textField, textField_1, textField_2, textField_3, textField_4, textField_5, textField_6;
    private CustomerAdapter customerAdapter;

    public Add_Customer() {
        getContentPane().setForeground(Color.BLUE);
        getContentPane().setBackground(Color.WHITE);
        setTitle("ADD CUSTOMER DETAILS");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(778, 486);
        getContentPane().setLayout(null);

        JLabel Passportno = new JLabel("PASSPORT NO");
        Passportno.setFont(new Font("Tahoma", Font.PLAIN, 17));
        Passportno.setBounds(60, 80, 150, 27);
        add(Passportno);

        textField = new JTextField();
        textField.setBounds(200, 80, 150, 27);
        add(textField);

        JButton Next = new JButton("SAVE");
        Next.setBounds(200, 420, 150, 30);
        Next.setBackground(Color.BLACK);
        Next.setForeground(Color.WHITE);
        add(Next);

        JLabel Pnrno = new JLabel("PNR NO");
        Pnrno.setFont(new Font("Tahoma", Font.PLAIN, 17));
        Pnrno.setBounds(60, 120, 150, 27);
        add(Pnrno);

        textField_1 = new JTextField();
        textField_1.setBounds(200, 120, 150, 27);
        add(textField_1);

        JLabel Address = new JLabel("ADDRESS");
        Address.setFont(new Font("Tahoma", Font.PLAIN, 17));
        Address.setBounds(60, 170, 150, 27);
        add(Address);

        textField_2 = new JTextField();
        textField_2.setBounds(200, 170, 150, 27);
        add(textField_2);

        JLabel Nationality = new JLabel("NATIONALITY");
        Nationality.setFont(new Font("Tahoma", Font.PLAIN, 17));
        Nationality.setBounds(60, 220, 150, 27);
        add(Nationality);

        textField_3 = new JTextField();
        textField_3.setBounds(200, 220, 150, 27);
        add(textField_3);

        JLabel Name = new JLabel("NAME");
        Name.setFont(new Font("Tahoma", Font.PLAIN, 17));
        Name.setBounds(60, 270, 150, 27);
        add(Name);

        textField_4 = new JTextField();
        textField_4.setBounds(200, 270, 150, 27);
        add(textField_4);

        JLabel Gender = new JLabel("GENDER");
        Gender.setFont(new Font("Tahoma", Font.PLAIN, 17));
        Gender.setBounds(60, 320, 150, 27);
        add(Gender);

        JRadioButton NewRadioButton = new JRadioButton("MALE");
        NewRadioButton.setBackground(Color.WHITE);
        NewRadioButton.setBounds(200, 320, 70, 27);
        add(NewRadioButton);

        JRadioButton Female = new JRadioButton("FEMALE");
        Female.setBackground(Color.WHITE);
        Female.setBounds(280, 320, 70, 27);
        add(Female);

        JLabel Phno = new JLabel("PH NO");
        Phno.setFont(new Font("Tahoma", Font.PLAIN, 17));
        Phno.setBounds(60, 370, 150, 27);
        add(Phno);

        textField_5 = new JTextField();
        textField_5.setBounds(200, 370, 150, 27);
        add(textField_5);

        setVisible(true);

        JLabel AddPassengers = new JLabel("ADD CUSTOMER DETAILS");
        AddPassengers.setForeground(Color.BLUE);
        AddPassengers.setFont(new Font("Tahoma", Font.PLAIN, 31));
        AddPassengers.setBounds(420, 24, 442, 35);
        add(AddPassengers);

        JLabel Flightcode = new JLabel("FLIGHT CODE");
        Flightcode.setFont(new Font("Tahoma", Font.PLAIN, 17));
        Flightcode.setBounds(60, 30, 150, 27);
        add(Flightcode);

        textField_6 = new JTextField();
        textField_6.setBounds(200, 30, 150, 27);
        add(textField_6);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("airline/management/system/icon/emp.png"));
        JLabel image = new JLabel(i1);
        image.setBounds(450, 80, 280, 410);
        add(image);

        // Initialize the customer adapter with `this` and a specific CustomerType implementation
        customerAdapter = new CustomerAdapter(this, new NormalCustomer());

        // Add ActionListener to the Next button
        Next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // Gather customer details from the text fields
                String passportNo = textField.getText();
                String pnrNo = textField_1.getText();
                String address = textField_2.getText();
                String nationality = textField_3.getText();
                String name = textField_4.getText();
                String flightCode = textField_6.getText();

                String gender = null;
                if (NewRadioButton.isSelected()) {
                    gender = "male";
                } else if (Female.isSelected()) {
                    gender = "female";
                }

                String phoneNumber = textField_5.getText();

                // Use the customer adapter to set the customer details
                customerAdapter.setCustomerDetails(name, passportNo, nationality, address, gender, phoneNumber, flightCode, pnrNo);
            }
        });

        setSize(900, 600);
        setVisible(true);
        setLocation(400, 200);
    }

    void setCustomerDetails(String name, String passportNo, String nationality, String address, String gender, String phoneNumber, String flightCode, String pnrNo) {
        // Logic to handle customer details (e.g., inserting them into a database)
        try {
            conn c = new conn();
            String str = "INSERT INTO passenger values( '" + pnrNo + "', '" + address + "', '" + nationality + "', '" + name + "', '" + gender + "', '" + phoneNumber + "', '" + passportNo + "', '" + flightCode + "')";
            c.s.executeUpdate(str);
            JOptionPane.showMessageDialog(null, "Customer Added");
            setVisible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Add_Customer();
    }
}
