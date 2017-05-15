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
    public void testSlotPlayerDidPlaceBet() {

        //given
        Account.AccountManager.addAccount("Hunter");
        SlotPlayer player = new SlotPlayer(Account.AccountManager.findAccount("Hunter"));
        boolean expectedResult = true;

        //when
        boolean actualResult = player.placeBet();

        //then
        Assert.assertEquals(expectedResult, actualResult);

    }

    @Test
    public void testSlotPlayerDidNotPlaceBet() {

        //given
        Account.AccountManager.addAccount("Leon");
        Account.AccountManager.findAccount("Leon").setAccountBalance(-100000);
        SlotPlayer player = new SlotPlayer(Account.AccountManager.findAccount("Leon"));
        boolean expectedResult = false;



        //when
        boolean actualResult = player.placeBet();

        //then
        Assert.assertEquals(expectedResult, actualResult);

    }


}
