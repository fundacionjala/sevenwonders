/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */
package org.fundacionjala.sevenwonders.core.rest;

import org.fundacionjala.sevenwonders.core.calculator.CalculatorType;

import java.util.EnumMap;
import java.util.Map;

/**
 * StoragePointModel the different type of points that the player can have it.
 *
 * @author Jhonatan Mamani
 */
public class StoragePointModel {

    private static final int DEFAULT_VALUE = 0;
    private Map<CalculatorType, Integer> points;

    public StoragePointModel() {
        points = new EnumMap<>(CalculatorType.class);
    }

    /**
     * Adds points to according to the {@link CalculatorType} and points quantity.
     *
     * @param type
     * @param pointsQuantity
     */
    public void addPointsType(CalculatorType type, int pointsQuantity) {
        int storedPoints = 0;
        if (points.containsKey(type)) {
            storedPoints = points.get(type);
        }
        points.put(type, pointsQuantity + storedPoints);
    }

    /**
     * Gets the point to according the type.
     *
     * @param type
     * @return points
     */
    public int getPoint(CalculatorType type) {
        if (!points.containsKey(type)) {
            points.put(type, DEFAULT_VALUE);
        }
        return points.get(type);
    }
}
