package com.leonslegion.casino;
import java.util.ArrayList;

/**
 * Created by jarrydstamatelos on 5/9/17.
 */

public class WarGame extends CardGame {

    // Deck is split between player and dealer
    WarGame war = new WarGame();
    ArrayList<Card> fullDeck = new ArrayList<>();
    ArrayList<Card> dealerDeck = (ArrayList<Card>) fullDeck.subList(0,25);
    ArrayList<Card> playerDeck = (ArrayList<Card>) fullDeck.subList(26,52);

    // Player bets initial bet
    

    // Dealer shows one card

    // Player shows one card

    // Compare cards - if tie - player bets again, adding to initial bet

    // High card wins - if player wins, add initial bet to player total

    
}
