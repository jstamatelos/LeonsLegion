package com.leonslegion.casino.AccountPackage;

import com.leonslegion.casino.Console;

import java.util.ArrayList;

/**
 * Created by sarahweisser on 5/9/17.
 */
public class Account {

    private static long idCounter = 0;
    private long id;
    private String accountHolderName;
    private long accountBalance = 100000;

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

    public long getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(long netWinnings) {
        accountBalance += netWinnings;
    }

    @Override
    //TODO - due to long conversion, this won't be right
    public String toString() {
        return "Account ID: " + id + "\nAccount Holder: " + accountHolderName + "\nAccount Balance: " + Console.moneyToString(accountBalance) + "\n";
    }

    /**
     * Created by sarahweisser on 5/10/17.
     */
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

        public static String showBalance(Account account) {
            return account.toString();
        }

        public static ArrayList<Account> getAccounts() {
            return accounts;
        }

        public static void addAccount(Account account) {
            accounts.add(account);
        }

        public static void removeAccount(Account account) {
            accounts.remove(account);
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
