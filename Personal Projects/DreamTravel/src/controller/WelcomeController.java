package controller;

import view.WelcomeView;

public class WelcomeController {
    private WelcomeView welcomeView = new WelcomeView();

    public WelcomeController() {
        welcomeView.setVisibility(true);
        welcomeView.getSignUpButton().addActionListener(e -> this.changeToSignUp());
        welcomeView.getLogInButton().addActionListener(e -> this.changeToLogIn());
    }

    private void changeToSignUp() {
        welcomeView.setVisibility(false);
        SignUpController signUpController = new SignUpController();
    }

    private void changeToLogIn() {
        welcomeView.setVisibility(false);
        LogInController logInController = new LogInController();
    }
}
