package com.gdb;

import com.gdb.domain.IAccount;
import com.gdb.domain.Transaction;
import com.gdb.command.TransactionCommand;
import com.gdb.logging.FileLogDestination;
import com.gdb.logging.LogDestination;
import com.gdb.logging.TransactionLogger;
import com.gdb.service.AccountService;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Global Digital Bank — Service Demo");
        System.out.println("=".repeat(60));

        LogDestination dest = new FileLogDestination();
        dest.clear();
        TransactionLogger logger = new TransactionLogger(dest);
        AccountService service = new AccountService(logger);

        IAccount acc1 = service.openAccount("SAVINGS", "Alice", 28, 50000);
        acc1.setPin(1111);
        IAccount acc2 = service.openAccount("CURRENT", "Bob", 35, 100000);
        acc2.setPin(2222);

        System.out.println("Opened Accounts:");
        System.out.println(acc1.getAccountInfo());
        System.out.println(acc2.getAccountInfo());

        service.deposit(acc1.getAccountNumber(), 20000);
        service.withdraw(acc2.getAccountNumber(), 5000, 2222);
        service.transfer(acc1.getAccountNumber(), acc2.getAccountNumber(), 10000, 1111);

        System.out.println("\nFinal Balances:");
        System.out.println(acc1.getAccountInfo());
        System.out.println(acc2.getAccountInfo());

        List<TransactionCommand> history = service.getTransactionHistory();
        System.out.println("\nTransaction History:");
        for (TransactionCommand cmd : history) {
            System.out.println(cmd.getTransaction());
        }
    }
}
