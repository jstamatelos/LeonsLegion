package com.leonslegion.casino;

/**
 * Created by danielprahl on 5/9/17.
 */


public class Lobby {
    public boolean isRunning;


    public Lobby(){
        // no-arg constructor
    }

    public void start(){
        initLobby();
        startLobby();
    }

    public void initLobby(){
        isRunning = true;
    }

    // this is the main game loop
    public void startLobby(){
        while(isRunning){
            System.out.println("!! !! !! !! !! !! !! !! !! !!");
            System.out.println("Welcome to the Casino Lobby!");
            actionSelection();

        }
    }

    public void actionSelection(){
        String selection = InputHandler.getStringInput("Would you like to play a game 'y' or 'n', 'Q' to quit ");
        if(selection.equalsIgnoreCase("Q")) {
            exit();
        } else if(selection.equalsIgnoreCase("y")){
            selectGame();
        }else if(selection.equalsIgnoreCase("n")){
            createAccountSelection();
        }else{
            System.out.println("selection unrecognized");
            actionSelection();
        }

    }

    public void createAccountSelection(){
        String selection = InputHandler.getStringInput("Would you like to create a new account? 'y' or 'n', 'Q' to quit ");
        if(selection.equalsIgnoreCase("Q")){
            exit();
        }else if(selection.equalsIgnoreCase("y")){
            createAccount();
            createAccountSelection();
        }else if(selection.equalsIgnoreCase("n")){
            actionSelection();
        }else{
            System.out.println("selection unrecognized");
            createAccountSelection();

        }
    }

    public void createAccount(){
        String newName = InputHandler.getStringInput("What is your name?");
        Account newAccount = AccountFactory.createAccountWithName(newName);
        AccountManager.addAccount(newAccount);
        System.out.println("Account has been created for '" + newName + "' with the ID: " + newAccount.getId());
    }

    public void selectGame(){
        System.out.println("Which game would you like to play? Please select a number.");
        System.out.println("Poker = 1, Blackjack = 2, War = 3, Roulette = 4, Slots = 5");
        int selectedGame = InputHandler.getIntInput("Enter 0 to exit.");
        switch (selectedGame){
            case 0:
                exit();
                break;

            case 1:
                System.out.println("Dummy Poker Game Played");
                break;

            case 2:
                System.out.println("Dummy Blackjack Game Played");
                break;

            case 3:
                System.out.println("Dummy War Game Played");
                break;

            case 4:
                System.out.println("Dummy Roulette Game Played");
                break;

            case 5:
                System.out.println("Dummy Slots Game Played");
                break;

            default:
                System.out.println(selectedGame + " is not a valid selection, please try again.");
                selectGame();
        }
    }

    public void exit(){
        isRunning = false;
    }

}
