import java.util.HashMap;
import java.util.Map;

class OutOfStockException extends Exception {
    private final int shortfall;

    public OutOfStockException(String item, int shortfall) {
        super("Insufficient stock for item '" + item + "'. Shortfall: " + shortfall);
        this.shortfall = shortfall;
    }

    public int getShortfall() {
        return shortfall;
    }
}

class InvalidQuantityException extends Exception {
    public InvalidQuantityException(String message) {
        super(message);
    }
}

public class WarehouseSystem {
    private static final Map<String, Integer> inventory = new HashMap<>();

    static {
        inventory.put("Laptop", 10);
        inventory.put("Smartphone", 5);
        inventory.put("Tablet", 0);
    }

    public static void issue(String item, int qty) throws InvalidQuantityException, OutOfStockException {
        if (qty <= 0) {
            throw new InvalidQuantityException("Invalid Quantity: Requested amount (" + qty + ") must be > 0.");
        }

        if (!inventory.containsKey(item)) {
            throw new OutOfStockException(item, qty);
        }

        int available = inventory.get(item);
        if (qty > available) {
            int shortfall = qty - available;
            throw new OutOfStockException(item, shortfall);
        }

        inventory.put(item, available - qty);
        System.out.println("Success: Issued " + qty + " x " + item + " (Remaining stock: " + (available - qty) + ")");
    }

    public static void main(String[] args) {
        Object[][] requests = {
            {"Laptop", 3},
            {"Smartphone", -2},
            {"Tablet", 2},
            {"Laptop", 12},
            {"Monitor", 1}
        };

        System.out.println("--- Processing Warehouse Stock Requests ---");

        for (Object[] req : requests) {
            String item = (String) req[0];
            int qty = (int) req[1];

            try {
                System.out.println("\nRequest: " + qty + " units of " + item);
                issue(item, qty);
            } catch (InvalidQuantityException e) {
                System.out.println("[Handled Error] " + e.getMessage());
            } catch (OutOfStockException e) {
                System.out.println("[Handled Error] " + e.getMessage() + " (Shortfall = " + e.getShortfall() + ")");
            }
        }

        System.out.println("\n--- Batch Stock Processing Completed ---");
    }
}
