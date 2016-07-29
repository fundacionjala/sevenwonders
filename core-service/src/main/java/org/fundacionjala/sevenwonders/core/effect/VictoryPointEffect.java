/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 *
 */

package org.fundacionjala.sevenwonders.core.effect;

import com.google.common.base.Preconditions;
import org.fundacionjala.sevenwonders.core.City;
import org.fundacionjala.sevenwonders.core.calculator.CalculatorType;
import org.fundacionjala.sevenwonders.core.City;

/**
 * @author Diego Fiengo
 */
public class VictoryPointEffect implements Effect {
    private int quantity;
    private CalculatorType type;

    public VictoryPointEffect(int quantity, CalculatorType type) {
        Preconditions.checkArgument(quantity > 0, "The victory points quantity is %s, but expected no-negative",
                quantity);
        this.quantity = quantity;
        this.type = type;
    }

    /**
     * Increments the victory points of the city with it's respective quantity and type.
     */
    @Override
    public void activate(City city) {
        city.getStoragePoint().addPointsType(type, quantity);
    }

    public int getQuantity() {
        return quantity;
    }

    public CalculatorType getType() {
        return type;
    }
}
