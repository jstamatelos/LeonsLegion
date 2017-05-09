package com.leonslegion.casino;

/**
 * Created by danielprahl on 5/9/17.
 */
public class Lobby {

    private AccountManager accountManager;
    private GameManager gameManager;
    private Account loggedInAccount;
    private InputHandler input;

    public Lobby(){
        // no-arg constructor
    }


    public void initLobby(){
        accountManager = AccountManager();
        gameManager = new GameManager();
        loggedInAccount = null;
        input = new InputHandler();
    }

    public void startLobby(){
        // todo
    }

    public void logIn(){
        //loggedInAccount = accountManager.getAccount(input.getStringInput("Please enter a valid name or Account ID:"));
    }

    public void createAccount(){
        // todo
    }

    public void selectGame(){
        // todo
    }

    public void viewRules(){
        // todo
    }

    public void exit(){
        // todo
    }

}
