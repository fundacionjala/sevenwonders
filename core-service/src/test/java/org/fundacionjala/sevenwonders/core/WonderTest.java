/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */
package org.fundacionjala.sevenwonders.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fundacionjala.sevenwonders.core.calculator.CalculatorType;
import org.fundacionjala.sevenwonders.core.effect.Effect;
import org.fundacionjala.sevenwonders.core.effect.VictoryPointEffect;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockingDetails;

/**
 *
 * @author alexander castro
 */
public class WonderTest {
    
    private Wonder wonder;

    @Test
    public void testGetWonderSide(){
        StoragePoint storagePoint = mock(StoragePoint.class);
        Storage storage = mock(Storage.class);

        Effect effect = mock(Effect.class);
        List<StageType>stages = mock(List.class);
        Side sideA = new Side(stages, effect);

        wonder  = new Wonder("Babylon");
        wonder.setSide("a", sideA);
        City city = new City(wonder, storagePoint, storage);
        city.setSide("a");
        String selectedSide = city.getSelectedSide();
        assertEquals(wonder.getSide("a"), city.getWonder().getSide(selectedSide));
    }

    @Test(expected = NullPointerException.class)
    public void testIfSadeIsNull(){
        Effect effect = mock(Effect.class);
        List<StageType> stages =mock(List.class);
        Side sideA = new Side(stages, effect);
        
        wonder.setSide("a", sideA);
        wonder.setSide("b", null);
    }

}
