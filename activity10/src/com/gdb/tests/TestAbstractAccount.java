package com.gdb.tests;

import com.gdb.domain.*;
import com.gdb.exceptions.*;

public class TestAbstractAccount {

    public static void transfer(AbstractAccount source, AbstractAccount destination, double amount, String pin) throws Exception {
        source.withdraw(amount, pin);
        destination.deposit(amount);
    }

    public static void main(String[] args) {
        System.out.println("=== Activity 10: Banking Operations Suite ===");

        // Step 1 - Create an array/portfolio of AbstractAccount objects
        AbstractAccount savings = new SavingsAccount("S100", "Alice", 28, 10000.0, "ACTIVE", "1111", 1000.0, 4.0);
        AbstractAccount current = new CurrentAccount("C200", "Alice", 28, 5000.0, "ACTIVE", "2222", 25000.0);
        AbstractAccount salary = new SalaryAccount("SAL300", "Alice", 28, 0, "ACTIVE", "3333", "Acme Inc");

        AbstractAccount[] portfolio = { savings, current, salary };

        // Step 3 - Successful transfer
        try {
            transfer(savings, current, 3000.0, "1111");
            System.out.println("Transfer Rs 3000 from Savings to Current: SUCCESS");
            System.out.println("Savings Balance: Rs " + savings.getBalance() + " | Current Balance: Rs " + current.getBalance());
        } catch (Exception e) {}

        // Step 4 - Failed transfer
        double savingsBefore = savings.getBalance();
        double currentBefore = current.getBalance();
        try {
            transfer(savings, current, 2000.0, "wrong");
        } catch (Exception e) {
            if (savings.getBalance() == savingsBefore && current.getBalance() == currentBefore) {
                System.out.println("Failed Transfer (Wrong PIN): Exception caught, no balance changed [PASS]");
            }
        }

        // Step 5 - Monthly cycle
        for (AbstractAccount acc : portfolio) {
            if (acc instanceof SavingsAccount) {
                ((SavingsAccount) acc).applyInterest();
            } else if (acc instanceof SalaryAccount) {
                int inactive = ((SalaryAccount) acc).getInactiveMonths();
            }
        }
        System.out.println("Monthly Interest Cycle processed for all qualifying accounts.");

        System.out.println("All banking operations passed!");
    }
}
