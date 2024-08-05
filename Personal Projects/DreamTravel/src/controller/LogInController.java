package controller;

import repository.LogInRepository;
import view.LogInView;

public class LogInController {
    private LogInView logInView = new LogInView();

    public LogInController() {
        logInView.setVisibility(true);
        logInView.getBackButton().addActionListener(e -> this.changeToWelcomeView());
        logInView.getLoginButton().addActionListener(e -> this.logIn());
    }

    private void changeToWelcomeView() {
        logInView.setVisibility(false);
        WelcomeController welcomeController = new WelcomeController();
    }

    private void logIn() {
        try {
            String username = logInView.getTextFieldUsername();
            String password = logInView.getTextFieldPassword();
            String typeOfUser = logInView.getDropDownType();

            if (!LogInRepository.isUsername(username)) {
                this.logInView.showMessage("There is no user with this username. Try to SignUp first!", 0);
            } else {

                if (LogInRepository.veritfyLogInProcess(username, password, typeOfUser) != null) {
                    this.logInView.showMessage("Successful LogIn!", 1);
                    this.logInView.setVisibility(false);
                    if (typeOfUser.equals("Client")) {
                        MenuClientController menuClientController = new MenuClientController();
                    } else if (typeOfUser.equals("Admin")) {
                        MenuAdminController menuAdminController = new MenuAdminController();
                    }
                } else {
                    this.logInView.showMessage("Invalid user credentials!", 0);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
