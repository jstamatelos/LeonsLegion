package com.leonslegion.casino;
import jdk.internal.util.xml.impl.Input;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by jarrydstamatelos on 5/9/17.
 */

public class WarGame extends CardGame implements Comparator {

    // Initialize Game, dealer and player get deck each
    
    WarGame war = new WarGame();
    ArrayList<Card> newDeck = new ArrayList<Card>();
    ArrayList<Card> dealerDeck = new ArrayList<Card>();
    ArrayList<Card> playerDeck = new ArrayList<Card>();


    // Player bets initial bet
    public static double placeBet() {
        InputHandler input = new InputHandler();
        double bet = input.getDoubleInput("Please place a bet: ");
        return bet;
    }

    // Dealer shows one card
    public String dealerShowCard (ArrayList<Card> cardDeck) {
        String dealerCard = (cardDeck.get(0)).getRank().toString() + " of " + (cardDeck.get(0)).getSuit().toString();
        for (int i = 1; i < cardDeck.size(); i++) {
            dealerCard += ", " + (cardDeck.get(i)).getRank().toString() + " of " + (cardDeck.get(i)).getSuit().toString();
        }
        return dealerCard;
    }


    // Player shows one card
    public String playerShowCard (ArrayList<Card> cardDeck) {
        String playerCard = (cardDeck.get(0)).getRank().toString() + " of " + (cardDeck.get(0)).getSuit().toString();
        for (int i = 1; i < cardDeck.size(); i++) {
            playerCard += ", " + (cardDeck.get(i)).getRank().toString() + " of " + (cardDeck.get(i)).getSuit().toString();
        }
        return playerCard;
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
