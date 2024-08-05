package controller;

import model.UserModel;
import repository.SignUpRepository;
import view.SignUpView;

public class SignUpController {
    private SignUpView signUpView = new SignUpView();

    public SignUpController() {
        signUpView.setVisibility(true);
        signUpView.getBackButton().addActionListener(e -> this.changeToWelcomeView());
        signUpView.getSignUpButton().addActionListener(e -> this.signUp());
    }

    private void changeToWelcomeView() {
        signUpView.setVisibility(false);
        WelcomeController welcomeController = new WelcomeController();
    }

    private void signUp() {
        try {
            String username = signUpView.getTextFieldUsername();
            String password = signUpView.getTextFieldPassword();
            String userType = signUpView.getDropDownType();

            if (SignUpRepository.isUsername(username)) {
                this.signUpView.showMessage("Username already exists. Please choose another username!", 0);
            } else {
                if (SignUpRepository.insertPerson(username, password, userType) != null) {
                    signUpView.showMessage("Successful SignUp!", 1);
                    signUpView.setVisibility(false);
                    UserModel.setUser(username, password, userType);
                    MenuClientController menuClientController = new MenuClientController();
                } else {
                    signUpView.showMessage("Failed to add the person to the database", 0);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
