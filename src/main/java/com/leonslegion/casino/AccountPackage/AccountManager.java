package com.leonslegion.casino.AccountPackage;

import java.util.ArrayList;

/**
 * Created by sarahweisser on 5/14/17.
 */
public final class AccountManager {

    private static ArrayList<Account> accounts;

    public AccountManager() {
        this.accounts = new ArrayList<Account>();
    }


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

        public static double getBalance(Account account) {
            return account.getAccountBalance();
        }

        public static String getAccountHolderName(Account account) {
            return account.getAccountHolderName();
        }

        public static void adjustAccountBalance(Account account, double netWinnings) {
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

