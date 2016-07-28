/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 *
 */

package org.fundacionjala.sevenwonders.core;

import org.fundacionjala.sevenwonders.core.calculator.CalculatorType;
import org.fundacionjala.sevenwonders.core.effect.Effect;
import org.fundacionjala.sevenwonders.core.effect.VictoryPointEffect;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.fail;

/**
 * Used to test all the functionality of the {@link VictoryPointEffect}.
 *
 * @author Diego Fiengo
 */
public class VictoryPointEffectTest {
    private City city;
    private Wonder wonder;
    private Storage storage;
    private VictoryPointEffect effect;
    private Effect temp;

    @Before
    public void setUp() {
        effect = new VictoryPointEffect(1, CalculatorType.CIVIC);
        storage = Mockito.mock(Storage.class);
        wonder = Mockito.mock(Wonder.class);
        city = new City(wonder, new StoragePoint(), storage);
    }

    @Test
    public void testActivateEffect() {
        int expected = effect.getQuantity();

        effect.activate(city);

        Assert.assertEquals(expected, city.getStoragePoint().getPoint(effect.getType()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateAnEffectWithANegativeQuantityOfVictoryPoints() {
        temp = new VictoryPointEffect(-4, CalculatorType.CIVIC);

        fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateAnEffectWithZeroVictoryPoints() {
        temp = new VictoryPointEffect(0, CalculatorType.CIVIC);

        fail();
    }
}
