package com.leonslegion.casino;

/**
 * Created by cameronsima on 5/9/17.
 */
public class PokerHand extends Hand implements Comparable {

    @Override
    public int compareTo(Object o) {
        return 0;

    HandType handType;

    public int compareTo(Object other) {
        PokerHand otherHand = (PokerHand) other;
        return handType.ordinal() - otherHand.getHandType().ordinal();
    }

    enum HandType {HIGHCARD, PAIR, TWOPAIR, THREEOFAKIND, STRAIGHT, FLUSH, FULLHOUSE, FOUROFAKIND, STRAIGHTFLUSH}

    HandType getHandType() {
        return handType;
    }

    void findHandType() {
        this.handType = HandType.HIGHCARD;
>>>>>>> 34e4835e5af087f7c19aae081b7772480a5c1765
    }

}
