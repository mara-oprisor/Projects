package view;

import model.CountryName;
import model.HotelModel;
import model.HotelName;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.border.EmptyBorder;

public class HotelsView {

    private JFrame frame = new JFrame("Hotels");
    private JPanel mainPanel = new JPanel(new GridLayout(0, 1, 0, 10));
    private JScrollPane scrollPane = new JScrollPane(mainPanel);
    private JButton backButton = new JButton("Back");
    private ArrayList<JButton> photoButtons = new ArrayList<>();
    private ArrayList<JButton> bookButtons = new ArrayList<>();
    private ArrayList<HotelModel> hotels = HotelModel.addHotels();
    private JPanel panel1 = new JPanel();
    private JPanel panel2 = new JPanel();
    private JPanel panel3 = new JPanel();
    private JPanel panel4 = new JPanel();
    private JPanel panel5 = new JPanel();
    private JPanel buttonPanel = new JPanel(new BorderLayout());
    private JPanel contentPanel = new JPanel(new BorderLayout());

    public ArrayList<HotelModel> getHotels() {
        return hotels;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public ArrayList<JButton> getBookButtons() {
        return bookButtons;
    }

    public ArrayList<JButton> getPhotoButtons() {
        return photoButtons;
    }

    public HotelsView() {
        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.decode("#D6EAF8"));

        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(Color.decode("#BBDCF4"));

        for (int i = 0; i < 5; i++) {
            JButton photoButton = new JButton("View Photos");
            JButton bookButton = new JButton("Book a Room");
            photoButton.setPreferredSize(new Dimension(120, 30));
            bookButton.setPreferredSize(new Dimension(120, 30));
            photoButton.setBackground(new Color(30, 144, 255));
            bookButton.setBackground(new Color(30, 144, 255));
            photoButton.setForeground(Color.WHITE);
            bookButton.setForeground(Color.WHITE);

            photoButtons.add(photoButton);
            bookButtons.add(bookButton);
        }

        panel1 = createHotelPanel(hotels.get(0).getHotelName(), hotels.get(0).getDescription(), hotels.get(0).getPhotoPath(), hotels.get(0).getCountryName(), photoButtons.get(0), bookButtons.get(0));
        panel2 = createHotelPanel(hotels.get(1).getHotelName(), hotels.get(1).getDescription(), hotels.get(1).getPhotoPath(), hotels.get(1).getCountryName(), photoButtons.get(1), bookButtons.get(1));
        panel3 = createHotelPanel(hotels.get(2).getHotelName(), hotels.get(2).getDescription(), hotels.get(2).getPhotoPath(), hotels.get(2).getCountryName(), photoButtons.get(2), bookButtons.get(2));
        panel4 = createHotelPanel(hotels.get(3).getHotelName(), hotels.get(3).getDescription(), hotels.get(3).getPhotoPath(), hotels.get(3).getCountryName(), photoButtons.get(3), bookButtons.get(3));
        panel5 = createHotelPanel(hotels.get(4).getHotelName(), hotels.get(4).getDescription(), hotels.get(4).getPhotoPath(), hotels.get(4).getCountryName(), photoButtons.get(4), bookButtons.get(4));

        mainPanel.add(panel1);
        mainPanel.add(panel2);
        mainPanel.add(panel3);
        mainPanel.add(panel4);
        mainPanel.add(panel5);

        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        backButton.setPreferredSize(new Dimension(100, 30));
        backButton.setBackground(new Color(30, 144, 255));
        backButton.setForeground(Color.WHITE);

        buttonPanel.add(backButton, BorderLayout.WEST);
        buttonPanel.setBackground(Color.decode("#BBDCF4"));

        contentPanel.add(scrollPane, BorderLayout.CENTER);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(contentPanel);
        frame.setVisible(true);
    }

    private JPanel createHotelPanel(HotelName hotelName, String description, String path, CountryName countryName, JButton photoButton, JButton bookButton) {
        JPanel panel = new JPanel(new BorderLayout(10, 10));

        String formattedHotelName = hotelName.name().replace("_", " ") + " - " + countryName.name();

        Dimension panelSize = new Dimension(300, 600);
        panel.setPreferredSize(panelSize);

        panel.setBackground(Color.decode("#D6EAF8"));

        ImageIcon icon = new ImageIcon(path);
        JLabel imageLabel = new JLabel(icon);
        panel.add(imageLabel, BorderLayout.WEST);

        JPanel centerPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel(formattedHotelName);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.decode("#5DADE2"));

        JLabel textLabel = new JLabel("<html>" + description + "</html>");
        textLabel.setVerticalAlignment(JLabel.CENTER);

        centerPanel.add(titleLabel, BorderLayout.NORTH);
        centerPanel.add(textLabel, BorderLayout.CENTER);
        centerPanel.setBackground(Color.decode("#D6EAF8"));

        panel.add(centerPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        buttonPanel.add(photoButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(5, 5)));
        buttonPanel.add(bookButton);
        buttonPanel.setBackground(Color.decode("#D6EAF8"));
        panel.add(buttonPanel, BorderLayout.EAST);

        panel.setBorder(new EmptyBorder(5, 5, 5, 5));

        //button1.addActionListener(e -> openPhotoViews(formattedHotelName));
        // button2.addActionListener(e->openBooking(hotelName));

        return panel;
    }

    public void openPhotoViews(String hotelName) {
        JFrame photosFrame = new JFrame("Photos - " + hotelName);
        photosFrame.setSize(1000, 600);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        String src1 = null, src2 = null, src3 = null;

        switch (hotelName) {
            case "LAUR_HOTELS":
                src1 = "src/images/LaurHotel1.jpg";
                src2 = "src/images/LaurHotel2.jpg";
                src3 = "src/images/LaurHotel3.jpg";
                break;
            case "OZEN_RESERVE_BOLIFUSHI":
                src1 = "src/images/ozen1.jpg";
                src2 = "src/images/ozen2.jpg";
                src3 = "src/images/ozen3.jpg";
                break;
            case "SHANGRI_LA_THE_SHARD":
                src1 = "src/images/shangri1.jpg";
                src2 = "src/images/shangri2.jpg";
                src3 = "src/images/shangri3.jpg";
                break;
            case "IKOS_ANDALUSIA":
                src1 = "src/images/ikos1.jpg";
                src2 = "src/images/ikos2.jpg";
                src3 = "src/images/ikos3.jpg";
                break;
            case "CHALET_DEL_SOGNO":
                src1 = "src/images/chalet1.jpg";
                src2 = "src/images/chalet2.jpg";
                src3 = "src/images/chalet3.jpg";
                break;
            default:
                break;
        }

        ImageIcon imageIcon1 = new ImageIcon(src1);
        ImageIcon imageIcon2 = new ImageIcon(src2);
        ImageIcon imageIcon3 = new ImageIcon(src3);

        JPanel panel1 = new JPanel(new BorderLayout());
        panel1.setSize(600, 450);
        panel1.setBackground(Color.decode("#D6EAF8"));
        JLabel label1 = new JLabel(imageIcon1);
        label1.setHorizontalAlignment(JLabel.CENTER);
        panel1.add(label1, BorderLayout.CENTER);
        mainPanel.add(panel1);

        JPanel panel2 = new JPanel(new BorderLayout());
        JLabel label2 = new JLabel(imageIcon2);
        panel2.setSize(600, 450);
        panel2.setBackground(Color.decode("#D6EAF8"));
        label2.setHorizontalAlignment(JLabel.CENTER);
        panel2.add(label2, BorderLayout.CENTER);
        mainPanel.add(panel2);

        JPanel panel3 = new JPanel(new BorderLayout());
        JLabel label3 = new JLabel(imageIcon3);
        panel3.setSize(600, 450);
        panel3.setBackground(Color.decode("#D6EAF8"));
        label3.setHorizontalAlignment(JLabel.CENTER);
        panel3.add(label3, BorderLayout.CENTER);
        mainPanel.add(panel3);

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        photosFrame.add(scrollPane);

        photosFrame.setVisible(true);
    }

    public void setVisibility(boolean isVisible) {
        frame.setVisible(isVisible);
    }
}