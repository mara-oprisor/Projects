package gui;

import logic.SimulationManager;
import model.Client;
import model.Queue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Iterator;

public class SimulationView {
    private JFrame frame = new JFrame("Simulation view");
    private JPanel contentPane = new JPanel(new GridLayout(0, 1));
    private JLabel currentTimeLabel = new JLabel();
    private JLabel waitingClientsLabel = new JLabel();
    private ArrayList<JLabel> queueLabels = new ArrayList<>();
    private JTextArea resultsArea = new JTextArea();
    private int currTime = 0;
    private SimulationManager simulationManager;

    public SimulationView(SimulationManager simulationManager) {
        this.simulationManager = simulationManager;
        setUpGUI();
    }

    private void setUpGUI() {
        frame.setSize(new Dimension(2000, 830));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setContentPane(contentPane);

        contentPane.setBackground(Color.WHITE);
        contentPane.add(currentTimeLabel);
        contentPane.add(waitingClientsLabel);
        contentPane.add(resultsArea);

        Timer timer = new Timer(1000, e -> updateGUI(e));
        timer.start();

        frame.setVisible(true);
    }

    private void updateGUI(ActionEvent e) {
        if (currTime <= simulationManager.getTimeLimit() && (!simulationManager.getClients().isEmpty() || !simulationManager.allQueuesAreEmpty())) {
            createTimeLabel();
            createWaitingClientsLabel();
            createQueueLabels();
            currTime++;
        } else {
            ((Timer) e.getSource()).stop();
            createResultsLabel();
            contentPane.add(resultsArea);
            contentPane.revalidate();
            contentPane.repaint();
            JOptionPane.showMessageDialog(frame, "Simulation ended successfully!");
        }
    }

    private void createTimeLabel() {
        String string = "Time: " + currTime;
        currentTimeLabel.setText(string);
    }

    private void createWaitingClientsLabel() {
        StringBuilder string = new StringBuilder();
        string.append("Waiting clients: ");

        Iterator<Client> iterator = simulationManager.getClients().iterator();
        while (iterator.hasNext()) {
            Client client = iterator.next();
            string.append("(").append(client.getId()).append(",").append(client.getTArrival())
                    .append(",").append(client.getTService()).append("); ");
        }

        waitingClientsLabel.setText(string.toString());
    }

    private String getQueueLabelText(int queueIndex) {
        Queue queue = simulationManager.getScheduler().getQueues().get(queueIndex);

        StringBuilder labelText = new StringBuilder();
        labelText.append("Queue ").append(queueIndex + 1).append(": ");

        if (queue.getSize() == 0) {
            labelText.append("closed");
        } else {
            for (Client client : queue.getClients()) {
                labelText.append("(").append(client.getId()).append(",").append(client.getTArrival())
                        .append(",").append(client.getTService()).append("); ");
            }
        }

        return labelText.toString();
    }


    private void createQueueLabels() {
        contentPane.removeAll();

        int nrLabels = simulationManager.getNrQueues();

        contentPane.add(currentTimeLabel);
        contentPane.add(waitingClientsLabel);

        for (int i = 0; i < nrLabels; i++) {
            JLabel label = new JLabel();
            label.setText(getQueueLabelText(i));
            contentPane.add(label);
            queueLabels.add(label);
        }

        contentPane.revalidate();
        contentPane.repaint();
    }

    private void createResultsLabel() {
        StringBuilder string = new StringBuilder();

        string.append("Peek time: ").append(simulationManager.getPeakTime()).append("   ");
        string.append("Average service time: ").append(simulationManager.getAvgServiceTime()).append("   ");
        string.append("Average waiting time: ").append(simulationManager.getAvgWaitingTime()).append("   ");

        resultsArea.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        resultsArea.setBackground(Color.WHITE);
        resultsArea.setText(string.toString());
    }
}
