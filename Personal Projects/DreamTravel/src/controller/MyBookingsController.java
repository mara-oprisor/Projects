package controller;

import model.UserModel;
import repository.MyBookingsRepository;
import view.MyBookingsView;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class MyBookingsController {
    private MyBookingsView myBookingsView = new MyBookingsView();

    public MyBookingsController() {
        myBookingsView.setVisibility(true);
        updateTable();
        myBookingsView.getBackButton().addActionListener(e -> changeToMenu());
    }

    private void updateTable() {
        String username = UserModel.getCurrentUser().getUsername();

        List<Object[]> bookings = MyBookingsRepository.extractBookings(username);

        if (bookings != null) {
            DefaultTableModel tableModel = (DefaultTableModel) myBookingsView.getBookingTable().getModel();
            tableModel.setRowCount(0);

            for (Object[] rowDetails : bookings) {
                tableModel.addRow(rowDetails);
            }
        }
    }

    private void changeToMenu() {
        myBookingsView.setVisibility(false);
        MenuClientController menuClientController = new MenuClientController();
    }
}
