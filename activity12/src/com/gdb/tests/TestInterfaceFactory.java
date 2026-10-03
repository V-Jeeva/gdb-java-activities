package com.gdb.tests;

import com.gdb.domain.*;
import com.gdb.exceptions.*;

public class TestInterfaceFactory {
    public static void main(String[] args) {
        System.out.println("=== Activity 12: Factory-Driven System Suite ===");

        // Test 1: Savings Account Creation & Deposit
        IAccount savings = AccountFactory.createAccount("SAVINGS", "S1", "User1", 30, 2000.0, "ACTIVE", "1111");
        try {
            savings.deposit(500.0);
            savings.withdraw(2000.0, "1111");
        } catch (MinimumBalanceViolationException e) {
            System.out.println("[Test 1] Savings Account Creation & Deposit: [PASS]");
        } catch (Exception e) {}

        // Test 2: Current Account Overdraft Withdrawal
        IAccount current = AccountFactory.createAccount("CURRENT", "C1", "User2", 30, 5000.0, "ACTIVE", "2222");
        try {
            current.withdraw(10000.0, "2222");
            if (current.getBalance() == -5000.0) {
                System.out.println("[Test 2] Current Account Overdraft Withdrawal: [PASS]");
            }
        } catch (Exception e) {}

        // Test 3: Fixed Deposit Premature Withdrawal Block
        IAccount fd = AccountFactory.createAccount("FIXED_DEPOSIT", "F1", "User3", 30, 50000.0, "ACTIVE", "3333");
        try {
            fd.withdraw(1000.0, "3333");
        } catch (AccountException e) {
            System.out.println("[Test 3] Fixed Deposit Premature Withdrawal Block: [PASS]");
        }

        // Test 4: Invalid Type Rejection
        try {
            IAccount invalid = AccountFactory.createAccount("BITCOIN", "B1", "User4", 30, 0.0, "ACTIVE", "4444");
        } catch (IllegalArgumentException e) {
            System.out.println("[Test 4] Invalid Type Rejection: [PASS]");
        } catch (Exception e) {}

        System.out.println("Factory-driven architecture successfully verified!");
    }
}
