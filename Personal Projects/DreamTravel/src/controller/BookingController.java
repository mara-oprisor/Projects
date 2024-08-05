package controller;

import model.HotelModel;
import repository.BookingRepository;
import view.BookingView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class BookingController {
    private BookingView bookingView;
    public BookingController(HotelModel hotel){
        bookingView = new BookingView(hotel);
        bookingView.setVisibility(true);
        bookingView.getBackButton().addActionListener(e->changeToHotels());
        bookingView.getBookButton().addActionListener(e->bookARoom(hotel));
    }

    public void changeToHotels(){
        bookingView.setVisibility(false);
        HotelsController hotelsController = new HotelsController();
    }

    public void bookARoom(HotelModel hotel) {
        try {
            String roomType = bookingView.getRoomTypeDropDown().getSelectedItem().toString();
            String checkInDate = bookingView.getCheckInDateField().getText();
            String checkOutDate = bookingView.getCheckOutDateField().getText();

            if (!isValidDateFormat(checkInDate) || !isValidDateFormat(checkOutDate)) {
                bookingView.showMessage("Invalid date format. Please enter dates in the format dd.MM.yyyy.", 0);
                return;
            }

            if (!BookingRepository.checkAvailability(roomType, hotel)) {
                bookingView.showMessage("There is no " + roomType + " available! Please choose another type of room!", 0);
            } else {
                boolean bookingSuccessful = BookingRepository.verifyBookingProcess(roomType, hotel, checkInDate, checkOutDate);
                if (bookingSuccessful) {
                    bookingView.showMessage("Successful Booking!", 1);
                } else {
                    bookingView.showMessage("Failed to Book the Room", 0);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isValidDateFormat(String date) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate.parse(date, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
