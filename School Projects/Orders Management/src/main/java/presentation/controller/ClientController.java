package presentation.controller;

import businessLayer.ClientBLL;
import businessLayer.Validator;
import model.Client;
import presentation.view.ClientView;
import presentation.view.WelcomeView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * The ClientController class controls the interaction between the ClientView (presentation layer) and the ClientBLL (business logic layer) for managing client data.
 * It handles actions such as adding, editing, and deleting clients, as well as updating the client table.
 */
public class ClientController {
    private ClientView clientView;
    private ClientBLL clientBLL;
    private int idSelected = -1;

    /**
     * Constructs a ClientController with the specified ClientView.
     *
     * @param clientView The ClientView associated with this controller.
     */
    public ClientController(ClientView clientView) {
        this.clientView = clientView;
        clientBLL = new ClientBLL();
        buttonAction();
        setUpMouseEvent();
    }

    /**
     * Sets up actions for the buttons in the ClientView.
     */
    private void buttonAction() {
        clientView.getBackButton().addActionListener(e -> returnToWelcome());
        clientView.getAddClientButton().addActionListener(e -> insertClient());
        clientView.getEditClientButton().addActionListener(e -> editClient());
        clientView.getDeleteButton().addActionListener(e -> deleteClient());
    }

    /**
     * Sets up mouse event for selecting clients in the table.
     */
    private void setUpMouseEvent() {
        clientView.getClientTable().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent event) {
                List<String> details = getDetailsSelectedClient(event);
                idSelected = Integer.parseInt(details.getFirst());
                setDetailsSelectedClient(details);
            }
        });
    }

    /**
     * Navigates back to the welcome view.
     */
    private void returnToWelcome() {
        clientView.setVisibility(false);
        new WelcomeView();
    }

    /**
     * Extracts client details from the input fields in the view.
     * Shows error messages on the screen depending on the error or success messages for successful actions.
     *
     * @return <p>The Client object with extracted details<br>null if validation fails.</p>
     */
    private Client extractDetailsClient() {
        String name = clientView.getNameField().getText();
        String ageString = clientView.getAgeField().getText();
        String email = clientView.getEmailField().getText();
        String phoneNr = clientView.getPhoneNrField().getText();

        if (name.isEmpty() || ageString.isEmpty() || email.isEmpty() || phoneNr.isEmpty()) {
            JOptionPane.showMessageDialog(clientView.getFrame(), "No field should be let empty!");
            return null;
        }

        if (!Validator.isInt(ageString)) {
            JOptionPane.showMessageDialog(clientView.getFrame(), "The age is not an integer!");
            return null;
        }
        int age = Integer.parseInt(ageString);

        if (!Validator.isEmailAddress(email)) {
            JOptionPane.showMessageDialog(clientView.getFrame(), "The email address does not have the right format");
            return null;
        }

        if (!Validator.isPhoneNr(phoneNr)) {
            JOptionPane.showMessageDialog(clientView.getFrame(), "The phone number does not have the right format");
            return null;
        }

        return new Client(name, age, email, phoneNr);
    }

    /**
     * Inserts a new client.
     * Shows error messages on the screen depending on the error or success messages for successful insertion.
     */
    private void insertClient() {
        Client client = extractDetailsClient();
        int status = clientBLL.addClient(client);
        if (status == -1) {
            JOptionPane.showMessageDialog(clientView.getFrame(), "Error adding the client");
        } else if (status == 1) {
            JOptionPane.showMessageDialog(clientView.getFrame(), "Client added successfully");
        }

        clientView.updateTable();
    }

    /**
     * Edits an existing client.
     * Shows error messages on the screen depending on the error or success messages for a successful edit.
     */
    private void editClient() {
        Client client = extractDetailsClient();

        int status = clientBLL.editClient(client, idSelected);

        if (status == -2) {
            JOptionPane.showMessageDialog(clientView.getFrame(), "Error editing the client!");
        } else if (status == -1) {
            JOptionPane.showMessageDialog(clientView.getFrame(), "The client was not found!");
        } else if (status == 1) {
            JOptionPane.showMessageDialog(clientView.getFrame(), "Client was edited successfully!");
        }

        clientView.updateTable();
    }

    /**
     * Deletes a client.
     * Shows error messages on the screen depending on the error or success messages for a successful deletion.
     */
    private void deleteClient() {
        String name = clientView.getDeleteFiled().getText();
        if (name == null) {
            JOptionPane.showMessageDialog(clientView.getFrame(), "Please enter the name of the client to be deleted!");
        }

        int status = clientBLL.deleteClient(name);

        if (status == 1) {
            JOptionPane.showMessageDialog(clientView.getFrame(), "Client deleted successfully!");
        } else if (status == 0) {
            JOptionPane.showMessageDialog(clientView.getFrame(), "There is no client with that name!");
        } else {
            JOptionPane.showMessageDialog(clientView.getFrame(), "There was an error deleting the client!");
        }

        clientView.updateTable();
    }

    /**
     * Gets the ClientBLL associated with this controller.
     *
     * @return The ClientBLL object.
     */
    public ClientBLL getClientBLL() {
        return clientBLL;
    }

    /**
     * Retrieves details of the selected client from the table.
     *
     * @param event The mouse event.
     * @return A list containing details of the selected client.
     */
    private List<String> getDetailsSelectedClient(java.awt.event.MouseEvent event) {
        List<String> details = new ArrayList<>();

        int row = clientView.getClientTable().getSelectedRow();
        DefaultTableModel tableModel = (DefaultTableModel) clientView.getClientTable().getModel();
        for (int i = 0; i < tableModel.getColumnCount(); i++) {
            String element = tableModel.getValueAt(row, i).toString();
            details.add(element);
        }

        return details;
    }

    /**
     * Sets the details of the selected client into the input fields.
     *
     * @param details The details of the selected client.
     */
    private void setDetailsSelectedClient(List<String> details) {
        clientView.setNameField(details.get(1));
        clientView.setAgeField(details.get(2));
        clientView.setEmailField(details.get(3));
        clientView.setPhoneNrField(details.get(4));
        clientView.setDeleteFiled(details.get(1));
    }
}
