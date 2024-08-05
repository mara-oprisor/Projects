package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class HotelBookingsView {
    private JFrame frame = new JFrame("Hotel Bookings");
    private JTable bookingTable;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;
    private JPanel titlePane = new JPanel();
    private JPanel buttonPane = new JPanel(new FlowLayout());
    private JLabel titleLabel = new JLabel("Hotel Bookings");
    private JButton backButton = new JButton("Back");

    public JTable getBookingTable() {
        return bookingTable;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public HotelBookingsView() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.setBackground(Color.decode("#D6EAF8"));

        // Set the background color, font, and color for consistency
        Font titleFont = new Font("Arial", Font.BOLD, 24);
        titleLabel.setFont(titleFont);
        titlePane.setBackground(Color.decode("#D6EAF8"));
        titleLabel.setForeground(Color.decode("#5DADE2"));
        titlePane.add(titleLabel);

        // Create the table model and set up the JTable
        tableModel = new DefaultTableModel();
        bookingTable = new JTable(tableModel);

        tableModel.addColumn("Username");
        tableModel.addColumn("Room Type");
        tableModel.addColumn("Check-In Date");
        tableModel.addColumn("Check-out Date");
        tableModel.addColumn("Total Price");

        // Set background color for the table
        bookingTable.setBackground(Color.decode("#D6EAF8"));

        // Set up the JScrollPane
        scrollPane = new JScrollPane(bookingTable);
        scrollPane.setBackground(Color.decode("#D6EAF8"));

        // Set up the back button
        backButton.setBackground(new Color(30, 144, 255));
        backButton.setForeground(Color.WHITE);
        backButton.setPreferredSize(new Dimension(80, 30)); // Set preferred size

        buttonPane.setBackground(Color.decode("#D6EAF8"));
        buttonPane.add(backButton);

        // Add components to the frame
        frame.add(titlePane, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(buttonPane, BorderLayout.SOUTH); // Adjust placement

        // Set background color for consistency
        frame.getContentPane().setBackground(Color.decode("#D6EAF8"));
    }

    public void setVisibility(boolean isVisible) {
        frame.setVisible(isVisible);
    }
}
