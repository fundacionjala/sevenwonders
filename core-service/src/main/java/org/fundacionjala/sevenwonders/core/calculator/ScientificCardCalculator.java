/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */
package org.fundacionjala.sevenwonders.core.calculator;

import com.google.common.base.Preconditions;
import org.fundacionjala.sevenwonders.core.Card;
import org.fundacionjala.sevenwonders.core.City;
import org.fundacionjala.sevenwonders.core.Symbol;
import org.fundacionjala.sevenwonders.core.effect.Effect;
import org.fundacionjala.sevenwonders.core.effect.ScientifPointEffect;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Luis Gumucio
 */
public class ScientificCardCalculator implements Calculable<List<Card>> {

    private int resultPoints;
    private City city;
    private List<Effect> effects;
    private int additionalPoints = 999999;
    private static final int BONUS = 7;

    public ScientificCardCalculator(City city) {
        this.city = city;
    }

    @Override
    public void calculate(List<Card> cards) {
        Preconditions.checkNotNull(cards, "The card's list is null");
        int aux = 0;
        addEffects(cards);
        for (Symbol current : Symbol.values()) {
            aux += subCalculate(current, effects);
        }
        resultPoints = aux + calculateAdditionalPoints();
        city.getStoragePoint().addPointsType(CalculatorType.SCIENTIFIC, resultPoints);
    }

    /**
     * Calculate the additionalPoints by bonus of scientific card.
     *
     * @return bonus the scientific cards
     */
    private int calculateAdditionalPoints() {
        return additionalPoints * BONUS;
    }

    /**
     * Calculate by list of effect and filter by effect
     *
     * @param current symbol current
     * @param effects list effect of the cards
     * @return value filter by effect
     */
    private int subCalculate(Symbol current, List<Effect> effects) {
        Preconditions.checkNotNull(current, "the current and effects is null");
        int result = (int) effects.stream().filter(b -> ((ScientifPointEffect) b).getSymbol() == current).count();
        if (result < additionalPoints) {
            additionalPoints = result;
        }
        return result * result;
    }

    private void addEffects(List<Card> cards) {
        effects = new ArrayList<>();
        for (Card card : cards) {
            for (Effect effect : card.getEffects()) {
                Preconditions.checkNotNull(effect, "The effects list is null");
                effects.add(effect);
            }
        }
    }
}

