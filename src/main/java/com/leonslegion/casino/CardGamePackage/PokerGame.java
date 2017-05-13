package com.leonslegion.casino.CardGamePackage;

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
    Calls compareTo method from PokerHand to find a winner.
     */
    private PokerPlayer compareHands(ArrayList<PokerPlayer> players) {
        PokerPlayer winner = players.get(0);
        for (int i = 1; i < players.size(); i++) {
            if(winner.getHand().compareTo(players.get(i).getHand()) < 0) {
                winner = players.get(i);
            }
        }

        return winner;
    }

    /*
    This helper method takes the PokerPlayerBettingRound
    object and deducts the amount they committed to the
    last pot from their account.
     */
    private void debitFromPokerPlayerAccount(PokerPlayer p, long amount) {
        p.getAccount().setAccountBalance(-1 * amount);
        Console.println(getPokerPlayerName(p) + ": After debiting your bets, you have " + Console.moneyToString(p.getBalance()) + " remaining in your account.\n");
    }

    /*
    Pays the pot to the Account of the winner.
     */
    private void payToWinnersAccount(PokerPlayer p) {
        p.getAccount().setAccountBalance(pot);
        Console.println("Congratulations! After your win, you have " + Console.moneyToString(p.getBalance()) + " remaining in your account.\n");
    }

    /*
    For getting a PokerPlayer's name.
     */
    private String getPokerPlayerName(PokerPlayer player) {
        return player.getAccount().getAccountHolderName();
    }

    /*
    For setting all the remaining players' HandTypes.
     */
    private void setPlayerHandTypes(ArrayList<PokerPlayer> remainingPlayers) {
        for(PokerPlayer p : remainingPlayers) {
            p.getHand().determineHandType();
            Console.println(p.getHand().handType.toString());
        }
        Console.printDashes();
    }

    /*
    Takes care of finding, announcing, paying winner.
     */
    private void resolveWinner(ArrayList<PokerPlayer> remainingPlayers) {
        PokerPlayer winner = compareHands(remainingPlayers);
        Console.println("The winner is " + getPokerPlayerName(winner) + "!\n");
        payToWinnersAccount(winner);
    }

    /*
    Ante up!
     */
    private void anteUp() {
        for(PokerPlayer p : getPlayers()) {
            String name = p.getAccount().getAccountHolderName();
            try {
                pot += p.placeBet(ante);
                Console.print(name + " antes " + Console.moneyToString(ante) + ".\n");
            } catch (Exception e){
                Console.print(name + ": You don't have to go home, but you can't stay here.\n");
                players.remove(p);
            }
        }
    }

    /*
    This method doesn't adhere to SRP. It rakes the bets
    into the pot and adds the non-folded players into
    an ArrayList to compare hands and choose winner.
     */
    private ArrayList<PokerPlayer> resolveRound(PokerBettingRound round) {
        ArrayList<PokerPlayer> remainingPlayers = new ArrayList<PokerPlayer>();
        for (PokerPlayerBettingRound p : round.playersInRound) {
            pot += p.amountIn;
            //debitFromPokerPlayerAccount(p.player, p.amountIn);
            if(!p.folded) {
                remainingPlayers.add(p.player);
            }
        }
        return  remainingPlayers;
    }

    private void promptPlayerExits() {
        for(PokerPlayer p : getPlayers()) {
            //
            players.remove(p);
        }
        return;
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
            hasFolded = new boolean[players.size()];
            pot = 0;
            initialDeal();
            PokerBettingRound round = new PokerBettingRound(getPlayers());
            anteUp();
            round.playersMakeBets();

            ArrayList<PokerPlayer> remainingPlayers = resolveRound(round);
            Console.println("There's currently " + Console.moneyToString(pot) + " in the pot.\n");

            setPlayerHandTypes(remainingPlayers);
            resolveWinner(remainingPlayers); //TODO - write this method
            promptPlayerExits();
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
