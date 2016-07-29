/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 *
 */

package org.fundacionjala.sevenwonders.core.calculator;

import com.google.common.base.Preconditions;
import org.fundacionjala.sevenwonders.core.Card;
import org.fundacionjala.sevenwonders.core.City;
import org.fundacionjala.sevenwonders.core.effect.Effect;
import org.fundacionjala.sevenwonders.core.effect.VictoryPointEffect;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Calculates all the Victory Point Effect like Civic and Wonder cards.
 * @author Diego Fiengo
 */
public class VictoryPointCalculator implements Calculable<List<Card>> {
    private City city;

    public VictoryPointCalculator(City city) {
        this.city = city;
    }

    /**
     * Checks and filter with some criteria and activates the effect.
     * @param cards The list of a specific cards.
     */
    @Override
    public void calculate(List<Card> cards) {
        Preconditions.checkNotNull(cards, "The card's list is null");
        List<Effect> effects;
        for (Card current : cards) {
            Preconditions.checkNotNull(current.getEffects(), "The effects of the current card is null");
            effects = current.getEffects().stream().filter(e -> e instanceof VictoryPointEffect)
                    .collect(Collectors.toList());
            if (!effects.isEmpty()) {
                effects.forEach(e -> e.activate(city));
            }
        }
    }
}