package com.leonslegion.casino;

import java.util.Comparator;

/**
 * Created by jarrydstamatelos on 5/9/17.
 */

public class WarGame extends CardGame implements Comparator {
    

    Deck dealerDeck = new Deck();
    Deck playerDeck = new Deck();

    Card playerCard = new Card (playerShowCard().getRank(), playerShowCard().getSuit());
    Card dealerCard = new Card(dealerShowCard().getRank(), dealerShowCard().getSuit());


    // Player bets initial bet
    public double placeBet() {
        InputHandler input = new InputHandler();
        double bet = input.getDoubleInput("Please place a bet: ");
        return bet;
    }

    public Card dealerShowCard() {
        return dealerCard = dealerDeck.dealCard();

    }

    public Card playerShowCard () {
        playerDeck.shuffleDeck();
        return playerCard = playerDeck.dealCard();

    }


    // Win - Lose - Draw Messages
    private String dealerWinMessage(){
        return "Dealer wins, bet again or exit.";
    }
    private String playerWinMessage(){
        return "You win! Bet again or exit.";
    }
    private String tieMessage(){
        return "Tie! You and dealer showed same card , bet again!";
    }


    public String pickHigherValue(Card playerCard,Card dealerCard){

        if (dealerCard.getPointValue() > playerCard.getPointValue()) {
            return dealerWinMessage();
        } else if (playerCard.getPointValue() > dealerCard.getPointValue()){
            return playerWinMessage();
        } else {
            return tieMessage();
        }

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

    // Start game
    public void startWar(){
        placeBet();
        dealerShowCard();
        playerShowCard();
        pickHigherValue(playerShowCard(), dealerShowCard());
        exit();

    }

    // Needed for implementation of interface
    public int compare(Object o1, Object o2) {
        Card dealerCard = (Card) o1;
        Card playerCard = (Card) o2;
        return dealerCard.getRank().ordinal() - playerCard.getRank().ordinal();
    }

    // Needed for implementation of interface
    @Override
    public void initialDeal() {
       //
    }

    @Override
    public void setHasDealer(boolean hasDealer) {
        super.setHasDealer(true);
    }
}
