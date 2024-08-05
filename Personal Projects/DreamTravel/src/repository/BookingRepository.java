package repository;

import model.BookingModel;
import model.HotelModel;
import model.UserModel;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class BookingRepository {
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

    public static boolean checkAvailability(String roomType, HotelModel hotel) {
        PreparedStatement preparedStatement = null;
        Connection con = connectToDataBase();
        ResultSet resultSet = null;
        try {
            String sql = "SELECT room_nr FROM rooms WHERE hotel_id = (SELECT hotel_id FROM hotel WHERE hotel_name = ?) AND type_id = (SELECT type_id FROM room_types WHERE type_name = ? ) AND availability_status='FREE'";
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, hotel.getHotelName().toString());
            preparedStatement.setString(2, roomType);
            resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
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

    public static boolean verifyBookingProcess(String roomType, HotelModel hotel, String checkInDate, String checkOutDate) throws SQLException {
        if (!checkAvailability(roomType, hotel)) {
            return false;
        }

        PreparedStatement preparedStatement = null;
        Connection con = connectToDataBase();

        try {
            int roomId = updateAvailability(roomType, hotel);
            if (roomId != -1) {
                createBooking(roomId, hotel, checkInDate, checkOutDate);
            } else {
                return false;
            }
            return true;

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
                return false;
            }
        }
    }

    private static int updateAvailability(String roomType, HotelModel hotel) {
        PreparedStatement preparedStatement = null;
        Connection con = connectToDataBase();
        int room_id = -1;

        try {
            String sql = "UPDATE rooms SET availability_status = ? WHERE room_nr = (SELECT r.room_nr FROM rooms r JOIN hotel h ON r.hotel_id = h.hotel_id JOIN room_types rt ON r.type_id = rt.type_id WHERE h.hotel_name = ? AND rt.type_name = ? AND r.availability_status = 'FREE' LIMIT 1)";

            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, "OCCUPIED");
            preparedStatement.setString(2, hotel.getHotelName().toString());
            preparedStatement.setString(3, roomType);

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                String selectRoomIdSql = "SELECT room_id FROM rooms WHERE room_nr = (SELECT r.room_nr FROM rooms r JOIN hotel h ON r.hotel_id = h.hotel_id JOIN room_types rt ON r.type_id = rt.type_id WHERE h.hotel_name = ? AND rt.type_name = ? AND r.availability_status = 'OCCUPIED' LIMIT 1)";
                preparedStatement = con.prepareStatement(selectRoomIdSql);
                preparedStatement.setString(1, hotel.getHotelName().toString());
                preparedStatement.setString(2, roomType);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    room_id = resultSet.getInt("room_id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
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

        return room_id;
    }


    private static void createBooking(int roomId, HotelModel hotel, String checkInDate, String checkOutDate) {
        PreparedStatement preparedStatement = null;
        Connection con = connectToDataBase();
        BookingModel booking = new BookingModel();
        try {
            String sql1 = "SELECT user_id FROM \"user\" WHERE user_name = ?";
            preparedStatement = con.prepareStatement(sql1);
            preparedStatement.setString(1, UserModel.getCurrentUser().getUsername());
            ResultSet resultSet = preparedStatement.executeQuery();
            int user_id = 0;
            while (resultSet.next()) {
                user_id = resultSet.getInt(1);
            }

            String sql2 = "SELECT hotel_id FROM hotel WHERE hotel_name = ?";
            preparedStatement = con.prepareStatement(sql2);
            preparedStatement.setString(1, hotel.getHotelName().toString());
            ResultSet resultSet1 = preparedStatement.executeQuery();
            int hotel_id = 0;
            while (resultSet1.next()) {
                hotel_id = resultSet1.getInt(1);
            }

            String sql3 = "SELECT price_per_night FROM rooms WHERE room_id = ?";
            preparedStatement = con.prepareStatement(sql3);
            preparedStatement.setInt(1, roomId);
            ResultSet resultSet2 = preparedStatement.executeQuery();
            int pricePerNight = 0;
            while (resultSet2.next()) {
                pricePerNight = resultSet2.getInt(1);
            }

            int nrNights = nrNights(checkInDate, checkOutDate);

            booking.setUserId(user_id);
            booking.setHotelId(hotel_id);
            booking.setRoomId(roomId);
            booking.setCheckInDate(checkInDate);
            booking.setCheckOutDate(checkOutDate);
            booking.setPrice(nrNights * pricePerNight);

            String sql4 = "INSERT INTO bookings (user_id, hotel_id, room_id, checkin_date, checkout_date, total_cost) VALUES (?,?,?,?,?,?)";
            preparedStatement = con.prepareStatement(sql4);
            preparedStatement.setInt(1, user_id);
            preparedStatement.setInt(2, hotel_id);
            preparedStatement.setInt(3, roomId);
            preparedStatement.setString(4, checkInDate);
            preparedStatement.setString(5, checkOutDate);
            preparedStatement.setInt(6, nrNights * pricePerNight);
            preparedStatement.executeUpdate();

            BookingModel.getBookings().add(booking);
        } catch (SQLException e) {
            e.printStackTrace();
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

    private static int nrNights(String checkInDate, String checkOutDate) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        LocalDate checkInDate1 = LocalDate.parse(checkInDate, dateTimeFormatter);
        LocalDate checkOutDate1 = LocalDate.parse(checkOutDate, dateTimeFormatter);

        int nrNights = (int) ChronoUnit.DAYS.between(checkInDate1, checkOutDate1);

        return nrNights;
    }
}
