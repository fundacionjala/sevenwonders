/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */
package org.fundacionjala.sevenwonders.core;

import org.fundacionjala.sevenwonders.core.effect.Effect;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

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

        List<Effect> effects = mock(List.class);
        List<StageType>stages = mock(List.class);
        Side sideA = new Side(stages, effects);

        wonder  = new Wonder("Babylon");
        wonder.setSide("a", sideA);
        City city = new City(wonder, storagePoint, storage);
        city.setSide("a");
        String selectedSide = city.getSelectedSide();
        assertEquals(wonder.getSide("a"), city.getWonder().getSide(selectedSide));
    }

    @Test(expected = NullPointerException.class)
    public void testIfSadeIsNull(){
        List<Effect> effects = mock(List.class);
        List<StageType> stages =mock(List.class);
        Side sideA = new Side(stages, effects);

        wonder.setSide("a", sideA);
        wonder.setSide("b", null);
    }

}
