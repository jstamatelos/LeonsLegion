package com.leonslegion.casino;

/**
 * Created by danielprahl on 5/9/17.
 */

public abstract class GameManager {

    AccountManager accountManager;




    public GameManager(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    public AccountManager getAccountManager() {
        return accountManager;
    }




}



