package businessLayer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderBLLTest {
    private OrderBLL orderBLL = new OrderBLL();
    @Test
    public void insertOrderSuccessfully() {
        orderBLL.getOrder().setClientId(27);
        orderBLL.getOrder().setProductId(10);

        int id = orderBLL.createOrder(3);

        assertEquals(1, id);
    }

    @Test
    public void insertOrderNonexistentProduct() {
        orderBLL.getOrder().setClientId(27);
        orderBLL.getOrder().setProductId(50);

        int id = orderBLL.createOrder(3);

        assertEquals(-1, id);
    }

    @Test
    public void insertOrderNonexistentClient() {
        orderBLL.getOrder().setClientId(50);
        orderBLL.getOrder().setProductId(10);

        int id = orderBLL.createOrder(3);

        assertEquals(-1, id);
    }

    @Test
    public void insertOrderQuantityTooLarge() {
        orderBLL.getOrder().setClientId(27);
        orderBLL.getOrder().setProductId(10);

        int id = orderBLL.createOrder(100);

        assertEquals(0, id);
    }
}