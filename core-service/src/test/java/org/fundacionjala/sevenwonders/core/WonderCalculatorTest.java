/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */
package org.fundacionjala.sevenwonders.core;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.fundacionjala.sevenwonders.core.calculator.CalculatorType;
import org.fundacionjala.sevenwonders.core.calculator.CardManager;
import org.fundacionjala.sevenwonders.core.effect.Effect;
import org.fundacionjala.sevenwonders.core.effect.VictoryPointEffect;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

/**
 * @author alexander castro
 */
public class WonderCalculatorTest {

    private CardManager cardManager;
    private City city;
    private StoragePoint storagePoints;
    private List<Effect> effects;
    private List<Requirement> requirements;
    private Stage stage;
    private List<Card> stages;

    @Before
    public void setUp() {
        Effect effect = new VictoryPointEffect(4, CalculatorType.WONDER);
        effects = new ArrayList<>();
        effects.add(effect);
        storagePoints = new StoragePoint();
        city = new City(new Wonder(new ArrayList<>()), storagePoints, new Storage());
        cardManager = new CardManager(city);
        requirements = new ArrayList<>();
        stage = new Stage(requirements, effects);
        stage.setBuildState(true);
        stages = new ArrayList();
    }

    @Test
    public void testCalculatorIfOnlyOneStage() {
        stages.add(stage);

        cardManager.calculateCards(stages, CalculatorType.WONDER);

        int expectedPoints = 4;
        assertEquals(expectedPoints,
                city.getStoragePoint().getPoint(CalculatorType.WONDER));
    }

    @Test
    public void testCalculatorIfHave4Stages() {
        stages.add(stage);
        stages.add(stage);
        stages.add(stage);
        stages.add(stage);

        cardManager.calculateCards(stages, CalculatorType.WONDER);

        int expectedPoints = 16;
        assertEquals(expectedPoints,
                city.getStoragePoint().getPoint(CalculatorType.WONDER));
    }

    @Test
    public void testCalculatorIfObtainStagesFromWonder() {
        List<Stage> stages = new ArrayList();
        stages.add(stage);
        stages.add(stage);
        stages.add(stage);

        Wonder wonder = new Wonder(stages);

        cardManager.calculateCards(new ArrayList<>(wonder.getBuildingsStages()),
                CalculatorType.WONDER);

        assertEquals(12, city.getStoragePoint().getPoint(CalculatorType.WONDER));
    }

    @Test
    public void testIfTheStageNotHaveVictoryPoint() {
        Effect effect = mock(Effect.class);
        List<Effect> listEffects = new ArrayList<>();
        listEffects.add(effect);
        Stage stage1 = new Stage(requirements, listEffects);
        stage1.setBuildState(true);
        List<Card> stages = new ArrayList();
        stages.add(stage1);
        
        cardManager.calculateCards(stages, CalculatorType.WONDER);
        
        assertEquals(0, city.getStoragePoint().getPoint(CalculatorType.WONDER));
    }

    @Test(expected = NullPointerException.class)
    public void testThrowsExceptionWhenSendNullWorld() {
        cardManager.calculateCards(null, CalculatorType.WONDER);
    }
}
