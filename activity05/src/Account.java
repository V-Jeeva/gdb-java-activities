public class Account {
    private static final double MIN_SAVINGS = 500.0;
    private static final double MIN_CURRENT = 1000.0;
    private static final int MIN_AGE = 18;
    private static final int MIN_PIN = 1000;
    private static final int MAX_PIN = 9999;

    private int accountNumber;
    private String name;
    private int age;
    private double balance;
    private String accountType;
    private String status;
    private Integer pin;

    public Account(int accountNumber, String name, int age, double initialBalance, String accountType) {
        if (age < MIN_AGE) {
            throw new IllegalArgumentException("Age must be at least 18.");
        }
        if (!"Savings".equals(accountType) && !"Current".equals(accountType)) {
            throw new IllegalArgumentException("Account type must be 'Savings' or 'Current'.");
        }
        
        this.accountType = accountType;
        
        double requiredMin = getMinimumBalance();
        if (initialBalance < requiredMin) {
            throw new IllegalArgumentException("Initial balance must be at least " + requiredMin + " for " + accountType + " account.");
        }

        this.accountNumber = accountNumber;
        this.name = name;
        this.age = age;
        this.balance = initialBalance;
        this.status = "Active";
        this.pin = null;
    }

    private double getMinimumBalance() {
        return "Current".equals(this.accountType) ? MIN_CURRENT : MIN_SAVINGS;
    }

    private void checkActive() throws InactiveAccountException {
        if (!"Active".equals(this.status)) {
            throw new InactiveAccountException("Account is currently inactive.");
        }
    }

    public void deposit(double amount) throws InactiveAccountException, InvalidAmountException {
        checkActive();
        if (amount <= 0) {
            throw new InvalidAmountException("Deposit amount must be positive.");
        }
        this.balance += amount;
    }

    public void withdraw(double amount, int pin) 
            throws InactiveAccountException, InvalidPinException, InvalidAmountException, 
                   InsufficientBalanceException, MinimumBalanceViolationException {
        checkActive();
        if (this.pin == null || this.pin != pin) {
            throw new InvalidPinException("Invalid or unset PIN.");
        }
        if (amount <= 0) {
            throw new InvalidAmountException("Withdrawal amount must be positive.");
        }
        if (amount > this.balance) {
            throw new InsufficientBalanceException("Insufficient balance for withdrawal.");
        }
        double minBalance = getMinimumBalance();
        if (this.balance - amount < minBalance) {
            throw new MinimumBalanceViolationException("Withdrawal violates the minimum balance requirement.");
        }
        this.balance -= amount;
    }

    public void closeAccount() {
        if ("Inactive".equals(this.status)) {
            throw new IllegalStateException("Account is already inactive.");
        }
        this.status = "Inactive";
    }

    public void reopenAccount() {
        if ("Active".equals(this.status)) {
            throw new IllegalStateException("Account is already active.");
        }
        this.status = "Active";
    }

    public void setPin(int pin) {
        if (pin < MIN_PIN || pin > MAX_PIN) {
            throw new IllegalArgumentException("PIN must be a 4-digit number (1000-9999).");
        }
        this.pin = pin;
    }

    public boolean verifyPin(int pin) {
        return hasPin() && this.pin == pin;
    }

    public boolean hasPin() {
        return this.pin != null;
    }

    public int getAccountNumber() { return accountNumber; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public double getBalance() { return balance; }
    public String getAccountType() { return accountType; }
    public String getStatus() { return status; }
    public Integer getPin() { return pin; }
}
