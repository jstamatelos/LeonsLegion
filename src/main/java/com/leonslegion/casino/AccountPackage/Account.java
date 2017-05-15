package com.leonslegion.casino.AccountPackage;

import com.leonslegion.casino.Console;

import java.util.ArrayList;

/**
 * Created by sarahweisser on 5/9/17.
 */
public class Account {

    private long id;
    private String accountHolderName;
    private long accountBalance = 100000;

    public Account() {}

    public Account(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public long getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(long netWinnings) {
        accountBalance += netWinnings;
    }

    @Override
    public String toString() {
        return "Account ID: " + id + "\nAccount Holder: " + accountHolderName + "\nAccount Balance: "
                + Console.moneyToString(accountBalance);
    }

    public static class AccountFactory {

        public static Account createAccountWithName(String accountHolderName) {
            return new Account(accountHolderName);
        }

    }
    /**
     * Created by sarahweisser on 5/9/17.
     */
    public static class AccountManager {

        private static ArrayList<Account> accounts = new ArrayList<Account>();

        public static void addAccount(String accountHolderName) {
            Account account = new Account(accountHolderName);
            account.setId(accounts.size() + 1);
            accounts.add(account);
        }
        public static ArrayList<Account> getAccounts() {
            return accounts;
        }

        public static String showBalance(Account account) {
            return account.toString();
        }

        public static long getBalance(Account account) {
            return account.getAccountBalance();
        }

        public static String getAccountHolderName(Account account) {
            return account.getAccountHolderName();
        }

        public static void adjustAccountBalance(Account account, long netWinnings) {
            account.setAccountBalance(netWinnings);
        }

        public static Account findAccount(String accountHolderName) {
            for (Account a : accounts) {
                if (accountHolderName.equalsIgnoreCase(a.getAccountHolderName())) {
                    return a;
                }
            }
            return null;
        }

        public static Account findAccount(long id) {
            for (Account a : accounts) {
                if (id == a.getId()) {
                    return a;
                }
            }
            return null;
        }

        public static int findAccountIndex(String accountHolderName) {
            for (Account a : accounts) {
                if (accountHolderName.equalsIgnoreCase(a.getAccountHolderName())) {
                    return accounts.indexOf(a);
                }
            }
            return -1;
        }

        public static int findAccountIndex(long id) {
            for (Account a : accounts) {
                if (id == a.getId()) {
                    return accounts.indexOf(a);
                }
            }
            return -1;
        }
    }
}
