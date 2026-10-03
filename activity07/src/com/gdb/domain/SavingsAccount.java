package com.gdb.domain;
import com.gdb.exceptions.*;

public class SavingsAccount extends Account {
    private double minBalance = 1000.0;
    private double interestRate = 4.0;

    public SavingsAccount(String accountNumber, String name, int age, double balance, String status, String pin, double minBalance, double interestRate) {
        super(accountNumber, name, age, balance, "SAVINGS", status, pin);
        this.minBalance = minBalance;
        this.interestRate = interestRate;
    }

    public void applyInterest() {
        double interest = getBalance() * (interestRate / 100.0);
        try {
            deposit(interest);
        } catch (Exception e) {}
    }

    public double getMinBalance() { return minBalance; }
    public double getInterestRate() { return interestRate; }
}
