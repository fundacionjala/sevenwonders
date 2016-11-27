/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */
package org.fundacionjala.sevenwonders.core.rest;

import org.fundacionjala.sevenwonders.core.calculator.CalculatorType;

/**
 * Builds a Points model according to the {@link CalculatorType}, id of {@link PlayerModel} and {@link GameModel}.
 *
 * @author Jhonatan Mamani
 */
public class PointsModel {

    private int playerId;
    private int gameId;
    private int calculatorType;

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getCalculatorType() {
        return calculatorType;
    }

    public void setCalculatorType(int calculatorType) {
        this.calculatorType = calculatorType;
    }

    /**
     * It converts an integer to an enum class called {@link CalculatorType}.
     *
     * @return a value of the CalculatorType
     */
    public CalculatorType convertCalculatorType() {
        return CalculatorType.values()[calculatorType];
    }
}
