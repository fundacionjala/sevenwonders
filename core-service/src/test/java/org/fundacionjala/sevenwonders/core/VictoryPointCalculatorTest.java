/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 *
 */

package org.fundacionjala.sevenwonders.core;

import com.google.common.collect.Lists;
import org.fundacionjala.sevenwonders.core.builder.BuildingBuilder;
import org.fundacionjala.sevenwonders.core.calculator.CalculatorType;
import org.fundacionjala.sevenwonders.core.calculator.CardManager;
import org.fundacionjala.sevenwonders.core.calculator.VictoryPointCalculator;
import org.fundacionjala.sevenwonders.core.effect.Effect;
import org.fundacionjala.sevenwonders.core.effect.VictoryPointEffect;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Used to test all the functionality of the {@link VictoryPointCalculator}.
 *
 * @author Diego Fiengo
 */
public class VictoryPointCalculatorTest {

    private static final String BUILDING_NAME = "Temple";
    private Effect effectOne;
    private Effect effectTwo;
    private Effect effectThree;
    private Effect effectFour;
    private Effect effectFive;
    private List<Card> cardList;
    private Building buildingOne;
    private Building buildingTwo;
    private Building buildingThree;
    private Building buildingFour;
    private Building buildingFive;
    private CardManager cardManager;
    private City city;

    @Before
    public void setUp() {
        cardList = new ArrayList<>();
        Wonder wonder = Mockito.mock(Wonder.class);
        Storage storage = Mockito.mock(Storage.class);
        city = new City(wonder, new StoragePoint(), storage);
        cardManager = new CardManager(city);
        effectOne = new VictoryPointEffect(1, CalculatorType.CIVIC);
        effectTwo = new VictoryPointEffect(2, CalculatorType.CIVIC);
        effectThree = new VictoryPointEffect(3, CalculatorType.CIVIC);
        effectFour = new VictoryPointEffect(4, CalculatorType.CIVIC);
        effectFive = new VictoryPointEffect(5, CalculatorType.CIVIC);
        buildingOne = new BuildingBuilder()
                .setEffects(Lists.newArrayList(effectOne))
                .setBuildingType(BuildingType.CIVIC)
                .setAge(1)
                .setName(BUILDING_NAME)
                .setPlayersNeeded(1)
                .createBuilding();
        buildingTwo = new BuildingBuilder()
                .setEffects(Lists.newArrayList(effectTwo))
                .setBuildingType(BuildingType.CIVIC)
                .setAge(1)
                .setName(BUILDING_NAME)
                .setPlayersNeeded(1)
                .createBuilding();
        buildingThree = new BuildingBuilder()
                .setEffects(Lists.newArrayList(effectThree))
                .setBuildingType(BuildingType.CIVIC)
                .setAge(1)
                .setName(BUILDING_NAME)
                .setPlayersNeeded(1)
                .createBuilding();
        buildingFour = new BuildingBuilder()
                .setEffects(Lists.newArrayList(effectFour))
                .setBuildingType(BuildingType.CIVIC)
                .setAge(1)
                .setName(BUILDING_NAME)
                .setPlayersNeeded(1)
                .createBuilding();
        buildingFive = new BuildingBuilder()
                .setEffects(Lists.newArrayList(effectFive))
                .setBuildingType(BuildingType.CIVIC)
                .setAge(1)
                .setName(BUILDING_NAME)
                .setPlayersNeeded(1)
                .createBuilding();
    }

    @Test
    public void testCalculateACivicBuilding() {
        int expected = 1;
        cardList.add(buildingOne);

        cardManager.calculateCards(cardList, CalculatorType.CIVIC);

        assertEquals(expected, city.getStoragePoint().getPoint(CalculatorType.CIVIC));
    }

    @Test
    public void testCalculateFiveCivicBuilding() {
        int expected = 15;
        cardList.add(buildingOne);
        cardList.add(buildingTwo);
        cardList.add(buildingThree);
        cardList.add(buildingFour);
        cardList.add(buildingFive);

        cardManager.calculateCards(cardList, CalculatorType.CIVIC);

        assertEquals(expected, city.getStoragePoint().getPoint(CalculatorType.CIVIC));
    }

    @Test
    public void testEmptyList() {
        int expected = 0;

        cardManager.calculateCards(cardList, CalculatorType.CIVIC);

        assertEquals(expected, city.getStoragePoint().getPoint(CalculatorType.CIVIC));
    }

    @Test(expected = NullPointerException.class)
    public void testNullCardList() {
        cardManager.calculateCards(null, CalculatorType.CIVIC);

        fail();
    }

    @Test
    public void testGivenAListCardsWithoutVictoryPointEffect() {
        int expected = 0;
        Effect effect = Mockito.mock(Effect.class);
        Building building = new BuildingBuilder()
                .setEffects(Lists.newArrayList(effect))
                .setBuildingType(BuildingType.CIVIC)
                .setAge(1)
                .setName(BUILDING_NAME)
                .setPlayersNeeded(1)
                .createBuilding();

        cardList.add(building);

        cardManager.calculateCards(cardList, CalculatorType.CIVIC);

        assertEquals(expected, city.getStoragePoint().getPoint(CalculatorType.CIVIC));
    }
}
