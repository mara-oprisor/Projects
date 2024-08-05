package controller;

import view.MenuClientView;

public class MenuClientController {
    private MenuClientView menuClientView = new MenuClientView();

    public MenuClientController() {
        menuClientView.setVisibility(true);
        menuClientView.getButtonBookRoom().addActionListener(e -> changeToBook());
        menuClientView.getButtonDetails().addActionListener(e -> changeToDetails());
        menuClientView.getButtonReviews().addActionListener(e -> changeToReviews());
        menuClientView.getButtonMyReviews().addActionListener(e -> changeToMyReviews());
        menuClientView.getButtonMyBooking().addActionListener(e -> changeToMyBookings());
    }

    private void changeToBook() {
        menuClientView.setVisibility(false);
        HotelsController hotelsController = new HotelsController();
    }

    private void changeToDetails() {
        menuClientView.setVisibility(false);
        ClientDetailsController clientDetailsController = new ClientDetailsController();
    }

    private void changeToReviews() {
        menuClientView.setVisibility(false);
        ReviewController reviewController = new ReviewController();
    }

    private void changeToMyReviews() {
        menuClientView.setVisibility(false);
        MyReviewController myReviewController = new MyReviewController();
    }

    private void changeToMyBookings() {
        menuClientView.setVisibility(false);
        MyBookingsController myBookingsController = new MyBookingsController();
    }
}
