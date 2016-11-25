package org.fundacionjala.sevenwonders.core.rest;

import org.fundacionjala.sevenwonders.core.calculator.CalculatorType;

import java.util.EnumMap;
import java.util.Map;

/**
 * StoragePointModel the different type of points that the player can have.
 * Created by johx on 25-11-16.
 */
public class StoragePointModel {

    private static final int DEFAULT_VALUE = 0;
    private Map<CalculatorType, Integer> points;
    private static final int INCREASE_VALUE = 1;

    public StoragePointModel() {
        points = new EnumMap<>(CalculatorType.class);
    }


    public void addPointsType(CalculatorType type, int pointsQuantity) {
        int storedPoints = 0;
        if (points.containsKey(type)) {
            storedPoints = points.get(type);
        }
        points.put(type, pointsQuantity + storedPoints);
    }


    public int getPoint(CalculatorType type) {
        if (!points.containsKey(type)) {
            points.put(type, DEFAULT_VALUE);
        }
        return points.get(type);
    }
}
