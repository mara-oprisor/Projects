package logic;

import model.Monomial;
import model.Polynomial;

import java.util.Map;
import java.util.TreeMap;

public class PolynomialCreator {

    public String recreatePolynomial(Polynomial polynomial, int option) {
        TreeMap<Integer, Monomial> terms = new TreeMap<>(polynomial.getMonomials());
        StringBuilder result = new StringBuilder();

        boolean isFirstTerm = true;

        for (Map.Entry<Integer, Monomial> entry : terms.descendingMap().entrySet()) {
            Monomial monomial = entry.getValue();
            int degree = monomial.getDegree();
            double coefficient = monomial.getCoefficient().doubleValue();

            if(coefficient!=0){
                if(!isFirstTerm){
                    appendSign(result, coefficient);
                } else {
                    isFirstTerm = false;
                    if(coefficient<0){
                        result.append("-");
                    }
                }

                appendCoefficient(result, coefficient, degree, option);
                appendDegree(result, degree);
            }
        }
        appendZeroPolynomial(result);

        return result.toString();
    }

    private void appendSign(StringBuilder result, double coefficient){
        if(coefficient > 0) {
            result.append("+");
        } else {
            result.append("-");
        }
    }

    private void appendCoefficient(StringBuilder result, double coefficient, int degree, int option) {
         if(degree == 0 || (coefficient != 1 && coefficient!= -1)) {
            if(option == 0){
                result.append(Math.abs((int)coefficient));
            } else {
                result.append(Math.abs(coefficient));
            }
        }
    }

    private void appendDegree(StringBuilder result, int degree){
        if (degree > 0) {
            result.append("x");
            if (degree > 1) {
                result.append("^").append(degree);
            }
        }
    }

    private void appendZeroPolynomial(StringBuilder result) {
        if(result.isEmpty()){
            result.append("0");
        }
    }

}
