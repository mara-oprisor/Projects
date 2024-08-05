package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MyBookingsView {
    private JFrame frame = new JFrame("My Bookings");
    private JTable bookingTable;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;
    private JPanel titlePane = new JPanel();
    private JPanel buttonPane = new JPanel(new FlowLayout());
    private JLabel titleLabel = new JLabel("My Bookings");
    private JButton backButton = new JButton("Back");

    public JTable getBookingTable() {
        return bookingTable;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public MyBookingsView() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.setBackground(Color.decode("#D6EAF8"));

        Font titleFont = new Font("Arial", Font.BOLD, 24);
        titleLabel.setFont(titleFont);
        titlePane.setBackground(Color.decode("#D6EAF8"));
        titleLabel.setForeground(Color.decode("#5DADE2"));
        titlePane.add(titleLabel);

        tableModel = new DefaultTableModel();
        bookingTable = new JTable(tableModel);

        tableModel.addColumn("Hotel Name");
        tableModel.addColumn("Room Type");
        tableModel.addColumn("Check-In Date");
        tableModel.addColumn("Check-out Date");
        tableModel.addColumn("Total Price");

        bookingTable.setBackground(Color.decode("#D6EAF8"));

        scrollPane = new JScrollPane(bookingTable);
        scrollPane.setBackground(Color.decode("#D6EAF8"));

        backButton.setBackground(new Color(30, 144, 255));
        backButton.setForeground(Color.WHITE);
        backButton.setPreferredSize(new Dimension(80, 30));

        buttonPane.setBackground(Color.decode("#D6EAF8"));
        buttonPane.add(backButton);

        frame.add(titlePane, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(buttonPane, BorderLayout.SOUTH);

        frame.getContentPane().setBackground(Color.decode("#D6EAF8"));
    }

    public void setVisibility(boolean isVisible) {
        frame.setVisible(isVisible);
    }
}

