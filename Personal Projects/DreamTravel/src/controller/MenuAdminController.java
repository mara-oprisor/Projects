package controller;

import view.MenuAdminView;

public class MenuAdminController {
    private MenuAdminView menuAdminView = new MenuAdminView();

    public MenuAdminController() {
        menuAdminView.setVisibility(true);
        menuAdminView.getButtonDetails().addActionListener(e -> changeToDetails());
        menuAdminView.getButtonReviews().addActionListener(e -> changeToReviews());
        menuAdminView.getButtonReservations().addActionListener(e -> changeToReservations());
    }

    private void changeToDetails() {
        menuAdminView.setVisibility(false);
        AdminDetailsController adminDetailsController = new AdminDetailsController();
    }

    private void changeToReviews() {
        menuAdminView.setVisibility(false);
        HotelReviewController hotelReviewController = new HotelReviewController();
    }

    private void changeToReservations() {
        menuAdminView.setVisibility(false);
        HotelBookingsController hotelBookingsController = new HotelBookingsController();
    }
}
