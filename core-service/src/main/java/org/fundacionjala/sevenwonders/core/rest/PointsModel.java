package org.fundacionjala.sevenwonders.core.rest;

import org.fundacionjala.sevenwonders.core.calculator.CalculatorType;

/**
 * Created by ubuntu on 25-11-16.
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
    public CalculatorType convertCalculator() {
         return  CalculatorType.values()[calculatorType];
    }
}
