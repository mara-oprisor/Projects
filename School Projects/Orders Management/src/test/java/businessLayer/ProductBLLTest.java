package businessLayer;

import model.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductBLLTest {
    private ProductBLL productBLL = new ProductBLL();
    @Test
    public void addProductSuccessfully() {
        Product product = new Product("Laptop", "Black laptop", 1600, 2);
        int id = productBLL.addProduct(product);

        assertEquals(1, id);
    }

    @Test
    public void addProductUnsuccessfully() {
        Product product = new Product("Laptop", "Black laptop", 1670, 1);
        int id = productBLL.addProduct(product);

        assertEquals(-1, id);
    }

    @Test
    public void addEmptyProduct() {
        Product product = null;
        int id = productBLL.addProduct(product);

        assertEquals(0, id);
    }

    @Test
    public void deleteProductSuccessfully() {
        int id = productBLL.deleteProduct("Laptop1");

        assertEquals(1, id);
    }

    @Test
    public void deleteNonexistentProduct() {
        int id = productBLL.deleteProduct("Laaptop");

        assertEquals(0, id);
    }

    @Test
    public void editProductSuccessfully() {
        Product product = new Product("Floral Dress", "Floral dress, we have all sizes", 120, 16);
        int id = productBLL.editProduct(product, 10);

        assertEquals(1, id);
    }

    @Test
    public void editNonexistentProduct() {
        Product product = new Product("Floral Dress", "Floral dress, we have all sizes", 120, 16);
        int id = productBLL.editProduct(product, 30);

        assertEquals(-1, id);
    }

    @Test
    public void editProductWithEmptyInformation() {
        Product product = null;
        int id = productBLL.editProduct(product, 10);

        assertEquals(0, id);
    }

    @Test
    public void extractStockSuccessfully() {
        int stock = productBLL.extractStock(10);

        assertEquals(16, stock);
    }

    @Test
    public void extractStockNonexistentProduct() {
        int stock = productBLL.extractStock(50);

        assertEquals(-1, stock);
    }

    @Test
    public void extractPriceSuccessfully() {
        int price = productBLL.extractPrice(10);

        assertEquals(120, price);
    }

    @Test
    public void extractPriceNonexistentProduct() {
        int price = productBLL.extractPrice(50);

        assertEquals(-1, price);
    }
}