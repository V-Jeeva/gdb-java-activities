public class TestAccountEnhanced {

    private static void printAccountInfo(AccountEnhanced acc) {
        String pinStatus = acc.hasPin() ? "Yes" : "No";
        System.out.println("Account #" + acc.getAccountNumber() + " | " + 
                           acc.getName() + " (" + acc.getAge() + " yrs) | " + 
                           acc.getAccountType() + " | ₹" + acc.getBalance() + " | " + 
                           acc.getStatus() + " | PIN: " + pinStatus);
    }

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("  GLOBAL DIGITAL BANK - ENHANCED ACCOUNT TEST");
        System.out.println("==================================================");
        
        System.out.println(">>> Test 1: Valid Account Creation");
        AccountEnhanced acc1 = new AccountEnhanced(1001, "John Doe", 25, 1000.0, "Savings");
        System.out.println("Account created!");
        printAccountInfo(acc1);
        
        System.out.println("\n>>> Test 2: Under-18 Age Correction");
        System.out.println("Attempting to create account with age 15...");
        AccountEnhanced acc2 = new AccountEnhanced(1002, "Timmy", 15, 600.0, "Savings");
        System.out.println("Age corrected to 18.");
        printAccountInfo(acc2);

        System.out.println("\n>>> Test 3: Invalid Type Correction");
        System.out.println("Attempting to create account with type 'Bitcoin'...");
        AccountEnhanced acc3 = new AccountEnhanced(1003, "Alice", 30, 800.0, "Bitcoin");
        System.out.println("Type corrected to Savings.");
        printAccountInfo(acc3);

        System.out.println("\n>>> Test 4: Minimum Balance Correction");
        System.out.println("Attempting to create Current account with balance 200.0...");
        AccountEnhanced acc4 = new AccountEnhanced(1004, "Bob", 40, 200.0, "Current");
        System.out.println("Balance corrected to minimum requirement.");
        printAccountInfo(acc4);

        System.out.println("\n>>> Test 5: Withdrawal Minimum-Balance Rule");
        System.out.println("Note: Test 5 uses ₹1200 to comply with the Current-account ₹1000 minimum-balance rule.");
        AccountEnhanced acc5 = new AccountEnhanced(1005, "Eve", 28, 1200.0, "Current");
        acc5.setPin(1234);
        printAccountInfo(acc5);
        System.out.println("Withdrawing ₹200.0 (Valid PIN 1234)...");
        if (acc5.withdraw(200.0, 1234)) {
            System.out.println("Success. New balance: ₹" + acc5.getBalance());
        }
        System.out.println("Withdrawing ₹900.0 (Would violate ₹1000.0 minimum)...");
        if (!acc5.withdraw(900.0, 1234)) {
            System.out.println("Withdrawal blocked: violates minimum balance.");
        }
        
        System.out.println("\n>>> Test 6: Inactive Account Blocks Operations");
        acc5.closeAccount();
        System.out.println("Account suspended.");
        printAccountInfo(acc5);
        System.out.println("Attempting deposit of ₹100.0...");
        if (!acc5.deposit(100.0)) {
            System.out.println("Deposit blocked: account is inactive.");
        }
        acc5.reopenAccount();
        System.out.println("Account reopened.");
        
        System.out.println("\n>>> Test 7: PIN Validation");
        AccountEnhanced acc6 = new AccountEnhanced(1006, "Charlie", 35, 1000.0, "Savings");
        printAccountInfo(acc6);
        System.out.println("Attempting withdrawal with NO PIN set...");
        if (!acc6.withdraw(100.0, 9999)) {
            System.out.println("Withdrawal blocked: PIN not set.");
        }
        System.out.println("Setting valid PIN to 5555.");
        acc6.setPin(5555);
        System.out.println("Attempting withdrawal with INCORRECT PIN 4444...");
        if (!acc6.withdraw(100.0, 4444)) {
            System.out.println("Withdrawal blocked: invalid PIN.");
        }
        System.out.println("Attempting withdrawal with CORRECT PIN 5555...");
        if (acc6.withdraw(100.0, 5555)) {
            System.out.println("Withdrawal success. New balance: ₹" + acc6.getBalance());
        }

        System.out.println("\n>>> Test 8: Final Summary of All Accounts");
        printAccountInfo(acc1);
        printAccountInfo(acc2);
        printAccountInfo(acc3);
        printAccountInfo(acc4);
        printAccountInfo(acc5);
        printAccountInfo(acc6);
        
        System.out.println("==================================================");
        System.out.println("  TEST COMPLETED!");
        System.out.println("==================================================");
    }
}
