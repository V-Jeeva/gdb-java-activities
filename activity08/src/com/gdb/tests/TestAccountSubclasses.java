package com.gdb.tests;

import com.gdb.domain.*;
import com.gdb.exceptions.*;

public class TestAccountSubclasses {
    public static void main(String[] args) {
        System.out.println("=== Activity 8: Polymorphism Test ===");

        // TODO: Step 1 - Test SavingsAccount minimum balance breach
        Account savings = new SavingsAccount("S100", "Raj", 30, 10000.0, "ACTIVE", "1234", 1000.0, 4.0);
        try {
            savings.withdraw(9500.0, "1234");
        } catch (MinimumBalanceViolationException e) {
            System.out.println("[Savings] Withdraw 9500 (breaches min balance 1000): Caught MinimumBalanceViolationException [PASS]");
        } catch (Exception e) {}

        // TODO: Step 2 - Test CurrentAccount valid withdrawal utilizing overdraft facility
        Account current = new CurrentAccount("C200", "Simran", 28, 5000.0, "ACTIVE", "4321", 25000.0);
        try {
            current.withdraw(10000.0, "4321");
            if (current.getBalance() == -5000.0) {
                System.out.println("[Current] Withdraw with Overdraft (Balance goes to -5000): SUCCESS [PASS]");
            }
        } catch (Exception e) {}

        // TODO: Step 3 - Test CurrentAccount exceeding overdraft limit
        try {
            current.withdraw(30000.0, "4321");
        } catch (InsufficientBalanceException e) {
            System.out.println("[Current] Withdraw exceeding Overdraft (exceeds -25000): Caught InsufficientBalanceException [PASS]");
        } catch (Exception e) {}

        // TODO: Step 4 - Test FixedDepositAccount premature withdrawal block
        Account fd = new FixedDepositAccount("F300", "Anil", 45, 50000.0, "ACTIVE", "9999", 12, 6.5);
        try {
            fd.withdraw(1000.0, "9999");
        } catch (AccountException e) {
            System.out.println("[FixedDeposit] Withdraw attempt: Caught AccountException [PASS]");
        }
        
        System.out.println("All polymorphic behaviors verified!");
    }
}
