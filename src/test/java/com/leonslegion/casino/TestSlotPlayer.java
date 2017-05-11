package com.leonslegion.casino;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by sarahweisser on 5/11/17.
 */
public class TestSlotPlayer {

    @Test
    public void testSlotPlayerPlaceBet() {

        //given
        Account acct = new Account("Leon");
        SlotPlayer sp = new SlotPlayer(acct.getAccountBalance(), acct.getId());
        try {
            sp.placeBet(sp.getBet());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        double expectedResult = 995;

        //when
        double actualResult = sp.getBalance();

        //then
        Assert.assertEquals(expectedResult, actualResult, 0);

    }
}
