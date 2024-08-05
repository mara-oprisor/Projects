package presentation.view;

import presentation.controller.WelcomeController;

import javax.swing.*;
import java.awt.*;

/**
 * The WelcomeView class represents the graphical user interface for the welcome screen.
 * It allows users to navigate to different operations such as managing clients, products, or creating orders.
 */
public class WelcomeView {
    private JFrame frame = new JFrame("Welcome");
    private JPanel contentPane = new JPanel(new BorderLayout());
    private JPanel centerPane = new JPanel(new GridLayout(0, 3, 5, 40));
    private JPanel titlePane = new JPanel();
    private JLabel titleLabel = new JLabel("WELCOME");
    private JButton clientButton = new JButton("<html>Add/Update/Delete/Display <br>  &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; clients</html>");
    private JButton productButton = new JButton("<html>Add/Update/Delete/Display <br> &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; products</html>");
    private JButton orderButton = new JButton("Create order");
    private WelcomeController welcomeController = new WelcomeController(this);

    /**
     * Constructs a WelcomeView object and initializes the GUI components.
     */
    public WelcomeView() {
        setUpGUI();
        frame.setVisible(true);
    }

    /**
     * Sets up the graphical user interface.
     */
    private void setUpGUI() {
        frame.setSize(new Dimension(700, 500));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        contentPane.setBackground(Color.decode("#D8C7CE"));
        createTitlePane();
        createButtonPane();
        contentPane.add(titlePane, BorderLayout.NORTH);
        contentPane.add(centerPane, SwingConstants.CENTER);
        frame.setContentPane(contentPane);
    }

    /**
     * Creates the title pane with the title label.
     */
    private void createTitlePane() {
        titlePane.setBackground(Color.decode("#D8C7CE"));
        titleLabel.setForeground(Color.decode("#B97375"));
        titleLabel.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titlePane.add(titleLabel);
    }

    /**
     * Creates the button pane with buttons for client, product, and order operations.
     */
    private void createButtonPane() {
        centerPane.setBackground(Color.decode("#D8C7CE"));

        clientButton.setBackground(Color.decode("#C4929A"));
        productButton.setBackground(Color.decode("#C4929A"));
        orderButton.setBackground(Color.decode("#C4929A"));

        centerPane.add(new JLabel());
        centerPane.add(new JLabel());
        centerPane.add(new JLabel());
        centerPane.add(new JLabel());
        centerPane.add(clientButton);
        centerPane.add(new JLabel());
        centerPane.add(new JLabel());
        centerPane.add(productButton);
        centerPane.add(new JLabel());
        centerPane.add(new JLabel());
        centerPane.add(orderButton);
        centerPane.add(new JLabel());
        centerPane.add(new JLabel());
        centerPane.add(new JLabel());
        centerPane.add(new JLabel());
    }

    /**
     * Sets the visibility of the frame.
     *
     * @param status The visibility status.
     */
    public void setVisibility(boolean status) {
        this.frame.setVisible(status);
    }

    /**
     * Retrieves the button for managing clients.
     *
     * @return The JButton object representing the button for managing clients.
     */
    public JButton getClientButton() {
        return clientButton;
    }

    /**
     * Retrieves the button for managing products.
     *
     * @return The JButton object representing the button for managing products.
     */
    public JButton getProductButton() {
        return productButton;
    }

    /**
     * Retrieves the button for creating orders.
     *
     * @return The JButton object representing the button for creating orders.
     */
    public JButton getOrderButton() {
        return orderButton;
    }
}
