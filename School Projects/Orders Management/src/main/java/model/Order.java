package model;

/**
 * Represents an order entity with basic information such as the ID of the client, the ID of the product and the quantity.
 */
public class Order {
    private int id;
    private int clientId;
    private int productId;
    private int quantity;

    /**
     * Retrieves the ID of the order.
     *
     * @return the ID of the order
     */
    public int getId() {
        return id;
    }

    /**
     * Retrieves the product ID of the order.
     *
     * @return the product ID of the order
     */
    public int getProductId() {
        return productId;
    }

    /**
     * Retrieves the client ID of the order.
     *
     * @return the client ID of the order
     */
    public int getClientId() {
        return clientId;
    }

    /**
     * Retrieves the quantity of the product from the order.
     *
     * @return the quantity of the product from the order
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the ID of the order.
     *
     * @param id the ID of the order
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets the client ID of the order.
     *
     * @param clientId the client ID of the order
     */
    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    /**
     * Sets the product ID of the order.
     *
     * @param productId the product ID of the order
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }

    /**
     * Sets the quantity of the product from the order.
     *
     * @param quantity the quantity of the product from the order
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Constructs a new Order object with no specified attributes.
     */
    public Order() {
    }
}
