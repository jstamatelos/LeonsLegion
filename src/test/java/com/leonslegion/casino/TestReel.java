package com.leonslegion.casino;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by sarahweisser on 5/11/17.
 */
public class TestReel {

    @Test
    public void testSpin() {

        //given
        Reel reel1 = new Reel();

        //when
        String actualResult = reel1.spinReel();

        //then
        Assert.assertTrue(reel1.getImages().contains(actualResult));

    }
}
