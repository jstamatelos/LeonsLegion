package com.leonslegion.casino;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by cameronsima on 5/9/17.
 */
public class BlackjackHand extends Hand implements Comparable {

    private int evaluateHand(Hand hand) {

        int sum = 0;
        for (Card card : hand.getHand()) {
            sum += card.getPointValue();
        }
        return sum;
    }

    @Override
    public int compareTo(Object otherHand) {

        int thisTally = evaluateHand(this);
        int otherTally = evaluateHand((Hand) otherHand);

        if (thisTally > 21 && otherTally > 21) {
            return 0;
        } else if (thisTally > otherTally || otherTally > 21) {
            return -1;
        } else if (thisTally < otherTally || thisTally > 21) {
            return 1;
        }
        return 0;
    }
}
