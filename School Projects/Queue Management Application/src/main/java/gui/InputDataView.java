package gui;

import logic.SimulationManager;
import model.StrategyPolicy;

import javax.swing.*;
import java.awt.*;

public class InputDataView {
    private JFrame frame = new JFrame("Input data");
    private JPanel contentPane = new JPanel(new BorderLayout());
    private JPanel dataPane = new JPanel(new GridLayout(9, 3, 10, 20));
    private JPanel titlePane = new JPanel(new GridLayout(2, 1));
    private JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
    private JLabel titleLabel = new JLabel("Queue Management Application");
    private JLabel subtitleLabel = new JLabel("Please enter the data for your simulation");
    private JLabel nrClientsLabel = new JLabel("Number of clients: ");
    private JTextField nrClientsText = new JTextField();
    private JLabel nrQueuesLabel = new JLabel("Number of queues: ");
    private JTextField nrQueuesText = new JTextField();
    private JLabel simulationTimeLabel = new JLabel("Simulation time: ");
    private JTextField simulationTimeText = new JTextField();
    private JLabel minTimeArrivalLabel = new JLabel("Minimum time of arrival: ");
    private JTextField minTimeArrivalText = new JTextField();
    private JLabel maxTimeArrivalLabel = new JLabel("Maximum time of arrival: ");
    private JTextField maxTimeArrivalText = new JTextField();
    private JLabel minTimeServiceLabel = new JLabel("Minimum time of service: ");
    private JTextField minTimeServiceText = new JTextField();
    private JLabel maxTimeServiceLabel = new JLabel("Maximum time of service: ");
    private JTextField maxTimeServiceText = new JTextField();
    private JLabel strategyChoiceLabel = new JLabel("Choose the desired strategy: ");
    private JComboBox<StrategyPolicy> strategyChoice = new JComboBox<>(StrategyPolicy.values());
    private JButton validateButton = new JButton("Start Simulation");


    public void initialiseGUI() {
        frame.setSize(new Dimension(2000, 830));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        contentPane.setBorder(BorderFactory.createEmptyBorder(50, 200, 30, 200));
        createTitlePane();
        createDataPane();
        createButtonPane();
        contentPane.add(titlePane, BorderLayout.NORTH);
        contentPane.add(dataPane, BorderLayout.CENTER);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
        frame.setContentPane(contentPane);
        frame.setVisible(true);
    }

    private void createTitlePane() {
        titleLabel.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        subtitleLabel.setFont(new Font(Font.SERIF, Font.BOLD, 15));
        subtitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titlePane.add(titleLabel);
        titlePane.add(subtitleLabel);
    }

    private void createDataPane() {
        dataPane.add(nrClientsLabel);
        dataPane.add(nrClientsText);
        dataPane.add(Box.createRigidArea(new Dimension(0, 10)));
        dataPane.add(nrQueuesLabel);
        dataPane.add(nrQueuesText);
        dataPane.add(Box.createRigidArea(new Dimension(0, 10)));
        dataPane.add(simulationTimeLabel);
        dataPane.add(simulationTimeText);
        dataPane.add(Box.createRigidArea(new Dimension(0, 10)));
        dataPane.add(minTimeArrivalLabel);
        dataPane.add(minTimeArrivalText);
        dataPane.add(Box.createRigidArea(new Dimension(0, 10)));
        dataPane.add(maxTimeArrivalLabel);
        dataPane.add(maxTimeArrivalText);
        dataPane.add(Box.createRigidArea(new Dimension(0, 10)));
        dataPane.add(minTimeServiceLabel);
        dataPane.add(minTimeServiceText);
        dataPane.add(Box.createRigidArea(new Dimension(0, 10)));
        dataPane.add(maxTimeServiceLabel);
        dataPane.add(maxTimeServiceText);
        dataPane.add(Box.createRigidArea(new Dimension(0, 10)));
        dataPane.add(strategyChoiceLabel);
        dataPane.add(strategyChoice);
        dataPane.add(Box.createRigidArea(new Dimension(0, 10)));
    }

    private void createButtonPane() {
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        validateButton.addActionListener(e -> startSimulation());
        buttonPanel.add(validateButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
    }

    private boolean validateInputs() {
        if (!isInteger(nrClientsText.getText()) || !isInteger(nrQueuesText.getText()) || !isInteger(simulationTimeText.getText()) ||
                !isInteger(minTimeArrivalText.getText()) || !isInteger(maxTimeArrivalText.getText()) ||
                !isInteger(minTimeServiceText.getText()) || !isInteger(maxTimeServiceText.getText())) {
            JOptionPane.showMessageDialog(frame, "All input fields must contain valid integers.", "Input Error", JOptionPane.ERROR_MESSAGE);

            return false;
        }

        int minArrival = Integer.parseInt(minTimeArrivalText.getText());
        int maxArrival = Integer.parseInt(maxTimeArrivalText.getText());
        int minService = Integer.parseInt(minTimeServiceText.getText());
        int maxService = Integer.parseInt(maxTimeServiceText.getText());

        if (minArrival > maxArrival) {
            JOptionPane.showMessageDialog(frame, "Minimum arrival time must be less than maximum arrival time.",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (minService > maxService) {
            JOptionPane.showMessageDialog(frame, "Minimum service time must be less than maximum service time.",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    private boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void startSimulation() {
        if (!validateInputs()) {
            return;
        }
        int nrClients = getNrClientsText();
        int nrQueues = getNrQueuesText();
        int simulationTime = getSimulationTimeText();
        int minTimeArrival = getMinTimeArrivalText();
        int maxTimeArrival = getMaxTimeArrivalText();
        int minTimeService = getMinTimeServiceText();
        int maxTimeService = getMaxTimeServiceText();
        StrategyPolicy strategy = getStrategyChoice();

        SimulationManager simulationManager = new SimulationManager(nrClients, nrQueues, simulationTime, minTimeArrival, maxTimeArrival, minTimeService, maxTimeService, strategy);

        Thread simulationThread = new Thread(simulationManager);
        simulationThread.start();

        frame.setVisible(false);
    }

    public int getNrClientsText() {
        int nrClients;
        try {
            nrClients = Integer.parseInt(nrClientsText.getText());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return -1;
        }
        return nrClients;
    }

    public int getNrQueuesText() {
        int nrQueues;
        try {
            nrQueues = Integer.parseInt(nrQueuesText.getText());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return -1;
        }
        return nrQueues;
    }

    public int getSimulationTimeText() {
        int time;
        try {
            time = Integer.parseInt(simulationTimeText.getText());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return -1;
        }
        return time;
    }

    public int getMinTimeArrivalText() {
        int time;
        try {
            time = Integer.parseInt(minTimeArrivalText.getText());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return -1;
        }
        return time;
    }

    public int getMaxTimeArrivalText() {
        int time;
        try {
            time = Integer.parseInt(maxTimeArrivalText.getText());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return -1;
        }
        return time;
    }

    public int getMinTimeServiceText() {
        int time;
        try {
            time = Integer.parseInt(minTimeServiceText.getText());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return -1;
        }
        return time;
    }

    public int getMaxTimeServiceText() {
        int time;
        try {
            time = Integer.parseInt(maxTimeServiceText.getText());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return -1;
        }
        return time;
    }

    public StrategyPolicy getStrategyChoice() {
        return (StrategyPolicy) strategyChoice.getSelectedItem();
    }
}
