package com.leonslegion.casino.CardGamePackage;

import com.leonslegion.casino.AccountPackage.Account;
import com.leonslegion.casino.Console;
import com.leonslegion.casino.InputHandler;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Comparator;

/**
 * Created by jarrydstamatelos on 5/9/17.
 */

public class WarGame extends CardGame implements Comparator {
    

    static Deck dealerDeck = new Deck();
    static Deck playerDeck = new Deck();

    static Card playerCard;
    static Card dealerCard;


    /**
     * Method called from the lobby to start the game
     */

    public static void startWarGame(){

        setUp();
        gameLogic();

    }


    /**
     * Handles game logic
     *  Dealer showing / Player showing card / comparison / result / exit
     */

    private static void gameLogic(){

        WarPlayer newWarPlayer = createWarPlayer();
        boolean playRound = true;
        while (playRound) {
            double bet = WarGame.placeBet(newWarPlayer);
            Console.println(setDealerCard());
            Console.println(setplayerCard());
            Console.println(determineWinner(newWarPlayer, bet));
            playRound = WarGame.exit();
        }

    }


    /**
     * Display for game start
     */

    private static void setUp(){

        Console.println("WAR! WHAT IS IT GOOD FOR!");
        Console.printDashes();
        Console.println("Welcome to the game of War \n May the odds never be in your favor");
        Console.printDashes();

    }

    /**
     * Creates player, tests that ID is valid, will not accept an invalid ID
     * @return
     */

    private static WarPlayer createWarPlayer() {
        while (true) {
            String warPlayerID = InputHandler.getStringInput("Please enter your ID.");
            if (!NumberUtils.isParsable(warPlayerID)) {
                Console.println("Not a valid ID");
                continue;
            }
            Account warPlayerAccount = Account.AccountManager.findAccount(Long.parseLong(warPlayerID));
            if (warPlayerAccount == null) {
                Console.println("ID not found!");
                continue;
            }
            Console.println("ID accepted!");
            WarPlayer newWarPlayer = new WarPlayer(warPlayerAccount);
            return newWarPlayer;
        }

    }

    /**
     * Places bet, 1 bet per round
     * @param warPlayer player that is linked with the entered ID at game start
     * @return places bet
     */

    private static double placeBet(WarPlayer warPlayer) {
        double bet = InputHandler.getDoubleInput("Please place a bet: ");
        long accountID = warPlayer.getAccount().getId();
        for (int account = 0; account < Account.AccountManager.getAccounts().size(); account++) {
            if (Account.AccountManager.getAccounts().get(account).getId() == accountID) {
                double balance = Account.AccountManager.getAccounts().get(account).getAccountBalance();
                if (balance == 0) {
                    Console.println("You have a balance of 0!");
                    Console.println("Sorry! You are out of money.");

                    return 0;
                }
                if (bet > balance) {
                    Console.println("Your bet is greater than your balance!");
                    return placeBet(warPlayer);
                }
                if (bet < 0) {
                    Console.println("You can bet a negative value.");
                    return placeBet(warPlayer);
                }
            }
        }
        return bet;
    }

    /**
     * Dealers card
     * @return displays dealers card
     */

    private static String setDealerCard(){
        dealerDeck.shuffleDeck();
        dealerCard = dealerDeck.dealCard();
        return "Dealer draws a : " + dealerCard.toString();
    }

    /**
     * players card
     * @return displays players card
     */
    private static String setplayerCard(){
        playerDeck.shuffleDeck();
        playerCard = playerDeck.dealCard();
        return "Player draws a : " + playerCard.toString();
    }


    /**
     * Compares dealercard / playercard / determines high card
     * @param warPlayer
     * @param bet player bet - doubled if 'War' condition met
     * @return win / loss / draw message
     */
    public static String determineWinner(WarPlayer warPlayer, double bet){
        if (playerCard.getPointValue() > dealerCard.getPointValue()){
            long accountID = warPlayer.getAccount().getId();
            for (int account = 0; account < Account.AccountManager.getAccounts().size(); account++) {
                if (Account.AccountManager.getAccounts().get(account).getId() == accountID) {
                    double balance = Account.AccountManager.getAccounts().get(account).getAccountBalance();
                    Account.AccountManager.getAccounts().get(account).setAccountBalance(bet);
                    Console.print("Your balance is now: ");
                    Console.println("$ %,.2f", Account.AccountManager.getAccounts().get(account).getAccountBalance());
                    Console.printDashes();

                }
            }
            return "You win! Nice!";
        } else if (playerCard.getPointValue() < dealerCard.getPointValue()){
            long accountID = warPlayer.getAccount().getId();
            for (int account = 0; account < Account.AccountManager.getAccounts().size(); account++) {
                if (Account.AccountManager.getAccounts().get(account).getId() == accountID) {
                    double balance = Account.AccountManager.getAccounts().get(account).getAccountBalance();
                    Account.AccountManager.getAccounts().get(account).setAccountBalance(-bet);
                    Console.println("Your balance is now: $");
                    Console.println("%,.2f", Account.AccountManager.getAccounts().get(account).getAccountBalance());
                    Console.printDashes();
                }
            }
            return "Dealer wins! Oh well.";
        } else {
            Console.println("Tie! WAR!!!!!! Your bet is doubled!!!!\n Another Card is pulled for each player!");
            Console.println(setDealerCard());
            Console.println(setplayerCard());
            return determineWinner(warPlayer, bet * 2);
        }
    }

    /**
     * Allows exit back to menu
     * @return
     */
    private static boolean exit() {
        String exitOpportunity = InputHandler.getStringInput("Type 'exit' to return to lobby or 'stay' to play again");
        if (exitOpportunity.equalsIgnoreCase("exit")) {
            return false;
        } else if (exitOpportunity.equalsIgnoreCase("stay")) {
            return true;
        } else {return exit();}
    }



    public int compare(Object o1, Object o2) {
        Card dealerCard = (Card) o1;
        Card playerCard = (Card) o2;
        return dealerCard.getRank().ordinal() - playerCard.getRank().ordinal();
    }


    @Override
    public void initialDeal() {
       //
    }

    @Override
    public void setHasDealer(boolean hasDealer) {
        super.setHasDealer(true);
    }
}
