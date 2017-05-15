package com.leonslegion.casino.RoulettePackage;

/**
 * Created by markbrown on 5/10/17.
 */
public class RouletteBet {



    private String betType;
    private long betValue;



    public RouletteBet (String betType, long betValue) {
        this.betType = betType;
        this.betValue = betValue;
    }



    public String getBetType() {return betType;}
    public long getBetValue() {return betValue;}



}
