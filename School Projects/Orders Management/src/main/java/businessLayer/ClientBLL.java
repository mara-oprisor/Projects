package businessLayer;

import dataAccessLayer.AbstractDAO;
import model.Client;

import java.util.ArrayList;

/**
 * The business logic layer class responsible for handling client-related operations.
 */
public class ClientBLL {

    /**
     * The Data Access Object responsible for handling database operations related to clients.
     */
    private AbstractDAO<Client> dao = new AbstractDAO<>(Client.class);

    /**
     * A list containing all clients currently stored in the database.
     * This list is initialized with the clients retrieved using {@code dao.selectAll()}.
     */
    private ArrayList<Client> clients = dao.selectAll();

    /**
     * This method sends the given client to the database to be inserted and receives the status of the insertion
     *
     * @param client the client that will be inserted into the database
     * @return <p>-1 - if the insertion did not happen because of an error<br>0 - if the client given as parameter is null<br>1 - if if the insertion was successful</p>
     */
    public int addClient(Client client) {
        if (client == null) {
            return 0;
        }

        int id = dao.insert(client);
        if (id == -1) {
            return -1;
        }

        client.setId(id);
        clients.add(client);

        return 1;
    }

    /**
     * This method sends the name of the given client to the database to be deleted and receives the status of the deletion
     *
     * @param name the name of the client that will be deleted
     * @return <p>-1 - if the deletion failed<br>0 - if no client with the given name was found<br> 1 - if the deletion was successful</p>
     */
    public int deleteClient(String name) {
        Client client = dao.findByName(name);
        if (client == null) {
            return 0;
        }

        boolean successfulDelete = dao.delete(name);
        if (successfulDelete) {
            for (Client client1 : clients) {
                if (client1.getName().equals(name)) {
                    clients.remove(client1);
                    return 1;
                }
            }
        }
        return -1;
    }

    /**
     * This method sends the id of the client and his new details and receives the status of the update
     *
     * @param client it contains the new details of the client that will be updated into the database
     * @param id     it represents the id of the client whose details will be updated
     * @return <p>-2 - if the update failed<br>-1 - if the client whose id was given is not found into the database<br>0 - if the given client details are null<br>1 - if the update was successful</p>
     */
    public int editClient(Client client, int id) {
        if (client == null) {
            return 0;
        }

        Client client1 = dao.findById(id);
        if (client1 == null) {
            return -1;
        }
        client.setId(id);

        boolean successfulDelete = dao.update(client);
        if (successfulDelete) {
            for (Client client2 : clients) {
                if (client2.getId() == id) {
                    client2.setName(client.getName());
                    client2.setAge(client.getAge());
                    client2.setEmail(client.getEmail());
                    client2.setPhoneNr(client.getPhoneNr());
                    return 1;
                }
            }
        }

        return -2;
    }

    /**
     * Retrieves the list of clients currently stored in the system.
     *
     * @return an ArrayList containing the clients currently stored in the system
     */
    public ArrayList<Client> getClients() {
        return clients;
    }
}
