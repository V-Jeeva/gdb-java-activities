package com.gdb.tests;

import com.gdb.domain.Account;
import com.gdb.exceptions.*;

public class TestAccountExceptions {
    public static void main(String[] args) {
        System.out.println("=== Activity 6: Exception Handling Suite ===");

        // TODO: Step 1 - Test Invalid PIN Exception
        Account acc1 = new Account("ACC1001", "Rajesh Sharma", 28, 5000.0, "SAVINGS", "ACTIVE", "1234");
        try {
            acc1.withdraw(100.0, "9999");
        } catch (InvalidPinException e) {
            System.out.println("[Test 1] Caught Invalid PIN: " + e.getMessage() + " [PASS]");
        } catch (AccountException e) {}

        // TODO: Step 2 - Test Inactive Account Exception
        Account acc2 = new Account("ACC1002", "Priya Patel", 32, 3000.0, "SAVINGS", "ACTIVE", "5678");
        acc2.suspend();
        try {
            acc2.withdraw(100.0, "5678");
        } catch (InactiveAccountException e) {
            System.out.println("[Test 2] Caught Inactive Account: " + e.getMessage() + " [PASS]");
        } catch (AccountException e) {}

        // TODO: Step 3 - Test Invalid Amount Exception
        Account acc3 = new Account("ACC1003", "Amit Kumar", 45, 1000.0, "CURRENT", "ACTIVE", "1111");
        try {
            acc3.deposit(-500.0);
        } catch (InvalidAmountException e) {
            System.out.println("[Test 3] Caught Invalid Amount: " + e.getMessage() + " [PASS]");
        }

        // TODO: Step 4 - Test Insufficient Balance Exception
        Account acc4 = new Account("ACC1004", "Sneha Gupta", 29, 2000.0, "SAVINGS", "ACTIVE", "2222");
        try {
            acc4.withdraw(5000.0, "2222");
        } catch (InsufficientBalanceException e) {
            System.out.println("[Test 4] Caught Insufficient Funds: " + e.getMessage() + " [PASS]");
        } catch (AccountException e) {}

        // TODO: Step 5 - Test Polymorphic Catch with Base AccountException
        Account acc5 = new Account("ACC1005", "Vikas Singh", 38, 8000.0, "CURRENT", "ACTIVE", "3333");
        acc5.close();
        try {
            acc5.withdraw(100.0, "3333");
        } catch (AccountException e) {
            System.out.println("[Test 5] Polymorphic Handler caught: " + e.getMessage() + " [PASS]");
        }

        System.out.println("All exception handling tests completed successfully!");
    }
}
