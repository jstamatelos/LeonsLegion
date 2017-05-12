package com.leonslegion.casino.CardGamePackage;

import com.leonslegion.casino.AccountPackage.Account;
import com.leonslegion.casino.InputHandler;

import java.util.ArrayList;

/**
 * Created by jarrydstamatelos on 5/9/17.
 */
public class PokerGame extends CardGame {

    private double pot;

    @Override
    public ArrayList<PokerPlayer> getPlayers() {
        return (ArrayList<PokerPlayer>)super.getPlayers();
    }


    private void printRules() {
        System.out.println( "Unfortunately for you, this is a degenerate form\n" +
                            "of poker, wherein tiebreakers are determined by\n" +
                            "who I like the best. So your pair of aces might\n" +
                            "lose to a pair of twos. Sucks, but if you wanted\n" +
                            "to win money, you should've gone on Price Is Right\n" +
                            "or become an Instagram model.\n\n");
    }

    /*
    Asks for number of players and loads in players by ID by calling loadPlayers.
     */
    private void promptGame() {
        int numPlayers = InputHandler.getIntInput("How many players?");
        while(numPlayers > 9) {
            numPlayers = InputHandler.getIntInput("That's too many players. Try again.");
        }
        loadPlayers(numPlayers);
    }

    private void loadPlayers(int numPlayers) {
        for (int i = 0; i < numPlayers; i++) {
            long accountid = InputHandler.getDoubleInput("Please enter Player " + (i + 1) + "'s ID.").longValue();
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
    private void debitFromPokerPlayerBettingRoundPlayerAccount(PokerPlayerBettingRound p) {
        Account account = Account.AccountManager.findAccount(p.player.getAccountId());
        account.setAccountBalance(-1 * p.amountIn);
        System.out.println("After debiting your bets, you have $" + account.getAccountBalance() + " remaining in your account.\n");
    }

    /*
    Pays the pot to the Account of the winner.
     */
    private void payToWinnersAccount(PokerPlayer p) {
        Account account = Account.AccountManager.findAccount(p.getAccountId());
        account.setAccountBalance(pot);
        System.out.println("Congratulations! After your win, you have $" + account.getAccountBalance() + " remaining in your account.\n");
    }

    /*
    For getting a PokerPlayer's name.
     */
    private String getPokerPlayerName(PokerPlayer player) {
        return Account.AccountManager.findAccount(player.getAccountId()).getAccountHolderName();
    }

    /*
    Sets into motion the logic behind a game of poker.
    The while loop will terminate when all players but
    one leave.

    This is set up for a deal, one round of betting, and
    then determining a winner.

    Currently, the order of play is going to be the same
    for each of these loops.

    This loop as constituted will run a five card game
    with no exchanges and one round of betting.
     */
    public void run() {
        promptGame();
        printRules();

        //The boolean ends is used here to make the game terminate after
        //one hand. The condition that might be best to use long term
        //is the one commented out below.
        boolean ends = true;

        while (ends) { //players.size() > 1
            pot = 0;
            initialDeal();
            PokerBettingRound round = new PokerBettingRound(getPlayers());
            round.playersMakeBets();

            ArrayList<PokerPlayer> remainingPlayers = new ArrayList<PokerPlayer>();

            for (PokerPlayerBettingRound p : round.playersInRound) {
                pot += p.amountIn;
                debitFromPokerPlayerBettingRoundPlayerAccount(p);
                if(!p.folded) {
                    remainingPlayers.add(p.player);
                }
            }

            System.out.println("There's currently $" + pot + " in the pot.");

            for(PokerPlayer p : remainingPlayers) {
                p.getHand().determineHandType();
                System.out.println(p.getHand().handType);
            }

            PokerPlayer winner = compareHands(remainingPlayers);
            System.out.println("The winner is " + getPokerPlayerName(winner));
            payToWinnersAccount(winner);
            ends = false;
        }
    }

    /*
    For now, the end condition of a poker game is any player deciding to leave.
     */


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
