package businessLayer;

import model.Client;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientBLLTest {
    private ClientBLL clientBLL = new ClientBLL();

    @Test
    public void addClientSuccessfully() {
        Client client = new Client("Sara", 34, "saraa@gmail.com", "0341004231");
        int id = clientBLL.addClient(client);

        assertEquals(1, id);
    }

    @Test
    public void addEmptyClient() {
        Client client = null;
        int id = clientBLL.addClient(client);

        assertEquals(0, id);
    }

    @Test
    public void addClientUnsuccessfully() {
        Client client = new Client("Sara", 36, "sara1@gmail.com", "0341094231");
        int id = clientBLL.addClient(client);

        assertEquals(-1, id);
    }

    @Test
    public void deleteClientSuccessfully() {
        int id = clientBLL.deleteClient("Sara");

        assertEquals(1, id);
    }

    @Test
    public void deleteNonExistentClient() {
        int id = clientBLL.deleteClient("Saraaa");

        assertEquals(0, id);
    }

    @Test
    public void editSuccessfully() {
        Client client = new Client("Maria123", 24, "mar@dfg.ro", "0234561728");
        int id = clientBLL.editClient(client, 15);

        assertEquals(1, id);
    }

    @Test
    public void editNonExistentClient() {
        Client client = new Client("Maria123", 24, "mar@dfg.ro", "0234561728");
        int id = clientBLL.editClient(client, 6);

        assertEquals(-1, id);
    }

    @Test
    public void editClientWithEmptyInformation() {
        Client client = null;
        int id = clientBLL.editClient(client, 15);

        assertEquals(0, id);
    }
}