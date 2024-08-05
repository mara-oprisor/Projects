package view;

import javax.swing.*;
import java.awt.*;

public class ClientDetailsView {

    private JFrame frame = new JFrame("Details Form");
    private JPanel mainPanel = new JPanel(new BorderLayout());
    private JPanel titlePanel = new JPanel();
    private JPanel centerPanel = new JPanel(new GridBagLayout());
    private JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

    private JTextField nameField = new JTextField();
    private JTextField ageField = new JTextField();
    private JTextField dobField = new JTextField();
    private JTextField emailField = new JTextField();
    private JTextField phoneField = new JTextField();
    private JButton backButton = new JButton("Back");
    private JButton viewButton = new JButton("View Details");
    private JButton insertButton = new JButton("Insert Details");
    private JButton updateButton = new JButton("Update Details");
    private JButton deleteButton = new JButton("Delete Details");

    public JButton getInsertButton() {
        return insertButton;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public JButton getViewButton() {
        return viewButton;
    }

    public JButton getUpdateButton() {
        return updateButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public void setNameField(String name) {
        this.nameField.setText(name);
    }

    public void setAgeField(int age) {
        this.ageField.setText(String.valueOf(age));
    }

    public void setDobField(String dob) {
        this.dobField.setText(dob);
    }

    public void setEmailField(String email) {
        this.emailField.setText(email);
    }

    public void setPhoneField(String phone) {
        this.phoneField.setText(phone);
    }

    public String getNameField() {
        return nameField.getText();
    }

    public String getAgeField() {
        return ageField.getText();
    }

    public String getDobField() {
        return dobField.getText();
    }

    public String getEmailField() {
        return emailField.getText();
    }

    public String getPhoneField() {
        return phoneField.getText();
    }

    public ClientDetailsView() {
        //this view resembles with the LogIn, but has more fields and more buttons
        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel.setBackground(Color.decode("#D6EAF8"));

        titlePanel.setBackground(Color.decode("#D6EAF8"));
        JLabel titleLabel = new JLabel("Details Form");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setForeground(Color.decode("#5DADE2"));
        titlePanel.add(titleLabel);
        mainPanel.add(titlePanel, BorderLayout.NORTH);

        centerPanel.setBackground(Color.decode("#D6EAF8"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;

        addFieldWithLabel("Name:", nameField, gbc);
        addFieldWithLabel("Age:", ageField, gbc);
        addFieldWithLabel("Date of Birth:", dobField, gbc);
        addFieldWithLabel("Email:", emailField, gbc);
        addFieldWithLabel("Phone Number:", phoneField, gbc);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        buttonPanel.setBackground(Color.decode("#D6EAF8"));
        backButton.setBackground(new Color(30, 144, 255));
        backButton.setForeground(Color.WHITE);
        viewButton.setBackground(new Color(30, 144, 255));
        viewButton.setForeground(Color.WHITE);
        insertButton.setBackground(new Color(30, 144, 255));
        insertButton.setForeground(Color.WHITE);
        updateButton.setBackground(new Color(30, 144, 255));
        updateButton.setForeground(Color.WHITE);
        deleteButton.setBackground(new Color(30, 144, 255));
        deleteButton.setForeground(Color.WHITE);

        buttonPanel.add(backButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(300, 0)));//this creates a space between the back button and the other three buttons
        buttonPanel.add(viewButton);
        buttonPanel.add(insertButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private void addFieldWithLabel(String labelText, JTextField textField, GridBagConstraints gbc) {
        JPanel fieldPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        fieldPanel.setBackground(Color.decode("#D6EAF8"));
        JLabel label = new JLabel(labelText);
        label.setPreferredSize(new Dimension(150, 30));
        textField.setPreferredSize(new Dimension(200, 30));
        fieldPanel.add(label);
        fieldPanel.add(textField);
        centerPanel.add(fieldPanel, gbc);

        gbc.gridy++;
    }

    public void setVisibility(boolean isVisible) {
        frame.setVisible(isVisible);
    }
}
