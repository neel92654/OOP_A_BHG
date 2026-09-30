class BankDatabaseConnection implements AutoCloseable {
    private String connectionName;

    public BankDatabaseConnection(String connectionName) {
        this.connectionName = connectionName;
        System.out.println("[Resource] Connection '" + connectionName + "' opened successfully.");
    }

    public void executeTransaction() {
        System.out.println("[Resource] Executing database transaction...");
        throw new RuntimeException("Database error: Transaction aborted due to network timeout.");
    }

    @Override
    public void close() {
        System.out.println("[Resource] Connection '" + connectionName + "' automatically CLOSED via AutoCloseable.");
    }
}

public class AutoCloseableDemo {

    public static void main(String[] args) {
        System.out.println("--- Demonstrating try-with-resources with AutoCloseable ---");

        try (BankDatabaseConnection conn = new BankDatabaseConnection("MainDB-Cluster")) {
            conn.executeTransaction();
        } catch (Exception e) {
            System.out.println("[Catch Block] Caught primary exception: " + e.getMessage());
        }

        System.out.println("\nExecution continues smoothly after try-with-resources.");
    }
}