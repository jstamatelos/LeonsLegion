package com.leonslegion.casino;

import java.util.ArrayList;

/**
 * Created by sarahweisser on 5/9/17.
 */
public class AccountManager {

    private ArrayList<Account> accounts = new ArrayList<Account>();

    public String showBalance(Account account) {
        return account.toString();
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public void removeAccount(Account account) {
        accounts.remove(account);
    }

    public double getBalance(Account account) {
        return account.getAccountBalance();
    }

    public String getAccountHolderName(Account account) {
        return account.getAccountHolderName();
    }

    public void adjustAccountBalance(Account account, double netWinnings) {
        account.setAccountBalance(netWinnings);
    }

    public Account findAccount(String accountHolderName) {
        for (Account a : accounts) {
            if (accountHolderName.equalsIgnoreCase(a.getAccountHolderName())) {
                return a;
            }
        }
        return null;
    }

    public Account findAccount(long id) {
        for (Account a : accounts) {
            if (id == a.getId()) {
                return a;
            }
        }
        return null;
    }

    public int findAccountIndex(String accountHolderName) {
        for (Account a : accounts) {
            if (accountHolderName.equalsIgnoreCase(a.getAccountHolderName())) {
                return accounts.indexOf(a);
            }
        }
        return -1;
    }

    public int findAccountIndex(long id) {
        for (Account a : accounts) {
            if (id == a.getId()) {
                return accounts.indexOf(a);
            }
        }
        return -1;
    }
}
