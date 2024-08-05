package presentation.controller;

import businessLayer.OrderBLL;
import businessLayer.Validator;
import presentation.view.OrderView;
import presentation.view.WelcomeView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The orderController class controls the interaction between the OrderView (presentation layer) and the OrderBLL (business logic layer) for managing order data.
 * It handles the creation of orders.
 */
public class OrderController {
    private OrderView orderView;
    private OrderBLL orderBLL;

    /**
     * Constructs a OrderController with the specified OrderView.
     *
     * @param orderView The OrderView associated with this controller.
     */
    public OrderController(OrderView orderView) {
        this.orderView = orderView;
        orderBLL = new OrderBLL();
        buttonAction();
        setUpMouseEvent();
    }

    /**
     * Sets up actions for the buttons in the OrderView.
     */
    private void buttonAction() {
        orderView.getBackButton().addActionListener(e -> returnToWelcome());
        orderView.getPlaceOrderButton().addActionListener(e -> placeOrder());
        orderView.getBillButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                orderView.seeBillFrame();
            }
        });
    }

    /**
     * Sets up mouse event for selecting clients and products from the tables.
     */
    private void setUpMouseEvent() {
        orderView.getClientTable().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent event) {
                int id = getSelectedId(event, orderView.getClientTable());
                orderBLL.getOrder().setClientId(id);
            }
        });

        orderView.getProductTable().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent event) {
                int id = getSelectedId(event, orderView.getProductTable());
                orderBLL.getOrder().setProductId(id);
            }
        });
    }

    /**
     * Navigates back to the welcome view.
     */
    private void returnToWelcome() {
        orderView.setVisibility(false);
        new WelcomeView();
    }

    /**
     * Place the order by inserting the details into the database
     * Shows error messages on the screen depending on the error or success messages for successful insertion.
     */
    private void placeOrder() {
        String quantityString = orderView.getQuantityField().getText();
        if (quantityString.isEmpty()) {
            JOptionPane.showMessageDialog(orderView.getFrame(), "Please enter a quantity before placing the order!");
        }

        if (!Validator.isInt(quantityString)) {
            JOptionPane.showMessageDialog(orderView.getFrame(), "The quantity is not an integer!");
        }
        int quantity = Integer.parseInt(quantityString);

        int status = orderBLL.createOrder(quantity);
        if (status == 1) {
            orderView.updateTables();
            JOptionPane.showMessageDialog(orderView.getFrame(), "Order was placed successfully");
        } else if (status == 0) {
            JOptionPane.showMessageDialog(orderView.getFrame(), "There are not enough products in stock. Please enter a smaller quantity! ");
        } else {
            JOptionPane.showMessageDialog(orderView.getFrame(), "Error creating the order!");
        }
    }


    /**
     * Gets the OrderBLL associated with this controller.
     *
     * @return The OrderBLL object.
     */
    public OrderBLL getOrderBLL() {
        return orderBLL;
    }

    /**
     * Retrieves details of the id of the selected item from the table.
     *
     * @param event The mouse event.
     * @return The id of the selected item.
     */
    private int getSelectedId(java.awt.event.MouseEvent event, JTable table) {
        int row = table.getSelectedRow();
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();

        return Integer.parseInt(tableModel.getValueAt(row, 0).toString());
    }
}
