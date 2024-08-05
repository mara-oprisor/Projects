package controller;

import model.UserModel;
import repository.HotelBookingsRepository;
import repository.MyBookingsRepository;
import view.HotelBookingsView;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class HotelBookingsController {
    HotelBookingsView hotelBookingsView = new HotelBookingsView();
    public HotelBookingsController(){
        hotelBookingsView.setVisibility(true);
        updateTable();
        hotelBookingsView.getBackButton().addActionListener(e -> changeToMenu());
    }

    private void updateTable(){
        String username = UserModel.getCurrentUser().getUsername();

        List<Object[]> bookings = HotelBookingsRepository.extractBookings(username);

        if(bookings != null){
            DefaultTableModel tableModel = (DefaultTableModel) hotelBookingsView.getBookingTable().getModel();
            tableModel.setRowCount(0);

            for(Object[] rowDetails : bookings){
                tableModel.addRow(rowDetails);
            }
        }
    }

    private void changeToMenu(){
        hotelBookingsView.setVisibility(false);
        MenuAdminController menuAdminController = new MenuAdminController();
    }
}
