package view;

import javax.swing.*;
import java.awt.*;

public class MenuClientView {
    private JFrame frame = new JFrame("Menu");
    private JPanel mainPanel = new JPanel();
    private JPanel leftPanel = new JPanel();
    private JPanel rightPanel = new JPanel();

    private ImageIcon img = new ImageIcon("src/images/MenuClientImage.jpg");
    private JLabel labelImage = new JLabel(img);

    private JLabel labelTitle = new JLabel("MENU");
    private JButton buttonDetails = new JButton("My Details");
    private JButton buttonReviews = new JButton("Hotel Reviews");
    private JButton buttonBookRoom = new JButton("Book A Room");
    private JButton buttonMyReviews = new JButton("My Reviews");
    private JButton buttonMyBooking = new JButton("My Bookings");

    public JButton getButtonDetails() {
        return buttonDetails;
    }

    public JButton getButtonReviews() {
        return buttonReviews;
    }

    public JButton getButtonBookRoom() {
        return buttonBookRoom;
    }

    public JButton getButtonMyReviews() {
        return buttonMyReviews;
    }

    public JButton getButtonMyBooking() {
        return buttonMyBooking;
    }

    public MenuClientView() {
        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel.setBackground(Color.decode("#D6EAF8"));

        mainPanel.setLayout(new BorderLayout());

        leftPanel.setLayout(new GridBagLayout());
        rightPanel.setLayout(new BorderLayout());

        Font titleFont = new Font("Arial", Font.BOLD, 40);
        Font buttonFont = new Font("Arial", Font.PLAIN, 16);

        labelTitle.setFont(titleFont);
        labelTitle.setForeground(Color.decode("#5DADE2"));

        buttonDetails.setFont(buttonFont);
        buttonReviews.setFont(buttonFont);
        buttonBookRoom.setFont(buttonFont);
        buttonMyReviews.setFont(buttonFont);
        buttonMyBooking.setFont(buttonFont);

        Dimension buttonSize = new Dimension(200, 40);
        buttonDetails.setPreferredSize(buttonSize);
        buttonDetails.setBackground(new Color(30, 144, 255));
        buttonDetails.setForeground(Color.WHITE);
        buttonReviews.setPreferredSize(buttonSize);
        buttonReviews.setBackground(new Color(30, 144, 255));
        buttonReviews.setForeground(Color.WHITE);
        buttonBookRoom.setPreferredSize(buttonSize);
        buttonBookRoom.setBackground(new Color(30, 144, 255));
        buttonBookRoom.setForeground(Color.WHITE);
        buttonMyReviews.setPreferredSize(buttonSize);
        buttonMyReviews.setBackground(new Color(30, 144, 255));
        buttonMyReviews.setForeground(Color.WHITE);
        buttonMyBooking.setPreferredSize(buttonSize);
        buttonMyBooking.setBackground(new Color(30, 144, 255));
        buttonMyBooking.setForeground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        leftPanel.add(labelTitle, gbc);

        gbc.gridy = 1;
        leftPanel.add(buttonDetails, gbc);

        gbc.gridy = 2;
        leftPanel.add(buttonReviews, gbc);

        gbc.gridy = 3;
        leftPanel.add(buttonBookRoom, gbc);

        gbc.gridy = 4;
        leftPanel.add(buttonMyReviews, gbc);

        gbc.gridy = 5;
        leftPanel.add(buttonMyBooking, gbc);

        rightPanel.add(labelImage);

        Dimension panelSize = new Dimension(500, 600);
        leftPanel.setPreferredSize(panelSize);
        rightPanel.setPreferredSize(panelSize);


        leftPanel.setBackground(Color.decode("#D6EAF8"));
        rightPanel.setBackground(Color.decode("#D6EAF8"));

        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(rightPanel, BorderLayout.EAST);

        frame.setContentPane(mainPanel);
    }

    public void setVisibility(boolean isVisible) {
        frame.setVisible(isVisible);
    }
}
