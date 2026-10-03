package com.gdb.domain;

public class SalaryAccount extends Account {
    private String employerName;
    private int inactiveMonths = 0;

    public SalaryAccount(String accountNumber, String name, int age, double balance, String status, String pin, String employerName) {
        super(accountNumber, name, age, balance, "SALARY", status, pin);
        this.employerName = employerName;
    }

    public String getEmployerName() { return employerName; }
    public int getInactiveMonths() { return inactiveMonths; }
    public void setInactiveMonths(int months) { this.inactiveMonths = months; }
    public void incrementInactiveMonths() { this.inactiveMonths++; }
}
