package model;

public class Monomial {
    private int degree;
    private Number coefficient;

    public int getDegree() {
        return degree;
    }

    public Number getCoefficient() {
        return coefficient;
    }

    public Monomial(int degree, Number coefficient) {
        this.degree = degree;
        this.coefficient = coefficient;
    }
}
