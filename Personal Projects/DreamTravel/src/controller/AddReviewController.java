package controller;

import model.UserModel;
import repository.AddReviewRepository;
import repository.AdminDetailsRepository;
import view.AddReviewView;

public class AddReviewController {
    AddReviewView addReviewView = new AddReviewView();
    public AddReviewController(){
        addReviewView.setVisibility(true);
        addReviewView.getBackButton().addActionListener(e -> changeToReviews());
        addReviewView.getAddReviewButton().addActionListener(e -> addReview(UserModel.getCurrentUser().getUsername()));
    }

    private void changeToReviews(){
        addReviewView.setVisibility(false);
        ReviewController reviewController = new ReviewController();
    }

    private void addReview(String username){
        try{
            String hotelName = addReviewView.getHotelDropDown();
            String reviewText = addReviewView.getReviewTextField();
            int rating = addReviewView.getRatingTextField();
            AddReviewRepository.insertReview(username, hotelName,reviewText, rating);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
