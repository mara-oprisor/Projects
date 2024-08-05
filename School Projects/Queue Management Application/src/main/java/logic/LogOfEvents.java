package logic;

import model.Client;
import model.Queue;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class LogOfEvents {
    public static void logEvents(String path, int time, List<Client> clients, List<Queue> queues) {
        writeToFile(path, "Time " + time + "\n");
        writeClientsToFile(path, clients);
        writeQueuesToFile(path, queues);
        writeToFile(path, "\n");
    }

    public static void createOrOpenFile(String path) {
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }

        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeToFile(String path, String content) {
        try {
            File file = new File(path);
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeClientsToFile(String path, List<Client> clients) {
        writeToFile(path, "Waiting clients: ");
        for (Client client : clients) {
            writeToFile(path, "(" + client.getId() + "," + client.getTArrival() + "," + client.getTService() + ");");
        }
        writeToFile(path, "\n");
    }

    private static void writeQueuesToFile(String path, List<Queue> queues) {
        int index = 1;
        for (Queue queue : queues) {
            writeToFile(path, "Queue " + index + ": ");
            index++;
            if (queue.getClients().isEmpty()) {
                writeToFile(path, "closed");
            } else {
                for (Client client : queue.getClients()) {
                    writeToFile(path, "(" + client.getId() + "," + client.getTArrival() + "," + client.getTService() + ");");
                }
            }
            writeToFile(path, "\n");
        }
    }

    public static void writeResultsToFile(String path, int peekTime, double avgWaitingTime, double avgServiceTime) {
        writeToFile(path, "Average waiting time: " + avgWaitingTime + "\n");
        writeToFile(path, "Peek time: " + peekTime + "\n");
        writeToFile(path, "Average service time: " + avgServiceTime + "\n");
    }
}
