package view;

import javax.swing.*;
import java.awt.*;

public class AddReviewView {
    private JFrame frame = new JFrame();
    private JPanel mainPanel = new JPanel(new BorderLayout());
    private JPanel titlePanel = new JPanel();
    private JLabel titleLabel = new JLabel();
    private JPanel centerPanel = new JPanel(new GridBagLayout());
    private JComboBox<String> hotelDropDown = new JComboBox<>(new String[]{"LAUR_HOTELS", "OZEN_RESERVE_BOLIFUSHI", "SHANGRI_LA_THE_SHARD", "IKOS_ANDALUSIA", "CHALET_DEL_SOGNO"});
    private JTextArea reviewTextArea = new JTextArea();
    private JComboBox<Integer> ratingTextField = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5});
    private JButton addReviewButton = new JButton("Add Review");
    private JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    private JButton backButton = new JButton("Back");

    public String getHotelDropDown() {
        return String.valueOf(hotelDropDown.getSelectedItem());
    }

    public String getReviewTextField() {
        return reviewTextArea.getText();
    }

    public int getRatingTextField() {
        return Integer.parseInt(String.valueOf(ratingTextField.getSelectedItem()));
    }

    public JButton getBackButton() {
        return backButton;
    }

    public JButton getAddReviewButton() {
        return addReviewButton;
    }

    public AddReviewView() {
        frame.setName("Add Review");
        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mainPanel.setBackground(Color.decode("#D6EAF8"));

        titlePanel.setBackground(Color.decode("#D6EAF8"));
        titleLabel.setText("Add Review Form");
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

        hotelDropDown.setPreferredSize(new Dimension(300, 30));
        reviewTextArea.setPreferredSize(new Dimension(400, 50));
        ratingTextField.setPreferredSize(new Dimension(300, 30));

        addFieldWithLabel("Hotel:", centerPanel, hotelDropDown, gbc);
        addFieldWithLabel("Review Text:", centerPanel, createScrollableTextArea(), gbc);
        addFieldWithLabel("Rating:", centerPanel, ratingTextField, gbc);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        addReviewButton.setBackground(new Color(30, 144, 255));
        addReviewButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(30, 144, 255));
        backButton.setForeground(Color.WHITE);
        buttonPanel.setBackground(Color.decode("#D6EAF8"));
        buttonPanel.add(backButton);
        buttonPanel.add(Box.createHorizontalStrut(20));
        buttonPanel.add(addReviewButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private JScrollPane createScrollableTextArea() {
        JScrollPane scrollPane = new JScrollPane(reviewTextArea);
        scrollPane.setPreferredSize(new Dimension(300, 70));
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        return scrollPane;
    }

    private void addFieldWithLabel(String labelText, JPanel panel, JComponent component, GridBagConstraints gbc) {
        JPanel fieldPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        fieldPanel.setBackground(Color.decode("#D6EAF8"));
        JLabel label = new JLabel(labelText);
        label.setPreferredSize(new Dimension(150, 30));
        fieldPanel.add(label);
        fieldPanel.add(component);
        panel.add(fieldPanel, gbc);

        gbc.gridy++;
    }

    public void setVisibility(boolean isVisible) {
        frame.setVisible(isVisible);
    }

    public void showMessage(String message, int option) {
        if (option == 0) {
            JOptionPane.showMessageDialog(frame, message, "Add Review Message", JOptionPane.ERROR_MESSAGE);
        }
        if (option == 1) {
            JOptionPane.showMessageDialog(frame, message, "Add Review Message", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
