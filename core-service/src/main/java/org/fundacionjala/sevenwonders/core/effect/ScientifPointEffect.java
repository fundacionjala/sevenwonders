/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */
package org.fundacionjala.sevenwonders.core.effect;

import com.google.common.base.Preconditions;
import org.fundacionjala.sevenwonders.core.City;
import org.fundacionjala.sevenwonders.core.Symbol;
import org.fundacionjala.sevenwonders.core.calculator.CalculatorType;

/**
 * @Author Luis Gumucio.
 */
public class ScientifPointEffect implements Effect {
    private CalculatorType type;
    private Symbol symbol;

    public ScientifPointEffect(Symbol symbol, CalculatorType type) {
        Preconditions.checkNotNull(symbol == null, "The symbol list is null",
                symbol);
        this.symbol = symbol;
        this.type = type;
    }
    
    @Override
    public void activate(City city) {

    }

    public Symbol getSymbol() {
        return symbol;
    }
}
