package org.fundacionjala.sevenwonders.core.effect;

import org.fundacionjala.sevenwonders.core.City;
import org.fundacionjala.sevenwonders.core.ResourceType;

/**
 * Created by diego on 11/10/2016.
 */
public class ResourceEffect implements Effect {
    private  ResourceType resourceType;
    private  int quantity;

    public ResourceEffect(ResourceType resourceType, int quantity) {
        this.resourceType = resourceType;
        this.quantity = quantity;
    }

    @Override
    public void activate(City city) {

    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public int getQuantity() {
        return quantity;
    }
}
