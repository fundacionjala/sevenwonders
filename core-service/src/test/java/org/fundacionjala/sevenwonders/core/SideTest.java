/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */

package org.fundacionjala.sevenwonders.core;

import org.fundacionjala.sevenwonders.core.effect.Effect;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

/**
 * Created Luis Gumucio.
 */
public class SideTest {
    private List<Effect> effects;
    private List<Requirement> requirements;
    private List<StageType> stageTypes;
    private StageType stageType;
    private Side side;

    @Before
    public void setUp() {
        effects = mock(List.class);
        requirements = mock(List.class);
        stageType = new StageType(requirements, effects);
        stageTypes = new ArrayList<>();
    }

    @Test
    public void testContainsStageType(){
        stageTypes.add(stageType);
        side = new Side(stageTypes, effects);

        assertEquals(1, side.getStages().size());
    }

    @Test(expected = NullPointerException.class)
    public void testIfStagesIsNull(){
        stageTypes = null;
        side = new Side(stageTypes, effects);

        side.getStages();
    }

    @Test(expected = NullPointerException.class)
    public void testIfEffectIsNull(){
        effects = null;
        side = new Side(stageTypes, effects);

        side.getStages();
    }

}
