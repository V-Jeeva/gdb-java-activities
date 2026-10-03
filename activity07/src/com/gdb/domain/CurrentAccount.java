package com.gdb.domain;

public class CurrentAccount extends Account {
    private double overdraftLimit = 25000.0;

    public CurrentAccount(String accountNumber, String name, int age, double balance, String status, String pin, double overdraftLimit) {
        super(accountNumber, name, age, balance, "CURRENT", status, pin);
        this.overdraftLimit = overdraftLimit;
    }

    public double getOverdraftLimit() { return overdraftLimit; }
    public void setOverdraftLimit(double limit) { this.overdraftLimit = limit; }
}
