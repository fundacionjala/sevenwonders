/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */
package org.fundacionjala.sevenwonders.core;

import com.google.common.collect.Lists;
import org.fundacionjala.sevenwonders.core.builder.BuildingBuilder;
import org.fundacionjala.sevenwonders.core.calculator.CalculatorType;
import org.fundacionjala.sevenwonders.core.calculator.CardManager;
import org.fundacionjala.sevenwonders.core.effect.Effect;
import org.fundacionjala.sevenwonders.core.effect.ScientifPointEffect;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * @author Luis Gumucio.
 */
public class CardScientificCalculatorTest {

    private static final String BUILDING_NAME = "Temple";
    private Building buildingOne;
    private Building buildingTwo;
    private Building buildingThree;
    private Building buildingFour;

    private Effect effectTable;
    private Effect effectGear;
    private Effect effectCompass;
    private Requirement requirement;
    private City city;
    private List<Card> listBuilding;
    private CardManager calculator;


    @Before
    public void setUp() {
        listBuilding = new ArrayList<>();
        Wonder wonder = Mockito.mock(Wonder.class);
        Storage storage = Mockito.mock(Storage.class);
        requirement = Mockito.mock(Requirement.class);
        StoragePoint storagePoint = new StoragePoint();
        city = new City(wonder, storagePoint, storage);
        calculator = new CardManager(city);
        effectTable = new ScientifPointEffect(Symbol.TABLE, CalculatorType.SCIENTIFIC);
        effectGear = new ScientifPointEffect(Symbol.GEAR, CalculatorType.SCIENTIFIC);
        effectCompass = new ScientifPointEffect(Symbol.COMPASS, CalculatorType.SCIENTIFIC);
        buildingOne = new BuildingBuilder()
                .setEffects(Lists.newArrayList(effectTable))
                .setRequirements(Lists.newArrayList(requirement))
                .setBuildingType(BuildingType.CIVIC)
                .setAge(1)
                .setName(BUILDING_NAME)
                .setPlayersNeeded(1)
                .createBuilding();
        buildingTwo = new BuildingBuilder()
                .setEffects(Lists.newArrayList(effectGear))
                .setRequirements(Lists.newArrayList(requirement))
                .setBuildingType(BuildingType.CIVIC)
                .setAge(1)
                .setName(BUILDING_NAME)
                .setPlayersNeeded(1)
                .createBuilding();
        buildingThree = new BuildingBuilder()
                .setEffects(Lists.newArrayList(effectCompass))
                .setRequirements(Lists.newArrayList(requirement))
                .setBuildingType(BuildingType.CIVIC)
                .setAge(1)
                .setName(BUILDING_NAME)
                .setPlayersNeeded(1)
                .createBuilding();
        buildingFour = new BuildingBuilder()
                .setEffects(Lists.newArrayList(effectTable))
                .setRequirements(Lists.newArrayList(requirement))
                .setBuildingType(BuildingType.CIVIC)
                .setAge(1)
                .setName(BUILDING_NAME)
                .setPlayersNeeded(1)
                .createBuilding();
    }

    @Test
    public void testCalculateAScientificCard() {
        int expected = 4;

        listBuilding.add(buildingOne);
        listBuilding.add(buildingFour);
        calculator.calculateCards(listBuilding, CalculatorType.SCIENTIFIC);
        assertEquals(expected, city.getStoragePoint().getPoint(CalculatorType.SCIENTIFIC));
    }

    @Test
    public void testCalculateByDifferentScientific() {
        int expected = 2;

        listBuilding.add(buildingOne);
        listBuilding.add(buildingTwo);
        calculator.calculateCards(listBuilding, CalculatorType.SCIENTIFIC);
        assertEquals(expected, city.getStoragePoint().getPoint(CalculatorType.SCIENTIFIC));
    }

    @Test
    public void testCalculateAdditionalPointsScientific() {
        int expected = 10;

        listBuilding.add(buildingOne);
        listBuilding.add(buildingTwo);
        listBuilding.add(buildingThree);
        calculator.calculateCards(listBuilding, CalculatorType.SCIENTIFIC);
        assertEquals(expected, city.getStoragePoint().getPoint(CalculatorType.SCIENTIFIC));
    }

    @Test
    public void testEmptyList() {
        int expected = 0;

        calculator.calculateCards(listBuilding, CalculatorType.SCIENTIFIC);

        assertEquals(expected, city.getStoragePoint().getPoint(CalculatorType.CIVIC));
    }

    @Test(expected = NullPointerException.class)
    public void testNullCardList() {
        calculator.calculateCards(null, CalculatorType.SCIENTIFIC);

        fail();
    }

    @Test(expected = AssertionError.class)
    public void testDifferentEffectCard() {

        listBuilding.add(buildingOne);
        listBuilding.add(buildingTwo);
        calculator.calculateCards(listBuilding, CalculatorType.CIVIC);
        fail();
    }
}
