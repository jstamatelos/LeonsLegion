package com.leonslegion.casino;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jarrydstamatelos on 5/9/17.
 */
public class BlackjackGame extends CardGame {
    // Take cards from deck
    // Deal cards
    // Show player initial total and Dealer  initial total
    // If player total = 21 player win , add bet to player to player total
    // If Dealer total = 21 dealer win, remove bet from player
    // Ask player if they want to hit / stay, if player total over 21, remove bet
    // Dealer hit - if 17 or over dealer stay, if dealer over 21, player win - add bet to player total
    // Restart game

    InputHandler ih = new InputHandler();
    ArrayList<BlackjackPlayer> currentPlayers =  (ArrayList<BlackjackPlayer>)(ArrayList<?>) players;

    public void promptGame() {

        int numPlayers = ih.getIntInput("How many players?");

    }

    public void promptTurnAction(BlackjackPlayer player) {
        String cardStringEnd;

        BlackjackHand hand = (BlackjackHand) player.getHand();
        String userPrompt = buildPromptString(hand);

        String turnAction = ih.getStringInput(userPrompt);

    }

    private String buildPromptString(BlackjackHand hand) {

        StringBuilder sb = buildHandString(hand);
        int points = hand.evaluateHand();

        sb.append("is worth ");
        sb.append(points);
        sb.append(" points. ");

        if (points > 21) {
            sb.append("You lose!");
        } else if (points == 21) {
            sb.append("You win!");
        } else {
            sb.append("Hit or stay?");
        }
        return sb.toString();
    }

    private StringBuilder buildHandString(BlackjackHand hand) {

        StringBuilder sb = new StringBuilder();
        sb.append("Your hand: ");
        for (Card card : hand.getHand()) {
            sb.append(" of ");
            sb.append(card.getSuit());
            sb.append("; ");
        }
        sb.append("\n");
        return sb;
    }


    public void initialDeal() {

        for (BlackjackPlayer player : currentPlayers) {
            BlackjackHand hand = new BlackjackHand();
            buildInitialHand(player, hand);
            player.setHand(hand);

        }
    }

    private void buildInitialHand(BlackjackPlayer player, Hand hand) {

        for (int i = 0; i <= 2; i++) {
            dealCard(hand);
        }
        player.setHand(hand);
    }

    private void dealCard(Hand hand) {
        Card card = deck.dealCard();
        hand.addCard(card);
    }

    public BlackjackGame() {
        this.setHasDealer(true);

    }



}
