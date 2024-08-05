package repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MyBookingsRepository {
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

    public static List<Object[]> extractBookings(String username) {
        Connection con = connectToDataBase();
        PreparedStatement preparedStatement = null;
        List<Object[]> bookings = new ArrayList<>();

        try {
            String sql = "SELECT * from bookings WHERE user_id = (SELECT user_id FROM \"user\" WHERE user_name = ?)";
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            int hotelId = -1;
            int roomId = -1;
            String checkInDate = null;
            String checkOutDate = null;
            int totalPrice = -1;
            String hotelName = null;
            String roomType = null;

            while (resultSet.next()) {
                hotelId = resultSet.getInt(3);
                roomId = resultSet.getInt(4);
                checkInDate = resultSet.getString(5);
                checkOutDate = resultSet.getString(6);
                totalPrice = resultSet.getInt(7);

                String sql1 = "SELECT hotel_name FROM hotel WHERE hotel_id = ?";
                preparedStatement = con.prepareStatement(sql1);
                preparedStatement.setInt(1, hotelId);
                ResultSet resultSet1 = preparedStatement.executeQuery();

                while (resultSet1.next()) {
                    hotelName = resultSet1.getString(1);
                }

                String sql2 = "SELECT type_name FROM room_types WHERE type_id = (SELECT type_id FROM rooms WHERE room_id = ?)";
                preparedStatement = con.prepareStatement(sql2);
                preparedStatement.setInt(1, roomId);
                ResultSet resultSet2 = preparedStatement.executeQuery();

                while (resultSet2.next()) {
                    roomType = resultSet2.getString(1);
                }

                Object[] rowDetails = {hotelName, roomType, checkInDate, checkOutDate, totalPrice};
                bookings.add(rowDetails);
            }
            return bookings;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
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
