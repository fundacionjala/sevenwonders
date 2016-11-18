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

        List<Effect>effects = mock(List.class);
        List<Stage>stages = mock(List.class);
        Side sideA = new Side(stages, effects);
        Map<String, Side>sideMap  = new HashMap<String, Side>();
        sideMap.put("a", sideA);

        wonder  = new Wonder("Babylon", sideMap);
        City city = new City(wonder, storagePoint, storage);

        assertEquals(wonder.getSide("a"), city.getWonder().getSide("a"));
    }
//
//    @Test
//    public void testGetSidesWonder() {
//
//        Effect effect = new VictoryPointEffect(4, CalculatorType.WONDER);
//        List<Effect> effects = new ArrayList<>();
//        effects.add(effect);
//        Stage stage1 = new Stage(new ArrayList<Requirement>(), effects);
//        stage1.setBuildState(true);
//        Stage stage2 = new Stage(new ArrayList<Requirement>(), effects);
//        stage2.setBuildState(false);
//        Stage stage3 = new Stage(new ArrayList<Requirement>(), effects);
//        stage3.setBuildState(false);
//        Stage stage4 = new Stage(new ArrayList<Requirement>(), effects);
//        stage4.setBuildState(true);
//        List<Stage> stagesSideA = new ArrayList();
//        stagesSideA.add(stage1);
//        stagesSideA.add(stage2);
//        List<Stage> stagesSideB = new ArrayList<>();
//
//        Side sideA = new Side("A", effect, stagesSideA);
//        Side sideB = new Side("B", effect, stagesSideB);
//        wonder = new Wonder("Babylon", sideA, sideB);
//
//        Side actualA = new Side("A", effect, stagesSideA);
//
//        assertEquals(sideA.getNameSide(), actualA.getNameSide());
//        assertNotEquals(sideB.getNameSide(), actualA.getNameSide());
//    }
//
//    @Test(expected = NullPointerException.class)
//    public void testIfSadesIsNull() {
//        Effect effect = mock(Effect.class);
//        List<Stage> stages =mock(List.class);
//        Side sideB = new Side("B", effect, stages);
//        wonder = new Wonder("A",null, sideB);
//
//        wonder.getSideA();
//    }
}
