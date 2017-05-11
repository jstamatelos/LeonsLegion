package com.leonslegion.casino;

/**
 * Created by sarahweisser on 5/11/17.
 */
public class SlotMachine {

    Reel reel1 = new Reel();
    Reel reel2 = new Reel();
    Reel reel3 = new Reel();

    String image1 = reel1.spinReel();
    String image2 = reel2.spinReel();
    String image3 = reel3.spinReel();

    public void displayImages() {

        System.out.println("Results: \t" + image1 + "\t" + image2 + "\t" + image3);

    }

    public boolean matchImages() {

        if ((image1.equals(image2) && (image2.equals(image3)))) {
            return true;
        }
        else {
            return false;
        }
    }

    public double determineWinnings(String image1) {
        switch (image1) {
            case "Cherry":
                return 500;
            case "Orange":
                return 500;
            case "Watermelon":
                return 500;
            case "Lemon":
                return 500;
            case "Bar":
                return 500;
            case "Double Bar":
                return 1000;
            case "Triple Bar":
                return 2000;
            case "Lucky Seven":
                return 5000;
            default:
                return 0;
            }
    }

    public double pullLever() {

        displayImages();
        if(matchImages()) {
            System.out.printf("You won: $%.2f\n", determineWinnings(image1));
            return determineWinnings(image1);
        }
        else {
            System.out.println("No Match.  Please try again.");
            return 0;
        }
    }


}
