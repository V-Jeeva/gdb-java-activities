package com.gdb.tests;

import com.gdb.domain.*;
import com.gdb.repository.*;
import com.gdb.service.AccountService;
import com.gdb.logging.*;

public class TestRepositoryInMemory {
    public static void main(String[] args) throws Exception {
        System.out.println("=".repeat(60));
        System.out.println("  ACTIVITY 21 — REPOSITORY PATTERN (IN-MEMORY)");
        System.out.println("=".repeat(60));

        AccountRepository accountRepo = new InMemoryAccountRepository();
        TransactionRepository txnRepo = new InMemoryTransactionRepository();
        TransactionLogger logger = new TransactionLogger(new MemoryLogDestination());

        AccountService service = new AccountService(accountRepo, txnRepo, logger);

        IAccount acc1 = AccountFactory.createAccount("SAVINGS", accountRepo.nextAccountNumber(), "Rajesh Sharma", 30, 50000.0);
        acc1.setPin(1234);
        IAccount acc2 = AccountFactory.createAccount("SAVINGS", accountRepo.nextAccountNumber(), "Priya Patel", 28, 40000.0);
        acc2.setPin(5678);
        accountRepo.save(acc1);
        accountRepo.save(acc2);

        IAccount retrievedAcc1 = accountRepo.findById(1001);
        assert retrievedAcc1 != null;
        ((Account) retrievedAcc1).deposit(15000.0);
        accountRepo.update(retrievedAcc1);

        assert accountRepo.findAll().size() == 2;
        System.out.println("STEP 17: Account Repository CRUD -> PASSED");

        Transaction t1 = new Transaction(Transaction.generateId(), null, 1001, TransactionType.DEPOSIT, 5000.0, 70000.0, "SUCCESS", "Deposit", 0, 0);
        Transaction t2 = new Transaction(Transaction.generateId(), null, 1001, TransactionType.WITHDRAW, 2000.0, 68000.0, "SUCCESS", "Withdraw", 0, 0);
        txnRepo.save(t1);
        txnRepo.save(t2);

        assert txnRepo.findByAccount(1001).size() == 2;
        System.out.println("STEP 18: Transaction Repository Operations -> PASSED");

        IAccount acc3 = service.openAccount("SAVINGS", "Sneha Rao", 26, 20000.0);
        assert acc3.getAccountNumber() == 1003;
        service.deposit(acc3.getAccountNumber(), 5000.0);

        assert service.getAccount(1003).getBalance() == 25000.0;
        System.out.println("STEP 19: Service Integration With Repositories -> PASSED");

        System.out.println("\n============================================================");
        System.out.println(" ALL ACTIVITY 21 IN-MEMORY REPOSITORY TESTS PASSED!");
        System.out.println("============================================================");
    }
}
