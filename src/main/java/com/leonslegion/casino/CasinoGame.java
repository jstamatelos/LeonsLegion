package com.leonslegion.casino;

import java.util.ArrayList;

/**
 * Created by jarrydstamatelos on 5/9/17.
 */
public abstract class CasinoGame {

    private int numberOfPlayers;
    private boolean hasDealer;

    // Diplays error until Player class is created
    // ArrayList<Players> Player = new ArrayList<Players>();


    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public boolean getHasDealer() {
        return hasDealer;
    }

    public void setHasDealer(boolean hasDealer) {
        this.hasDealer = hasDealer;
    }











}
