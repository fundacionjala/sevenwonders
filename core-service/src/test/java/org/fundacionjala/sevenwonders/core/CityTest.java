/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */

package org.fundacionjala.sevenwonders.core;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;



/**
 * @author Vania Catorceno
 */
public class CityTest {
    private List<Requirement> requirements;
    private Map<ResourceType, Integer> resourcesRequirements;
    private Map<ResourceType, Integer> resourcesRequirementsOther;
    private Stage stage;
    private City city;
    private Storage storage;

    @Before
    public void setUp() {

        requirements = new ArrayList<>();

        resourcesRequirements = new EnumMap<>(ResourceType.class);
        resourcesRequirements.put(ResourceType.BRICK, 2);
        resourcesRequirements.put(ResourceType.GLASS, 1);

        resourcesRequirementsOther = new EnumMap<>(ResourceType.class);
        resourcesRequirementsOther.put(ResourceType.STONE, 2);
        resourcesRequirementsOther.put(ResourceType.WOOD, 1);

        requirements.add(new ResourceRequirement(resourcesRequirements));
        requirements.add(new ResourceRequirement(resourcesRequirementsOther));

        stage = new Stage(requirements, mock(List.class));
        storage = mock(Storage.class);
        city = new City(mock(Wonder.class), mock(StoragePoint.class), storage);
    }

    @Test
    public void TestCreateCity() {
        Wonder wonder = mock(Wonder.class);
        StoragePoint storagePoint = mock(StoragePoint.class);
        Storage storage = mock(Storage.class);

        City city = new City(wonder, storagePoint, storage);

        assertEquals(wonder, city.getWonder());
        assertEquals(storagePoint, city.getStoragePoint());
        assertEquals(storage, city.getStorage());
    }

    @Test
    public void testBuildStageReturnTrueIfStateChangeToTrue() {
        when(storage.getResourceQuantity(anyObject())).thenReturn(2);
        assertTrue(city.buildStage(stage));
    }

    @Test
    public void testBuildStageWhenTheTwoRequirementsHisValidateIsTrue() {
        when(storage.getResourceQuantity(anyObject())).thenReturn(2);
        assertTrue(city.buildStage(stage));
    }

    @Test
    public void testBuildStageButNotCompliesRequirements() {
        when(storage.getResourceQuantity(anyObject())).thenReturn(1);
        assertFalse(city.buildStage(stage));
    }

    @Test
    public void testBuildStageButOneRequirementHisValidateIFalseThenReturnFalse() {
        when(storage.getResourceQuantity(anyObject())).thenReturn(1);
        assertFalse(city.buildStage(stage));
    }

    @Test(expected = NullPointerException.class)
    public void testBuildStageButTheListRequirementsIsNull() {
        List<Requirement> requirementsNull = null;
        stage = new Stage(requirementsNull, mock(List.class));

        when(storage.getResourceQuantity(anyObject())).thenReturn(2);
        assertFalse(city.buildStage(stage));
    }

    @Test(expected = NullPointerException.class)
    public void testBuildStageButTheStageIsNull() {
        stage = null;
        when(storage.getResourceQuantity(anyObject())).thenReturn(2);
        assertNull(stage);
        assertFalse(city.buildStage(stage));
    }
}
