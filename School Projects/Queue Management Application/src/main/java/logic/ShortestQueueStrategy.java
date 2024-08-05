package logic;

import model.Client;
import model.Queue;

import java.util.List;

public class ShortestQueueStrategy extends Strategy {
    @Override
    public void addToQueue(Client newClient, List<Queue> queues) {
        Queue chosenQueue = new Queue();
        int minNrClients = Integer.MAX_VALUE;

        for (Queue queue : queues) {
            if (queue.getSize() < minNrClients) {
                minNrClients = queue.getSize();
                chosenQueue = queue;
            }
        }

        chosenQueue.addClientIntoQueue(newClient);
    }
}
