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

    public enum HandType {HIGHCARD, PAIR, TWOPAIR, THREEOFAKIND, STRAIGHT, FLUSH, FULLHOUSE, FOUROFAKIND, STRAIGHTFLUSH}

    public HandType getHandType() {
        return handType;
    }

    public int compareTo(Object other) {
        PokerHand otherHand = (PokerHand) other;
        if(handType.ordinal() - otherHand.getHandType().ordinal() != 0) {
            return handType.ordinal() - otherHand.getHandType().ordinal();
        } else {
            CardComparator comp = new CardComparator();
            switch(handType) {
                case STRAIGHTFLUSH:
                    return comp.compare(getCards().get(2), ((PokerHand) other).getCards().get(2));
                case FOUROFAKIND:
                    return comp.compare(getCards().get(2), ((PokerHand) other).getCards().get(2));
                case FULLHOUSE:
                    return comp.compare(getCards().get(2), ((PokerHand) other).getCards().get(2));
                case STRAIGHT:
                    return comp.compare(getCards().get(2), ((PokerHand) other).getCards().get(2));
                case THREEOFAKIND:
                    return comp.compare(getCards().get(2), ((PokerHand) other).getCards().get(2));
                case TWOPAIR:
                    if(comp.compare(getCards().get(3), ((PokerHand) other).getCards().get(3)) != 0) {
                        return comp.compare(getCards().get(3), ((PokerHand) other).getCards().get(3));
                    } else if(comp.compare(getCards().get(1), ((PokerHand) other).getCards().get(1)) != 0) {
                        return comp.compare(getCards().get(1), ((PokerHand) other).getCards().get(1));
                    } else { //TODO - add condition for final kicker, wherein the ordinal values of the
                             //cards in each hand are added and it returns the difference between the two
                        return 0;
                    }
                case FLUSH:
                    for(int i = 4; i >= 0; i--) {
                        if(comp.compare(getCards().get(i), ((PokerHand) other).getCards().get(i)) != 0){
                            return comp.compare(getCards().get(i), ((PokerHand) other).getCards().get(i));
                        }
                    }
                    return 0;
                case HIGHCARD:
                    for(int i = 4; i >= 0; i--) {
                        if(comp.compare(getCards().get(i), ((PokerHand) other).getCards().get(i)) != 0){
                            return comp.compare(getCards().get(i), ((PokerHand) other).getCards().get(i));
                        }
                    }
                    return 0;
                case PAIR:
                    Card.Rank rank1 = null;
                    Card.Rank rank2 = null;
                    for(int i = 0; i < 4; i++) {
                        if(getCards().get(i).getRank() == getCards().get(i+1).getRank()) {
                            rank1 = getCards().get(i).getRank();
                            break;
                        }
                    }
                    for(int i = 0; i < 4; i++) {
                        if(otherHand.getCards().get(i).getRank() == otherHand.getCards().get(i+1).getRank()) {
                            rank2 = otherHand.getCards().get(i).getRank();
                            break;
                        }
                    }
                    return rank1.ordinal() - rank2.ordinal(); //TODO - compare kickers
                default:
                    return 0;
            }
        }
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
