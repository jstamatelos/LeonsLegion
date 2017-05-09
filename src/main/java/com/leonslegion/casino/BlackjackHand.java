package com.leonslegion.casino;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by cameronsima on 5/9/17.
 */
public class BlackjackHand extends Hand implements Comparable {

    @Override
    public int compareTo(Object otherHand) {

        int thisTally = 0;
        int otherTally = 0;

        for (Card card : this.getCards()) {
            thisTally += card.
        }

        return 0;
    }
}
