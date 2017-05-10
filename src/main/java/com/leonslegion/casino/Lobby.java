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
            System.out.println("!! !! !! !! !! !! !! !! !! !!");
            System.out.println("Welcome to the Casino Lobby!");
            if(loggedInAccount == null) {
                logInOrCreate();
            }else {
                selectGame();
            }
        }
    }

    public void logIn(){
        String loginName = input.getStringInput("Please enter a valid name");
        loggedInAccount = accountManager.findAccount(loginName);
    }

    public void createAccount(){
        AccountFactory accountFactory = new AccountFactory();
        String newName = input.getStringInput("What is your name?");
        accountManager.addAccount(accountFactory.getAccountHolderName(newName));
        loggedInAccount = accountManager.findAccount(newName);
    }

    public void selectGame(){
        System.out.println("Which game would you like to play? Please select a number.");
        System.out.println("Poker = 1, Blackjack = 2, War = 3, Roulette = 4, Slots = 5");
        int selectedGame = input.getIntInput("Enter 0 to exit.");
        switch (selectedGame){
            case 0:
                exit();
                break;

            case 1:
                gameManager = new PokerGameManager(accountManager);
                gameManager.playGame();
                break;

            case 2:
                gameManager = new BlackjackGameManager(accountManager);
                gameManager.playGame();
                break;

            case 3:
                gameManager = new WarGameManager(accountManager);
                gameManager.playGame();
                break;

            case 4:
                gameManager = new RouletteGameManager(accountManager);
                gameManager.playGame();
                break;

            case 5:
                gameManager = new SlotGameManager(accountManager);
                gameManager.playGame();
                break;

            default:
                System.out.println(selectedGame + " is not a valid selection, please try again.");
                selectGame();
        }
    }

    public void logInOrCreate(){
        System.out.println("Please continue to the Casino Registration Office.");
        System.out.println("Enter 1 to login to an existing account.");
        System.out.println("Enter 2 to create a new account.");
        int selection = input.getIntInput("Enter 0 to exit.");
        switch (selection){
            case 0:
                exit();
                break;

            case 1:
                logIn();
                break;

            case 2:
                createAccount();
                break;

            default:
                System.out.println(" is not a valid selection, please try again.");
                logInOrCreate();
                break;
        }
    }

    public void viewRules(){
        // todo
    }

    public void exit(){
        isRunning = false;
    }

}
