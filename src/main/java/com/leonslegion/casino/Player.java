package com.leonslegion.casino;

public abstract class Player {
    private double balance;
    private long accountId;

    Player(double balance, long accountId) {
        this.balance = balance;
        this.accountId = accountId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public abstract double placeBet(double bet) throws Exception;

}
