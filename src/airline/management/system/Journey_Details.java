package airline.management.system;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import net.proteanit.sql.DbUtils;

public class Journey_Details extends JFrame {

    private JTable table;
    private JComboBox<String> comboBox;
    private JComboBox<String> comboBox1;
    
    public static void main(String[] args) {
        new Journey_Details().setVisible(true);
    }
    
    public Journey_Details() {
        setTitle("Journey Details");
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        
        JLabel sourceLabel = new JLabel("SOURCE");
        sourceLabel.setFont(new Font("Tahoma", Font.PLAIN, 19));
        sourceLabel.setBounds(60, 100, 150, 27);
        add(sourceLabel);
        
        JLabel destinationLabel = new JLabel("DESTINATION");
        destinationLabel.setFont(new Font("Tahoma", Font.PLAIN, 19));
        destinationLabel.setBounds(350, 100, 150, 27);
        add(destinationLabel);
        
        JButton showButton = new JButton("SHOW");
        showButton.setBounds(680, 100, 100, 30);
        add(showButton);
        
        JLabel journeyDetailsLabel = new JLabel("JOURNEY DETAILS");
        journeyDetailsLabel.setForeground(Color.BLUE);
        journeyDetailsLabel.setFont(new Font("Tahoma", Font.PLAIN, 31));
        journeyDetailsLabel.setBounds(280, 27, 359, 31);
        add(journeyDetailsLabel);
        
        initializeTableHeaders();
        
        String[] items = {"BANGALORE", "MUMBAI", "CHENNAI", "PATNA", "DELHI", "HYDERABAD"};
        comboBox = new JComboBox<>(items);
        comboBox.setBounds(150, 100, 150, 27);
        add(comboBox);
        
        comboBox1 = new JComboBox<>(items);
        comboBox1.setBounds(500, 100, 150, 27);
        add(comboBox1);
        
        table = new JTable();
        table.setBounds(38, 310, 770, 130);
        add(new JScrollPane(table));
        
        showButton.addActionListener(this::showJourneyDetails);
        
        setSize(860, 600);
        setLocation(400, 200);
        setVisible(true);
    }
    
    private void initializeTableHeaders() {
        JLabel pnrNoLabel = new JLabel("PNR_NO");
        pnrNoLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        pnrNoLabel.setBounds(79, 270, 83, 20);
        add(pnrNoLabel);
        
        JLabel ticketIdLabel = new JLabel("TICKET_ID");
        ticketIdLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        ticketIdLabel.setBounds(172, 270, 71, 20);
        add(ticketIdLabel);
        
        JLabel fCodeLabel = new JLabel("F_CODE");
        fCodeLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        fCodeLabel.setBounds(297, 270, 103, 20);
        add(fCodeLabel);
        
        JLabel jnyDateLabel = new JLabel("JNY_DATE");
        jnyDateLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        jnyDateLabel.setBounds(390, 270, 94, 20);
        add(jnyDateLabel);
        
        JLabel jnyTimeLabel = new JLabel("JNY_TIME");
        jnyTimeLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        jnyTimeLabel.setBounds(494, 270, 83, 20);
        add(jnyTimeLabel);
        
        JLabel sourceLabel = new JLabel("SOURCE");
        sourceLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        sourceLabel.setBounds(613, 270, 94, 20);
        add(sourceLabel);
        
        JLabel destinationLabel = new JLabel("DESTINATION");
        destinationLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        destinationLabel.setBounds(717, 270, 94, 20);
        add(destinationLabel);
    }
    
    private void showJourneyDetails(ActionEvent ae) {
        try {
            String src = (String) comboBox.getSelectedItem();
            String dst = (String) comboBox1.getSelectedItem();
            
            // Retrieve the singleton instance of Conn
            Conn connInstance = Conn.getInstance();
            Connection connection = connInstance.getConnection();

            // Prepare SQL query
            String query = "SELECT pnr_no, ticket_id, f_code, jny_date, jny_time, src, dst FROM reservation WHERE src = ? AND dst = ?";
            
            // Prepare a statement
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, src);
            preparedStatement.setString(2, dst);
            
            // Execute query and update table
            ResultSet resultSet = preparedStatement.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
            
            // Close the statement and the result set
            preparedStatement.close();
            resultSet.close();
            
            if (!resultSet.next()) {
                JOptionPane.showMessageDialog(null, "No Flights between Source and Destination");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
