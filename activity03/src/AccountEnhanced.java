public class AccountEnhanced {
    private int accountNumber;
    private String name;
    private int age;
    private double balance;
    private String accountType;
    private String status;
    private Integer pin;

    public AccountEnhanced(int accountNumber, String name, int age, double initialBalance, String accountType) {
        this.accountNumber = accountNumber;
        this.name = name;
        
        // Age validation
        this.age = (age < 18) ? 18 : age;
        
        // Account type validation
        if ("Savings".equals(accountType) || "Current".equals(accountType)) {
            this.accountType = accountType;
        } else {
            this.accountType = "Savings";
        }
        
        // Minimum balance calculation
        double minBalance = getMinBalanceAmount();
        this.balance = (initialBalance < minBalance) ? minBalance : initialBalance;
        
        this.status = "Active";
    }

    private double getMinBalanceAmount() {
        return "Current".equals(this.accountType) ? 1000.0 : 500.0;
    }

    public boolean deposit(double amount) {
        if (!"Active".equals(this.status) || amount <= 0) {
            return false;
        }
        this.balance += amount;
        return true;
    }

    public boolean withdraw(double amount, int pin) {
        if (!"Active".equals(this.status)) {
            return false;
        }
        if (!verifyPin(pin)) {
            return false;
        }
        if (amount <= 0) {
            return false;
        }
        if (this.balance - amount < getMinBalanceAmount()) {
            return false;
        }
        this.balance -= amount;
        return true;
    }

    public boolean closeAccount() {
        if ("Inactive".equals(this.status)) {
            return false;
        }
        this.status = "Inactive";
        return true;
    }

    public boolean reopenAccount() {
        if ("Active".equals(this.status)) {
            return false;
        }
        this.status = "Active";
        return true;
    }

    public boolean setPin(int pin) {
        if (pin >= 1000 && pin <= 9999) {
            this.pin = pin;
            return true;
        }
        return false;
    }

    public boolean verifyPin(int pin) {
        return hasPin() && this.pin == pin;
    }

    public boolean hasPin() {
        return this.pin != null;
    }

    // Getters and Setters
    public int getAccountNumber() { return accountNumber; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public double getBalance() { return balance; }
    public String getAccountType() { return accountType; }
    public String getStatus() { return status; }
    public Integer getPin() { return pin; }
    
    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = (age < 18) ? 18 : age; }
}
