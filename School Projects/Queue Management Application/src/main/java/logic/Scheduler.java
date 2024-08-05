package logic;

import model.StrategyPolicy;
import model.Client;
import model.Queue;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {
    private List<Queue> queues;
    private Strategy strategy;

    public Scheduler(int nrQueues) {
        queues = new ArrayList<>();
        for (int i = 1; i <= nrQueues; i++) {
            queues.add(new Queue());
        }

        for (Queue queue : queues) {
            Thread thread = new Thread(queue);
            thread.start();
        }
    }

    public void chooseStrategy(StrategyPolicy strategyPolicy) {
        if (strategyPolicy == StrategyPolicy.SHORTEST_QUEUE) {
            strategy = new ShortestQueueStrategy();
        } else if (strategyPolicy == StrategyPolicy.SHORTEST_TIME) {
            strategy = new ShortestTimeStrategy();
        }
    }

    public void addClientToQueue(Client newClient, List<Queue> queues) {
        strategy.addToQueue(newClient, queues);
    }

    public List<Queue> getQueues() {
        return queues;
    }
}
