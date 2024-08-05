package repository;

import model.ReviewsModel;

import java.sql.*;
import java.util.ArrayList;

public class ReviewRepository {
    private static Connection connectToDataBase() {
        Connection con = null;
        try {
            String url = "jdbc:postgresql://localhost:5432/travel site";
            String user = "postgres";
            String password = "Qwerty1!";

            con = DriverManager.getConnection(url, user, password);
            return con;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void extractReviews() {
        Connection con = connectToDataBase();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String sql = "SELECT * FROM reviews";
            preparedStatement = con.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int user_id = resultSet.getInt(2);
                int hotel_id = resultSet.getInt(3);
                ReviewsModel reviewsModel = new ReviewsModel();

                reviewsModel.setReviewText(resultSet.getString(4));
                reviewsModel.setRating(resultSet.getInt(5));

                String sql1 = "SELECT hotel_name FROM hotel WHERE hotel_id = ?";
                PreparedStatement preparedStatement1 = con.prepareStatement(sql1);
                preparedStatement1.setInt(1, hotel_id);
                ResultSet resultSet1 = preparedStatement1.executeQuery();

                if (resultSet1.next()) {
                    reviewsModel.setHotelName(resultSet1.getString(1));
                }

                String sql2 = "SELECT user_name FROM \"user\" WHERE user_id = ?";
                PreparedStatement preparedStatement2 = con.prepareStatement(sql2);
                preparedStatement2.setInt(1, user_id);
                ResultSet resultSet2 = preparedStatement2.executeQuery();

                if (resultSet2.next()) {
                    reviewsModel.setUsername(resultSet2.getString(1));
                }

                ReviewsModel.getReviews().add(reviewsModel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void extractReviews(String hotelName) {
        Connection con = connectToDataBase();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String sql;
            if (hotelName == null) {
                // If hotelName is null, fetch all reviews without filtering by hotel
                sql = "SELECT * FROM reviews";
                preparedStatement = con.prepareStatement(sql);
            } else {
                // If hotelName is not null, fetch reviews for the specified hotel
                sql = "SELECT * FROM reviews WHERE hotel_id = (SELECT hotel_id FROM hotel WHERE hotel_name = ?)";
                preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, hotelName);
            }

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int user_id = resultSet.getInt(2);
                int hotel_id = resultSet.getInt(3);
                ReviewsModel reviewsModel = new ReviewsModel();

                reviewsModel.setReviewText(resultSet.getString(4));
                reviewsModel.setRating(resultSet.getInt(5));

                String sql1 = "SELECT hotel_name FROM hotel WHERE hotel_id = ?";
                PreparedStatement preparedStatement1 = con.prepareStatement(sql1);
                preparedStatement1.setInt(1, hotel_id);
                ResultSet resultSet1 = preparedStatement1.executeQuery();

                if (resultSet1.next()) {
                    reviewsModel.setHotelName(resultSet1.getString(1));
                }

                String sql2 = "SELECT user_name FROM \"user\" WHERE user_id = ?";
                PreparedStatement preparedStatement2 = con.prepareStatement(sql2);
                preparedStatement2.setInt(1, user_id);
                ResultSet resultSet2 = preparedStatement2.executeQuery();

                if (resultSet2.next()) {
                    reviewsModel.setUsername(resultSet2.getString(1));
                }

                ReviewsModel.getReviews().add(reviewsModel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public static void extractReviewsAscending(String hotelName) {
        Connection con = connectToDataBase();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String sql;
            if (hotelName == null) {
                sql = "SELECT * FROM reviews ORDER BY rating ASC";
                preparedStatement = con.prepareStatement(sql);
            } else {
                sql = "SELECT * FROM reviews WHERE hotel_id = (SELECT hotel_id FROM hotel WHERE hotel_name = ?) ORDER BY rating ASC";
                preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, hotelName);
            }

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int user_id = resultSet.getInt(2);
                int hotel_id = resultSet.getInt(3);
                ReviewsModel reviewsModel = new ReviewsModel();

                reviewsModel.setReviewText(resultSet.getString(4));
                reviewsModel.setRating(resultSet.getInt(5));

                String sql1 = "SELECT hotel_name FROM hotel WHERE hotel_id = ?";
                PreparedStatement preparedStatement1 = con.prepareStatement(sql1);
                preparedStatement1.setInt(1, hotel_id);
                ResultSet resultSet1 = preparedStatement1.executeQuery();

                if (resultSet1.next()) {
                    reviewsModel.setHotelName(resultSet1.getString(1));
                }

                String sql2 = "SELECT user_name FROM \"user\" WHERE user_id = ?";
                PreparedStatement preparedStatement2 = con.prepareStatement(sql2);
                preparedStatement2.setInt(1, user_id);
                ResultSet resultSet2 = preparedStatement2.executeQuery();

                if (resultSet2.next()) {
                    reviewsModel.setUsername(resultSet2.getString(1));
                }

                ReviewsModel.getReviews().add(reviewsModel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void extractReviewsDescending(String hotelName) {
        Connection con = connectToDataBase();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String sql;
            if (hotelName == null) {
                sql = "SELECT * FROM reviews ORDER BY rating DESC";
                preparedStatement = con.prepareStatement(sql);
            } else {
                sql = "SELECT * FROM reviews WHERE hotel_id = (SELECT hotel_id FROM hotel WHERE hotel_name = ?) ORDER BY rating DESC";
                preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, hotelName);
            }

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int user_id = resultSet.getInt(2);
                int hotel_id = resultSet.getInt(3);
                ReviewsModel reviewsModel = new ReviewsModel();

                reviewsModel.setReviewText(resultSet.getString(4));
                reviewsModel.setRating(resultSet.getInt(5));

                String sql1 = "SELECT hotel_name FROM hotel WHERE hotel_id = ?";
                PreparedStatement preparedStatement1 = con.prepareStatement(sql1);
                preparedStatement1.setInt(1, hotel_id);
                ResultSet resultSet1 = preparedStatement1.executeQuery();

                if (resultSet1.next()) {
                    reviewsModel.setHotelName(resultSet1.getString(1));
                }

                String sql2 = "SELECT user_name FROM \"user\" WHERE user_id = ?";
                PreparedStatement preparedStatement2 = con.prepareStatement(sql2);
                preparedStatement2.setInt(1, user_id);
                ResultSet resultSet2 = preparedStatement2.executeQuery();

                if (resultSet2.next()) {
                    reviewsModel.setUsername(resultSet2.getString(1));
                }

                ReviewsModel.getReviews().add(reviewsModel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}