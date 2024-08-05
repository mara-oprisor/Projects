package view;

import model.ReviewsModel;

import javax.swing.*;
import java.awt.*;

public class MyReviewView {

    private JFrame frame = new JFrame("My Reviews");
    private JPanel upperPanel = new JPanel();
    private JPanel contentPanel = new JPanel();
    private JScrollPane contentScrollPane = new JScrollPane(contentPanel);
    private JLabel titleLabel = new JLabel("My Reviews");

    private JPanel lowerPanel = new JPanel();
    private JButton backButton = new JButton("Back");


    public JButton getBackButton() {
        return backButton;
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }

    public MyReviewView() {
        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setForeground(Color.decode("#5DADE2"));

        upperPanel.setBackground(Color.decode("#D6EAF8"));
        upperPanel.add(titleLabel);

        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.decode("#BBDCF4"));

        contentScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        lowerPanel.setLayout(new FlowLayout());
        lowerPanel.setBackground(Color.decode("#D6EAF8"));
        backButton.setBackground(new Color(30, 144, 255));
        backButton.setForeground(Color.WHITE);
        backButton.setPreferredSize(new Dimension(80, 30));
        lowerPanel.add(backButton);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.decode("#D6EAF8"));
        mainPanel.add(upperPanel, BorderLayout.NORTH);
        mainPanel.add(contentScrollPane, BorderLayout.CENTER);
        mainPanel.add(lowerPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);
        frame.setVisible(true);

        createReviewPanels();
    }

    private JPanel createReviewPanel(String hotelName, String reviewText, String username, ImageIcon userPhoto) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.decode("#D6EAF8"));

        JLabel photoLabel = new JLabel(userPhoto);
        panel.add(photoLabel, BorderLayout.WEST);

        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(Color.decode("#D6EAF8"));
        contentPanel.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel(hotelName);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        JLabel textLabel = new JLabel("<html>" + reviewText.replaceAll("\n", "<br>") + "</html>");

        JLabel usernameLabel = new JLabel("user: " + username);
        usernameLabel.setHorizontalAlignment(JLabel.RIGHT);

        contentPanel.add(titleLabel, BorderLayout.NORTH);
        contentPanel.add(textLabel, BorderLayout.CENTER);
        contentPanel.add(usernameLabel, BorderLayout.SOUTH);

        panel.add(contentPanel, BorderLayout.CENTER);

        return panel;
    }

    private void addPanel(JPanel panel) {
        contentPanel.add(panel);
        frame.revalidate();
        frame.repaint();
    }

    private void createReviewPanels() {
        for (ReviewsModel review : ReviewsModel.getReviews()) {
            ImageIcon imageIcon = new ImageIcon(review.getUrlPhoto());
            JPanel reviewPanel = createReviewPanel(review.getHotelName(), review.getReviewText(), review.getUsername(), imageIcon);
            reviewPanel.setBackground(Color.decode("#D6EAF8"));
            addPanel(reviewPanel);
        }
    }

    public void displayReviews() {
        for (ReviewsModel review : ReviewsModel.getReviews()) {
            ImageIcon imageIcon = new ImageIcon(review.getUrlPhoto());
            JPanel reviewPanel = this.createReviewPanel(review.getHotelName(), review.getReviewText(), review.getUsername(), imageIcon);
            reviewPanel.setBackground(Color.decode("#D6EAF8"));
            this.addPanel(reviewPanel);
        }
    }

    public void setVisibility(boolean isVisible) {
        frame.setVisible(isVisible);
    }

}
