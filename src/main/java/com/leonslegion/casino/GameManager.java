package com.leonslegion.casino;

import java.util.ArrayList;

/**
 * Created by danielprahl on 5/9/17.
 */

public abstract class GameManager {
    private ArrayList<Player> players;

    @Deprecated
    public GameManager(AccountManager accountManager){
        this();
    }

    public GameManager(){
        players = new ArrayList<>(16);
    }

    // dummy implementation, override in subclass
    public void playGame(){
        System.out.println("Playing game....");
        System.out.println(".");
        System.out.println("..");
        System.out.println("...");
        System.out.println("Game over, returning to lobby.");
        System.out.println("");
    }

    public ArrayList<Player> getPlayers(){
        return players;
    }

    public void addPlayer(Player p){
        players.add(p);
    }

    public void removePlayer(Player p){
        players.remove(p);
    }

}



