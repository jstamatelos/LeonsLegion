package com.leonslegion.casino;

import java.util.ArrayList;

/**
 * Created by jarrydstamatelos on 5/9/17.
 */
public abstract class CasinoGame {

    private int numberOfPlayers;
    private boolean hasDealer;


    ArrayList<Player> players = new ArrayList<Player>();


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

    public void setPlayer(Player player) {
        players.add(player);
    }

    public void setPlayers(ArrayList<Player> somePlayers) {
        players.addAll(somePlayers);
    }
    
}
