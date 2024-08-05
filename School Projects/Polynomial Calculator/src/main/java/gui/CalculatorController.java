package gui;

import logic.Operations;
import logic.PolynomialCreator;
import logic.PolynomialExtractor;
import model.Monomial;
import model.Polynomial;

import javax.swing.*;
import java.util.List;
import java.util.Map;

public class CalculatorController {
    private CalculatorView calculatorView;
    private Operations operations = new Operations();
    private PolynomialExtractor polynomialExtractor = new PolynomialExtractor();
    private PolynomialCreator polynomialCreator = new PolynomialCreator();

    public CalculatorController(CalculatorView calculatorView) {
        this.calculatorView = calculatorView;
    }

    public void executeOperations() {
        calculatorView.getAddButton().addActionListener(e -> addPolynomials());
        calculatorView.getSubtractButton().addActionListener(e -> subtractPolynomials());
        calculatorView.getClearButton().addActionListener(e -> clearTextFields());
        calculatorView.getMultiplyButton().addActionListener(e -> multiplyPolynomials());
        calculatorView.getDivideButton().addActionListener(e -> dividePolynomials());
        calculatorView.getDerivativeButton().addActionListener(e -> derivationPolynomial());
        calculatorView.getIntegrateButton().addActionListener(e -> integratePolynomial());
        calculatorView.getExitButton().addActionListener(e -> exitApp());
    }

    private void addPolynomials() {
        Polynomial[] poly = extractOperands(2);
        if(poly == null){
            return;
        }
        Polynomial result = operations.add(poly[0], poly[1]);
        printResult(result, 0);
    }

    private void subtractPolynomials() {
        Polynomial[] poly = extractOperands(2);
        if(poly == null){
            return;
        }
        Polynomial result = operations.subtract(poly[0], poly[1]);
        printResult(result, 0);
    }

    private void clearTextFields() {
        calculatorView.getFirstPolynomialTextBox().setText("");
        calculatorView.getSecondPolynomialTextBox().setText("");
    }

    private void multiplyPolynomials() {
        Polynomial[] poly = extractOperands(2);
        if(poly == null){
            return;
        }
        Polynomial result = operations.multiply(poly[0], poly[1]);
        printResult(result, 0);
    }

    private void dividePolynomials(){
        Polynomial[] poly = extractOperands(2);
        Polynomial[] result;
        if(poly == null){
            return;
        }
        try {
            result = operations.division(poly[0], poly[1]);
        } catch(IllegalArgumentException e){
            JOptionPane.showMessageDialog(null, "Error! Division by 0!");
            return;
        }
        printResultDivision(result);
    }

    private void derivationPolynomial() {
        Polynomial[] poly = extractOperands(1);
        if(poly == null){
            return;
        }
        Polynomial result = operations.derivation(poly[0]);
        printResult(result, 0);
    }

    private void integratePolynomial() {
        Polynomial[] poly = extractOperands(1);
        if(poly == null){
            return;
        }
        Polynomial result = operations.integration(poly[0]);
        printResult(result, 1);
    }

    private void exitApp() {
        System.exit(0);
    }

    private Polynomial[] extractOperands(int nrOperands) {
        String op1 = calculatorView.getFirstPolynomialText();
        String op2 = calculatorView.getSecondPolynomialText();

        if(nrOperands==2){
            if(op1.isEmpty() || op2.isEmpty()){
                JOptionPane.showMessageDialog(calculatorView.getFrame(), "Please enter a polynomial in both fields");
                return null;
            }
        } else if (nrOperands == 1){
            if(op1.isEmpty()){
                JOptionPane.showMessageDialog(calculatorView.getFrame(), "Please enter a polynomial in the first field");
                return null;
            }
        }

        List<Monomial> monomials1 = polynomialExtractor.extractCoefAndDeg(op1);
        List<Monomial> monomials2 = polynomialExtractor.extractCoefAndDeg(op2);

        if (monomials1 == null || monomials2 == null) {
            return null;
        }

        Polynomial poly1 = new Polynomial();
        Polynomial poly2 = new Polynomial();

        poly1.createPolynomial(monomials1);
        poly2.createPolynomial(monomials2);

        return new Polynomial[]{poly1, poly2};
    }

    private void printResult(Polynomial result, int option) {
        String resultAsString = polynomialCreator.recreatePolynomial(result, option);
        calculatorView.setResultTextField("Result = " + resultAsString);
    }

    private void printResultDivision(Polynomial[] result){
        String quotientAsString = polynomialCreator.recreatePolynomial(result[0], 1);
        String remainderAsString = polynomialCreator.recreatePolynomial(result[1], 1);
        calculatorView.setResultTextField("Quotient = " + quotientAsString + "  Remainder = " + remainderAsString);
    }
}
