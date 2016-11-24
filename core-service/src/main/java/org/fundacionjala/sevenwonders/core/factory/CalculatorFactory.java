/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 *
 */

package org.fundacionjala.sevenwonders.core.factory;

import org.fundacionjala.sevenwonders.core.City;
import org.fundacionjala.sevenwonders.core.calculator.Calculable;
import org.fundacionjala.sevenwonders.core.calculator.CalculatorType;
import org.fundacionjala.sevenwonders.core.calculator.ScientificCardCalculator;
import org.fundacionjala.sevenwonders.core.calculator.VictoryPointCalculator;

/**
 * @author Diego Fiengo
 */
public class CalculatorFactory {

    private final City city;

    public CalculatorFactory(City city) {
        this.city = city;
    }

    /**
     * Get a specific calculator {@link Calculable} for a type.
     * @param type The specific type calculator {@link CalculatorType}
     * @return A specific calculator
     */
    public Calculable getCalculator(CalculatorType type) {
        switch (type) {
            case CIVIC:
                return new VictoryPointCalculator(city);
            case WONDER:
                return new VictoryPointCalculator(city);
            case SCIENTIFIC:
                return new ScientificCardCalculator(city);
            default:
                throw new EnumConstantNotPresentException(CalculatorType.class, "No supported enum");
        }
    }
}
