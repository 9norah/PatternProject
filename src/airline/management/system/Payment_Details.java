package airline.management.system;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import net.proteanit.sql.DbUtils;

public class Payment_Details extends JFrame {
    private JTextField pnrNoField;
    private JTable table;
    private JButton showButton;
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Payment_Details::new);
    }

    public Payment_Details() {
        initialize();
    }

    private void initialize() {
        setTitle("Payment Details");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(960, 590);
        setLocationRelativeTo(null);
        setBackground(Color.WHITE);
        setLayout(null);

        JLabel titleLabel = new JLabel("PAYMENT DETAILS");
        titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 31));
        titleLabel.setForeground(Color.BLUE);
        titleLabel.setBounds(51, 17, 300, 39);
        add(titleLabel);

        JLabel pnrNoLabel = new JLabel("PNR No:");
        pnrNoLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
        pnrNoLabel.setBounds(60, 160, 150, 26);
        add(pnrNoLabel);

        pnrNoField = new JTextField();
        pnrNoField.setBounds(200, 160, 150, 26);
        add(pnrNoField);

        showButton = new JButton("SHOW");
        showButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
        showButton.setBackground(Color.BLACK);
        showButton.setForeground(Color.WHITE);
        showButton.setBounds(200, 210, 150, 26);
        add(showButton);

        showButton.addActionListener(this::handleShowButtonAction);

        table = new JTable();
        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setBounds(45, 329, 766, 87);
        add(tableScrollPane);

        JLabel pnrNoHeader = new JLabel("PNR No");
        pnrNoHeader.setFont(new Font("Tahoma", Font.PLAIN, 13));
        pnrNoHeader.setBounds(84, 292, 108, 26);
        add(pnrNoHeader);

        JLabel paidAmountHeader = new JLabel("Paid Amount");
        paidAmountHeader.setFont(new Font("Tahoma", Font.PLAIN, 13));
        paidAmountHeader.setBounds(183, 298, 92, 14);
        add(paidAmountHeader);

        JLabel payDateHeader = new JLabel("Pay Date");
        payDateHeader.setFont(new Font("Tahoma", Font.PLAIN, 13));
        payDateHeader.setBounds(322, 294, 101, 24);
        add(payDateHeader);

        JLabel chequeNoHeader = new JLabel("Cheque No");
        chequeNoHeader.setFont(new Font("Tahoma", Font.PLAIN, 13));
        chequeNoHeader.setBounds(455, 298, 114, 14);
        add(chequeNoHeader);

        JLabel cardNoHeader = new JLabel("Card No");
        cardNoHeader.setFont(new Font("Tahoma", Font.PLAIN, 13));
        cardNoHeader.setBounds(602, 299, 101, 19);
        add(cardNoHeader);

        JLabel phoneNoHeader = new JLabel("Phone No");
        phoneNoHeader.setFont(new Font("Tahoma", Font.PLAIN, 13));
        phoneNoHeader.setBounds(712, 294, 86, 24);
        add(phoneNoHeader);

        // Add icon or image if necessary.
        JLabel iconLabel = new JLabel(new ImageIcon(ClassLoader.getSystemResource("airline/management/system/icon/payment.png")));
        iconLabel.setBounds(425, 15, 239, 272);
        add(iconLabel);

        setVisible(true);
    }

    private void handleShowButtonAction(ActionEvent ae) {
        try {
            String pnrNo = pnrNoField.getText();

            // Retrieve singleton instance of Conn
            Conn connInstance = Conn.getInstance();
            Connection connection = connInstance.getConnection();

            String query = "SELECT pnr_no, paid_amt, pay_date, cheque_no, card_no, ph_no FROM payment WHERE pnr_no = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, pnrNo);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                table.setModel(DbUtils.resultSetToTableModel(resultSet));
            } else {
                JOptionPane.showMessageDialog(this, "No payment details found for the given PNR No.", "No Records Found", JOptionPane.INFORMATION_MESSAGE);
            }

            // Close resources
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred while fetching payment details. Please try again.", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
