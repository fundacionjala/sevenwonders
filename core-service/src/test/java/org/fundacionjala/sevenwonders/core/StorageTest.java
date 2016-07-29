package org.fundacionjala.sevenwonders.core;

import org.fundacionjala.sevenwonders.exception.NotEnoughResourceExeption;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by RobertoCarlos on 5/20/2016.
 */
public class StorageTest {

    private Storage storage;
    private int expectedValue;
    private int currentValue;

    @Before
    public void setUp() {
        storage = new Storage();
    }

    @Test(expected = NullPointerException.class)
    public void testAddNullResource() {
        storage.addResource(null, 0);
        fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNullQuantity() {
        storage.addResource(ResourceType.WOOD, -5);
        fail();
    }

    @Test
    public void testAddResource() {
        expectedValue = 1;
        storage.addResource(ResourceType.ORE, 1);
        currentValue = storage.getResourceQuantity(ResourceType.ORE);
        assertEquals(expectedValue, currentValue);
    }

    @Test
    public void testConsumeResource() {
        expectedValue = 3;
        storage.addResource(ResourceType.WOOD, 3);
        currentValue = storage.getResourceQuantity(ResourceType.WOOD);
        assertEquals(expectedValue, currentValue);
        storage.consumeResource(ResourceType.WOOD, 2);
        expectedValue = 1;
        currentValue = storage.getResourceQuantity(ResourceType.WOOD);
        assertEquals(expectedValue, currentValue);
    }

    @Test(expected = NotEnoughResourceExeption.class)
    public void testConsumeMoreResource() {
        expectedValue = 4;
        storage.addResource(ResourceType.WOOD, 4);
        currentValue = storage.getResourceQuantity(ResourceType.WOOD);
        assertEquals(expectedValue, currentValue);
        storage.consumeResource(ResourceType.WOOD, 2);
        expectedValue = 2;
        currentValue = storage.getResourceQuantity(ResourceType.WOOD);
        assertEquals(expectedValue, currentValue);
        storage.consumeResource(ResourceType.WOOD, 3);
        fail();
    }

    @Test(expected = NotEnoughResourceExeption.class)
    public void testConsumeResourceInvalidate() {
        storage.addResource(ResourceType.BRICK, 2);
        storage.consumeResource(ResourceType.BRICK, 5);
        fail();
    }

    @Test
    public void testAddQuantity() {
        expectedValue = 1;
        storage.addResource(ResourceType.STONE, 1);
        currentValue = storage.getResourceQuantity(ResourceType.STONE);
        assertEquals(expectedValue, currentValue);
        storage.addResource(ResourceType.STONE, 3);
        expectedValue = 4;
        currentValue = storage.getResourceQuantity(ResourceType.STONE);
        assertEquals(expectedValue, currentValue);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddQuantityInvalidate() {
        storage.addResource(ResourceType.STONE, 1);
        storage.addResource(ResourceType.STONE, -5);
        fail();
    }

    @Test
    public void testGetQuantityOfResourceTypeNotStored() {
        expectedValue = 0;
        storage.getResourceQuantity(ResourceType.WOOD);
        currentValue = storage.getResourceQuantity(ResourceType.WOOD);
        assertEquals(expectedValue, currentValue);
    }
}