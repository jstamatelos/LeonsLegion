package com.leonslegion.casino.CardGamePackage;

import com.leonslegion.casino.Abstracts.Player;
import com.leonslegion.casino.AccountPackage.Account;
import com.leonslegion.casino.Console;
import com.leonslegion.casino.InputHandler;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by jarrydstamatelos on 5/9/17.
 */
public class PokerGame extends CardGame {

    private long pot;
    private long ante = 1000;  //For the time being, ante is set to $10 automatically.
    private boolean[] hasFolded;
    private long[] amountInThePot;
    private int turnIndex = 0;

    @Override
    public ArrayList<PokerPlayer> getPlayers() {
        return (ArrayList<PokerPlayer>)super.getPlayers();
    }


    private void printRules() {
        Console.println( "Unfortunately for you, this is a degenerate form\n" +
                            "of poker, wherein tiebreakers are determined by\n" +
                            "whom I like the best. So your pair of aces might\n" +
                            "lose to a pair of twos. Sucks, but if you wanted\n" +
                            "to win money, you should've gone on Price Is Right\n" +
                            "or become an Instagram model.\n\n" +
                            "We will, however, let you play by yourself. You\n" +
                            "won't win any money, but it'll feel kind of like\n" +
                            "you're gambling.\n\n" +
                            "...Kind of.\n\n");
    }

    /*
    Asks for number of players and loads in players by ID by calling loadPlayers.
     */
    private void promptGame() {
        int numPlayers = InputHandler.getIntInput("How many players?");
        while(numPlayers > 9 || numPlayers < 1) {
            numPlayers = InputHandler.getIntInput("Invalid number of players. Try in the 1 - 9 range.");
        }
        loadPlayers(numPlayers);
    }

    private void loadPlayers(int numPlayers) {
        for (int i = 0; i < numPlayers; i++) {
            long accountid = InputHandler.getLongInput("Please enter Player " + (i + 1) + "'s ID.\n");
            Account account = Account.AccountManager.findAccount(accountid);
            if (account != null) {
                players.add(new PokerPlayer(account));
            } else {
                i--;
            }
        }
    }

    /*
    Ante up!
     */
    private void anteUp() {
        for(PokerPlayer p : getPlayers()) {
            String name = getPokerPlayerName(p);
            try {
                pot += p.placeBet(ante);
                Console.print(name + " antes " + Console.moneyToString(ante) + ".\n");
            } catch (Exception e){
                Console.println(name + ": You don't have to go home, but you can't stay here.\n");
                players.remove(p);
            }
        }
    }

    /*
    Called at the beginning of each round to repopulate PokerPlayers' Hands.
     */
    public void initialDeal() {
        deck.shuffleDeck(); //I want to put this in the Deck constructor.
        for (PokerPlayer p : getPlayers()) {
            p.hand = new PokerHand();
            for (int i = 0; i < 5; i++) {
                p.getHand().addCard(deck.dealCard());
            }
        }
    }

    /*
    This method holds the logic that ends a round
    of betting when it becomes the turn of the last
    player who raised.
    */
    void playersMakeBets() {
        //each iteration of the loop is a turn by a player
        do {
            playerChoice(turnIndex);
            turnIndex = getNextPlayer(turnIndex);
        } while(countFolds() < hasFolded.length - 1 && amountInThePot[turnIndex] != getHighBet() && getHighBet() > 0);
        // end of round
    }

    /*
    Offers each player their options and routs their choice appropriately.
    */
    private void playerChoice(int playerIndex) {
        PokerPlayer player = getPlayers().get(playerIndex);
        long highBet = getHighBet();

        Console.printDashes();
        Console.println(player.getAccount().getAccountHolderName() + "\n" + player.getHand().toString());
        Console.printDashes();

        boolean loopCondition = true;

        while(loopCondition) {
            loopCondition = false;
            String choice = InputHandler.getStringInput("\nYou can FOLD, RAISE, CALL a raise, or if no bets have been made, CHECK.\n");
            try {
                switch (choice.toUpperCase()) {
                    case "FOLD":
                        hasFolded[playerIndex] = true;
                        break;
                    case "RAISE":
                        long raise = Console.getMoneyInput("\nThe high bet is currently " + Console.moneyToString(highBet) + ". How much would you like to raise above that?");
                        player.placeBet(highBet + raise);
                        amountInThePot[playerIndex] = highBet + raise;
                        break;
                    case "CALL":
                        if (highBet == 0) {
                            throw new Exception("\nThere was no raise to call.");
                        }
                        player.placeBet(highBet - amountInThePot[playerIndex]);
                        amountInThePot[playerIndex] = highBet;
                        break;
                    case "CHECK":
                        if (highBet > 0) {
                            throw new Exception("\nYou cannot check.");
                        }
                        break;
                    default:
                        throw new Exception("\nNot a valid choice. Read the instructions again.");
                }
            } catch (Exception e) {
                Console.println(e.getMessage());
                loopCondition = true;
            }
        }
    }

    /*
    An exception will be generated if all the players
    fold consecutively, so this will control for that
    possibility.
    */
    private int countFolds() {
        int count = 0;
        for(boolean bool : hasFolded) {
            if(bool) {
                count++;
            }
        }
        return count;
    }

    /*
    getNextPlayer iterates around the table and skips folded players.
     */
    private int getNextPlayer(int playerIndex) {
        do {
            turnIndex = (turnIndex + 1) % hasFolded.length;
        } while(!hasFolded[turnIndex]);

        return turnIndex;
    }

    private long getHighBet() {
        long highBet = 0;
        for(long l : amountInThePot) {
            if(l > highBet) {
                highBet = l;
            }
        }
        return highBet;
    }

    private void rakeBetsIn() {
        for(int i = 0; i < players.size(); i++) {
            pot += amountInThePot[i];
        }
    }

    private void printPot() {
        Console.println("There's currently " + Console.moneyToString(pot) + " in the pot.\n");
    }

    /*
    Calls compareTo method from PokerHand to find a winner.
     */
    private PokerPlayer compareHands() {
        int firstNotToFold = 0;
        while(hasFolded[firstNotToFold]) {
            firstNotToFold++;
        }
        PokerPlayer winner = getPlayers().get(firstNotToFold);
        for (int i = 0; i < hasFolded.length; i++) {
            if(hasFolded[i]) {
                continue;
            }
            setPlayerHandType(getPlayers().get(i));
            if(winner.getHand().compareTo(getPlayers().get(i).getHand()) < 0) {
                winner = getPlayers().get(i);
            }
        }

        return winner;
    }

    /*
    Sets handType and prints it.
    */
    private void setPlayerHandType(PokerPlayer player) {
        player.getHand().determineHandType();
        Console.println(player.getHand().handType.toString());
        Console.printDashes();
    }

    /*
    This helper method takes the PokerPlayer
    object and deducts the amount committed to
    the last pot from their account.
     */
    private void debitFromPokerPlayerAccount(PokerPlayer p, long amount) {
        p.getAccount().setAccountBalance(-1 * amount);
        Console.println(getPokerPlayerName(p) + ": After debiting your bets, you have " + Console.moneyToString(p.getBalance()) + " remaining in your account.\n");
    }


    /*
    For getting a PokerPlayer's name.
     */
    private String getPokerPlayerName(PokerPlayer player) {
        return player.getAccount().getAccountHolderName();
    }

    /*
    Takes care of finding, announcing, paying winner.
     */
    private void resolveWinner() {
        PokerPlayer winner = compareHands();
        Console.println("The winner is " + getPokerPlayerName(winner) + "!\n");
        payToWinnersAccount(winner);
    }

    /*
    Pays the pot to the Account of the winner.
    */
    private void payToWinnersAccount(PokerPlayer p) {
        p.getAccount().setAccountBalance(pot);
        Console.println("Congratulations! After your win, you have " + Console.moneyToString(p.getBalance()) + " remaining in your account.\n");
    }


    private void promptPlayerExits() {
        ArrayList<PokerPlayer> players = getPlayers();
        for(PokerPlayer p : players) {
            if(p.leaveGame()) {
                players.remove(p);
            }
        }
    }

    private void resetHand() {
        hasFolded = new boolean[players.size()];
        amountInThePot = new long[players.size()];
        pot = 0;
        turnIndex = 0;
        for(PokerPlayer p : getPlayers()) {
            p.hand = null;
        }
        deck = new Deck();
        initialDeal();
    }


    /*
    Sets into motion the logic behind a game of poker.

    This is set up for a deal, one round of betting, and
    then determining a winner.

    Currently, the order of play is going to be the same
    for each iteration of the loop.

    This loop as constituted will run a five card game
    with no exchanges and one round of betting.
     */
    public void run() {
        promptGame();
        printRules();

        while (players.size() > 1) { //
            anteUp();
            playersMakeBets();
            rakeBetsIn();
            printPot();
            resolveWinner();
            promptPlayerExits();
            resetHand();
        }
    }

    /*
    Needed to create this main for testing. There are so
    many dependencies that it's hard to test a method.
     */
    public static void main(String[] args) {
        for(int i = 1; i <= 10; i++){
            Account.AccountManager.addAccount(Account.AccountFactory.createAccountWithName("Guest" + i));
        }

        PokerGame game = new PokerGame();
        game.run();
    }

}
