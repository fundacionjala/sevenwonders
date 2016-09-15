package org.fundacionjala.sevenwonders.core.rest;

/**
 * Created by Dell on 9/15/2016.
 */
public class WonderModel {
    private String cityName;
    private char CurrentSide;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public char getCurrentSide() {
        return CurrentSide;
    }

    public void setCurrentSide(char currentSide) {
        CurrentSide = currentSide;
    }
}
