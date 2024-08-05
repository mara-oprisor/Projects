package controller;

import model.ReviewsModel;
import model.UserModel;
import repository.HotelReviewRepository;
import view.HotelReviewView;

public class HotelReviewController {
    private HotelReviewView hotelReviewView = new HotelReviewView();

    public HotelReviewController() {
        hotelReviewView.setVisibility(true);
        hotelReviewView.getContentPanel().removeAll();
        HotelReviewRepository.extractReviews(UserModel.getCurrentUser().getUsername());
        ReviewsModel.assignPhotoURL();
        hotelReviewView.displayReviews();
        hotelReviewView.getBackButton().addActionListener(e -> changeToMenu());
    }

    private void changeToMenu() {
        hotelReviewView.setVisibility(false);
        MenuAdminController menuAdminController = new MenuAdminController();
    }
}
