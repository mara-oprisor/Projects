package controller;

import model.ReviewsModel;
import model.UserModel;
import repository.MyReviewsRepository;
import view.MyReviewView;

public class MyReviewController {
    public MyReviewView myReviewView = new MyReviewView();

    public MyReviewController() {
        myReviewView.setVisibility(true);
        ReviewsModel.getReviews().clear();
        myReviewView.getContentPanel().removeAll();
        MyReviewsRepository.extractReviews(UserModel.getCurrentUser().getUsername());
        ReviewsModel.assignPhotoURL();
        myReviewView.displayReviews();
        myReviewView.getBackButton().addActionListener(e -> changeToMenu());
    }

    private void changeToMenu() {
        myReviewView.setVisibility(false);
        MenuClientController menuClientController = new MenuClientController();
    }
}
