package model;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.sleep;

public class Queue implements Runnable {
    private BlockingQueue<Client> clients = new LinkedBlockingQueue<>();
    private AtomicInteger waitingTime = new AtomicInteger(0);
    private volatile boolean running = true;

    public void addClientIntoQueue(Client newClient) {
        this.clients.add(newClient);
        waitingTime.addAndGet(newClient.getTService());
    }

    @Override
    public void run() {
        while (running) {
            Client firstClient;
            if (!clients.isEmpty()) {
                firstClient = clients.peek();
                try {
                    sleep(firstClient.getTService() * 1000L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                clients.remove();
                waitingTime.addAndGet(-firstClient.getTService());
            } else {
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void stop() {
        running = false;
    }

    public int getSize() {
        return this.clients.size();
    }

    public int getWaitingTime() {
        return this.waitingTime.get();
    }

    public BlockingQueue<Client> getClients() {
        return clients;
    }

    public boolean contains(Client isClient) {
        for(Client client: this.getClients()) {
            if(client == isClient) {
                return true;
            }
        }
        return false;
    }
}
