package org.fundacionjala.sevenwonders.core;

import com.google.common.base.Preconditions;
import org.fundacionjala.sevenwonders.exception.NotEnoughResourceExeption;

import java.util.EnumMap;
import java.util.Map;

/**
 * This class is responsible for controlling all types of resources.
 * @author by Roberto Carlos on 5/19/2016.
 */
public class Storage {
    private Map<ResourceType, Integer> resources;
    public final static int DEFAULT_STOCK_NUMBER = 0;

    /**
     * Constructs the object will be stored warehouse where the resource types.
     */
    public Storage() {
        resources = new EnumMap<>(ResourceType.class);
    }

    /**
     * Add a new resource type in storage.
     *
     * @param resourceType
     * @param value
     */
    public void addResource(ResourceType resourceType, int value) {
        Preconditions.checkNotNull(resourceType, "The resource type in null");
        Preconditions.checkArgument(value > 0, "The quantity is negative");
        if (!resources.containsKey(resourceType)) {
            resources.put(resourceType, DEFAULT_STOCK_NUMBER);
        }
        int storedResource = resources.get(resourceType);
        int valueAdd = storedResource + value;
        resources.replace(resourceType, valueAdd);
    }

    /**
     * Get quantity of resource type.
     *
     * @param resourceType
     */
    public int getResourceQuantity(ResourceType resourceType) {
        Preconditions.checkNotNull(resourceType, "The resource type in null");
        if (!resources.containsKey(resourceType)) {
            resources.put(resourceType, DEFAULT_STOCK_NUMBER);
        }
        return resources.get(resourceType);
    }

    /**
     * Removes a resource type of storage.
     *
     * @param quantity
     */
    public void consumeResource(ResourceType resourceType, int quantity) {
        Preconditions.checkNotNull(resourceType, "The resource type in null");
        Preconditions.checkArgument(quantity > 0, "The quantity is negative");
        if (!resources.containsKey(resourceType)) {
            resources.put(resourceType, DEFAULT_STOCK_NUMBER);
        }
        int currentValue = resources.get(resourceType);
        if (quantity > currentValue) {
            throw new NotEnoughResourceExeption();
        }
        resources.replace(resourceType, currentValue - quantity);
    }
}
