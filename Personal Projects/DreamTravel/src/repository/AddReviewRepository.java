package repository;

import javax.swing.*;
import java.sql.*;

public class AddReviewRepository {
    private static Connection connectToDataBase(){
        Connection con=null;
        try{
            String url="jdbc:postgresql://localhost:5432/travel site";
            String user="postgres";
            String password="Qwerty1!";

            con= DriverManager.getConnection(url, user, password);
            return con;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public static void insertReview(String username, String hotelName, String reviewText, int rating){
        PreparedStatement preparedStatement = null;
        Connection con = connectToDataBase();

        try{
            String sql = "SELECT user_id FROM \"user\" WHERE user_name=?";
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            int userId = -1;
            if (resultSet.next()) {
                userId = resultSet.getInt("user_id");
            }

            String sql1 = "SELECT hotel_id FROM hotel WHERE hotel_name=?";
            preparedStatement = con.prepareStatement(sql1);
            preparedStatement.setString(1, hotelName);
            ResultSet resultSet1 = preparedStatement.executeQuery();

            int hotelId = -1;
            if (resultSet1.next()) {
                hotelId = resultSet1.getInt("hotel_id");
            }

            String sql2 = "INSERT INTO reviews (user_id, hotel_id, review_text, rating) VALUES (?,?,?,?)";
            preparedStatement=con.prepareStatement(sql2);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, hotelId);
            preparedStatement.setString(3, reviewText);
            preparedStatement.setInt(4, rating);

            int rowsAffected = preparedStatement.executeUpdate();

            if(rowsAffected>0){
                JOptionPane.showMessageDialog(null, "The review was added successfully!");
            } else{
                JOptionPane.showMessageDialog(null, "No review found to be inserted.");
            }
        }catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error inserting details: " + e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
