package com.leonslegion.casino;

import java.util.ArrayList;

/**
 * Created by jarrydstamatelos on 5/9/17.
 */
public class PokerGame extends CardGame {

    double pot;

    @Override
    public ArrayList<PokerPlayer> getPlayers() {
        return (ArrayList<PokerPlayer>)super.getPlayers();
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
            Account account = AccountManager.findAccount(accountid);
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
    public PokerPlayer compareHands(ArrayList<PokerPlayer> players) {
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
    public void debitFromAccount(PokerPlayerBettingRound p) {
        Account account = AccountManager.findAccount(p.player.getAccountId());
        account.setAccountBalance(-1 * p.amountIn);
    }

    /*
    Pays the pot to the Account of the winner.
     */
    public void payToWinnersAccount(PokerPlayer p) {
        Account account = AccountManager.findAccount(p.getAccountId());
        account.setAccountBalance(pot);
    }

    /*
    For getting a PokerPlayer's name.
     */
    public String getPokerPlayerName(PokerPlayer player) {
        return AccountManager.findAccount(player.getAccountId()).getAccountHolderName();
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
        boolean ends = true;
        while (ends) { //players.size() > 1
            pot = 0;
            initialDeal();
            PokerBettingRound round = new PokerBettingRound(getPlayers());
            round.playersMakeBets();

            ArrayList<PokerPlayer> remainingPlayers = new ArrayList<PokerPlayer>();

            for (PokerPlayerBettingRound p : round.playersInRound) {
                pot += p.amountIn;
                debitFromAccount(p);
                if(!p.folded) {
                    remainingPlayers.add(p.player);
                }
            }

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
    Needed to create this main for testing. There are so
    many dependencies that it's hard to isolate a method.
     */
    public static void main(String[] args) {
        for(int i = 1; i <= 10; i++){
            AccountManager.addAccount(AccountFactory.createAccountWithName("Guest" + i));
        }

        PokerGame game = new PokerGame();
        game.run();
    }

}
