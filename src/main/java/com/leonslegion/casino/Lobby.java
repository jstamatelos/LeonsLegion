package com.leonslegion.casino;

/**
 * Created by danielprahl on 5/9/17.
 */
public class Lobby {
    public boolean isRunning;
    private AccountManager accountManager;
    private GameManager gameManager;
    private Account loggedInAccount;
    private InputHandler input;


    public Lobby(){
        // no-arg constructor
    }

    public void start(){
        initLobby();
        startLobby();
    }

    public void initLobby(){
        isRunning = true;
        accountManager =  new AccountManager();
        gameManager = null;
        loggedInAccount = null;
        input = new InputHandler();
    }

    public void startLobby(){
        while(isRunning){
            // game loop
        }
    }

    public void logIn(){
        //loggedInAccount = accountManager.getAccount(input.getStringInput("Please enter a valid name or Account ID:"));
    }

    public void createAccount(){
        // todo
    }

    public void selectGame(){
        System.out.println("Which game would you like to play? Please select a number. Enter 0 to return to lobby.");
        System.out.println("Poker = 1, Blackjack = 2, War = 3, Roulette = 4, Slots = 5");
        int selectedGame = input.getIntInput("");
        switch (selectedGame){
            case 0:
                //startLobby();
                break;

            case 1:
                gameManager = new PokerGameManager(accountManager);
                break;

            case 2:
                gameManager = new BlackjackGameManager(accountManager);
                break;

            case 3:
                gameManager = new WarGameManager(accountManager);
                break;

            case 4:
                gameManager = new RouletteGameManager(accountManager);
                break;

            case 5:
                gameManager = new SlotGameManager(accountManager);
                break;

            default:
                System.out.println(selectedGame + " is not a valid selection, please try again.");
                selectGame();
        }
    }

    public void viewRules(){
        // todo
    }

    public void exit(){
        isRunning = false;
    }

}
