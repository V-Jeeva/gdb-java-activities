public class TestAccount {
    public static void printAccountInfo(Account acc) {
        System.out.println("Account #" + acc.getAccountNumber() + " | " + 
                           acc.getName() + " (" + acc.getAge() + " yrs) | " + 
                           acc.getAccountType() + " | ₹" + acc.getBalance() + " | " + 
                           acc.getStatus());
    }

    public static void main(String[] args) {
        System.out.println("=== Activity 2: Test Account ===");
        
        Account acc1 = new Account(1001, "John Doe", 25, 1000.0, "Savings");
        System.out.println("\nCreated Account 1001:");
        printAccountInfo(acc1);
        
        System.out.println("\nDepositing ₹500.0...");
        if (acc1.deposit(500.0)) {
            System.out.println("Deposit successful. New balance: ₹" + acc1.getBalance());
        }
        
        System.out.println("\nAttempting to deposit ₹-100.0...");
        if (!acc1.deposit(-100.0)) {
            System.out.println("Deposit failed: invalid amount.");
        }
        
        System.out.println("\nWithdrawing ₹200.0...");
        if (acc1.withdraw(200.0)) {
            System.out.println("Withdrawal successful. New balance: ₹" + acc1.getBalance());
        }
        
        System.out.println("\nAttempting to withdraw ₹2000.0...");
        if (!acc1.withdraw(2000.0)) {
            System.out.println("Withdrawal failed: insufficient balance.");
        }
        
        System.out.println("\nCurrent balance: ₹" + acc1.getBalance());
        
        Account acc2 = new Account(1002, "Jane Smith", 30, 2000.0, "Current");
        
        System.out.println("\n--- All Accounts ---");
        printAccountInfo(acc1);
        printAccountInfo(acc2);
        
        System.out.println("\n=== End of Activity 2 ===");
    }
}
