package businessLayer;

import dataAccessLayer.AbstractDAO;
import model.Product;

import java.util.ArrayList;

/**
 * The business logic layer class responsible for handling product-related operations.
 */
public class ProductBLL {
    /**
     * The Data Access Object responsible for handling database operations related to products.
     */
    AbstractDAO<Product> dao = new AbstractDAO<>(Product.class);
    /**
     * A list containing all clients currently stored in the database.
     * This list is initialized with the clients retrieved using {@code dao.selectAll()}.
     */
    private ArrayList<Product> products = dao.selectAll();

    /**
     * This method sends the given product to the database to be inserted and receives the status of the insertion
     *
     * @param product the product that will be inserted into the database
     * @return <p>-1 - if the insertion failed<br>0 - if the product given as parameter is null<br>1 - if if the insertion was successful</p>
     */
    public int addProduct(Product product) {
        if (product == null) {
            return 0;
        }

        int id = dao.insert(product);
        if (id == -1) {
            return -1;
        }

        product.setId(id);
        products.add(product);

        return 1;
    }

    /**
     * This method sends the name of the given product to the database to be deleted and receives the status of the deletion
     *
     * @param name the name of the product that will be deleted
     * @return <p>-1 - if the deletion failed<br>0 - if no product with the given name was found<br> 1 - if the deletion was successful</p>
     */
    public int deleteProduct(String name) {
        Product product = dao.findByName(name);
        if (product == null) {
            return 0;
        }

        boolean successfulDelete = dao.delete(name);
        if (successfulDelete) {
            for (Product product1 : products) {
                if (product1.getName().equals(name)) {
                    products.remove(product1);
                    return 1;
                }
            }
        }

        return -1;
    }

    /**
     * This method sends the id of the product and his new details and receives the status of the update
     *
     * @param product it contains the new details of the product that will be updated into the database
     * @param id      it represents the id of the product whose details will be updated
     * @return <p>-2 - if the update failed<br>-1 - if the product whose id was given is not found into the database<br>0 - if the given product details are null<br>1 - if the update was successful</p>
     */
    public int editProduct(Product product, int id) {
        if (product == null) {
            return 0;
        }

        Product product1 = dao.findById(id);
        if (product1 == null) {
            return -1;
        }
        product.setId(id);

        boolean successfulEdit = dao.update(product);
        if (successfulEdit) {
            for (Product product2 : products) {
                if (product2.getId() == id) {
                    product2.setName(product.getName());
                    product2.setDescription(product.getDescription());
                    product2.setPrice(product.getPrice());
                    product2.setStock(product.getStock());
                    return 1;
                }
            }
        }

        return -2;
    }

    /**
     * Retrieves the current stock quantity of a product with the specified ID.
     *
     * @param id the ID of the product
     * @return <p>the current stock quantity of the product<br>-1 if the product with the specified ID is not found</p>
     */
    public int extractStock(int id) {
        Product product = dao.findById(id);
        if (product == null) {
            return -1;
        }

        return product.getStock();
    }

    /**
     * Retrieves the price of a product with the specified ID.
     *
     * @param id – the ID of the product
     * @return <p>the price of the product<br>-1 if the product with the specified ID is not found</p>
     */
    public int extractPrice(int id) {
        Product product = dao.findById(id);
        if (product == null) {
            return -1;
        }

        return product.getPrice();
    }

    /**
     * Resets the stock quantity of a product with the specified ID to the given value.
     *
     * @param id       the ID of the product
     * @param newStock the new stock quantity to set for the product
     */
    public void resetStock(int id, int newStock) {
        for (Product product : products) {
            if (product.getId() == id) {
                product.setStock(newStock);
                dao.update(product);
            }
        }
    }

    /**
     * Retrieves the list of products currently stored in the system.
     *
     * @return an ArrayList containing the products currently stored in the system
     */
    public ArrayList<Product> getProducts() {
        return products;
    }
}
