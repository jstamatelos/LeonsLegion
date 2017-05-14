package com.leonslegion.casino.CardGamePackage;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by cameronsima on 5/9/17.
 */
public class PokerHand extends Hand implements Comparable {

    public HandType handType;

    public PokerHand() {
        handType = null;
    }

    public int compareTo(Object other) {
        PokerHand otherHand = (PokerHand) other;
        return handType.ordinal() - otherHand.getHandType().ordinal();
    }

    public enum HandType {HIGHCARD, PAIR, TWOPAIR, THREEOFAKIND, STRAIGHT, FLUSH, FULLHOUSE, FOUROFAKIND, STRAIGHTFLUSH}

    public HandType getHandType() {
        return handType;
    }

    void determineHandType() {
        Collections.sort(getCards(), new CardComparator());
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

    public void setHandType(HandType type) {
        handType = type;
    }

    public boolean hasFlush() {
        Card.Suit suit = getCards().get(0).getSuit();
        for(Card c : getCards()) {
            if(c.getSuit() != suit) {
                return false;
            }
        }
        return true;
    }

    public boolean hasStraight() {
        int ordinal = getCards().get(0).getRank().ordinal();
        for(int i = 1; i < 5; i++) {
            ordinal++;
            if(getCards().get(i).getRank().ordinal() != ordinal) {
                return false;
            }
        }
        return true;
    }

    public int countRank(Card.Rank rank) {
        int count = 0;
        for(Card c : getCards()) {
            if(c.getRank() == rank) {
                count++;
            }
        }
        return count;
    }

    public int numberOfRanksPresent() {
        ArrayList<Card.Rank> ranks = new ArrayList<Card.Rank>();
        for(Card c : getCards()) {
            if(!ranks.contains(c.getRank())) {
                ranks.add(c.getRank());
            }
        }
        return ranks.size();
    }

    public HandType fiveRankHandChooser() {
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

    public HandType fourRankHandChooser() {
        return HandType.PAIR;
    }

    public HandType threeRankHandChooser() {
        if(hasTriple()) {
            return HandType.THREEOFAKIND;
        } else {
            return HandType.TWOPAIR;
        }
    }

    public HandType twoRankHandChooser() {
        Card.Rank rank = getCards().get(0).getRank();
        if(getCards().get(1).getRank() == rank) {
            return HandType.FULLHOUSE;
        }
        return HandType.FOUROFAKIND;
    }

    public boolean hasTriple() {
        Card.Rank rank = getCards().get(2).getRank();
        if(countRank(rank) == 3) {
            return true;
        }
        return false;
    }

}
