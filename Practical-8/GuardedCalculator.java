import java.util.Scanner;

class DivideByZeroException extends Exception {
    public DivideByZeroException(String message) {
        super(message);
    }
}

public class GuardedCalculator {

    public static double calculate(double a, double b, String op) throws DivideByZeroException, IllegalArgumentException {
        switch (op) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                if (b == 0) {
                    throw new DivideByZeroException("Error: Division by zero is not permitted.");
                }
                return a / b;
            default:
                throw new IllegalArgumentException("Error: Unsupported operator '" + op + "'. Use +, -, *, or /.");
        }
    }

    public static void main(String[] args) {
        // Demonstrate handling via simulated test inputs first, and interactive scanner if needed
        String[][] testInputs = {
            {"10", "0", "/"},
            {"abc", "5", "+"},
            {"15", "3", "/"}
        };

        System.out.println("--- Running Guarded Calculator Simulation ---");
        for (String[] input : testInputs) {
            try {
                System.out.println("\nAttempting calculation: " + input[0] + " " + input[2] + " " + input[1]);
                double num1 = Double.parseDouble(input[0]);
                double num2 = Double.parseDouble(input[1]);
                String op = input[2];

                double result = calculate(num1, num2, op);
                System.out.println("Result: " + result);
            } catch (NumberFormatException e) {
                System.out.println("Caught NumberFormatException: Invalid number format entered.");
            } catch (DivideByZeroException e) {
                System.out.println("Caught DivideByZeroException: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Caught IllegalArgumentException: " + e.getMessage());
            } finally {
                System.out.println("[Log] Calculation attempt processed.");
            }
        }
    }
}