package com.leonslegion.casino;

import com.leonslegion.casino.AccountPackage.Account;
import com.leonslegion.casino.SlotPackage.SlotPlayer;
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
        SlotPlayer sp = new SlotPlayer(acct);
        boolean expectedResult = true;

        //when
        boolean actualResult = sp.placeBet();

        //then
        Assert.assertEquals(expectedResult, actualResult);

    }
}
