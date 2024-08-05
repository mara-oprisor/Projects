package controller;

import model.HotelModel;
import view.HotelsView;

import javax.swing.*;
import java.util.ArrayList;

public class HotelsController {
    HotelsView hotelsView = new HotelsView();

    public HotelsController() {
        hotelsView.setVisibility(true);
        hotelsView.getBackButton().addActionListener(e -> changeToMenu());
        photoButtonsFunctionality(hotelsView.getPhotoButtons(), hotelsView.getHotels());
        bookingButtonsFunctionality(hotelsView.getBookButtons(), hotelsView.getHotels());
    }

    private void changeToMenu() {
        hotelsView.setVisibility(false);
        MenuClientController menuClientController = new MenuClientController();
    }

    private void photoButtonsFunctionality(ArrayList<JButton> photoButtons, ArrayList<HotelModel> hotels) {
        for (int i = 0; i < 5; i++) {
            final int finalI = i;
            photoButtons.get(i).addActionListener(e -> hotelsView.openPhotoViews(hotels.get(finalI).getHotelName().toString()));
        }
    }

    private void bookingButtonsFunctionality(ArrayList<JButton> bookingButtons, ArrayList<HotelModel> hotels) {
        for (int i = 0; i < 5; i++) {
            final int finalI = i;
            bookingButtons.get(i).addActionListener(e -> changeToBooking(hotels.get(finalI)));
        }
    }

    private void changeToBooking(HotelModel hotel) {
        hotelsView.setVisibility(false);
        BookingController bookingController = new BookingController(hotel);
    }
}
