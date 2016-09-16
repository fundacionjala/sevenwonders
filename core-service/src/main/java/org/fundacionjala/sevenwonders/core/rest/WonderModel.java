/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */
package org.fundacionjala.sevenwonders.core.rest;

/**
 * @author Vania Catorceno
 */
public class WonderModel {
    private String cityName;
    private char currentSide;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public char getCurrentSide() {
        return currentSide;
    }

    public void setCurrentSide(char currentSide) {
        currentSide = currentSide;
    }
}
