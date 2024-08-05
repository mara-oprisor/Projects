package view;

import model.ReviewsModel;

import javax.swing.*;
import java.awt.*;

public class ReviewView {

    private JFrame frame = new JFrame("Reviews");
    private JPanel upperPanel = new JPanel(new FlowLayout());
    private JPanel contentPanel = new JPanel();
    private JScrollPane contentScrollPane = new JScrollPane(contentPanel);

    private JPanel lowerPanel = new JPanel();
    private JButton backButton = new JButton("Back");
    private JButton reviewButton = new JButton("Add a review");

    private JComboBox<String> dropdown1 = new JComboBox<>(new String[]{"All hotels", "LAUR_HOTELS", "OZEN_RESERVE_BOLIFUSHI", "SHANGRI_LA_THE_SHARD", "IKOS_ANDALUSIA", "CHALET_DEL_SOGNO"});
    private JComboBox<String> dropdown2 = new JComboBox<>(new String[]{"Default", "Ascending", "Descending"});
    private JLabel filterLabel = new JLabel("Filter by hotel:");
    private JLabel sortLabel = new JLabel("Sort by rating:");
    private JButton filterButton = new JButton("Filter");
    private JButton sortButton = new JButton("Sort");

    public JButton getBackButton() {
        return backButton;
    }

    public JButton getReviewButton() {
        return reviewButton;
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }

    public String getDropdown1() {
        return String.valueOf(dropdown1.getSelectedItem());
    }

    public String getDropdown2() {
        return String.valueOf(dropdown2.getSelectedItem());
    }

    public JButton getFilterButton() {
        return filterButton;
    }

    public JButton getSortButton() {
        return sortButton;
    }

    public JFrame getFrame() {
        return frame;
    }

    public ReviewView() {
        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        upperPanel.setBackground(Color.decode("#D6EAF8"));
        filterButton.setBackground(new Color(30, 144, 255));
        filterButton.setForeground(Color.WHITE);
        filterButton.setPreferredSize(new Dimension(80, 30));
        sortButton.setBackground(new Color(30, 144, 255));
        sortButton.setForeground(Color.WHITE);
        sortButton.setPreferredSize(new Dimension(80, 30));

        upperPanel.add(filterLabel);
        upperPanel.add(dropdown1);
        upperPanel.add(filterButton);
        upperPanel.add(sortLabel);
        upperPanel.add(dropdown2);
        upperPanel.add(sortButton);

        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.decode("#BBDCF4"));

        contentScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        lowerPanel.setLayout(new FlowLayout());
        lowerPanel.setBackground(Color.decode("#D6EAF8"));
        backButton.setBackground(new Color(30, 144, 255));
        backButton.setForeground(Color.WHITE);
        backButton.setPreferredSize(new Dimension(80, 30));
        lowerPanel.add(backButton);
        lowerPanel.add(Box.createRigidArea(new Dimension(700, 0)));
        reviewButton.setBackground(new Color(30, 144, 255));
        reviewButton.setForeground(Color.WHITE);
        reviewButton.setPreferredSize(new Dimension(120, 30));
        lowerPanel.add(reviewButton);

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
