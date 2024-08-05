package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Polynomial {
    private Map<Integer, Monomial> monomials = new HashMap<>();

    public Map<Integer, Monomial> getMonomials() {
        return monomials;
    }

    public int getDegree() {
        int maxDegree = 0;
        for (Map.Entry<Integer, Monomial> entry : this.getMonomials().entrySet()) {
            if (entry.getKey() > maxDegree && entry.getValue().getCoefficient().doubleValue() != 0) {
                maxDegree = entry.getKey();
            }
        }
        return maxDegree;
    }

    public void createPolynomial(List<Monomial> monomials){
        for(Monomial monomial: monomials){
            int degree = monomial.getDegree();
            int coefficient = monomial.getCoefficient().intValue();

            if(this.getMonomials().get(degree)==null){
                this.getMonomials().put(degree, new Monomial(degree, coefficient));
            } else {
                int aux = this.getMonomials().get(degree).getCoefficient().intValue();
                coefficient += aux;
                this.getMonomials().put(degree, new Monomial(degree, coefficient));
            }
        }
    }

    public Polynomial( Monomial monomial) {
        this.monomials.put(monomial.getDegree(), monomial);
    }

    public Polynomial(Map<Integer, Monomial> monomials){
        this.monomials = monomials;
    }

    public Polynomial(){

    }

    public Monomial getLeadTerm(){
        int maxDegree = this.getDegree();
        return this.getMonomials().get(maxDegree);
    }
}
