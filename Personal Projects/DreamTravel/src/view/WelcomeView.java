package view;

import javax.swing.*;
import java.awt.*;

public class WelcomeView {

    private JFrame frame = new JFrame("Welcome to Dream Travel");
    private JPanel mainPanel = new JPanel(new GridLayout(1, 2));
    private JPanel leftPanel = new JPanel(new GridBagLayout());
    private ImageIcon imageIcon = new ImageIcon("src/images/Iconarchive-Seaside-Island.256.png");
    private JLabel labelBeforeTitle = new JLabel(imageIcon);
    private JLabel labelTitle = new JLabel("<html><p>&nbsp;&nbsp;&nbsp;Welcome To</p><p>DREAM TRAVEL</p></html>");
    private JLabel labelAfterTitle = new JLabel("Discover your dream destinations with us!");
    private JPanel rightPanel = new JPanel();
    private JLabel signUpLabel = new JLabel("Are you new or do you have an account?");
    private JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
    private JButton signUpButton = new JButton("SignUp");
    private JButton logInButton = new JButton("LogIn");

    public JButton getSignUpButton() {
        return signUpButton;
    }

    public JButton getLogInButton() {
        return logInButton;
    }

    public WelcomeView() {

        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.decode("#D6EAF8"));

        mainPanel.setBackground(Color.decode("#D6EAF8"));

        leftPanel.setBackground(Color.decode("#D6EAF8"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;

        gbc.gridy = 0;
        leftPanel.add(labelBeforeTitle, gbc);

        labelTitle.setFont(new Font("Arial", Font.BOLD, 30));
        labelTitle.setForeground(Color.decode("#5DADE2"));

        gbc.gridy = 1;
        leftPanel.add(labelTitle, gbc);

        labelAfterTitle.setFont(new Font("Arial", Font.PLAIN, 15));
        labelAfterTitle.setForeground(Color.decode("#5DADE2"));

        gbc.gridy = 2;
        leftPanel.add(labelAfterTitle, gbc);

        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBackground(Color.decode("#D6EAF8"));

        signUpLabel.setFont(new Font("Arial", Font.BOLD, 18));
        signUpLabel.setForeground(Color.decode("#5DADE2"));
        signUpLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        rightPanel.add(Box.createVerticalGlue());
        rightPanel.add(signUpLabel);

        buttonPanel.setBackground(Color.decode("#D6EAF8"));
        signUpButton.setPreferredSize(new Dimension(80, 30));
        signUpButton.setBackground(new Color(30, 144, 255));
        signUpButton.setForeground(Color.WHITE);
        logInButton.setPreferredSize(new Dimension(80, 30));
        logInButton.setBackground(new Color(30, 144, 255));
        logInButton.setForeground(Color.WHITE);
        buttonPanel.add(signUpButton);
        buttonPanel.add(logInButton);
        rightPanel.add(buttonPanel);

        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    public void setVisibility(boolean isVisible) {
        frame.setVisible(isVisible);
    }
}
