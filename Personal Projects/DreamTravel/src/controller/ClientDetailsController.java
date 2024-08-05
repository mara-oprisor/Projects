package controller;

import model.ClientModel;
import repository.ClientDetailsRepository;
import view.ClientDetailsView;

import javax.swing.*;

public class ClientDetailsController {
    ClientDetailsView clientDetailsView = new ClientDetailsView();

    public ClientDetailsController() {
        clientDetailsView.setVisibility(true);
        clientDetailsView.getBackButton().addActionListener(e -> changeToMenu());
        clientDetailsView.getViewButton().addActionListener(e -> selectDetails(ClientModel.getCurrentUser().getUsername()));
        clientDetailsView.getUpdateButton().addActionListener(e -> updateDetails(ClientModel.getCurrentUser().getUsername(), clientDetailsView.getNameField(), clientDetailsView.getAgeField(), clientDetailsView.getDobField(), clientDetailsView.getEmailField(), clientDetailsView.getPhoneField()));
        clientDetailsView.getDeleteButton().addActionListener(e -> deleteDetails(ClientModel.getCurrentUser().getUsername()));
        clientDetailsView.getInsertButton().addActionListener(e -> insertDetails(ClientModel.getCurrentUser().getUsername(), clientDetailsView.getNameField(),clientDetailsView.getAgeField(), clientDetailsView.getDobField(), clientDetailsView.getEmailField(), clientDetailsView.getPhoneField()));
    }

    private void changeToMenu() {
        clientDetailsView.setVisibility(false);
        MenuClientController menuClientController = new MenuClientController();
    }

    private void selectDetails(String username) {
        try {
            ClientDetailsRepository.selectDetails(username);
            clientDetailsView.setNameField(ClientModel.getCurrentClient().getName());
            clientDetailsView.setAgeField(ClientModel.getCurrentClient().getAge());
            clientDetailsView.setDobField(ClientModel.getCurrentClient().getDateOfBirth());
            clientDetailsView.setEmailField(ClientModel.getCurrentClient().getEmail());
            clientDetailsView.setPhoneField(ClientModel.getCurrentClient().getPhoneNr());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateDetails(String username, String name, String ageStr, String dateOfBirth, String email, String phoneNr) {
        try {
            int age = Integer.parseInt(ageStr); // Parse age as an integer
            ClientDetailsRepository.updateDetails(username, name, age, dateOfBirth, email, phoneNr);
            clientDetailsView.setNameField(ClientModel.getCurrentClient().getName());
            clientDetailsView.setAgeField(ClientModel.getCurrentClient().getAge());
            clientDetailsView.setDobField(ClientModel.getCurrentClient().getDateOfBirth());
            clientDetailsView.setEmailField(ClientModel.getCurrentClient().getEmail());
            clientDetailsView.setPhoneField(ClientModel.getCurrentClient().getPhoneNr());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid age format. Please enter a valid integer for age.");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteDetails(String username) {
        try {
            ClientDetailsRepository.deleteDetails(username);
            clientDetailsView.setNameField(ClientModel.getCurrentClient().getName());
            clientDetailsView.setAgeField(ClientModel.getCurrentClient().getAge());
            clientDetailsView.setDobField(ClientModel.getCurrentClient().getDateOfBirth());
            clientDetailsView.setEmailField(ClientModel.getCurrentClient().getEmail());
            clientDetailsView.setPhoneField(ClientModel.getCurrentClient().getPhoneNr());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertDetails(String username, String name, String ageStr, String dateOfBirth, String email, String phoneNr) {
        try {
            int age = Integer.parseInt(ageStr); // Parse age as an integer
            ClientDetailsRepository.insertDetails(username, name, age, dateOfBirth, email, phoneNr);
            clientDetailsView.setNameField(ClientModel.getCurrentClient().getName());
            clientDetailsView.setAgeField(ClientModel.getCurrentClient().getAge());
            clientDetailsView.setDobField(ClientModel.getCurrentClient().getDateOfBirth());
            clientDetailsView.setEmailField(ClientModel.getCurrentClient().getEmail());
            clientDetailsView.setPhoneField(ClientModel.getCurrentClient().getPhoneNr());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid age format. Please enter a valid integer for age.");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

