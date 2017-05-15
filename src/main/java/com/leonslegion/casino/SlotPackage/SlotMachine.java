package com.leonslegion.casino.SlotPackage;

import com.leonslegion.casino.Console;

/**
 * Created by sarahweisser on 5/11/17.
 */
public class SlotMachine {

    private SlotPlayer sp;

    Reel reel1 = new Reel();
    Reel reel2 = new Reel();
    Reel reel3 = new Reel();

    String image1 = reel1.spinReel();
    String image2 = reel2.spinReel();
    String image3 = reel3.spinReel();

    public String getImage1() {
        return image1;
    }

    public String getImage2() {
        return image2;
    }

    public String getImage3() {
        return image3;
    }

    public SlotMachine(SlotPlayer sp) {
        this.sp = sp;
    }

    public String displayImages() {

        return "Results: \t" + image1 + "\t|\t" + image2 + "\t|\t" + image3;
    }

    public boolean matchImages() {

        if ((image1.equals(image2) && (image2.equals(image3)))) {
            return true;
        }
        else {
            return false;
        }
    }

    public long determineWinnings(String image1) {
        switch (image1) {
            case "Cherry":
                return 50000;
            case "Orange":
                return 50000;
            case "Watermelon":
                return 50000;
            case "Lemon":
                return 50000;
            case "Bar":
                return 50000;
            case "Double Bar":
                return 100000;
            case "Triple Bar":
                return 200000;
            case "Lucky Seven":
                return 500000;
            default:
                return 0;
            }
    }

    public long pullLever() {

        Console.println(displayImages());
        if(matchImages()) {
            Console.println("You won: "+ Console.moneyToString(determineWinnings(image1)));
            sp.getAccount().setAccountBalance(sp.getBalance() + determineWinnings(image1));
            return determineWinnings(image1);
        }
        else {
            Console.println("No Match.  Please try again.");
            return 0;
        }

    }


}
