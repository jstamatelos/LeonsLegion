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
    

    Deck deck;
    private ArrayList<Card> dealerDeck = new ArrayList<Card>();
    private ArrayList<Card> playerDeck = new ArrayList<Card>();


    // Player bets initial bet
    public static double placeBet() {
        InputHandler input = new InputHandler();
        double bet = input.getDoubleInput("Please place a bet: ");
        return bet;
    }

    // Dealer shows one card
    public void dealerShowCard () {
    // show a random card
    }


    // Player shows one card
    public void playerShowCard () {
    //show a random card
    }


    // Compare cards - if tie - player bets again, adding to initial bet
    private String dealerWinMessage(){
        return "Dealer wins, bet again or exit.";
    }
    private String playerWinMessage(){
        return "You win! Bet again or exit.";
    }
    private String tieMessage(){
        return "Tie! You and dealer showed same card , bet again!";
    }

    public String findTheHigherCard(Card dealerCard, Card playerCard){
        if (dealerCard.getPointValue() > playerCard.getPointValue()) {
            return dealerWinMessage();
        } else if (playerCard.getPointValue() > dealerCard.getPointValue()){
            return playerWinMessage();
        } else {
            return tieMessage();
        }


    }
    public int compare(Object o1, Object o2) {
        Card dealerCard = (Card) o1;
        Card playerCard = (Card) o2;
        return dealerCard.getRank().ordinal() - playerCard.getRank().ordinal();
    }

    public void runWarGame(){
        placeBet();
        dealerShowCard();
        playerShowCard();
        findTheHigherCard(dealerShowCard(), playerShowCard());



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
