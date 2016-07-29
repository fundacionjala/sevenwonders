/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 *
 */

package org.fundacionjala.sevenwonders.core.calculator;

import org.fundacionjala.sevenwonders.core.Card;
import org.fundacionjala.sevenwonders.core.City;
import org.fundacionjala.sevenwonders.core.factory.CalculatorFactory;

import java.util.List;

/**
 * @author Diego Fiengo
 */
public class CardManager {
    private City city;

    public CardManager(City city) {
        this.city = city;
    }

    /**
     * Calculates a specific collection of cards with their respective calculator.
     *
     * @param cards A specific collection of colors of cards.
     */
    public void calculateCards(List<Card> cards, CalculatorType type) {
        CalculatorFactory factory = new CalculatorFactory(city);
        factory.getCalculator(type).calculate(cards);
    }
}
