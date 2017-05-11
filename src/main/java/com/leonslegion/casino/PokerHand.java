package com.leonslegion.casino;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by cameronsima on 5/9/17.
 */
public class PokerHand extends Hand implements Comparable {

    HandType handType;

    /*
    Necessary to sort in constructor?
     */
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

    void determineHandType() {
        Collections.sort(getHand(), new CardComparator());
        int switchNum = numberOfRanksPresent();
        switch(switchNum) {
            case 2:
                handType = twoRankHandChooser();
                break;
            case 3:
                handType = threeRankHandChooser();
                break;
            case 4:
                handType = fourRankHandChooser();
                break;
            case 5:
                handType = fiveRankHandChooser();
                break;
            default:
                break;
        }
    }

    void setHandType(HandType type) {
        handType = type;
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
        int ordinal = getHand().get(0).getRank().ordinal();
        for(int i = 1; i < 5; i++) {
            ordinal++;
            if(getHand().get(i).getRank().ordinal() != ordinal) {
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
        if(hasTriple()) {
            return HandType.THREEOFAKIND;
        } else {
            return HandType.TWOPAIR;
        }
    }

    HandType twoRankHandChooser() {
        Card.Rank rank = getHand().get(0).getRank();
        if(getHand().get(1).getRank() == rank) {
            return HandType.FULLHOUSE;
        }
        return HandType.FOUROFAKIND;
    }

    boolean hasTriple() {
        Card.Rank rank = getHand().get(2).getRank();
        if(countRank(rank) == 3) {
            return true;
        }
        return false;
    }

}
