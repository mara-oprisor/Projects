package repository;

import model.ReviewsModel;

import java.sql.*;

public class HotelReviewRepository {
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
            int admin_id = -1;
            String sql = "SELECT user_id FROM \"user\" WHERE user_name = ?";
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                admin_id = resultSet.getInt(1);
            }

            int hotel_id = -1;
            String sql1 = "SELECT hotel_id FROM hotel WHERE admin_id = ?";
            preparedStatement = con.prepareStatement(sql1);
            preparedStatement.setInt(1, admin_id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                hotel_id = resultSet.getInt(1);
            }


            String sql2 = "SELECT * FROM reviews WHERE hotel_id = ?";
            preparedStatement = con.prepareStatement(sql2);
            preparedStatement.setInt(1, hotel_id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int user_id = resultSet.getInt(2);
                ReviewsModel reviewsModel = new ReviewsModel();

                reviewsModel.setReviewText(resultSet.getString(4));
                reviewsModel.setRating(resultSet.getInt(5));

                String sql3 = "SELECT hotel_name FROM hotel WHERE hotel_id = ?";
                PreparedStatement preparedStatement1 = con.prepareStatement(sql3);
                preparedStatement1.setInt(1, hotel_id);
                ResultSet resultSet1 = preparedStatement1.executeQuery();

                if (resultSet1.next()) {
                    reviewsModel.setHotelName(resultSet1.getString(1));
                }

                String sql4 = "SELECT user_name FROM \"user\" WHERE user_id = ?";
                PreparedStatement preparedStatement2 = con.prepareStatement(sql4);
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
