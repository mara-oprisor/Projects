package logic;

import model.Monomial;
import model.Polynomial;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OperationsTest {
    Operations operations = new Operations();
    PolynomialCreator polynomialCreator = new PolynomialCreator();
    Polynomial poly1 = new Polynomial();
    Polynomial poly2 = new Polynomial();
    Polynomial polyResult = new Polynomial();
    Polynomial polyRemainder = new Polynomial();

    @Test
    void testAdditionSameDegree(){
        poly1.getMonomials().put(1, new Monomial(1, 2));
        poly1.getMonomials().put(3, new Monomial(3, -2));

        poly2.getMonomials().put(3, new Monomial(3, 7));
        poly2.getMonomials().put(1, new Monomial(1, -1));
        poly2.getMonomials().put(0, new Monomial(0, 9));

        polyResult.getMonomials().put(3, new Monomial(3, 5));
        polyResult.getMonomials().put(1, new Monomial(1, 1));
        polyResult.getMonomials().put(0, new Monomial(0, 9));

        assertEquals(polynomialCreator.recreatePolynomial(polyResult, 0), polynomialCreator.recreatePolynomial(operations.add(poly1, poly2), 0));
    }
    @Test
    void testAdditionDifferentDegree() {
        poly1.getMonomials().put(1, new Monomial(1, 2));
        poly1.getMonomials().put(3, new Monomial(3, -2));

        poly2.getMonomials().put(2, new Monomial(2, 7));
        poly2.getMonomials().put(1, new Monomial(1, -1));
        poly2.getMonomials().put(0, new Monomial(0, 9));

        polyResult.getMonomials().put(3, new Monomial(3, -2));
        polyResult.getMonomials().put(2, new Monomial(2, 7));
        polyResult.getMonomials().put(1, new Monomial(1, 1));
        polyResult.getMonomials().put(0, new Monomial(0, 9));

        assertEquals(polynomialCreator.recreatePolynomial(polyResult, 0), polynomialCreator.recreatePolynomial(operations.add(poly1, poly2), 0));
    }

    @Test
    void testAdditionCoefficientBecomesZero(){
        poly1.getMonomials().put(1, new Monomial(1, 2));
        poly1.getMonomials().put(2, new Monomial(2, -7));
        poly1.getMonomials().put(3, new Monomial(3, -2));

        poly2.getMonomials().put(2, new Monomial(2, 7));
        poly2.getMonomials().put(1, new Monomial(1, -1));
        poly2.getMonomials().put(0, new Monomial(0, 9));

        polyResult.getMonomials().put(3, new Monomial(3, -2));
        polyResult.getMonomials().put(1, new Monomial(1, 1));
        polyResult.getMonomials().put(0, new Monomial(0, 9));

        assertEquals(polynomialCreator.recreatePolynomial(polyResult, 0), polynomialCreator.recreatePolynomial(operations.add(poly1, poly2), 0));
    }

    @Test
    void testSubtractionSameDegree(){
        poly1.getMonomials().put(1, new Monomial(1, 2));
        poly1.getMonomials().put(3, new Monomial(3, -2));

        poly2.getMonomials().put(3, new Monomial(3, 7));
        poly2.getMonomials().put(1, new Monomial(1, -1));
        poly2.getMonomials().put(0, new Monomial(0, 9));

        polyResult.getMonomials().put(3, new Monomial(3, -9));
        polyResult.getMonomials().put(1, new Monomial(1, 3));
        polyResult.getMonomials().put(0, new Monomial(0, -9));

        assertEquals(polynomialCreator.recreatePolynomial(polyResult, 0), polynomialCreator.recreatePolynomial(operations.subtract(poly1, poly2), 0));
    }

    @Test
    void testSubtractionsDifferentDegree(){
        poly1.getMonomials().put(1, new Monomial(1, 2));
        poly1.getMonomials().put(3, new Monomial(3, -2));

        poly2.getMonomials().put(2, new Monomial(2, 7));
        poly2.getMonomials().put(1, new Monomial(1, -1));
        poly2.getMonomials().put(0, new Monomial(0, 9));

        polyResult.getMonomials().put(3, new Monomial(3, -2));
        polyResult.getMonomials().put(2, new Monomial(2, -7));
        polyResult.getMonomials().put(1, new Monomial(1, 3));
        polyResult.getMonomials().put(0, new Monomial(0, -9));

        assertEquals(polynomialCreator.recreatePolynomial(polyResult, 0), polynomialCreator.recreatePolynomial(operations.subtract(poly1, poly2), 0));
    }

    @Test
    void testSubtractionEqualPolynomials(){
        poly1.getMonomials().put(2, new Monomial(2, 7));
        poly1.getMonomials().put(1, new Monomial(1, -1));
        poly1.getMonomials().put(0, new Monomial(0, 9));

        poly2.getMonomials().put(2, new Monomial(2, 7));
        poly2.getMonomials().put(1, new Monomial(1, -1));
        poly2.getMonomials().put(0, new Monomial(0, 9));

        polyResult.getMonomials().put(0, new Monomial(0, 0));

        assertEquals(polynomialCreator.recreatePolynomial(polyResult, 0), polynomialCreator.recreatePolynomial(operations.subtract(poly1, poly2), 0));
    }

    @Test
    void testSubtractionFromZero(){
        poly1.getMonomials().put(0, new Monomial(0, 0));

        poly2.getMonomials().put(5, new Monomial(5, 6));
        poly2.getMonomials().put(3, new Monomial(3, -9));
        poly2.getMonomials().put(2, new Monomial(2, 7));
        poly2.getMonomials().put(1, new Monomial(1, -1));
        poly2.getMonomials().put(0, new Monomial(0, 9));

        polyResult.getMonomials().put(5, new Monomial(5, -6));
        polyResult.getMonomials().put(3, new Monomial(3, 9));
        polyResult.getMonomials().put(2, new Monomial(2, -7));
        polyResult.getMonomials().put(1, new Monomial(1, 1));
        polyResult.getMonomials().put(0, new Monomial(0, -9));

        assertEquals(polynomialCreator.recreatePolynomial(polyResult, 0), polynomialCreator.recreatePolynomial(operations.subtract(poly1, poly2), 0));
    }

    @Test
    void testMultiplication() {
        poly1.getMonomials().put(1, new Monomial(1, 2));
        poly1.getMonomials().put(3, new Monomial(3, -2));

        poly2.getMonomials().put(2, new Monomial(2, 7));
        poly2.getMonomials().put(1, new Monomial(1, -1));
        poly2.getMonomials().put(0, new Monomial(0, 9));

        polyResult.getMonomials().put(5, new Monomial(5, -14));
        polyResult.getMonomials().put(4, new Monomial(4, 2));
        polyResult.getMonomials().put(3, new Monomial(3, -4));
        polyResult.getMonomials().put(2, new Monomial(2, -2));
        polyResult.getMonomials().put(1, new Monomial(1, 18));

        assertEquals(polynomialCreator.recreatePolynomial(polyResult, 0), polynomialCreator.recreatePolynomial(operations.multiply(poly1, poly2), 0));
    }

    @Test
    void testMultiplicationWithZero(){
        poly1.getMonomials().put(0, new Monomial(0, 0));

        poly2.getMonomials().put(2, new Monomial(2, 7));
        poly2.getMonomials().put(1, new Monomial(1, -1));
        poly2.getMonomials().put(0, new Monomial(0, 9));

        polyResult.getMonomials().put(0, new Monomial(0, 0));

        assertEquals(polynomialCreator.recreatePolynomial(polyResult, 0), polynomialCreator.recreatePolynomial(operations.multiply(poly1, poly2), 0));
    }

    @Test
    void testDerivativeWithoutConstantTerm() {
        poly1.getMonomials().put(5, new Monomial(5, 2));
        poly1.getMonomials().put(4, new Monomial(4, 6));
        poly1.getMonomials().put(2, new Monomial(2, -7));
        poly1.getMonomials().put(1, new Monomial(1, 8));

        polyResult.getMonomials().put(4, new Monomial(4, 10));
        polyResult.getMonomials().put(3, new Monomial(3, 24));
        polyResult.getMonomials().put(1, new Monomial(1, -14));
        polyResult.getMonomials().put(0, new Monomial(0, 8));

        assertEquals(polynomialCreator.recreatePolynomial(polyResult, 0), polynomialCreator.recreatePolynomial(operations.derivation(poly1), 0));
    }

    @Test
    void testDerivationWithConstantTerm() {
        poly1.getMonomials().put(5, new Monomial(5, 2));
        poly1.getMonomials().put(4, new Monomial(4, 6));
        poly1.getMonomials().put(2, new Monomial(2, -7));
        poly1.getMonomials().put(1, new Monomial(1, 8));
        poly1.getMonomials().put(0, new Monomial(0, 4));

        polyResult.getMonomials().put(4, new Monomial(4, 10));
        polyResult.getMonomials().put(3, new Monomial(3, 24));
        polyResult.getMonomials().put(1, new Monomial(1, -14));
        polyResult.getMonomials().put(0, new Monomial(0, 8));

        assertEquals(polynomialCreator.recreatePolynomial(polyResult, 0), polynomialCreator.recreatePolynomial(operations.derivation(poly1), 0));
    }

    @Test
    void testDerivationOfConstant(){
        poly1.getMonomials().put(0, new Monomial(0, 10));

        polyResult.getMonomials().put(0, new Monomial(0, 0));

        assertEquals(polynomialCreator.recreatePolynomial(polyResult, 1), polynomialCreator.recreatePolynomial(operations.derivation(poly1), 1));
    }

    @Test
    void testIntegrationWithResultingIntegerCoefficients() {
        poly1.getMonomials().put(4, new Monomial(4, 10));
        poly1.getMonomials().put(3, new Monomial(3, 4));
        poly1.getMonomials().put(1, new Monomial(1, 6));
        poly1.getMonomials().put(0, new Monomial(0, 5));

        polyResult.getMonomials().put(5, new Monomial(5, 2));
        polyResult.getMonomials().put(4, new Monomial(4, 1));
        polyResult.getMonomials().put(2, new Monomial(2, 3));
        polyResult.getMonomials().put(1, new Monomial(1, 5));

        assertEquals(polynomialCreator.recreatePolynomial(polyResult, 1), polynomialCreator.recreatePolynomial(operations.integration(poly1), 1));
    }

    @Test
    void testIntegrationWithResultingRealCoefficients() {
        poly1.getMonomials().put(4, new Monomial(4, 3));
        poly1.getMonomials().put(3, new Monomial(3, 4));
        poly1.getMonomials().put(1, new Monomial(1, 5));
        poly1.getMonomials().put(0, new Monomial(0, 5));

        polyResult.getMonomials().put(5, new Monomial(5, 0.6));
        polyResult.getMonomials().put(4, new Monomial(4, 1));
        polyResult.getMonomials().put(2, new Monomial(2, 2.5));
        polyResult.getMonomials().put(1, new Monomial(1, 5));

        assertEquals(polynomialCreator.recreatePolynomial(polyResult, 1), polynomialCreator.recreatePolynomial(operations.integration(poly1), 1));
    }

    @Test
    void testDivide(){
        poly1.getMonomials().put(0, new Monomial(0, -5));
        poly1.getMonomials().put(1, new Monomial(1, 6));
        poly1.getMonomials().put(2, new Monomial(2, -2));
        poly1.getMonomials().put(3, new Monomial(3, 1));

        poly2.getMonomials().put(0, new Monomial(0, -1));
        poly2.getMonomials().put(2, new Monomial(2, 1));

        polyResult.getMonomials().put(0, new Monomial(0, -2));
        polyResult.getMonomials().put(1, new Monomial(1, 1));

        polyRemainder.getMonomials().put(1, new Monomial(1, 7));
        polyRemainder.getMonomials().put(0, new Monomial(0, -7));

        assertEquals(polynomialCreator.recreatePolynomial(polyResult, 1), polynomialCreator.recreatePolynomial(operations.division(poly1, poly2)[0], 1));
        assertEquals(polynomialCreator.recreatePolynomial(polyRemainder, 1), polynomialCreator.recreatePolynomial(operations.division(poly1, poly2)[1], 1));
    }

    @Test
    void testDivideReminderZero(){
        poly1.getMonomials().put(2, new Monomial(2, 5));
        poly1.getMonomials().put(1, new Monomial(1, 3));
        poly1.getMonomials().put(0, new Monomial(0, -2));

        poly2.getMonomials().put(1, new Monomial(1, 1));
        poly2.getMonomials().put(0, new Monomial(0, 1));

        polyResult.getMonomials().put(1, new Monomial(1, 5));
        polyResult.getMonomials().put(0, new Monomial(0, -2));

        polyRemainder.getMonomials().put(0, new Monomial(0, 0));

        assertEquals(polynomialCreator.recreatePolynomial(polyResult, 1), polynomialCreator.recreatePolynomial(operations.division(poly1, poly2)[0], 1));
        assertEquals(polynomialCreator.recreatePolynomial(polyRemainder, 1), polynomialCreator.recreatePolynomial(operations.division(poly1, poly2)[1], 1));
    }

    @Test
    void testDivideEqualPolynomials(){
        poly1.getMonomials().put(0, new Monomial(0, -5));
        poly1.getMonomials().put(1, new Monomial(1, 6));
        poly1.getMonomials().put(2, new Monomial(2, -2));
        poly1.getMonomials().put(3, new Monomial(3, 1));

        poly2.getMonomials().put(0, new Monomial(0, -5));
        poly2.getMonomials().put(1, new Monomial(1, 6));
        poly2.getMonomials().put(2, new Monomial(2, -2));
        poly2.getMonomials().put(3, new Monomial(3, 1));

        polyResult.getMonomials().put(0, new Monomial(0, 1));
        polyRemainder.getMonomials().put(0, new Monomial(0, 0));

        assertEquals(polynomialCreator.recreatePolynomial(polyResult, 1), polynomialCreator.recreatePolynomial(operations.division(poly1, poly2)[0], 1));
        assertEquals(polynomialCreator.recreatePolynomial(polyRemainder, 1), polynomialCreator.recreatePolynomial(operations.division(poly1, poly2)[1], 1));
    }

    @Test
    void testDivideByZero(){
        poly1.getMonomials().put(2, new Monomial(2, 5));
        poly1.getMonomials().put(1, new Monomial(1, 3));
        poly1.getMonomials().put(0, new Monomial(0, -2));

        poly2.getMonomials().put(0, new Monomial(0, 0));

        try{
            polyResult = operations.division(poly1, poly2)[0];
            fail("Expected exception was not thrown");
        } catch (IllegalArgumentException e){
            e.getMessage();
        }
    }

}