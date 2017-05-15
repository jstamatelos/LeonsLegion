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
                    return comp.compare(getCards().get(2), otherHand.getCards().get(2));
                case FOUROFAKIND:
                    return comp.compare(getCards().get(2), otherHand.getCards().get(2));
                case FULLHOUSE:
                    return comp.compare(getCards().get(2), otherHand.getCards().get(2));
                case STRAIGHT:
                    return comp.compare(getCards().get(2), otherHand.getCards().get(2));
                case THREEOFAKIND:
                    return comp.compare(getCards().get(2), otherHand.getCards().get(2));
                case TWOPAIR:
                    if(comp.compare(getCards().get(3), otherHand.getCards().get(3)) != 0) {
                        return comp.compare(getCards().get(3), otherHand.getCards().get(3));
                    } else if(comp.compare(getCards().get(1), otherHand.getCards().get(1)) != 0) {
                        return comp.compare(getCards().get(1), otherHand.getCards().get(1));
                    } else {
                        //this finds out which hand has the better kicker by adding the ordinal values of
                        //each hand's ranks. since we know four of the cards have the same rank, this
                        //reveals the tiebreaker.
                        int handSum1 = 0;
                        for(int i = 0; i < 5; i++) {
                            handSum1 += getCards().get(i).getRank().ordinal();
                        }
                        int handSumToo = 0;
                        for(int i = 0; i < 5; i++) {
                            handSumToo += otherHand.getCards().get(i).getRank().ordinal();
                        }
                        return handSum1 - handSumToo;
                    }
                case FLUSH:
                    for(int i = 4; i >= 0; i--) {
                        if(comp.compare(getCards().get(i), otherHand.getCards().get(i)) != 0){
                            return comp.compare(getCards().get(i), otherHand.getCards().get(i));
                        }
                    }
                    return 0;
                case HIGHCARD:
                    for(int i = 4; i >= 0; i--) {
                        if(comp.compare(getCards().get(i), otherHand.getCards().get(i)) != 0){
                            return comp.compare(getCards().get(i), otherHand.getCards().get(i));
                        }
                    }
                    return 0;
                case PAIR:
                    Card.Rank rank1 = null; //the rank of the pair in the Hand calling the method
                    Card.Rank rank2 = null; //the rank of the pair in the other Hand
                    ArrayList<Integer> list1 = new ArrayList<>();
                    ArrayList<Integer> list2 = new ArrayList<>();

                    //the ensuing lines and loops create lists of the ranks present in each hand
                    //and locate the rank of the pairs

                    for(int i = 0; i < 5; i++) {
                        if(countRank(getCards().get(i).getRank()) == 2) {
                            rank1 = getCards().get(i).getRank();
                        } else {
                            list1.add(getCards().get(i).getRank().ordinal());
                        }
                    }

                    for(int i = 0; i < 5; i++) {
                        if(otherHand.countRank(getCards().get(i).getRank()) == 2) {
                            rank2 = otherHand.getCards().get(i).getRank();
                        } else {
                            list2.add(otherHand.getCards().get(i).getRank().ordinal());
                        }
                    }

                    if(rank1.ordinal() != rank2.ordinal()) { //pairs are different ranks
                        return rank1.ordinal() - rank2.ordinal();
                    } else {  //else walk through the kickers one by one
                        for(int i = 2; i >= 0; i--) {
                            if(list1.get(i) != list2.get(i)) {
                                return list1.get(i) - list2.get(i);
                            }
                        }
                    }
                    return 0;
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
