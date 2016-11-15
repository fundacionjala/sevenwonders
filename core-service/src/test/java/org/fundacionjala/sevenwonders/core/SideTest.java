/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */

package org.fundacionjala.sevenwonders.core;

import org.fundacionjala.sevenwonders.core.calculator.CalculatorType;
import org.fundacionjala.sevenwonders.core.effect.Effect;
import org.fundacionjala.sevenwonders.core.effect.VictoryPointEffect;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

/**
 * Created Luis Gumucio.
 */
public class SideTest {

    @Test
    public void testGetBuildingsStagesOfSide() {

        Effect effect = new VictoryPointEffect(4, CalculatorType.WONDER);
        List<Effect> effects = new ArrayList<>();
        effects.add(effect);
        Stage stage1 = new Stage(new ArrayList<Requirement>(), effects);
        stage1.setBuildState(true);
        Stage stage2 = new Stage(new ArrayList<Requirement>(), effects);
        stage2.setBuildState(false);
        Stage stage3 = new Stage(new ArrayList<Requirement>(), effects);
        stage3.setBuildState(false);
        Stage stage4 = new Stage(new ArrayList<Requirement>(), effects);
        stage4.setBuildState(true);
        List<Stage> stagesSideA = new ArrayList();
        stagesSideA.add(stage1);
        stagesSideA.add(stage4);

        Side sideA = new Side("A", effect, stagesSideA);

        List<Stage> result = sideA.getBuildingsStages();

        assertEquals(2, result.size());
        assertEquals(stage1, result.get(0));
        assertEquals(stage4, result.get(1));
    }

    @Test(expected = NullPointerException.class)
    public void testIfSadeListStageIsNull() {
        Effect effect = mock(Effect.class);
        Side sideA = new Side("A", effect, null);
        List<Stage> result = sideA.getBuildingsStages();
    }
}
