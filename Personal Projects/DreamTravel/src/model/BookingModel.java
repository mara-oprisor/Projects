package model;

import java.util.ArrayList;

public class BookingModel {
    private int userId;
    private int hotelId;
    private int roomId;
    private String checkInDate;
    private String checkOutDate;
    private int price;
    private static ArrayList<BookingModel> bookings = new ArrayList<>();

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public static ArrayList<BookingModel> getBookings() {
        return bookings;
    }

    public void setBookings(ArrayList<BookingModel> bookings) {
        this.bookings = bookings;
    }
}
