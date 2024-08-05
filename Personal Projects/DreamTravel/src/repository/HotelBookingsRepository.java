package repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HotelBookingsRepository {
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
        ResultSet resultSet = null;
        List<Object[]> bookings = new ArrayList<>();

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

            String sql3 = "SELECT * from bookings WHERE hotel_id = ?";
            preparedStatement = con.prepareStatement(sql3);
            preparedStatement.setInt(1, hotel_id);
            resultSet = preparedStatement.executeQuery();

            int userId = -1;
            int roomId = -1;
            String checkInDate = null;
            String checkOutDate = null;
            int totalPrice = -1;
            String userName = null;
            String roomType = null;

            while (resultSet.next()) {
                userId = resultSet.getInt(2);
                roomId = resultSet.getInt(4);
                checkInDate = resultSet.getString(5);
                checkOutDate = resultSet.getString(6);
                totalPrice = resultSet.getInt(7);

                String sql4 = "SELECT user_name FROM \"user\" WHERE user_id = ?";
                preparedStatement = con.prepareStatement(sql4);
                preparedStatement.setInt(1, userId);
                ResultSet resultSet1 = preparedStatement.executeQuery();

                while (resultSet1.next()) {
                    userName = resultSet1.getString(1);
                }

                String sql5 = "SELECT type_name FROM room_types WHERE type_id = (SELECT type_id FROM rooms WHERE room_id = ?)";
                preparedStatement = con.prepareStatement(sql5);
                preparedStatement.setInt(1, roomId);
                ResultSet resultSet2 = preparedStatement.executeQuery();

                while (resultSet2.next()) {
                    roomType = resultSet2.getString(1);
                }

                Object[] rowDetails = {userName, roomType, checkInDate, checkOutDate, totalPrice};
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
