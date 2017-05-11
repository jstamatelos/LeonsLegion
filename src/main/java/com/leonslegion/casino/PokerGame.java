package com.leonslegion.casino;

import org.apache.commons.lang3.math.NumberUtils;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by jarrydstamatelos on 5/9/17.
 */
public class PokerGame extends CardGame {


    private void promptGame() {
        InputHandler ih = new InputHandler();
        int numPlayers = ih.getIntInput("How many players?");
        loadPlayers(numPlayers);
    }

    private void loadPlayers(int numPlayers) {
        InputHandler ih = new InputHandler();
        for(int i = 0; i < numPlayers; i++) {
            long accountid = Long.parseLong(ih.getStringInput("Please enter Player " + (i+1) + "'s ID."));
            Account account = AccountManager.findAccount(accountid);
            if(account != null) {
                players.add(new PokerPlayer(AccountManager.getBalance(account), accountid));
            } else {
                i--;
            }
        }
    }

    public void initialDeal() {
        for(Player p :  players) {
            for(int i = 0; i < 5; i++) {
                ((CardPlayer) p).hand.addCard(deck.dealCard());
            }
        }
    }

    public Player compareHands(ArrayList<Player> players) {
        Player winner = players.get(0);
        for(int i = 1; i < players.size(); i++) {
            //if players.get(i) has better hand than winner
            //winner = players.get(i)
        }

        return winner;
    }

    public void run() {
        promptGame();
        while(players.size() > 1) {
            double pot = 0;
            initialDeal();
            PokerBettingRound round = new PokerBettingRound(players);
            round.playersMakeBets();
            for(PokerPlayerBettingRound p : round.outOfGame) {
                pot += p.amountIn;
                players.remove(p); //this will be useful when there are multiple rounds.
            }
            for(PokerPlayerBettingRound p : round.stillInGame) {
                pot += p.amountIn;
            }
            //need to debit from the players' accounts
            Player winner = compareHands(players);



            //exchange - this can be implemented later
            //betting round
        }
    }

}
