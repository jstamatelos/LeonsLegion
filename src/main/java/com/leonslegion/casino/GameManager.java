package com.leonslegion.casino;

/**
 * Created by danielprahl on 5/9/17.
 */

public abstract class GameManager {



    AccountManager accountManager;

    public GameManager(){
        this.accountManager = new AccountManager();
    }


    public GameManager(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    public AccountManager getAccountManager() {
        return accountManager;
    }

    // dummy implementation, needs correction
    public void playGame(){
        System.out.println("Playing game....");
        System.out.println("Game over, returning to lobby.");
        System.out.println("");
    }


}



