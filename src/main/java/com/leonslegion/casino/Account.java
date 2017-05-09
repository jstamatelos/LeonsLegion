package com.leonslegion.casino;

/**
 * Created by sarahweisser on 5/9/17.
 */
public class Account {

    private static long idCounter = 0;
    private long id;
    private String accountHolderName;
    private double accountBalance = 1000;

    public Account() {
        idCounter++;
        this.id = idCounter;
    }

    public Account(String accountHolderName) {
        this();
        this.accountHolderName = accountHolderName;
    }

    public long getIdCounter() {
        return idCounter;
    }

    public long getId() {
        return id;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double netWinnings) {
        accountBalance += netWinnings;
    }

    @Override
    public String toString() {
        return String.format("Account ID: " + id + "\nAccount Holder: " + accountHolderName + "\nAccount Balance: $%.2f", accountBalance);
    }

}
