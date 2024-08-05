package repository;

import model.ReviewsModel;

import java.sql.*;

public class MyReviewsRepository {
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

    public static void extractReviews(String username) {
        Connection con = connectToDataBase();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            int user_id = -1;
            String sql = "SELECT user_id FROM \"user\" WHERE user_name = ?";
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user_id = resultSet.getInt(1);
            }

            String sql4 = "SELECT * FROM reviews WHERE user_id = ?";
            preparedStatement = con.prepareStatement(sql4);
            preparedStatement.setInt(1, user_id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
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

                reviewsModel.setUsername(username);


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
