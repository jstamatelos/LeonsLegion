package com.leonslegion.casino;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by cameronsima on 5/9/17.
 */
public class PokerHand extends Hand implements Comparable {

    HandType handType;

    PokerHand() {
        Collections.sort(getHand(), new CardComparator());
    }

    public int compareTo(Object other) {
        PokerHand otherHand = (PokerHand) other;
        return handType.ordinal() - otherHand.getHandType().ordinal();
    }

    enum HandType {HIGHCARD, PAIR, TWOPAIR, THREEOFAKIND, STRAIGHT, FLUSH, FULLHOUSE, FOUROFAKIND, STRAIGHTFLUSH}

    HandType getHandType() {
        return handType;
    }

    void setHandType() {
        this.handType = HandType.HIGHCARD;
        /*
          helper methods below.
          first step will be to find
          the total number of ranks
          present.
          that will determine how to
          proceed.
          this way doesn't provide for
          tie-breakers.
        */
    }

    boolean hasFlush() {
        Card.Suit suit = getHand().get(0).getSuit();
        for(Card c : getHand()) {
            if(c.getSuit() != suit) {
                return false;
            }
        }
        return true;
    }

    boolean hasStraight() {
        Card.Rank rank = getHand().get(0).getRank();
        for(int i = 1; i < 5; i++) {
            rank++;
            if(getHand().get(i).getRank() != rank) {
                return false;
            }
        }
        return true;
    }

    int countRank(Card.Rank rank) {
        int count = 0;
        for(Card c : getHand()) {
            if(c.getRank() == rank) {
                count++;
            }
        }
        return count;
    }

    int numberOfRanksPresent() {
        ArrayList<Card.Rank> ranks = new ArrayList<Card.Rank>();
        for(Card c : getHand()) {
            if(!ranks.contains(c.getRank())) {
                ranks.add(c.getRank());
            }
        }
        return ranks.size();
    }

    HandType fiveRankHandChooser() {
        if(hasStraight()) {
            if(hasFlush()) {
                return HandType.STRAIGHTFLUSH;
            } else {
                return HandType.STRAIGHT;
            }
        } else if(hasFlush()) {
            return HandType.FLUSH;
        } else {
            return HandType.HIGHCARD;
        }
    }

    HandType fourRankHandChooser() {
        return HandType.PAIR;
    }

    HandType threeRankHandChooser() {
        //todo
    }

    HandType twoRankHandChooser() {
        Card.Rank rank = getHand().get(0).getRank();
        if(getHand().get(1).getRank() == rank) {
            return HandType.FULLHOUSE;
        }
        return HandType.FOUROFAKIND;
    }

}