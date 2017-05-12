package com.leonslegion.casino;

/**
 * Created by danielprahl on 5/9/17.
 */


public class Lobby {
    private boolean isRunning;

    public void start(){
        isRunning = true;
        populateAccounts(16); // create some dummy accounts named Guest1, Guest2, ... GuestN
        System.out.println("\n* * * * * * * * * * * * * * * * * * * * * * * * * * ");
        System.out.println(" * * * * * * Welcome to Leon's Casino !!! * * * * * *");
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * *\n");
        System.out.println("        Please sign in or create an account. \n");
        createAccount();
        startLobby();
    }

    // this is the main game loop
    public void startLobby(){
        while(isRunning){
            System.out.println("\n------------------------------------------------");
            System.out.println("~~~~~~~~~~~~~~~~~ Casino Lobby ~~~~~~~~~~~~~~~~~");
            System.out.println("------------------------------------------------");
            System.out.println("         (Enter 'Q' at any time to quit) \n");
            actionSelection();

        }
        System.out.println("\nThanks for playing!  Have a nice day! \n");
    }

    public void actionSelection() {
        System.out.println("\nWhat would you like to do?");
        System.out.println("*'play' a game \n*'create' an account \n*'check' balance \n*'buy' more chips \n*'exit' casino");
        String question = "Please enter a keyword to select";
        String selection = InputHandler.getStringInput(question).toLowerCase();
        switch (selection) {
            case "q":
                exit();
                break;

            case "exit":
                exit();
                break;

            case "play":
                selectGame();
                break;

            case "buy":
                buyMoreChips();
                actionSelection();
                break;

            case "check":
                Account account = AccountManager.findAccount(InputHandler.getLongInput("Please enter ID"));
                System.out.format("This account has a balance of: $%.2f \n", account.getAccountBalance());
                actionSelection();
                break;

            case "create":
                createAccount();
                break;

            default:
                System.out.println("That selection was unrecognized. Please enter a valid selection.");
                actionSelection();
                break;
        }
    }

    public void askToBuyMoreChips(){
        String question = "\nWould you like to buy more chips? 'y' or 'n' ";
        String selection = InputHandler.getStringInput(question).toLowerCase();
        switch(selection){
            case "q":
                exit();
                break;

            case "y":
                buyMoreChips();
                actionSelection();
                break;

            case "n":
                actionSelection();
                break;

            default:
                System.out.println("That selection was unrecognized. Please enter a valid selection.");
                askToBuyMoreChips();
                break;
        }
    }

    public void createAccount(){
        String newName = InputHandler.getStringInput("What is your name?");
        Account newAccount = AccountFactory.createAccountWithName(newName);
        AccountManager.addAccount(newAccount);
        System.out.println(newAccount.toString());
        askToBuyMoreChips();
    }

    public void buyMoreChips(){
        String stringID = InputHandler.getStringInput("Please enter your account ID.");
        if(stringID.equalsIgnoreCase("q")){
            exit();
            return;
        }
        long id = Long.parseLong(stringID);
        if(AccountManager.findAccountIndex(id) != -1){
            buyMoreChips(id);
        }else{
            System.out.println("Account not found.");
            buyMoreChips();
        }
    }

    public void buyMoreChips(long ID){
        Account account = AccountManager.findAccount(ID);
        System.out.format("This account has a balance of: $%.2f \n",  account.getAccountBalance() );
        double bal = InputHandler.getDoubleInput("How much money would you like to add to your account?");
        account.setAccountBalance(bal);
        System.out.format("This account now has a balance of: $%.2f \n", account.getAccountBalance() );
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
                selectGame();
                break;

            case "blackjack":
                BlackjackGame.startBlackJack();
                selectGame();
                break;

            case "war":
                WarGame.startWarGame();
                break;

            case "roulette":
                RouletteGameManager.playRoulette();
                break;

            case "slots":
                SlotGame.playSlots();
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
