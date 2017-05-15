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

    public void hit(Deck deck) {
        Card card = deck.dealCard();
        hand.addCard(card);
    }

    public void hitSplitHand(int hand, Deck deck) {
        Card card = deck.dealCard();
        if (hand == 1) {
            BlackjackHand h = getSplitHands().get(0);
            h.addCard(card);
        } else if (hand == 2) {
            BlackjackHand h = getSplitHands().get(1);
            h.addCard(card);
        }
    }

    public void showHand() {
        if (hasSplitHands()) {
            int num = 1;
            for (Hand h : getSplitHands()) {
                Console.println("Hand " + num + ": ");
                Console.println(h.toString());
                num +=1 ;
            }
        } else {
            Console.println("Your hand: ");
            Console.println(getHand().toString());
        }
    }


    public BlackjackHand getHand() {
        return hand;
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
            hand1.addCard(hand.getCards().get(0));
            hand2.addCard(hand.getCards().get(1));
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

    public void deductBetFromAccount(int bet) {
        getAccount().setAccountBalance(bet * -1);
    }

    public void addBetToAccount(int bet) {
        getAccount().setAccountBalance(bet * 1.5);
    }



    //TODO - dzf - probably good idea to use getMoneyInput here.

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