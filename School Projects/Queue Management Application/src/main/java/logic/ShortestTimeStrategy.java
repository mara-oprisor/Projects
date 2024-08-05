package logic;

import model.Client;
import model.Queue;

import java.util.List;

public class ShortestTimeStrategy extends Strategy {
    @Override
    public void addToQueue(Client newClient, List<Queue> queues) {
        Queue chosenQueue = new Queue();
        int minWaitingTime = Integer.MAX_VALUE;

        for (Queue queue : queues) {
            if (queue.getWaitingTime() < minWaitingTime) {
                minWaitingTime = queue.getWaitingTime();
                chosenQueue = queue;
            }
        }

        chosenQueue.addClientIntoQueue(newClient);
    }
}
