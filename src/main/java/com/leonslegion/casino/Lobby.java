package com.leonslegion.casino;

/**
 * Created by danielprahl on 5/9/17.
 */
public class Lobby {
    public boolean isRunning;
    private GameManager gameManager;


    public Lobby(){
        // no-arg constructor
    }

    public void start(){
        initLobby();
        startLobby();
    }

    public void initLobby(){
        isRunning = true;
        gameManager = null;
    }

    // this is the main game loop
    public void startLobby(){
        while(isRunning){
            System.out.println("!! !! !! !! !! !! !! !! !! !!");
            System.out.println("Welcome to the Casino Lobby!");
            actionSelection();
            selectGame();

        }
    }

    public void actionSelection(){
        String selection = InputHandler.getStringInput("Would you like to play a game 'y' or 'n' ");
        if(selection.equalsIgnoreCase("y")){
            //do nothing, cascade down to selectGame
        }else if(selection.equalsIgnoreCase("n")){
            createAccountSelection();
        }else{
            System.out.println("selection unrecognized");
            actionSelection();
        }
    }

    public void createAccountSelection(){
        String selection = InputHandler.getStringInput("Would you like to create a new account? 'y' or 'n' ");
        if(selection.equalsIgnoreCase("y")){
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
                gameManager = new PokerGameManager();
                gameManager.playGame();
                break;

            case 2:
                gameManager = new BlackjackGameManager();
                gameManager.playGame();
                break;

            case 3:
                gameManager = new WarGameManager();
                gameManager.playGame();
                break;

            case 4:
                gameManager = new RouletteGameManager();
                gameManager.playGame();
                break;

            case 5:
                gameManager = new SlotGameManager();
                gameManager.playGame();
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
