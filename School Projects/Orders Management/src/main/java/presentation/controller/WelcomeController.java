package presentation.controller;

import presentation.view.WelcomeView;
import presentation.view.ClientView;
import presentation.view.OrderView;
import presentation.view.ProductView;

/**
 * The WelcomeController class controls the navigation between different operations from the WelcomeView.
 */
public class WelcomeController {
    private WelcomeView welcomeView;

    /**
     * Constructs a WelcomeController with the specified WelcomeView.
     *
     * @param welcomeView The WelcomeView associated with this controller.
     */
    public WelcomeController(WelcomeView welcomeView) {
        this.welcomeView = welcomeView;
        buttonAction();
    }

    /**
     * Sets up actions for the buttons in the WelcomeView.
     */
    private void buttonAction() {
        welcomeView.getClientButton().addActionListener(e -> switchToClientOperation());
        welcomeView.getProductButton().addActionListener(e -> switchToProductOperation());
        welcomeView.getOrderButton().addActionListener(e -> switchToOrderOperation());
    }

    /**
     * Switches to the client operation view.
     */
    private void switchToClientOperation() {
        welcomeView.setVisibility(false);
        new ClientView();
    }

    /**
     * Switches to the product operation view.
     */
    private void switchToProductOperation() {
        welcomeView.setVisibility(false);
        new ProductView();
    }

    /**
     * Switches to the product operation view.
     */
    private void switchToOrderOperation() {
        welcomeView.setVisibility(false);
        new OrderView();
    }
}
