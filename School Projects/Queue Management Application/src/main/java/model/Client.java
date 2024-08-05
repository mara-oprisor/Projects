package model;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Client implements Comparable<Client> {
    private int id;
    private int tArrival;
    private int tService;

    public void setTimeArrival(int tArrivalMin, int tArrivalMax) {
        Random random = new Random();
        this.tArrival = random.nextInt(tArrivalMax - tArrivalMin + 1) + tArrivalMin;
    }

    public void setTimeService(int tServiceMin, int tServiceMax) {
        Random random = new Random();
        this.tService = random.nextInt(tServiceMax - tServiceMin + 1) + tServiceMin;
    }

    @Override
    public int compareTo(Client client) {
        return Integer.compare(this.tArrival, client.tArrival);
    }

    public void setId(int ID) {
        this.id = ID;
    }

    public int getId() {
        return id;
    }

    public int getTArrival() {
        return tArrival;
    }

    public int getTService() {
        return tService;
    }

    public static void sortClients(List<Client> clients) {
        Collections.sort(clients);
    }

    public void decrementServiceTime() {
        this.tService--;
    }
}
