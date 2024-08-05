package presentation.view;

import presentation.TableCreator;
import presentation.controller.ProductController;

import javax.swing.*;
import java.awt.*;

/**
 * The ProductView class represents the graphical user interface for product operations.
 */
public class ProductView {
    private JFrame frame = new JFrame("Product operations");
    private JPanel contentPane = new JPanel(new BorderLayout());
    private JPanel titlePanel = new JPanel();
    private JLabel title = new JLabel("PRODUCT OPERATIONS");
    private JPanel centerPane = new JPanel(new GridLayout(3, 1, 20, 5));
    private JScrollPane scrollPane = new JScrollPane();
    private JPanel productPane = new JPanel(new BorderLayout());
    private JLabel addProductTitle = new JLabel("Add/Edit a Product");
    private JTable productTable = new JTable();
    private JPanel addProductPane = new JPanel(new GridLayout(4, 2, 10, 30));
    private JLabel nameLabel = new JLabel("Name");
    private JTextField nameField = new JTextField();
    private JLabel descriptionLabel = new JLabel("Description");
    private JTextField descriptionField = new JTextField();
    private JLabel priceLabel = new JLabel("Price");
    private JTextField priceField = new JTextField();
    private JLabel stockLabel = new JLabel("Stock");
    private JTextField stockField = new JTextField();
    private JButton addProductButton = new JButton("Add Product");
    private JButton editProductButton = new JButton("Edit Product");
    private JPanel deletePane = new JPanel(new GridLayout(3, 4, 10, 30));
    private JLabel deleteTitleLabel = new JLabel("Delete Product");
    private JLabel deleteLabel = new JLabel("Name");
    private JTextField deleteFiled = new JTextField();
    private JButton deleteButton = new JButton("Delete Product");
    private JPanel backPane = new JPanel(new GridLayout(1, 6, 20, 5));
    private JButton backButton = new JButton("Back");
    private ProductController productController = new ProductController(this);

    /**
     * Constructs a ProductView and initializes the GUI components.
     */
    public ProductView() {
        setUpGUI();
        frame.setVisible(true);
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
     * Creates the center pane with product, add product, and delete product panels.
     */
    private void createCenterPane() {
        centerPane.setBackground(Color.decode("#D8C7CE"));
        createProductPane();
        centerPane.add(scrollPane);
        createAddProductPane();
        centerPane.add(addProductPane);
        createDeletePane();
        centerPane.add(deletePane);
    }

    /**
     * Creates the product panel with the product table.
     */
    private void createProductPane() {
        productPane.setBackground(Color.decode("#D8C7CE"));
        productTable.setRowSelectionAllowed(true);
        scrollPane.setViewportView(productTable);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        TableCreator.createTable(productController.getProductBLL().getProducts(), productTable);
    }

    /**
     * Creates the add product panel with input fields and buttons.
     */
    private void createAddProductPane() {
        addProductPane.setBackground(Color.decode("#D8C7CE"));
        addProductTitle.setForeground(Color.decode("#B97375"));
        addProductTitle.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        addProductPane.add(addProductTitle);
        addProductPane.add(new JLabel());
        addProductPane.add(new JLabel());
        addProductPane.add(new JLabel());
        addProductPane.add(nameLabel);
        addProductPane.add(nameField);
        addProductPane.add(priceLabel);
        addProductPane.add(priceField);
        addProductPane.add(descriptionLabel);
        addProductPane.add(descriptionField);
        addProductPane.add(stockLabel);
        addProductPane.add(stockField);
        addProductPane.add(new JLabel());
        addProductButton.setBackground(Color.decode("#C4929A"));
        editProductButton.setBackground(Color.decode("#C4929A"));
        addProductPane.add(addProductButton);
        addProductPane.add(editProductButton);
    }

    /**
     * Creates the delete product panel with input field and button.
     */
    private void createDeletePane() {
        deletePane.setBackground(Color.decode("#D8C7CE"));
        deleteTitleLabel.setForeground(Color.decode("#B97375"));
        deleteTitleLabel.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        deletePane.add(deleteTitleLabel);
        deletePane.add(new JLabel());
        deletePane.add(new JLabel());
        deletePane.add(new JLabel());
        deletePane.add(new JLabel());
        deletePane.add(deleteLabel);
        deletePane.add(deleteFiled);
        deletePane.add(new JLabel());
        deletePane.add(new JLabel());
        deleteButton.setBackground(Color.decode("#C4929A"));
        deletePane.add(deleteButton);
    }

    /**
     * Creates the back pane with the back button.
     */
    private void createBackPane() {
        backPane.setBackground(Color.decode("#D8C7CE"));
        backButton.setBackground(Color.decode("#C4929A"));
        backPane.add(backButton);
        backPane.add(new JLabel());
        backPane.add(new JLabel());
        backPane.add(new JLabel());
        backPane.add(new JLabel());
        backPane.add(new JLabel());
    }

    /**
     * Updates the product table.
     */
    public void updateTable() {
        createProductPane();
    }

    /**
     * Retrieves the button for adding a product.
     *
     * @return The JButton object representing the button for adding a product.
     */
    public JButton getAddProductButton() {
        return addProductButton;
    }

    /**
     * Retrieves the button for editing a product.
     *
     * @return The JButton object representing the button for editing a product.
     */
    public JButton getEditProductButton() {
        return editProductButton;
    }

    /**
     * Retrieves the button for deleting a product.
     *
     * @return The JButton object representing the button for deleting a product.
     */
    public JButton getDeleteButton() {
        return deleteButton;
    }

    /**
     * Retrieves the button for going back to the WelcomeView.
     *
     * @return The JButton object representing the button for adding a client.
     */
    public JButton getBackButton() {
        return backButton;
    }

    /**
     * Retrieves the text field used for entering the name of the product.
     *
     * @return The JTextField object representing the text field for entering the name of the product.
     */
    public JTextField getNameField() {
        return nameField;
    }

    /**
     * Retrieves the text field used for entering the description of the product.
     *
     * @return The JTextField object representing the text field for entering the description of the product.
     */
    public JTextField getDescriptionField() {
        return descriptionField;
    }

    /**
     * Retrieves the text field used for entering the price of the product.
     *
     * @return The JTextField object representing the text field for entering the price of the product.
     */
    public JTextField getPriceField() {
        return priceField;
    }

    /**
     * Retrieves the text field used for entering the stock of the product.
     *
     * @return The JTextField object representing the text field for entering the stock of the product.
     */
    public JTextField getStockField() {
        return stockField;
    }

    /**
     * Retrieves the text field used for entering the name of the product to be deleted.
     *
     * @return The JTextField object representing the text field for entering the name of the product to be deleted.
     */
    public JTextField getDeleteFiled() {
        return deleteFiled;
    }

    /**
     * Retrieves the main frame of the product operations view.
     *
     * @return The JFrame object representing the main frame of the product operations view.
     */
    public JFrame getFrame() {
        return frame;
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
     * Sets the text field used for entering the name of the product.
     *
     * @param name The name that will be set into the text field.
     */
    public void setNameField(String name) {
        this.nameField.setText(name);
    }

    /**
     * Sets the text field used for entering the description of the product.
     *
     * @param description The description that will be set into the text field.
     */
    public void setDescriptionField(String description) {
        this.descriptionField.setText(description);
    }

    /**
     * Sets the text field used for entering the price of the product.
     *
     * @param price The price that will be set into the text field.
     */
    public void setPriceField(String price) {
        this.priceField.setText(price);
    }

    /**
     * Sets the text field used for entering the stock of the product.
     *
     * @param stock The stock that will be set into the text field.
     */
    public void setStockField(String stock) {
        this.stockField.setText(stock);
    }

    /**
     * Sets the text field used for entering the name of the product to be deleted.
     *
     * @param name The name of the product to be deleted that will be set into the text field.
     */
    public void setDeleteFiled(String name) {
        this.deleteFiled.setText(name);
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
