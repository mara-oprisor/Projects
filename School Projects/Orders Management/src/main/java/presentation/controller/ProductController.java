package presentation.controller;

import businessLayer.ProductBLL;
import businessLayer.Validator;
import model.Product;
import presentation.view.ProductView;
import presentation.view.WelcomeView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * The ProductController class controls the interaction between the ProductView (presentation layer) and the ProductBLL (business logic layer) for managing product data.
 * It handles actions such as adding, editing, and deleting products, as well as updating the product table.
 */
public class ProductController {
    private ProductView productView;
    private ProductBLL productBLL;
    private int idSelected = -1;

    /**
     * Constructs a ProductController with the specified ProductView.
     *
     * @param productView The ProductView associated with this controller.
     */
    public ProductController(ProductView productView) {
        this.productView = productView;
        productBLL = new ProductBLL();
        buttonAction();
        setUpMouseEvent();
    }

    /**
     * Sets up actions for the buttons in the ProductView.
     */
    private void buttonAction() {
        productView.getAddProductButton().addActionListener(e -> insertProduct());
        productView.getEditProductButton().addActionListener(e -> editProduct());
        productView.getDeleteButton().addActionListener(e -> deleteProduct());
        productView.getBackButton().addActionListener(e -> returnToWelcome());
    }

    /**
     * Sets up mouse event for selecting products in the table.
     */
    private void setUpMouseEvent() {
        productView.getProductTable().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent event) {
                List<String> details = getDetailsSelectedProduct(event);
                idSelected = Integer.parseInt(details.getFirst());
                setDetailsSelectedProduct(details);
            }
        });
    }

    /**
     * Extracts product details from the input fields in the view.
     * Shows error messages on the screen depending on the error or success messages for successful actions.
     *
     * @return <p>The Product object with extracted details<br>null if validation fails.</p>
     */
    private Product extractDetailsProduct() {
        String name = productView.getNameField().getText();
        String description = productView.getDescriptionField().getText();
        String priceString = productView.getPriceField().getText();
        String stockString = productView.getStockField().getText();

        if (name.isEmpty() || description.isEmpty() || priceString.isEmpty() || stockString.isEmpty()) {
            JOptionPane.showMessageDialog(productView.getFrame(), "No field should be left empty");
        }

        if (!Validator.isValidDescription(description)) {
            JOptionPane.showMessageDialog(productView.getFrame(), "The description is too long");
            return null;
        }

        if (!Validator.isInt(priceString)) {
            JOptionPane.showMessageDialog(productView.getFrame(), "The price is not an integer");
            return null;
        }
        int price = Integer.parseInt(priceString);

        if (!Validator.isInt(stockString)) {
            JOptionPane.showMessageDialog(productView.getFrame(), "The stock is not an integer");
            return null;
        }
        int stock = Integer.parseInt(stockString);

        return new Product(name, description, price, stock);
    }

    /**
     * Navigates back to the welcome view.
     */
    private void returnToWelcome() {
        productView.setVisibility(false);
        new WelcomeView();
    }

    /**
     * Inserts a new product.
     * Shows error messages on the screen depending on the error or success messages for successful insertion.
     */
    private void insertProduct() {
        Product product = extractDetailsProduct();

        int status = productBLL.addProduct(product);

        if (status == -1) {
            JOptionPane.showMessageDialog(productView.getFrame(), "Error inserting the product!");
        } else if (status == 1) {
            JOptionPane.showMessageDialog(productView.getFrame(), "The product was inserted successfully!");
        }

        productView.updateTable();
    }

    /**
     * Edits an existing product.
     * Shows error messages on the screen depending on the error or success messages for a successful edit.
     */
    private void editProduct() {
        Product product = extractDetailsProduct();

        int status = productBLL.editProduct(product, idSelected);

        if (status == -2) {
            JOptionPane.showMessageDialog(productView.getFrame(), "Error editing the product!");
        } else if (status == -1) {
            JOptionPane.showMessageDialog(productView.getFrame(), "The product was not found!");
        } else if (status == 1) {
            JOptionPane.showMessageDialog(productView.getFrame(), "Product was edited successfully!");
        }

        productView.updateTable();
    }

    /**
     * Deletes a product.
     * Shows error messages on the screen depending on the error or success messages for a successful deletion.
     */
    private void deleteProduct() {
        String name = productView.getDeleteFiled().getText();
        int status = productBLL.deleteProduct(name);

        if (status == 1) {
            JOptionPane.showMessageDialog(productView.getFrame(), "Product deleted successfully!");
        } else if (status == 0) {
            JOptionPane.showMessageDialog(productView.getFrame(), "There is no product with that name!");
        } else {
            JOptionPane.showMessageDialog(productView.getFrame(), "There was an error deleting the product!");
        }

        productView.updateTable();
    }

    /**
     * Gets the ProductBLL associated with this controller.
     *
     * @return The ProductBLL object.
     */
    public ProductBLL getProductBLL() {
        return productBLL;
    }

    /**
     * Retrieves details of the selected product from the table.
     *
     * @param event The mouse event.
     * @return A list containing details of the selected product.
     */
    private List<String> getDetailsSelectedProduct(java.awt.event.MouseEvent event) {
        List<String> details = new ArrayList<>();

        int row = productView.getProductTable().getSelectedRow();
        DefaultTableModel tableModel = (DefaultTableModel) productView.getProductTable().getModel();
        for (int i = 0; i < tableModel.getColumnCount(); i++) {
            String element = tableModel.getValueAt(row, i).toString();
            details.add(element);
        }

        return details;
    }

    /**
     * Sets the details of the selected product into the input fields.
     *
     * @param details The details of the selected product.
     */
    private void setDetailsSelectedProduct(List<String> details) {
        productView.setNameField(details.get(1));
        productView.setDescriptionField(details.get(2));
        productView.setPriceField(details.get(3));
        productView.setStockField(details.get(4));
        productView.setDeleteFiled(details.get(1));
    }
}
