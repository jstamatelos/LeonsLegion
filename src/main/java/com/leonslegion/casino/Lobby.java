package com.leonslegion.casino;

/**
 * Created by danielprahl on 5/9/17.
 */


public class Lobby {
    private boolean isRunning;

    public void start(){
        isRunning = true;
        populateAccounts(16); // create some dummy accounts named Guest1, Guest2, ... GuestN
        startLobby();
    }

    // this is the main game loop
    public void startLobby(){
        while(isRunning){
            System.out.println("\n------------------------------------------------");
            System.out.println("~~~~~~!!! Welcome to the Casino Lobby! !!!~~~~~~ ");
            System.out.println("------------------------------------------------");
            System.out.println("         (Enter 'Q' at any time to quit) \n");
            actionSelection();

        }
        System.out.println("\nThanks for playing!  Have a nice day! \n");
    }

    public void actionSelection(){
        String question = "Would you like to play a game? 'y' or 'n' ";
        String selection = InputHandler.getStringInput(question).toLowerCase();
        switch(selection){
            case "q":
                exit();
                break;

            case "y":
                selectGame();
                break;

            case "n":
                chooseToCreate();
                break;

            case "a":
                System.out.println(getNumAccounts() + " accounts on record.");
                actionSelection();
                break;

            default:
                System.out.println("That selection was unrecognized. Please enter a valid selection.");
                actionSelection();
                break;
        }
    }

    public void chooseToCreate(){
        String question = "Would you like to create a new account? 'y' or 'n' ";
        String selection = InputHandler.getStringInput(question).toLowerCase();
        switch(selection){
            case "q":
                exit();
                break;

            case "y":
                createAccount();
                actionSelection();
                break;

            case "n":
                actionSelection();
                break;

            case "a":
                System.out.println(getNumAccounts() + " accounts on record.");
                break;

            default:
                System.out.println("That selection was unrecognized. Please enter a valid selection.");
                chooseToCreate();
                break;
        }
    }

    public void createAccount(){
        String newName = InputHandler.getStringInput("What is your name?");
        Account newAccount = AccountFactory.createAccountWithName(newName);
        AccountManager.addAccount(newAccount);
        System.out.println("Account has been created for '" + newName + "' with the ID: " + newAccount.getId());
    }

    public void selectGame(){
        System.out.println("Which game would you like to play? Please select from the following: ");
        String gamesList = "Poker, Blackjack, War, Roulette, Slots";
        String selectedGame = InputHandler.getStringInput(gamesList).toLowerCase();
        switch (selectedGame){
            case "q":
                exit();
                break;

            case "poker":
                System.out.println("Poker is currently unavailable, please make another selection. \n");
                break;

            case "blackjack":
                System.out.println("Blackjack is currently unavailable, please make another selection. \n");
                break;

            case "war":
                System.out.println("War is currently unavailable, please make another selection. \n");
                break;

            case "roulette":
                RouletteGameManager.playRoulette();
                break;

            case "slots":
                System.out.println("Slots is currently unavailable, please make another selection. \n");
                break;

            default:
                System.out.println("We do not currently offer " + selectedGame + ", please make another selection.");
                selectGame();
        }
    }

    public void exit(){
        isRunning = false;
    }

    @Deprecated // backdoor method to check how many accounts have been created
    private int getNumAccounts(){
        return  AccountManager.getAccounts().size();
    }

    @Deprecated // convenience method to generate generic accounts for testing
    private void populateAccounts(int count){
        for(int i = 1; i <= count; i++){
            AccountManager.addAccount(AccountFactory.createAccountWithName("Guest" + i));
        }
    }

}// End of Class
