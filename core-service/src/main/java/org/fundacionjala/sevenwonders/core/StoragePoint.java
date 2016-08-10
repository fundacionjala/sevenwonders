/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 *
 */

package org.fundacionjala.sevenwonders.core;

import java.util.EnumMap;
import java.util.Map;
import org.fundacionjala.sevenwonders.core.calculator.CalculatorType;

/**
 * Storage the different type of points that the player can have.
 *
 * @author Diego Fiengo.
 */
public class StoragePoint {

    private static final int DEFAULT_VALUE = 0;
    private Map<CalculatorType, Integer> points;
    private static final  int INCREASE_VALUE = 1;

    public StoragePoint() {
        points = new EnumMap<>(CalculatorType.class);
    }

    /**
     * Store the quantity of points with it's respective type
     * @param type The type of points {@link CalculatorType}
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
     * Return the quantity of points for type, but if it isn't that type in the storage it'll create it.
     * @param type The type of points {@link CalculatorType}
     * @return The quantity of the points
     */
    public int getPoint(CalculatorType type) {
        if (!points.containsKey(type)) {
            points.put(type, DEFAULT_VALUE);
        }
        return points.get(type);
    }
}
