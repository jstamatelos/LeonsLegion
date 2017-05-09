package com.leonslegion.casino;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by cameronsima on 5/9/17.
 */
public class BlackjackHand extends Hand implements Comparable {

    private int tally(Hand hand) {

        int sum = 0;
        for (Card card : hand.getHand()) {
            sum += card.getPointValue();
        }
        return sum;
    }

    @Override
    public int compareTo(Object otherHand) {

        int thisTally = tally(this);
        int otherTally = tally((Hand) otherHand);

        if (thisTally > otherTally) {
            return -1;
        } else if (thisTally < otherTally) {
            return 1;
        }
        return 0;
    }
}
