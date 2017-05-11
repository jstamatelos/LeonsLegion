package com.leonslegion.casino;

import org.apache.commons.lang3.math.NumberUtils;

import java.util.ArrayList;

/**
 * Created by jarrydstamatelos on 5/9/17.
 */
public class PokerGame extends CardGame {
    // Take cards from deck
    // Deal cards
    // Remove selected cards from player hand
    // Repopulate player hand with new cards
    // Compare player hand to odds
    // Payout
    // Restart

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
                //create new player and add to game?
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


}
