package model;

import repository.ReviewRepository;

import javax.swing.*;
import java.util.ArrayList;

public class ReviewsModel {
    private String username;
    private String hotelName;
    private String reviewText;
    private int rating;
    private String urlPhoto;
    private static ArrayList<ReviewsModel> reviews = new ArrayList<>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public static ArrayList<ReviewsModel> getReviews() {
        return reviews;
    }

    public static void setReviews(ArrayList<ReviewsModel> reviews) {
        ReviewsModel.reviews = reviews;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }


    public static void assignPhotoURL() {
        for (ReviewsModel review : ReviewsModel.getReviews()) {
            switch (review.getRating()) {
                case 1:
                    review.setUrlPhoto("src/images/rating_1star.png");
                    break;
                case 2:
                    review.setUrlPhoto("src/images/rating_2star.png");
                    break;
                case 3:
                    review.setUrlPhoto("src/images/rating_3star.png");
                    break;
                case 4:
                    review.setUrlPhoto("src/images/rating_4star.png");
                    break;
                case 5:
                    review.setUrlPhoto("src/images/rating_5star.png");
                    break;
            }
        }
    }

}