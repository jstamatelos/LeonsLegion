package com.leonslegion.casino;

import com.leonslegion.casino.AccountPackage.Account;
import com.leonslegion.casino.SlotPackage.SlotMachine;
import com.leonslegion.casino.SlotPackage.SlotPlayer;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by sarahweisser on 5/11/17.
 */
public class TestSlotMachine {

    @Test
    public void displayImagesTest() {

        //given
        Account.AccountManager.addAccount("Leon");
        SlotPlayer sp = new SlotPlayer(Account.AccountManager.findAccount("Leon"));
        SlotMachine machine = new SlotMachine(sp);
        String expectedResult = "Results: \t" + machine.getImage1() + "\t|\t" + machine.getImage2() + "\t|\t" + machine.getImage3();

        //when
        String actualResult = machine.displayImages();

        //then
        Assert.assertEquals(expectedResult, actualResult);

    }

    @Test
    public void testDetermineWinningsCherry() {

        //given
        Account.AccountManager.addAccount("Leon");
        SlotPlayer sp = new SlotPlayer(Account.AccountManager.findAccount("Leon"));
        SlotMachine machine = new SlotMachine(sp);
        long expectedResult = 50000;

        //when
        long actualResult = machine.determineWinnings("Cherry");

        //then
        Assert.assertEquals(expectedResult, actualResult);

    }

    @Test
    public void testDetermineWinningsOrange() {

        //given
        Account.AccountManager.addAccount("Leon");
        SlotPlayer sp = new SlotPlayer(Account.AccountManager.findAccount("Leon"));
        SlotMachine machine = new SlotMachine(sp);
        long expectedResult = 50000;

        //when
        long actualResult = machine.determineWinnings("Orange");

        //then
        Assert.assertEquals(expectedResult, actualResult);

    }

    @Test
    public void testDetermineWinningsWatermelon() {

        //given
        Account.AccountManager.addAccount("Leon");
        SlotPlayer sp = new SlotPlayer(Account.AccountManager.findAccount("Leon"));
        SlotMachine machine = new SlotMachine(sp);
        long expectedResult = 50000;

        //when
        long actualResult = machine.determineWinnings("Watermelon");

        //then
        Assert.assertEquals(expectedResult, actualResult);

    }

    @Test
    public void testDetermineWinningsBar() {

        //given
        Account.AccountManager.addAccount("Leon");
        SlotPlayer sp = new SlotPlayer(Account.AccountManager.findAccount("Leon"));
        SlotMachine machine = new SlotMachine(sp);
        long expectedResult = 50000;

        //when
        long actualResult = machine.determineWinnings("Bar");

        //then
        Assert.assertEquals(expectedResult, actualResult);

    }

    @Test
    public void testDetermineWinningsLemon() {

        //given
        Account.AccountManager.addAccount("Leon");
        SlotPlayer sp = new SlotPlayer(Account.AccountManager.findAccount("Leon"));
        SlotMachine machine = new SlotMachine(sp);
        long expectedResult = 50000;

        //when
        long actualResult = machine.determineWinnings("Lemon");

        //then
        Assert.assertEquals(expectedResult, actualResult);

    }

    @Test
    public void testDetermineWinningsDoubleBar() {

        //given
        Account.AccountManager.addAccount("Leon");
        SlotPlayer sp = new SlotPlayer(Account.AccountManager.findAccount("Leon"));
        SlotMachine machine = new SlotMachine(sp);
        long expectedResult = 100000;

        //when
        long actualResult = machine.determineWinnings("Double Bar");

        //then
        Assert.assertEquals(expectedResult, actualResult);

    }

    @Test
    public void testDetermineWinningsTripleBar() {

        //given
        Account.AccountManager.addAccount("Leon");
        SlotPlayer sp = new SlotPlayer(Account.AccountManager.findAccount("Leon"));
        SlotMachine machine = new SlotMachine(sp);
        long expectedResult = 200000;

        //when
        long actualResult = machine.determineWinnings("Triple Bar");

        //then
        Assert.assertEquals(expectedResult, actualResult);

    }

    @Test
    public void testDetermineWinningsLuckySeven() {

        //given
        Account.AccountManager.addAccount("Leon");
        SlotPlayer sp = new SlotPlayer(Account.AccountManager.findAccount("Leon"));
        SlotMachine machine = new SlotMachine(sp);
        long expectedResult = 500000;

        //when
        long actualResult = machine.determineWinnings("Lucky Seven");

        //then
        Assert.assertEquals(expectedResult, actualResult);

    }

    @Test
    public void testPullLeverWithMatchingImages() {

        //given
        Account.AccountManager.addAccount("Leon");
        SlotPlayer sp = new SlotPlayer(Account.AccountManager.findAccount("Leon"));
        SlotMachine machine = new SlotMachine(sp);
        do {

            machine = new SlotMachine(sp);
            machine.pullLever();
        } while (!machine.matchImages());
        boolean expectedResult = true;


        //when
        boolean actualResult = machine.matchImages();

        //then
        Assert.assertEquals(expectedResult, actualResult);

    }

    @Test
    public void testPullLeverImagesDoNotMatch() {

        //given
        Account.AccountManager.addAccount("Leon");
        SlotPlayer sp = new SlotPlayer(Account.AccountManager.findAccount("Leon"));
        SlotMachine machine = new SlotMachine(sp);
        do {
            machine = new SlotMachine(sp);
            machine.pullLever();
        } while (machine.matchImages());
        boolean expectedResult = false;


        //when
        boolean actualResult = machine.matchImages();

        //then
        Assert.assertEquals(expectedResult, actualResult);

    }

}
