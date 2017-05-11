package com.leonslegion.casino;

import java.util.Comparator;

/**
 * Created by jarrydstamatelos on 5/9/17.
 */

public class WarGame extends CardGame implements Comparator {
    

    static Deck dealerDeck = new Deck();
    static Deck playerDeck = new Deck();

    static Card playerCard;
    static Card dealerCard;

    // Game starter
    public static void startWarGame(){
        System.out.println("WAR! WHAT IS IT GOOD FOR!");
        System.out.println("Welcome to the game of War");
        WarGame.placeBet();
        System.out.println(setDealerCard());
        System.out.println(setplayerCard());
        System.out.println(determineWinner());
        WarGame.exit();

    }

    // Initial Bet
    public static double placeBet() {
        InputHandler input = new InputHandler();
        double bet = input.getDoubleInput("Please place a bet: ");
        return bet;
    }
    // Dealer draws card from dealer deck (deck == hand)
    public static String setDealerCard(){
        dealerDeck.shuffleDeck();
        dealerCard = dealerDeck.dealCard();
        return "Dealer draws a : " + dealerCard.toString(dealerCard);
    }
    // Player draws card from player deck (deck == hand)
    public static String setplayerCard(){
        playerDeck.shuffleDeck();
        playerCard = playerDeck.dealCard();
        return "Player draws a : " + playerCard.toString(playerCard);
    }
    // Dealer card is compared to player card by point value
    public static String determineWinner(){
        if (playerCard.getPointValue() > dealerCard.getPointValue()){
            return "You win! Nice!";
        } else if (playerCard.getPointValue() < dealerCard.getPointValue()){
            return "Dealer wins! Oh well.";
        } else {
            return "Tie! WAR!!!!!!";
        }
    }

    // Exit game
    public static boolean exit() {
        InputHandler inputHandler = new InputHandler();
        String exitOpportunity = inputHandler.getStringInput("Type 'exit' to return to lobby or 'stay' to play again");
        if (exitOpportunity.equalsIgnoreCase("exit")) {
            return false;
        } else if (exitOpportunity.equalsIgnoreCase("stay")) {
            WarGame.startWarGame();
        }
        return false;
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
