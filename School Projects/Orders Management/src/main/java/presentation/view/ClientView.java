package presentation.view;

import presentation.TableCreator;
import presentation.controller.ClientController;

import javax.swing.*;
import java.awt.*;

/**
 * The ClientView class represents the graphical user interface for client operations.
 */
public class ClientView {
    private JFrame frame = new JFrame("Client operations");
    private JPanel contentPane = new JPanel(new BorderLayout());
    private JPanel titlePanel = new JPanel();
    private JLabel title = new JLabel("CLIENT OPERATIONS");
    private JPanel centerPane = new JPanel(new GridLayout(3, 1, 20, 5));
    private JScrollPane scrollPane = new JScrollPane();
    private JPanel clientPane = new JPanel(new BorderLayout());
    private JLabel addClientTitle = new JLabel("Add/Edit a Client");
    private JTable clientTable = new JTable();
    private JPanel addClientPane = new JPanel(new GridLayout(4, 2, 10, 30));
    private JLabel nameLabel = new JLabel("Name");
    private JTextField nameField = new JTextField();
    private JLabel ageLabel = new JLabel("Age");
    private JTextField ageField = new JTextField();
    private JLabel emailLabel = new JLabel("Email Address");
    private JTextField emailField = new JTextField();
    private JLabel phoneNrLabel = new JLabel("Phone Number");
    private JTextField phoneNrField = new JTextField();
    private JButton addClientButton = new JButton("Add Client");
    private JButton editClientButton = new JButton("Edit Client");
    private JPanel deletePane = new JPanel(new GridLayout(3, 4, 10, 30));
    private JLabel deleteTitleLabel = new JLabel("Delete Client");
    private JLabel deleteLabel = new JLabel("Name");
    private JTextField deleteFiled = new JTextField();
    private JButton deleteButton = new JButton("Delete Client");
    private JPanel backPane = new JPanel(new GridLayout(1, 6, 20, 5));
    private JButton backButton = new JButton("Back");
    private ClientController clientController = new ClientController(this);

    /**
     * Constructs a ClientView and initializes the GUI components.
     */
    public ClientView() {
        setUpGUI();
    }

    /**
     * Sets up the graphical user interface.
     */
    private void setUpGUI() {
        frame.setSize(new Dimension(1000, 700));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
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
     * Creates the center pane with client, add client, and delete client panels.
     */
    private void createCenterPane() {
        centerPane.setBackground(Color.decode("#D8C7CE"));
        createClientsPane();
        centerPane.add(scrollPane);
        createAddClientPane();
        centerPane.add(addClientPane);
        createDeletePane();
        centerPane.add(deletePane);
    }

    /**
     * Creates the clients panel with the client table.
     */
    private void createClientsPane() {
        clientPane.setBackground(Color.decode("#D8C7CE"));
        clientTable.setRowSelectionAllowed(true);
        scrollPane.setViewportView(clientTable);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        TableCreator.createTable(clientController.getClientBLL().getClients(), clientTable);
    }

    /**
     * Creates the add client panel with input fields and buttons.
     */
    private void createAddClientPane() {
        addClientPane.setBackground(Color.decode("#D8C7CE"));
        addClientTitle.setForeground(Color.decode("#B97375"));
        addClientTitle.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        addClientPane.add(addClientTitle);
        addClientPane.add(new JLabel());
        addClientPane.add(new JLabel());
        addClientPane.add(new JLabel());
        addClientPane.add(nameLabel);
        addClientPane.add(nameField);
        addClientPane.add(emailLabel);
        addClientPane.add(emailField);
        addClientPane.add(ageLabel);
        addClientPane.add(ageField);
        addClientPane.add(phoneNrLabel);
        addClientPane.add(phoneNrField);
        addClientPane.add(new JLabel());
        addClientButton.setBackground(Color.decode("#C4929A"));
        editClientButton.setBackground(Color.decode("#C4929A"));
        addClientPane.add(addClientButton);
        addClientPane.add(editClientButton);
    }

    /**
     * Creates the delete client panel with input field and button.
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
     * Updates the client table.
     */
    public void updateTable() {
        createClientsPane();
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
     * Retrieves the button for adding a client.
     *
     * @return The JButton object representing the button for adding a client.
     */
    public JButton getAddClientButton() {
        return addClientButton;
    }

    /**
     * Retrieves the button for editing a client.
     *
     * @return The JButton object representing the button for editing a client.
     */
    public JButton getEditClientButton() {
        return editClientButton;
    }

    /**
     * Retrieves the button for deleting a client.
     *
     * @return The JButton object representing the button for deleting a client.
     */
    public JButton getDeleteButton() {
        return deleteButton;
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
     * Retrieves the text field used for entering the name of the client.
     *
     * @return The JTextField object representing the text field for entering the name of the client.
     */
    public JTextField getNameField() {
        return nameField;
    }

    /**
     * Retrieves the text field used for entering the age of the client.
     *
     * @return The JTextField object representing the text field for entering the age of the client.
     */
    public JTextField getAgeField() {
        return ageField;
    }

    /**
     * Retrieves the text field used for entering the email address of the client.
     *
     * @return The JTextField object representing the text field for entering the email address of the client.
     */
    public JTextField getEmailField() {
        return emailField;
    }

    /**
     * Retrieves the text field used for entering the phone number of the client.
     *
     * @return The JTextField object representing the text field for entering the phone number of the client.
     */
    public JTextField getPhoneNrField() {
        return phoneNrField;
    }

    /**
     * Retrieves the text field used for entering the name of the client to be deleted.
     *
     * @return The JTextField object representing the text field for entering the name of the client to be deleted.
     */
    public JTextField getDeleteFiled() {
        return deleteFiled;
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
     * Retrieves the table used for showing all clients.
     *
     * @return The JTable object representing the table used for showing all clients.
     */
    public JTable getClientTable() {
        return clientTable;
    }

    /**
     * Sets the text field used for entering the name of the client.
     *
     * @param name The name that will be set into the text field.
     */
    public void setNameField(String name) {
        this.nameField.setText(name);
    }

    /**
     * Sets the text field used for entering the age of the client.
     *
     * @param age The age that will be set into the text field.
     */
    public void setAgeField(String age) {
        this.ageField.setText(age);
    }

    /**
     * Sets the text field used for entering the email of the client.
     *
     * @param email The email that will be set into the text field.
     */
    public void setEmailField(String email) {
        this.emailField.setText(email);
    }

    /**
     * Sets the text field used for entering the phone number of the client.
     *
     * @param phoneNr The phone number that will be set into the text field.
     */
    public void setPhoneNrField(String phoneNr) {
        this.phoneNrField.setText(phoneNr);
    }

    /**
     * Sets the text field used for entering the name of the client that will be deleted.
     *
     * @param name The name of the client to be deleted that will be set into the text field.
     */
    public void setDeleteFiled(String name) {
        this.deleteFiled.setText(name);
    }
}
