package com.leonslegion.casino.SlotPackage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by sarahweisser on 5/11/17.
 */

public class Reel {

    private String[] images = {
            "Cherry",
            "Cherry",
            "Cherry",
            "Cherry",
            "Orange",
            "Orange",
            "Orange",
            "Orange",
            "Watermelon",
            "Watermelon",
            "Watermelon",
            "Watermelon",
            "Lemon",
            "Lemon",
            "Lemon",
            "Lemon",
            "Bar",
            "Bar",
            "Bar",
            "Bar",
            "Double Bar",
            "Double Bar",
            "Double Bar",
            "Triple Bar",
            "Triple Bar",
            "Lucky Seven"
    };

    private ArrayList<String> imagesList = new ArrayList<String>(Arrays.asList(images));

    public ArrayList<String> getImages() {
        return imagesList;
    }

    public String spinReel() {
        ArrayList<String> shuffled = new ArrayList<String>(Arrays.asList(images));
        Collections.shuffle(shuffled);
        return shuffled.get(0);
    }

}
