package com.leonslegion.casino;
import jdk.internal.util.xml.impl.Input;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by jarrydstamatelos on 5/9/17.
 */

public class WarGame extends CardGame implements Comparator {

    // Initialize Game, Deck is split between player and dealer
    
    WarGame warGame = new WarGame();
    ArrayList<Card> newDeck = new ArrayList<Card>();
    ArrayList<Card> dealerDeck = new ArrayList<Card>();
    ArrayList<Card> playerDeck = new ArrayList<Card>();

    public void splitDeck() {
        for (int i = 0; i < newDeck.size(); i++){
            int j = (int) (Math.random()*newDeck.size()-i);
            if(i%2==0){
                dealerDeck.add(newDeck.get(i));
            } else {
                playerDeck.add(newDeck.get(j));
            }
        }
    }
    // Player bets initial bet
    public static double placeBet() {
        InputHandler input = new InputHandler();
        double bet = input.getDoubleInput("Please place a bet: ");
        return bet;
    }

    // Dealer shows one card
    public String dealerShowsCard(){
        return "";
    }
    // Player shows one card
    public String playerShowsCard(){
        return "";
    }

    // Compare cards - if tie - player bets again, adding to initial bet
    public int compare(Object o1, Object o2) {
        Card c1 = (Card) o1;
        Card c2 = (Card) o2;
        return c1.getRank().ordinal() - c2.getRank().ordinal();
    }
    // High card wins - if player wins, add initial bet to player total
    public void adjustAccountBalance(Account account, double netWinnings) {
        account.setAccountBalance(netWinnings);
    }



    // Exit game
    public static boolean exit() {
        InputHandler inputHandler = new InputHandler();
        String exitOpportunity = inputHandler.getStringInput("Type exit to quit");
        if (exitOpportunity.equalsIgnoreCase("exit")) {
            return false;
        } else {
            return true;
        }
    }

    
}
