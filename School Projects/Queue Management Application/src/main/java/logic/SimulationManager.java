package logic;

import gui.SimulationView;
import model.Client;
import model.Queue;
import model.StrategyPolicy;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class SimulationManager implements Runnable {
    private int timeLimit;
    private int maxArrivalTime;
    private int minArrivalTime;
    private int minServiceTime;
    private int maxServiceTime;
    private int nrClients;
    private int nrQueues;
    private StrategyPolicy strategyPolicy = StrategyPolicy.SHORTEST_QUEUE;
    private Scheduler scheduler;
    private List<Client> clients;
    private int peakTime;
    private double avgServiceTime;
    private double avgWaitingTime;
    private int currentTime = 0;
    private int waitingTime = 0;

    public SimulationManager(int nrClients, int nrQueues, int simulationTime, int minTimeArrival, int maxTimeArrival, int minTimeService, int maxTimeService, StrategyPolicy strategy) {
        scheduler = new Scheduler(nrQueues);
        new SimulationView(this);

        setValues(nrClients, nrQueues, simulationTime, minTimeArrival, maxTimeArrival, minTimeService, maxTimeService, strategy);

        scheduler.chooseStrategy(strategyPolicy);
        clients = new ArrayList<>();

        for (int i = 0; i < nrClients; i++) {
            Client client = new Client();
            client.setId(i + 1);
            client.setTimeService(minServiceTime, maxServiceTime);
            client.setTimeArrival(minArrivalTime, maxArrivalTime);

            clients.add(client);
        }
        Client.sortClients(clients);
    }

    @Override
    public void run() {
        int maxPeopleInQueue = 0;
        int totalNrClients = clients.size();
        this.avgServiceTime = computeAvgServiceTime();
        LogOfEvents.createOrOpenFile("logOfEvents.txt");
        do {
            handleArrivingClient(currentTime);
            if (maxPeopleInQueue < computeNrClientsInQueue()) {
                peakTime = currentTime;
                maxPeopleInQueue = computeNrClientsInQueue();
            }
            LogOfEvents.logEvents("logOfEvents.txt", currentTime, clients, scheduler.getQueues());
            decrementTimesOfFirstClients();
            currentTime++;
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }while (currentTime <= timeLimit && (!getClients().isEmpty() || !allQueuesAreEmpty()));
        if (currentTime <= timeLimit) {
            LogOfEvents.logEvents("logOfEvents.txt", currentTime, clients, scheduler.getQueues());
        }
        stopQueues();

        this.avgWaitingTime = (double) waitingTime / totalNrClients;
        LogOfEvents.writeResultsToFile("logOfEvents.txt", peakTime, avgWaitingTime, avgServiceTime);
    }

    private void setValues(int nrClients, int nrQueues, int simulationTime, int minTimeArrival, int maxTimeArrival, int minTimeService, int maxTimeService, StrategyPolicy strategy) {
        this.setTimeLimit(simulationTime);
        this.setMaxArrivalTime(maxTimeArrival);
        this.setMinArrivalTime(minTimeArrival);
        this.setMinServiceTime(minTimeService);
        this.setMaxServiceTime(maxTimeService);
        this.setNrClients(nrClients);
        this.setNrQueues(nrQueues);
        this.setStrategyPolicy(strategy);

        this.peakTime = 0;
        this.avgServiceTime = 0;
        this.avgWaitingTime = 0;
    }

    private void decrementTimesOfFirstClients() {
        for (Queue queue : scheduler.getQueues()) {
            Client firstClient = queue.getClients().peek();
            if (firstClient != null) {
                firstClient.decrementServiceTime();
            }
        }
    }

    private void handleArrivingClient(int currentTime) {
        for (int i = 0; i < nrClients; i++) {
            if (clients.get(i).getTArrival() == currentTime) {
                scheduler.addClientToQueue(clients.get(i), scheduler.getQueues());
                for (Queue queue : scheduler.getQueues()) {
                    if (queue.contains(clients.get(i))) {
                        int clientWaitingTime = 0;
                        for (Client client : queue.getClients()) {
                            if (client.equals(clients.get(i))) {
                                break;
                            }
                            clientWaitingTime += client.getTService();
                        }
                        waitingTime += clientWaitingTime;
                    }
                }
                clients.remove(clients.get(i--));
                nrClients--;
            }
        }
    }

    private double computeAvgServiceTime() {
        int sumServiceTime = 0;
        for (Client client : clients) {
            sumServiceTime += client.getTService();
        }

        return (double) sumServiceTime / clients.size();
    }

    private int computeNrClientsInQueue() {
        int nrClientsInQueue = 0;

        for (Queue queue : scheduler.getQueues()) {
            nrClientsInQueue += queue.getSize();
        }

        return nrClientsInQueue;
    }

    public Scheduler getScheduler() {
        return scheduler;
    }

    public List<Client> getClients() {
        return clients;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public void setMaxArrivalTime(int maxArrivalTime) {
        this.maxArrivalTime = maxArrivalTime;
    }

    public void setMinArrivalTime(int minArrivalTime) {
        this.minArrivalTime = minArrivalTime;
    }

    public void setMinServiceTime(int minServiceTime) {
        this.minServiceTime = minServiceTime;
    }

    public void setMaxServiceTime(int maxServiceTime) {
        this.maxServiceTime = maxServiceTime;
    }

    public void setNrClients(int nrClients) {
        this.nrClients = nrClients;
    }

    public int getNrQueues() {
        return nrQueues;
    }

    public void setNrQueues(int nrQueues) {
        this.nrQueues = nrQueues;
    }

    public void setStrategyPolicy(StrategyPolicy strategyPolicy) {
        this.strategyPolicy = strategyPolicy;
    }

    public int getPeakTime() {
        return peakTime;
    }

    public double getAvgServiceTime() {
        return avgServiceTime;
    }

    public double getAvgWaitingTime() {
        return avgWaitingTime;
    }

    public boolean allQueuesAreEmpty() {
        for (Queue queue : scheduler.getQueues()) {
            if (queue.getSize() != 0) {
                return false;
            }
        }
        return true;
    }

    private void stopQueues() {
        for (Queue queue : scheduler.getQueues()) {
            queue.stop();
        }
    }
}