package logic;

import model.Monomial;
import model.Polynomial;

import java.util.Map;

public class Operations {
    public Polynomial add(Polynomial poly1, Polynomial poly2) {
        Polynomial polyResult = new Polynomial();
        for (Map.Entry<Integer, Monomial> entry : poly1.getMonomials().entrySet()) {
            int degree = entry.getKey();
            Monomial monomial1 = entry.getValue();
            Monomial monomial2 = poly2.getMonomials().getOrDefault(degree, new Monomial(0, 0));
            polyResult.getMonomials().put(degree, new Monomial(degree, monomial1.getCoefficient().intValue() + monomial2.getCoefficient().intValue()));
        }

        for (Map.Entry<Integer, Monomial> entry : poly2.getMonomials().entrySet()) {
            int degree = entry.getKey();
            if (!poly1.getMonomials().containsKey(degree)) {
                Monomial monomial2 = entry.getValue();
                polyResult.getMonomials().put(degree, new Monomial(degree, monomial2.getCoefficient()));
            }
        }

        return polyResult;
    }

    public Polynomial subtract(Polynomial poly1, Polynomial poly2) {
        Polynomial polyResult = new Polynomial();
        for (Map.Entry<Integer, Monomial> entry : poly1.getMonomials().entrySet()) {
            int degree = entry.getKey();
            Monomial monomial1 = entry.getValue();
            Monomial monomial2 = poly2.getMonomials().getOrDefault(degree, new Monomial(0, 0));
            polyResult.getMonomials().put(degree, new Monomial(degree, monomial1.getCoefficient().intValue() - monomial2.getCoefficient().intValue()));
        }

        for (Map.Entry<Integer, Monomial> entry : poly2.getMonomials().entrySet()) {
            int degree = entry.getKey();
            if (!poly1.getMonomials().containsKey(degree)) {
                Monomial monomial2 = entry.getValue();
                polyResult.getMonomials().put(degree, new Monomial(degree, -monomial2.getCoefficient().intValue()));
            }
        }

        return polyResult;
    }

    public Polynomial multiply(Polynomial poly1, Polynomial poly2) {
        Polynomial polyResult = new Polynomial();
        for (Map.Entry<Integer, Monomial> entry1 : poly1.getMonomials().entrySet()) {
            for (Map.Entry<Integer, Monomial> entry2 : poly2.getMonomials().entrySet()) {
                int degree = entry1.getKey() + entry2.getKey();
                int coefficient = entry1.getValue().getCoefficient().intValue() * entry2.getValue().getCoefficient().intValue();
                if (polyResult.getMonomials().get(degree) != null) {
                    coefficient += polyResult.getMonomials().get(degree).getCoefficient().intValue();
                }
                polyResult.getMonomials().put(degree, new Monomial(degree, coefficient));
            }
        }
        return polyResult;
    }

    public Polynomial[] division(Polynomial poly1, Polynomial poly2){
        if(poly2.getDegree()==0 && poly2.getMonomials().get(0).getCoefficient().intValue()==0){
            throw new IllegalArgumentException("Division by 0!");
        }

        Polynomial quotient = new Polynomial();
        Polynomial remainder = new Polynomial(poly1.getMonomials());

        while(remainder.getDegree() != 0 && remainder.getDegree() >= poly2.getDegree()) {
            Monomial leadTermReminder = remainder.getLeadTerm();
            Monomial leadTermPoly2 = poly2.getLeadTerm();

            Monomial divisionTerm = divideMonomials(leadTermReminder, leadTermPoly2);
            Polynomial multiplicationTerm = multiply(poly2, new Polynomial(divisionTerm));
            remainder = subtract(remainder, multiplicationTerm);
            quotient = add(quotient, new Polynomial(divisionTerm));
        }

        return new Polynomial[]{quotient, remainder};
    }

    private Monomial divideMonomials(Monomial monomial1, Monomial monomial2) {
        int resultDegree = monomial1.getDegree() - monomial2.getDegree();
        double resultCoefficient = monomial1.getCoefficient().doubleValue() / monomial2.getCoefficient().doubleValue();

        return new Monomial(resultDegree, resultCoefficient);
    }

    public Polynomial derivation(Polynomial poly) {
        Polynomial polyResult = new Polynomial();
        for (Map.Entry<Integer, Monomial> entry : poly.getMonomials().entrySet()) {
            int degree = entry.getKey();
            int coefficient = entry.getValue().getCoefficient().intValue();

            coefficient *= degree;
            if (degree > 0) {
                degree--;
                polyResult.getMonomials().put(degree, new Monomial(degree, coefficient));
            }
        }

        return polyResult;
    }

    public Polynomial integration(Polynomial poly) {
        Polynomial polyResult = new Polynomial();
        for (Map.Entry<Integer, Monomial> entry : poly.getMonomials().entrySet()) {
            int degree = entry.getKey();
            int coefficient = entry.getValue().getCoefficient().intValue();

            degree++;
            double coefficientIntegration = (double) coefficient / (degree);
            polyResult.getMonomials().put(degree, new Monomial(degree, coefficientIntegration));
        }
        return polyResult;
    }


}
