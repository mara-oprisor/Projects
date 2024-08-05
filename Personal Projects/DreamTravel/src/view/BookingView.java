package view;

import model.HotelModel;

import javax.swing.*;
import java.awt.*;

public class BookingView {
    private JFrame frame = new JFrame();
    private JPanel mainPanel = new JPanel(new BorderLayout());
    private JPanel titlePanel = new JPanel();
    private JLabel titleLabel = new JLabel();
    private JPanel centerPanel = new JPanel(new GridLayout());
    private JComboBox<String> roomTypeDropDown = new JComboBox<>(new String[]{"double room", "twin room", "triple room", "suite"});
    private JTextField checkInDateField = new JTextField();
    private JTextField checkOutDateField = new JTextField();
    private JButton bookButton = new JButton("Book");
    private JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    private JButton backButton = new JButton("Back");

    public JComboBox<String> getRoomTypeDropDown() {
        return roomTypeDropDown;
    }

    public JTextField getCheckInDateField() {
        return checkInDateField;
    }

    public JTextField getCheckOutDateField() {
        return checkOutDateField;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public JButton getBookButton() {
        return bookButton;
    }

    public BookingView(HotelModel hotel) {
        frame.setName("Booking " + hotel.getHotelName().toString());
        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mainPanel.setBackground(Color.decode("#D6EAF8"));

        titlePanel.setBackground(Color.decode("#D6EAF8"));
        titleLabel.setText("Booking Form for " + hotel.getHotelName().toString());
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setForeground(Color.decode("#5DADE2"));
        titlePanel.add(titleLabel);
        mainPanel.add(titlePanel, BorderLayout.NORTH);

        centerPanel.setBackground(Color.decode("#D6EAF8"));
        centerPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;

        addFieldWithLabel("Room Type:", centerPanel, roomTypeDropDown, gbc);
        addFieldWithLabel("Check-in Date:", centerPanel, checkInDateField, gbc);
        addFieldWithLabel("Check-out Date:", centerPanel, checkOutDateField, gbc);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        bookButton.setBackground(new Color(30, 144, 255));
        bookButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(30, 144, 255));
        backButton.setForeground(Color.WHITE);
        buttonPanel.setBackground(Color.decode("#D6EAF8"));
        buttonPanel.add(backButton);
        buttonPanel.add(Box.createHorizontalStrut(20));
        buttonPanel.add(bookButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        frame.add(mainPanel);
    }

    private void addFieldWithLabel(String labelText, JPanel panel, JComponent component, GridBagConstraints gbc) {
        JPanel fieldPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        fieldPanel.setBackground(Color.decode("#D6EAF8"));
        JLabel label = new JLabel(labelText);
        label.setPreferredSize(new Dimension(150, 30));
        component.setPreferredSize(new Dimension(200, 30));
        fieldPanel.add(label);
        fieldPanel.add(component);
        panel.add(fieldPanel, gbc);

        gbc.gridy++;
    }

    public void setVisibility(boolean isVisible){
        frame.setVisible(isVisible);
    }

    public void showMessage(String message, int option){
        if( option == 0) {
            JOptionPane.showMessageDialog(frame, message, "Booking Message", JOptionPane.ERROR_MESSAGE);
        }
        if(option ==1){
            JOptionPane.showMessageDialog(frame, message, "Booking Message", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
