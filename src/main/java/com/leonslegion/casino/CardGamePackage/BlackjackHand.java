package com.leonslegion.casino.CardGamePackage;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by cameronsima on 5/9/17.
 */
public class BlackjackHand extends Hand implements Comparable {

    public int getPoints() {
        if (isOver21() && hasAce()) {
            return sumHand() - (numAces() * 10);
        }
        return sumHand();
    }

    public boolean hasAce() {
        for (Card card : getCards()) {
            if (card.getRank() == Card.Rank.ACE) {
                return true;
            }
        }
        return false;
    }

    public boolean isOver21() {
        return sumHand() > 21;
    }

    public int numAces() {
        int aces = 0;
        for (Card card : getCards()) {
            if (card.getRank() == Card.Rank.ACE) {
                aces += 1;
            }
        }
        return aces;
    }

    public int sumHand() {
        int sum = 0;
        for (Card card: getCards()) {
            sum += card.getPointValue();
        }
        return sum;
    }

    private int sumHand(Hand hand) {
        int sum = 0;
        for (Card card : hand.getCards()) {
            sum += card.getPointValue();
        }
        return sum;
    }

    public boolean splitPossible() {
        if (getCards().size() > 2) {
            return false;
        }
        Set<String> seen = new HashSet<>();
        for (Card card : getCards()) {
            if (seen.contains(card.getRank().toString())) {
                return true;
            } else {
                seen.add(card.getRank().toString());
            }
        }
        return false;
    }


    @Override
    public int compareTo(Object otherHand) {

        if (getPoints() == ((BlackjackHand) otherHand).getPoints()) {
            return 0;
        } else if (getPoints() > ((BlackjackHand) otherHand).getPoints()) {
            return -1;
        } else {
            return 1;
        }
    }
}
