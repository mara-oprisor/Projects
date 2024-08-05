package logic;

import model.Monomial;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class PolynomialExtractor {

    public List<Monomial> extractCoefAndDeg(String polynomial) {
        List<Monomial> monomials = new ArrayList<>();
        if (validatePattern(polynomial) == 0) {
            JOptionPane.showMessageDialog(null, "Wrong format of the polynomial!");
            return null;
        }

        processTerms(polynomial, monomials);
        return monomials;
    }

    private void processTerms(String polynomial, List<Monomial> monomials){
        Pattern termPattern = Pattern.compile("([+-]?\\d*x(\\^\\d+)?)|([+-]?\\d+)");
        Matcher termMatcher = termPattern.matcher(polynomial);

        while (termMatcher.find()) {
            String term = termMatcher.group();
            int degree = extractDegree(term);
            int coefficient = extractCoefficient(term);
            monomials.add(new Monomial(degree, coefficient));
        }
    }

    private int extractDegree(String term){
        int degree = 0;
        if(term.contains("x")) {
            int xIndex = term.indexOf("x");
            if(term.contains("^")){
                String degreePart = term.substring((xIndex + 2));
                degree = Integer.parseInt(degreePart);
            } else {
                degree = 1;
            }
        }
        return degree;
    }

    private int extractCoefficient(String term){
        int coefficient;
        if(term.contains("x")) {
            int xIndex = term.indexOf("x");
            String coefPart = term.substring(0, xIndex);
            if(coefPart.isEmpty() || coefPart.equals("+")){
                coefficient = 1;
            } else if (coefPart.equals("-")) {
                coefficient = -1;
            } else {
                coefficient = Integer.parseInt(coefPart);
            }
        } else {
            coefficient = Integer.parseInt(term);
        }
        return coefficient;
    }

    private int validatePattern(String polynomial) {
        Pattern validationPattern = Pattern.compile("^-?(\\b(\\d+)?(x(\\^\\d+)?)?)([+-](\\b(\\d+)?(x(\\^\\d+)?)?))*$");
        Matcher validationMatcher = validationPattern.matcher(polynomial);

        if (!validationMatcher.matches()) {
            return 0;
        }
        return 1;
    }
}

