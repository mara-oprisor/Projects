package dataAccessLayer;

import connection.ConnectionFactory;
import model.Bill;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The BillDAO class provides methods to interact with the database for bill-related operations.
 */
public class BillDAO {
    private static final Logger LOGGER = Logger.getLogger(BillDAO.class.getName());
    private static final String selectQuery = "SELECT * FROM log";
    private static final String insertStatement = "INSERT INTO log (id, idClient, price) VALUES (?, ?, ?)";

    /**
     * Selects all bills from the database.
     *
     * @return An ArrayList containing all the bills retrieved from the database.
     */
    public static ArrayList<Bill> selectBills() {
        Connection connection = ConnectionFactory.getConnection();
        ArrayList<Bill> bills = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet rs = null;

        try{
            statement = connection.prepareStatement(selectQuery);

            rs = statement.executeQuery();
            while(rs.next()) {
                Bill bill = new Bill(rs.getInt(1), rs.getInt(2), rs.getInt(3));
                bills.add(bill);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Error selecting all bills");
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return bills;
    }

    /**
     * Inserts a bill into the database.
     *
     * @param bill The Bill object to be inserted into the database.
     */
    public static void insertBill(Bill bill) {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;

        try{
            statement = connection.prepareStatement(insertStatement);
            statement.setInt(1, bill.id());
            statement.setInt(2, bill.idClient());
            statement.setInt(3, bill.price());

            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Error inserting the bill in the database");
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }
}
