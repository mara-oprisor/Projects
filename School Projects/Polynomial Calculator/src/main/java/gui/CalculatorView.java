package gui;

import javax.swing.*;
import java.awt.*;

public class CalculatorView {
    private JFrame frame = new JFrame("Polynomial Calculator");
    private JPanel contentPane = new JPanel(new BorderLayout());
    private JPanel titlePane = new JPanel();
    private JLabel titleLabel = new JLabel("Polynomial Calculator");
    private JPanel operationPane = new JPanel(new GridLayout(4, 2));
    private JButton addButton = new JButton("Addition");
    private JButton subtractButton = new JButton("Subtraction");
    private JButton multiplyButton = new JButton("Multiplication");
    private JButton divideButton = new JButton("Division");
    private JButton derivativeButton = new JButton("Differentiation");
    private JButton integrateButton = new JButton("Integration");
    private JButton clearButton = new JButton("Clear");
    private JButton exitButton = new JButton("Exit");
    private JPanel polynomialsPane = new JPanel(new GridLayout(3, 2, -150, 70));
    private JLabel firstPolynomial = new JLabel("First Polynomial =");
    private JTextField firstPolynomialText = new JTextField();
    private JLabel secondPolynomial = new JLabel("Second Polynomial =");
    private JTextField secondPolynomialText = new JTextField();
    private JTextField resultTextField = new JTextField();
    private JPanel centerPane = new JPanel(new GridLayout(2, 1));

    private CalculatorController calculatorController = new CalculatorController(this);

    public CalculatorView() {
        initialiseGUI();
    }

    private void initialiseGUI() {
        frame.setSize(new Dimension(500, 600));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        createPolynomialPane();
        createOperationPane();
        createTitlePane();
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPane.setBackground(Color.decode("#E2E9F3"));
        contentPane.add(titlePane, BorderLayout.NORTH);
        centerPane.add(polynomialsPane);
        centerPane.add(operationPane);
        contentPane.add(centerPane, BorderLayout.CENTER);
        calculatorController.executeOperations();
        frame.setContentPane(contentPane);
        frame.setVisible(true);
    }

    private void createTitlePane() {
        titlePane.setBackground(Color.decode("#E2E9F3"));
        titleLabel.setFont(new Font(titleLabel.getFont().getFontName(), Font.BOLD, 30));
        titleLabel.setForeground(Color.decode("#6189BD"));
        titlePane.add(titleLabel);
    }

    private void createOperationPane() {
        operationPane.setBackground(Color.decode("#E2E9F3"));
        setButtonLook(addButton);
        operationPane.add(addButton);
        setButtonLook(subtractButton);
        operationPane.add(subtractButton);
        setButtonLook(multiplyButton);
        operationPane.add(multiplyButton);
        setButtonLook(divideButton);
        operationPane.add(divideButton);
        setButtonLook(derivativeButton);
        operationPane.add(derivativeButton);
        setButtonLook(integrateButton);
        operationPane.add(integrateButton);
        setButtonLook(clearButton);
        operationPane.add(clearButton);
        setButtonLook(exitButton);
        operationPane.add(exitButton);
    }

    private void setButtonLook(JButton button) {
        button.setBackground(Color.decode("#B7C9E1"));
        button.setForeground(Color.BLACK);
    }

    private void createPolynomialPane() {
        polynomialsPane.setBackground(Color.decode("#E2E9F3"));
        firstPolynomial.setFont(new Font(titleLabel.getFont().getFontName(), Font.BOLD, 12));
        polynomialsPane.add(firstPolynomial);
        firstPolynomialText.setPreferredSize(new Dimension(10, 100));
        polynomialsPane.add(firstPolynomialText);
        secondPolynomial.setFont(new Font(titleLabel.getFont().getFontName(), Font.BOLD, 12));
        polynomialsPane.add(secondPolynomial);
        secondPolynomialText.setPreferredSize(new Dimension(20, 100));
        polynomialsPane.add(secondPolynomialText);
        resultTextField.setBackground(Color.decode("#E2E9F3"));
        resultTextField.setFont(new Font(titleLabel.getFont().getFontName(), Font.BOLD, 12));
        resultTextField.setBorder(null);
        resultTextField.setEditable(false);
        polynomialsPane.add(resultTextField);
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getSubtractButton() {
        return subtractButton;
    }

    public JButton getMultiplyButton() {
        return multiplyButton;
    }

    public JButton getDivideButton() {
        return divideButton;
    }

    public JButton getDerivativeButton() {
        return derivativeButton;
    }

    public JButton getIntegrateButton() {
        return integrateButton;
    }

    public JButton getClearButton() {
        return clearButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }

    public String getFirstPolynomialText() {
        return firstPolynomialText.getText();
    }

    public String getSecondPolynomialText() {
        return secondPolynomialText.getText();
    }

    public JTextField getFirstPolynomialTextBox() {
        return firstPolynomialText;
    }

    public JTextField getSecondPolynomialTextBox() {
        return secondPolynomialText;
    }

    public void setResultTextField(String text) {
        this.resultTextField.setText(text);
    }

    public JFrame getFrame() {
        return frame;
    }
}
