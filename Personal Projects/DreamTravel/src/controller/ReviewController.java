package controller;

import model.ReviewsModel;
import repository.ReviewRepository;
import view.AddReviewView;
import view.ReviewView;

import javax.swing.*;

public class ReviewController {
    private ReviewView reviewView = new ReviewView();

    public ReviewController() {
        reviewView.setVisibility(true);
        reviewView.getContentPanel().removeAll();
        reviewView.getBackButton().addActionListener(e -> changeToMenu());
        ReviewsModel.getReviews().clear();
        ReviewRepository.extractReviews();
        ReviewsModel.assignPhotoURL();
        reviewView.displayReviews();
        reviewView.getReviewButton().addActionListener(e -> addReview());
        reviewView.getSortButton().addActionListener(e -> sortReviews());
        reviewView.getFilterButton().addActionListener(e -> filterReviews());
    }

    private void changeToMenu() {
        reviewView.setVisibility(false);
        MenuClientController menuClientController = new MenuClientController();
    }

    private void addReview() {
        reviewView.setVisibility(false);
        AddReviewController addReviewController = new AddReviewController();
    }

    private static String currentFilter = null;

    private void sortReviews() {
        if ("Default".equals(reviewView.getDropdown2())) {
            ReviewsModel.getReviews().clear();
            ReviewRepository.extractReviews(currentFilter);
            ReviewsModel.assignPhotoURL();
            reviewView.getContentPanel().removeAll();
            reviewView.displayReviews();
        } else if ("Ascending".equals(reviewView.getDropdown2())) {
            ReviewsModel.getReviews().clear();
            ReviewRepository.extractReviewsAscending(currentFilter);
            ReviewsModel.assignPhotoURL();
            reviewView.getContentPanel().removeAll();
            reviewView.displayReviews();
        } else {
            ReviewsModel.getReviews().clear();
            ReviewRepository.extractReviewsDescending(currentFilter);
            ReviewsModel.assignPhotoURL();
            reviewView.getContentPanel().removeAll();
            reviewView.displayReviews();
        }
    }


    private void filterReviews() {
        if ("All hotels".equals(reviewView.getDropdown1())) {
            currentFilter = null;
            ReviewsModel.getReviews().clear();
            ReviewRepository.extractReviews();
            ReviewsModel.assignPhotoURL();
            reviewView.getContentPanel().removeAll();
            reviewView.displayReviews();
        } else {
            String hotelName = reviewView.getDropdown1();
            currentFilter = hotelName;
            ReviewsModel.getReviews().clear();
            ReviewRepository.extractReviews(hotelName);
            ReviewsModel.assignPhotoURL();
            reviewView.getContentPanel().removeAll();
            reviewView.displayReviews();
        }
    }

}