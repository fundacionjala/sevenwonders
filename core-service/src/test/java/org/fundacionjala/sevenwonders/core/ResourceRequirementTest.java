/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */

package org.fundacionjala.sevenwonders.core;

import org.junit.Before;
import org.junit.Test;

import java.util.EnumMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Vania Catorceno
 */
public class ResourceRequirementTest {
    private Map<ResourceType, Integer> requirements;
    private Requirement requirement;
    private City city;
    private Storage storage;

    @Before
    public void setUp() {
        requirements = new EnumMap<>(ResourceType.class);
        storage = mock(Storage.class);
        city = new City(mock(Wonder.class), mock(StoragePoint.class), storage);
    }

    @Test
    public void testValidateResourcesThatCompliesWithRequirements() {
        requirements.put(ResourceType.BRICK, 2);
        requirements.put(ResourceType.GLASS, 1);

        requirement = new ResourceRequirement(requirements);
        when(storage.getResourceQuantity(anyObject())).thenReturn(2);

        assertTrue(requirement.validate(city));
    }

    @Test
    public void testValidateResourcesButNotEnoughToMeetRequirements() {
        requirements.put(ResourceType.BRICK, 2);
        requirements.put(ResourceType.GLASS, 1);

        requirement = new ResourceRequirement(requirements);
        when(storage.getResourceQuantity(anyObject())).thenReturn(1);

        assertFalse(requirement.validate(city));
    }

    @Test
    public void testValidateTheResourcesButDoNotEnoughQuantityToMeetTheRequirement() {
        requirements.put(ResourceType.PAPYRUS, 3);

        requirement = new ResourceRequirement(requirements);
        when(storage.getResourceQuantity(anyObject())).thenReturn(1);

        assertFalse(requirement.validate(city));
    }

    @Test(expected = NullPointerException.class)
    public void testTryValidateButNotThereAreRequirements() {
        Map<ResourceType, Integer> requirementNull = null;
        requirement = new ResourceRequirement(requirementNull);
        when(storage.getResourceQuantity(anyObject())).thenReturn(1);

        assertFalse(requirement.validate(city));
    }
}
