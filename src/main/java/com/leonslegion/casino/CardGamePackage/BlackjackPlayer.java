package com.leonslegion.casino.CardGamePackage;

import com.leonslegion.casino.AccountPackage.Account;
import com.leonslegion.casino.Console;
import com.leonslegion.casino.InputHandler;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class BlackjackPlayer extends CardPlayer {

    private ArrayList<BlackjackHand> splitHands = new ArrayList<>();
    private BlackjackHand hand;

    public BlackjackPlayer(Account account) {

        super(account);
        hand = (BlackjackHand) super.getHand();
    }

    public void hit(Card card) {
        hand.addCard(card);
    }

    public void stay() {
        //nothing happens. not sure if this needs a method.
    }

    public void split() {
        if (hand.splitPossible()) {
            splitHand();
        }
    }

    private void splitHand() {
        if (hand.splitPossible()) {
            BlackjackHand hand1 = new BlackjackHand();
            BlackjackHand hand2 = new BlackjackHand();
            hand1.addCard(hand.getHand().get(0));
            hand2.addCard(hand.getHand().get(1));
            splitHands.add(hand1);
            splitHands.add(hand2);
        }
    }

    public boolean hasSplitHands() {
        return splitHands.size() == 2;
    }

    public ArrayList<BlackjackHand> getSplitHands() {
        return splitHands;
    }
    public void setHand(BlackjackHand blackjackHand) {
        hand = blackjackHand;
    }


    public String placeBet (String bet) {
        if (!NumberUtils.isParsable(bet)) {
            String newBet = InputHandler.getStringInput("That's not a valid bet.");
            return placeBet(newBet);
        }
        else if (Double.parseDouble(bet) < 0) {
            String newBet = InputHandler.getStringInput("You cannot make a negative bet!");
            return placeBet(newBet);
        }
        else if (getBalance() - Double.parseDouble(bet) < 0) {
            String newBet = InputHandler.getStringInput("Your bet is greater than your balance!");
            return placeBet(newBet);
        }
        else {
            return bet;
        }
    }
}