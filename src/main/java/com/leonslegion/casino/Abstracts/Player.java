package com.leonslegion.casino.Abstracts;

import com.leonslegion.casino.AccountPackage.Account;

public abstract class Player {

    Account account;

    public Player(Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }

    public long getBalance() {
        return account.getAccountBalance();
    }

    public abstract long placeBet(long bet) throws Exception;

}
