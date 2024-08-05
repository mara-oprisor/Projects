# Polynomial Calculator
## Description
This was one of the projects for the Programming Techniques courses from university. It represents a polynomial calculator which can perform multiple operations:
- addition
- subtraction
- multiplication
- division
- differentiation
- integration
It is realized in Java and it has a JavaSwing GUI.

## How to use the application
- The user must insert one or two polynomils, depending on the operation that he wants to perform, into the spaces.
- Then, he has to choose the opeartion that will be performed.
- The polynomials are checked to have the correct format (they do not contain 2 variables and respect the “rules” of the format of polynomials (there are no groups “+-“, signs are not missing, there isn't a “+” as the first character)
- If they have the correct format the result is shown into the non-editable space, otherwise an error occurs.

## Implementation
1.	Monomial
This class implements the model for a monomial – each of them has an integer degree and an integer or real coefficient (real coefficients appear only when calculating a division or an integration). Therefore, the coefficient will be represented by the Number format.
2.	Polynomial
This class implements the model for polynomials. Each polynomial is a list of monomials represented as a HashMap. The key is the degree of it and the value is the corresponding monomial. 
Some of the more important methods are: 
•	getDegree() – it returns the highest degree from the list of monomials
•	createPolynomial() – it adds the coefficients of the monomials with the same degree before performing calculations with it
•	getLeadTerm() – returns the monomial with the highest degree

3.	CalculatorView
This is the class that implements the graphical user interface. The interface contains of eight buttons (Addition, Subtraction, Multiplication, Division, Differentiation, Integration, Clear and Exit). It also has to editable text fields where the user has to insert the two polynomials used for calculation and a non-editable one in which the result will be displayed.
The application closes when the “X” or  the Exit button is pressed.
![image](https://github.com/user-attachments/assets/00a2179e-cb83-4d03-b529-2141026b7ba6)

4.	CalculatorController
This class implements the controller of the view. Here the buttons receive their action listener in the method exectuteOperations(). These action listeners call the private methods that call the method needed from the Operations class in order to perform the operation selected in the interface.
There is also present a method that extracts the string from the text fields – extract Operands() – and one that prints the result in the text field – print Result().

5.	Operations
This class implements the six methods corresponding to the six arithmetic operations. They take as parameter one or 2 polynomials and return one polynomial or 2 in the case of division (quotient and remainder).

6.	PolynomialCreator
This class changes the format of the polynomial (represented as a HashMap) into a string that shows the classic format of the polynomial – it shows both the degree and the coefficient (ex. x^3+5x^2-x+9). It is sort of a Pretty Print.

7.	PolynomialExtractor
This class changes the format of the polynomial received as a string into the one used in calculation – the Hash Map format. It verifies the degree and the coefficients and enters them into the Map.

## Tests
I have tested the operations at the logic level, including various edge cases, using the JUnit testing.

## Technologies
- Java
- JavaSwing for the GUI
