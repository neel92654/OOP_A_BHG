public class Customer {
    private String name;
    private String email;
    private String mobile;
    private final String customerId;
    private static long customerCounter = 100;

    public Customer(String name, String email, String mobile) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.customerId = generateCustomerId();
    }

    private static String generateCustomerId() {
        return "CUST" + customerCounter++;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public String getCustomerId() {
        return customerId;
    }

    public static long getCustomerCounter() {
        return customerCounter;
    }

    public class Account {
        private final String accountNumber;
        private String ownerName;
        private long balance;
        private boolean active;
        private static long accountCounter = 1;

        public Account(String ownerName, long openingBalance) {
            this.ownerName = ownerName;
            this.balance = openingBalance;
            this.accountNumber = generateAccountNumber();
            this.active = true;
        }

        public Account(String ownerName) {
            this(ownerName, 0);
        }

        private static String generateAccountNumber() {
            return "AC" + String.format("%04d", accountCounter++);
        }

        public void deposit(long amount) {
            if (amount > 0) {
                balance += amount;
            }
        }

        public boolean withdraw(long amount) {
            if (amount > 0 && balance >= amount) {
                balance -= amount;
                return true;
            }
            return false;
        }

        public String getAccountNumber() {
            return accountNumber;
        }

        public String getOwnerName() {
            return ownerName;
        }

        public long getBalance() {
            return balance;
        }

        public boolean isActive() {
            return active;
        }
    }

    public static void main(String[] args) {
        Account[] accounts = new Account[3];
        accounts[0] = new Customer("Alice", "alice@gmail.com", "1234567890").new Account("Alice", 1000);
        accounts[1] = new Customer("Bob", "bob@gmail.com", "0987654321").new Account("Bob", 500);
        accounts[2] = new Customer("Charlie", "charlie@gmail.com", "1122334455").new Account("Charlie");

        accounts[0].deposit(200);
        accounts[1].withdraw(100);
        accounts[2].deposit(300);

        for (Account account : accounts) {
            System.out.println("Account Number: " + account.getAccountNumber() +
                    ", Owner: " + account.getOwnerName() +
                    ", Balance: " + account.getBalance());
        }
    }
}