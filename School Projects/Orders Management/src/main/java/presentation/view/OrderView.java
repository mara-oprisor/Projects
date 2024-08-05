package presentation.view;

import model.Bill;
import presentation.TableCreator;
import presentation.controller.OrderController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

/**
 * The OrderView class represents the graphical user interface for order operations.
 */
public class OrderView {
    private JFrame frame = new JFrame("Create Order");
    private JPanel contentPane = new JPanel(new BorderLayout());
    private JPanel titlePanel = new JPanel();
    private JLabel title = new JLabel("CREATE ORDER");
    private JPanel centerPane = new JPanel(new GridLayout(3, 1, 20, 5));
    private JScrollPane scrollClientPane = new JScrollPane();
    private JPanel clientPane = new JPanel(new BorderLayout());
    private JLabel selectClientTitle = new JLabel("Select Client");
    private JTable clientTable = new JTable();
    private JScrollPane scrollProductPane = new JScrollPane();
    private JPanel productPane = new JPanel(new BorderLayout());
    private JLabel selectProductTitle = new JLabel("Select Product");
    private JTable productTable = new JTable();
    private JPanel quantityPane = new JPanel(new GridLayout(5, 4, 30, 10));
    private JLabel quantityLabel = new JLabel("Insert Quantity");
    private JTextField quantityField = new JTextField();
    private JButton placeOrderButton = new JButton("Place Order");
    private JPanel backPane = new JPanel(new GridLayout(1, 6, 20, 5));
    private JButton backButton = new JButton("Back");
    private JButton billButton = new JButton("See bills");
    private JFrame billFrame = new JFrame("Bills");
    private JScrollPane billPanel = new JScrollPane();
    private JTable billTable = new JTable();
    private OrderController orderController = new OrderController(this);

    /**
     * Constructs a OrderView and initializes the GUI components.
     */
    public OrderView() {
        frame.setVisible(true);
        setUpGUI();
    }

    /**
     * Sets up the graphical user interface.
     */
    private void setUpGUI() {
        frame.setSize(new Dimension(1000, 700));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        contentPane.setBackground(Color.decode("#D8C7CE"));
        createTitlePane();
        contentPane.add(titlePanel, BorderLayout.NORTH);
        createCenterPane();
        contentPane.add(centerPane, BorderLayout.CENTER);
        createBackPane();
        contentPane.add(backPane, BorderLayout.SOUTH);
        frame.setContentPane(contentPane);
    }

    /**
     * Creates the title pane with the title label.
     */
    private void createTitlePane() {
        title.setForeground(Color.decode("#B97375"));
        title.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        titlePanel.setBackground(Color.decode("#D8C7CE"));
        titlePanel.add(title);
    }

    /**
     * Creates the center pane with the scroll panes and quantity pane
     */
    private void createCenterPane() {
        centerPane.setBackground(Color.decode("#D8C7CE"));
        clientTable.setRowSelectionAllowed(true);
        productTable.setRowSelectionAllowed(true);
        createScrollPane(scrollClientPane, clientPane, clientTable, selectClientTitle);
        createScrollPane(scrollProductPane, productPane, productTable, selectProductTitle);
        TableCreator.createTable(orderController.getOrderBLL().getClientBLL().getClients(), clientTable);
        TableCreator.createTable(orderController.getOrderBLL().getProductBLL().getProducts(), productTable);
        createQuantityPane();
        centerPane.add(scrollClientPane);
        centerPane.add(scrollProductPane);
        centerPane.add(quantityPane);
    }

    /**
     * Creates the scroll panels where are placed the tables
     */
    private void createScrollPane(JScrollPane scrollPane, JPanel panel, JTable table, JLabel label) {
        panel.setBackground(Color.decode("#D8C7CE"));
        label.setForeground(Color.decode("#B97375"));
        label.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        scrollPane.setViewportView(table);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * Creates the panel where the quantity is inserted.
     */
    private void createQuantityPane() {
        quantityPane.setBackground(Color.decode("#D8C7CE"));
        quantityPane.add(new JLabel());
        quantityPane.add(quantityLabel);
        quantityPane.add(quantityField);
        quantityPane.add(new JLabel());
        quantityPane.add(new JLabel());
        placeOrderButton.setBackground(Color.decode("#C4929A"));
        quantityPane.add(placeOrderButton);
        for(int i = 1; i <= 10; i++) {
            quantityPane.add(new JLabel());
        }
    }

    /**
     * Creates the back pane with the back button.
     */
    private void createBackPane() {
        backPane.setBackground(Color.decode("#D8C7CE"));
        backButton.setBackground(Color.decode("#C4929A"));
        billButton.setBackground(Color.decode("#C4929A"));
        backPane.add(backButton);
        backPane.add(new JLabel());
        backPane.add(new JLabel());
        backPane.add(new JLabel());
        backPane.add(new JLabel());
        backPane.add(billButton);
    }

    /**
     * Creates the frame with the table that contains the bills. It will be shown if the "See Bills" button will be pressed
     */
    public void seeBillFrame() {
        billFrame.setVisible(true);
        billFrame.setSize(new Dimension(1000, 700));
        createBillTable(orderController.getOrderBLL().getBills());

        billPanel.setViewportView(billTable);
        billFrame.getContentPane().add(billPanel);
    }


    /**
     * Creates a bill table in the UI based on the provided list of bills.
     * This method populates the table with bill data.
     *
     * @param bills An ArrayList containing bill objects to populate the table.
     */
    private void createBillTable(ArrayList<Bill> bills) {
        DefaultTableModel tableModel = (DefaultTableModel) billTable.getModel();

        tableModel.setRowCount(0);
        tableModel.setColumnCount(3);
        String[] columnNames = {"ID", "ID client", "price"};
        tableModel.setColumnIdentifiers(columnNames);


        for(Bill bill: bills) {
            Object[] row = new Object[3];
            row[0] = bill.id();
            row[1] = bill.idClient();
            row[2] = bill.price();

            tableModel.addRow(row);
        }
    }

    /**
     * Updates the tables.
     */
    public void updateTables() {
        createCenterPane();
    }

    /**
     * Retrieves the table used for showing all clients.
     *
     * @return The JTable object representing the table used for showing all clients.
     */
    public JTable getClientTable() {
        return clientTable;
    }

    /**
     * Retrieves the table used for showing all products.
     *
     * @return The JTable object representing the table used for showing all products.
     */
    public JTable getProductTable() {
        return productTable;
    }

    /**
     * Retrieves the text field used for entering the quantity of the product to be ordered.
     *
     * @return The JTextField object representing the text field for entering quantity of the product to be ordered.
     */
    public JTextField getQuantityField() {
        return quantityField;
    }

    /**
     * Retrieves the button for creating an order.
     *
     * @return The JButton object representing the button for creating an order.
     */
    public JButton getPlaceOrderButton() {
        return placeOrderButton;
    }

    /**
     * Retrieves the button for going back to the WelcomeView.
     *
     * @return The JButton object for going back to the WelcomeView.
     */
    public JButton getBackButton() {
        return backButton;
    }

    /**
     * Retrieves the button used for seeing all bills.
     *
     * @return The JButton object used for seeing all bills.
     */
    public JButton getBillButton() {
        return billButton;
    }

    /**
     * Retrieves the main frame of the client operations view.
     *
     * @return The JFrame object representing the main frame of the client operations view.
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     * Sets the visibility of the frame.
     *
     * @param status The visibility status.
     */
    public void setVisibility(boolean status) {
        this.frame.setVisible(status);
    }
}
