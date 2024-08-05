package view;

import javax.swing.*;
import java.awt.*;

public class SignUpView {
    private JFrame frame = new JFrame("SignUp Form");
    private JPanel mainPanel = new JPanel(new BorderLayout());
    private JPanel centerPanel = new JPanel(new GridBagLayout());

    private JLabel labelUsername = new JLabel("Username:");
    private JLabel labelPassword = new JLabel("Password:");
    private JLabel labelTitle = new JLabel("SignUp Form");
    private ImageIcon icon = new ImageIcon("src/images/icons8-sign-up-80.png");
    private JLabel labelSubtitle = new JLabel(icon);
    private JLabel labelType = new JLabel("Type of user:");

    private JTextField textFieldUsername = new JTextField();
    private JPasswordField textFieldPassword = new JPasswordField();
    private JComboBox<String> dropDownType = new JComboBox<>(new String[]{"Client"});

    private JButton signUpButton = new JButton("SignUp");
    private JButton backButton = new JButton("Back");

    public JButton getSignUpButton() {
        return signUpButton;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public String getTextFieldUsername() {
        return textFieldUsername.getText();
    }

    public String getTextFieldPassword() {
        return String.valueOf(textFieldPassword.getPassword());
    }

    public String getDropDownType() {
        return String.valueOf(dropDownType.getSelectedItem());
    }

    public SignUpView() {
        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel.setBackground(Color.decode("#D6EAF8"));

        Font labelFont = new Font("Arial", Font.BOLD, 16);
        Color labelColor = new Color(60, 60, 60);
        labelTitle.setFont(new Font("Arial", Font.BOLD, 30));
        labelTitle.setForeground(Color.decode("#5DADE2"));
        labelSubtitle.setFont(new Font("Arial", Font.PLAIN, 16));
        labelSubtitle.setForeground(Color.decode("#7DF9FF"));
        labelUsername.setFont(labelFont);
        labelUsername.setForeground(labelColor);
        labelPassword.setFont(labelFont);
        labelPassword.setForeground(labelColor);
        labelType.setFont(labelFont);
        labelType.setForeground(labelColor);

        textFieldUsername.setPreferredSize(new Dimension(200, 30));
        textFieldPassword.setPreferredSize(new Dimension(200, 30));

        dropDownType.setPreferredSize(new Dimension(200, 30));
        signUpButton.setPreferredSize(new Dimension(100, 30));
        signUpButton.setBackground(new Color(30, 144, 255));
        signUpButton.setForeground(Color.WHITE);

        backButton.setPreferredSize(new Dimension(100, 30));
        backButton.setBackground(new Color(30, 144, 255));
        backButton.setForeground(Color.WHITE);

        centerPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.PAGE_START;
        centerPanel.add(labelTitle, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.PAGE_START;
        centerPanel.add(labelSubtitle, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        centerPanel.add(labelUsername, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        centerPanel.add(textFieldUsername, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_END;
        centerPanel.add(labelPassword, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_START;
        centerPanel.add(textFieldPassword, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.LINE_END;
        centerPanel.add(labelType, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.LINE_START;
        centerPanel.add(dropDownType, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        centerPanel.add(signUpButton, gbc);

        centerPanel.setBackground(Color.decode("#D6EAF8"));

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        JPanel spacerPanel = new JPanel();
        spacerPanel.setPreferredSize(new Dimension(900, 30));
        spacerPanel.setBackground(Color.decode("#D6EAF8"));

        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.setBackground(Color.decode("#D6EAF8"));
        southPanel.add(spacerPanel, BorderLayout.LINE_END);
        southPanel.add(backButton, BorderLayout.CENTER);

        mainPanel.add(southPanel, BorderLayout.SOUTH);

        frame.setContentPane(mainPanel);
        frame.setVisible(true);
    }

    public void setVisibility(boolean isVisible) {
        frame.setVisible(isVisible);
    }

    public void showMessage(String message, int option) {
        if (option == 0) {
            JOptionPane.showMessageDialog(frame, message, "SignUp Message", JOptionPane.ERROR_MESSAGE);
        }
        if (option == 1) {
            JOptionPane.showMessageDialog(frame, message, "SignUp Message", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
