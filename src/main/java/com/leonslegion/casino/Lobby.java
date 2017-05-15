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
        Console.println(" * * * * * * * * * * * * * * * * * * * * * * * * * * ");
        Console.println("* * * * * * Welcome to Leon's Casino!!! * * * * * * * ");
        Console.println(" * * * * * * * * * * * * * * * * * * * * * * * * * * ");
        Console.println("        Please sign in or create an account. \n");
        createAccount();
        startLobby();
    }

    // this is the main game loop
    public void startLobby(){

        while(isRunning){
            Console.println("------------------------------------------------");
            Console.println("~~~~~~~~~~~~~~~~~ Casino Lobby ~~~~~~~~~~~~~~~~~");
            Console.println("------------------------------------------(Q)uit");

            actionSelection();
        }

        Console.println("\nThanks for playing!  Have a nice day! \n");
    }

    public void actionSelection() {
        Console.println("What would you like to do?");
        Console.println("*(play) a game \n*(create) an account \n*(check) balance \n*(buy) more chips \n*(exit) casino");
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
                checkBalance();
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
                // do nothing but return
                break;

            default:
                // do nothing but return
                break;
        }
    }

    public void createAccount(){
        String newName = InputHandler.getStringInput("What is your name?");
        Account.AccountManager.addAccount(newName);
        Console.println(Account.AccountManager.findAccount(newName).toString());
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
        checkBalance(ID);
        long bal = Console.getMoneyInput("How much money would you like to add to your account?");
        account.setAccountBalance(bal);
        checkBalance(ID);
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
                BlackjackGame blackjackGame = new BlackjackGame();
                blackjackGame.startBlackJack();
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
                // selectGame(); // recursive method call, dangerous
        }
    }

    public void checkBalance(){
        long id = InputHandler.getLongInput("Please enter ID");
        checkBalance(id);
    }

    public void checkBalance(long id){
        Account account = Account.AccountManager.findAccount(id);
        Console.println("This account currently has a balance of: " + Console.moneyToString(account.getAccountBalance()));
    }

    public void exit(){
        isRunning = false;
    }

    @Deprecated // convenience method to generate generic accounts for testing
    private void populateAccounts(int count){
        for(int i = 1; i <= count; i++){
            Account.AccountManager.addAccount("Guest" + i);
        }
    }

}// End of Class
