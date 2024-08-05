package model;

/**
 * Represents a product entity with basic information such as name, age, email, and phone number.
 */
public class Product {
    private int id;
    private String name;
    private String description;
    private int price;
    private int stock;

    /**
     * Constructs a new Product object with the specified attributes.
     *
     * @param name        the name of the product
     * @param description the description of the product
     * @param price       the price of the product
     * @param stock       the stock of the product
     */
    public Product(String name, String description, int price, int stock) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    /**
     * Constructs a new Product object with no specified attributes.
     */
    public Product() {

    }

    /**
     * Retrieves the ID of the product.
     *
     * @return the ID of the product
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the product.
     *
     * @param id the ID of the product
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retrieves the name of the product.
     *
     * @return the name of the product
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the product.
     *
     * @param name the name of the product
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the description of the product.
     *
     * @return the description of the product
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the product.
     *
     * @param description the description of the product
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Retrieves the price of the product.
     *
     * @return the price of the product
     */
    public int getPrice() {
        return price;
    }

    /**
     * Sets the price of the product.
     *
     * @param price the price of the product
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Retrieves the stock of the product.
     *
     * @return the stock of the product
     */
    public int getStock() {
        return stock;
    }

    /**
     * Sets the stock of the product.
     *
     * @param stock the stock of the product
     */
    public void setStock(int stock) {
        this.stock = stock;
    }
}
