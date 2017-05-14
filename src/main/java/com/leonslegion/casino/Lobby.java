package com.leonslegion.casino;

import com.leonslegion.casino.AccountPackage.Account;
import com.leonslegion.casino.CardGamePackage.BlackjackGame;
import com.leonslegion.casino.CardGamePackage.PokerGame;
import com.leonslegion.casino.CardGamePackage.WarGame;
import com.leonslegion.casino.RoulettePackage.RouletteGameManager;
import com.leonslegion.casino.SlotPackage.SlotGame;
import org.apache.commons.lang3.*;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * Created by danielprahl on 5/9/17.
 */


public class Lobby {
    private boolean isRunning;

    public void start(){
        isRunning = true;
        populateAccounts(16); // create some dummy accounts named Guest1, Guest2, ... GuestN
        Console.println("\n* * * * * * * * * * * * * * * * * * * * * * * * * * ");
        Console.println(" * * * * * * Welcome to Leon's Casino !!! * * * * * *");
        Console.println("* * * * * * * * * * * * * * * * * * * * * * * * * *\n");
        Console.println("        Please sign in or create an account. \n");
        createAccount();
        startLobby();
    }

    // this is the main game loop
    public void startLobby(){
        Console.println("\n------------------------------------------------");
        Console.println("~~~~~~~~~~~~~~~~~ Casino Lobby ~~~~~~~~~~~~~~~~~");
        Console.println("------------------------------------------------");
        Console.println("         (Enter 'Q' at any time to quit) \n");

        while(isRunning){
            actionSelection();
            Console.println("\n------------------------------------------------");
            Console.println("        you are back in the Lobby \n");
        }

        Console.println("\nThanks for playing!  Have a nice day! \n");
    }

    public void actionSelection() {
        Console.println("\nWhat would you like to do?");
        Console.println("*'play' a game \n*'create' an account \n*'check' balance \n*'buy' more chips \n*'exit' casino");
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
                break;

            case "check":
                Account account = Account.AccountManager.findAccount(InputHandler.getLongInput("Please enter ID"));
                Console.println("This account has a balance of: " + Console.moneyToString(account.getAccountBalance()) + "\n");
                break;

            case "create":
                createAccount();
                break;

            default:
                Console.println("That selection was unrecognized. Please enter a valid selection.");
                break;
        }
    }

    public void askToBuyMoreChips(){
        String question = "\nWould you like to add more funds to your account? 'y' or 'n' ";
        String selection = InputHandler.getStringInput(question).toLowerCase();
        switch(selection){
            case "q":
                exit();
                break;

            case "y":
                buyMoreChips();
                break;

            case "n":
                break;

            default:
                break;
        }
    }

    public void createAccount(){
        String newName = InputHandler.getStringInput("What is your name?");
        Account newAccount = Account.AccountFactory.createAccountWithName(newName);
        Account.AccountManager.addAccount(newAccount);
        Console.println(newAccount.toString());
        askToBuyMoreChips();
    }

    public void buyMoreChips(){
        String stringID = InputHandler.getStringInput("Please enter your account ID.");
        if(stringID.equalsIgnoreCase("q")){
            exit();
            return;
        }
        if (!NumberUtils.isParsable(stringID)) {
            Console.println("I don't recognize that as an account number.");
            return;
        }
        long id = Long.parseLong(stringID);
        if(Account.AccountManager.findAccountIndex(id) != -1){
            buyMoreChips(id);
        }else{
            Console.println("Account not found.");
        }
    }

    public void buyMoreChips(long ID){
        Account account = Account.AccountManager.findAccount(ID);
        account.toString();
        long bal = Console.getMoneyInput("How much money would you like to add to your account?");
        account.setAccountBalance(bal);
        account.toString();
    }

    public void selectGame(){
        Console.println("Which game would you like to play? Please select from the following: ");
        String gamesList = "Poker, Blackjack, War, Roulette, Slots";
        String selectedGame = InputHandler.getStringInput(gamesList).toLowerCase();
        switch (selectedGame){
            case "q":
                exit();
                break;

            case "poker":
                PokerGame pokerGame = new PokerGame();
                pokerGame.run();
                break;

            case "blackjack":
                BlackjackGame.startBlackJack();
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
                Console.println("We do not currently offer " + selectedGame + ", please make another selection.");
                selectGame();
        }
    }

    public void exit(){
        isRunning = false;
    }

    @Deprecated // convenience method to generate generic accounts for testing
    private void populateAccounts(int count){
        for(int i = 1; i <= count; i++){
            Account.AccountManager.addAccount(Account.AccountFactory.createAccountWithName("Guest" + i));
        }
    }

}// End of Class
