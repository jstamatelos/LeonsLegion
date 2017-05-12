package com.leonslegion.casino.CardGamePackage;

import java.util.Comparator;

/**
 * Created by danzygmund-felt on 5/9/17.
 */
public class CardComparator implements Comparator {

    public int compare(Object o1, Object o2) {
        Card c1 = (Card) o1;
        Card c2 = (Card) o2;
        return c1.getRank().ordinal() - c2.getRank().ordinal();
    }
}
