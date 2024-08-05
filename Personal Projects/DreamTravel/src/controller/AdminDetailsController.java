package controller;

import model.AdminModel;
import model.ClientModel;
import repository.AdminDetailsRepository;
import repository.ClientDetailsRepository;
import view.AdminDetailsView;

public class AdminDetailsController {
    AdminDetailsView adminDetailsView = new AdminDetailsView();
    public AdminDetailsController(){
        adminDetailsView.setVisibility(true);
        adminDetailsView.getBackButton().addActionListener(e->changeToMenu());
        adminDetailsView.getViewButton().addActionListener(e->selectDetails(AdminModel.getCurrentUser().getUsername()));
        adminDetailsView.getUpdateButton().addActionListener(e->updateDetails(AdminModel.getCurrentUser().getUsername(), adminDetailsView.getNameField(), adminDetailsView.getAgeField(), adminDetailsView.getDobField(), adminDetailsView.getEmailField(), adminDetailsView.getPhoneField()));
        adminDetailsView.getDeleteButton().addActionListener(e->deleteDetails(AdminModel.getCurrentUser().getUsername()));
        adminDetailsView.getInsertButton().addActionListener(e->insertDetails(AdminModel.getCurrentUser().getUsername(), adminDetailsView.getNameField(), adminDetailsView.getAgeField(), adminDetailsView.getDobField(), adminDetailsView.getEmailField(), adminDetailsView.getPhoneField()));
    }

    public void changeToMenu(){
        adminDetailsView.setVisibility(false);
        MenuAdminController menuAdminController = new MenuAdminController();
    }

    public void selectDetails(String username){
        try{
            AdminDetailsRepository.selectDetails(username);
            adminDetailsView.setNameField(AdminModel.getCurrentAdmin().getName());
            adminDetailsView.setAgeField(AdminModel.getCurrentAdmin().getAge());
            adminDetailsView.setDobField(AdminModel.getCurrentAdmin().getDateOfBirth());
            adminDetailsView.setEmailField(AdminModel.getCurrentAdmin().getEmail());
            adminDetailsView.setPhoneField(AdminModel.getCurrentAdmin().getPhoneNr());
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void updateDetails(String username, String name, int age, String dateOfBirth, String email, String phoneNr){
        try{
            AdminDetailsRepository.updateDetails(username, name, age, dateOfBirth, email, phoneNr);
            adminDetailsView.setNameField(AdminModel.getCurrentAdmin().getName());
            adminDetailsView.setAgeField(AdminModel.getCurrentAdmin().getAge());
            adminDetailsView.setDobField(AdminModel.getCurrentAdmin().getDateOfBirth());
            adminDetailsView.setEmailField(AdminModel.getCurrentAdmin().getEmail());
            adminDetailsView.setPhoneField(AdminModel.getCurrentAdmin().getPhoneNr());
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void deleteDetails(String username){
        try{
            AdminDetailsRepository.deleteDetails(username);
            adminDetailsView.setNameField(AdminModel.getCurrentAdmin().getName());
            adminDetailsView.setAgeField(AdminModel.getCurrentAdmin().getAge());
            adminDetailsView.setDobField(AdminModel.getCurrentAdmin().getDateOfBirth());
            adminDetailsView.setEmailField(AdminModel.getCurrentAdmin().getEmail());
            adminDetailsView.setPhoneField(AdminModel.getCurrentAdmin().getPhoneNr());
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void insertDetails(String username, String name, int age, String dateOfBirth, String email, String phoneNr){
        try{
            AdminDetailsRepository.insertDetails(username, name, age, dateOfBirth, email, phoneNr);
            adminDetailsView.setNameField(AdminModel.getCurrentAdmin().getName());
            adminDetailsView.setAgeField(AdminModel.getCurrentAdmin().getAge());
            adminDetailsView.setDobField(AdminModel.getCurrentAdmin().getDateOfBirth());
            adminDetailsView.setEmailField(AdminModel.getCurrentAdmin().getEmail());
            adminDetailsView.setPhoneField(AdminModel.getCurrentAdmin().getPhoneNr());
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
