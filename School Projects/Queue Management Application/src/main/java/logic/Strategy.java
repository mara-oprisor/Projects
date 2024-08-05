package logic;

import model.Client;
import model.Queue;

import java.util.List;

public class Strategy {
    public void addToQueue(Client newClient, List<Queue> queues) {
        queues.getFirst().addClientIntoQueue(newClient);
    }
}
