package businessLayer;

import dataAccessLayer.AbstractDAO;
import dataAccessLayer.BillDAO;
import model.Bill;
import model.Order;

import java.util.ArrayList;

/**
 * The business logic layer class responsible for handling order-related operations.
 */
public class OrderBLL {
    /**
     * The Data Access Object responsible for handling database operations related to orders.
     */
    private AbstractDAO<Order> dao = new AbstractDAO<>(Order.class);
    /**
     * The business logic layer class responsible for handling client-related operations.
     */
    private ClientBLL clientBLL = new ClientBLL();
    /**
     * The business logic layer class responsible for handling product-related operations.
     */
    private ProductBLL productBLL = new ProductBLL();
    /**
     * The order instance used for processing orders.
     */
    private Order order = new Order();
    /**
     * The Bills array that is extracted from the database
     */
    private ArrayList<Bill> bills = BillDAO.selectBills();

    /**
     * Creates a new order with the specified quantity. Creates the bill for that order.
     *
     * @param quantity the quantity of the product to be ordered
     * @return -1 if the operation failed due to an error, 0 if there is insufficient stock, and 1 if the order was successfully created
     */
    public int createOrder(int quantity) {
        int stock = productBLL.extractStock(order.getProductId());
        if (stock == -1) {
            return -1;
        }

        if (stock >= quantity) {
            order.setQuantity(quantity);
            int id = dao.insert(order);
            if (id == -1) {
                return -1;
            }
            order.setId(id);
            createBill();
            stock -= quantity;
            productBLL.resetStock(order.getProductId(), stock);
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Computes the price paid for the order. Creates the Bill object and inserts it into the database. Inserts the Bill in the array of Bills
     */
    private void createBill() {
        int priceUnit = productBLL.extractPrice(order.getProductId());
        int finalPrice = order.getQuantity() * priceUnit;

        Bill bill = new Bill(order.getId(), order.getClientId(), finalPrice);
        BillDAO.insertBill(bill);
        bills.add(bill);
    }

    /**
     * Retrieves the ClientBLL instance associated with this OrderBLL.
     *
     * @return the ClientBLL instance
     */
    public ClientBLL getClientBLL() {
        return clientBLL;
    }

    /**
     * Retrieves the ProductBLL instance associated with this OrderBLL.
     *
     * @return the ProductBLL instance
     */
    public ProductBLL getProductBLL() {
        return productBLL;
    }

    /**
     * Retrieves the order instance used for processing orders.
     *
     * @return the order instance
     */
    public Order getOrder() {
        return order;
    }

    /**
     * Retrieves the array list of Bills
     *
     * @return the array list of Bills
     */
    public ArrayList<Bill> getBills() {
        return bills;
    }
}
