import java.util.Arrays;
import java.util.List;

@FunctionalInterface
interface DiscountRule {
    double apply(double price);
}

public class DiscountEngine {
    public static void main(String[] args) {
        List<Double> prices = Arrays.asList(1000.0, 2500.0, 500.0, 150.0);

        // Define discount rules using lambda expressions
        DiscountRule tenPercentOff = price -> price * 0.90;
        DiscountRule twentyPercentOff = price -> price * 0.80;
        DiscountRule flatHundredOff = price -> Math.max(0, price - 100);

        System.out.println("Original Prices: " + prices);

        System.out.println("\n--- Applying 10% Discount Rule ---");
        for (double price : prices) {
            System.out.printf("Original: $%.2f -> Discounted: $%.2f%n", price, tenPercentOff.apply(price));
        }

        System.out.println("\n--- Applying 20% Discount Rule ---");
        for (double price : prices) {
            System.out.printf("Original: $%.2f -> Discounted: $%.2f%n", price, twentyPercentOff.apply(price));
        }

        System.out.println("\n--- Applying Flat $100 Off Rule ---");
        for (double price : prices) {
            System.out.printf("Original: $%.2f -> Discounted: $%.2f%n", price, flatHundredOff.apply(price));
        }
    }
}