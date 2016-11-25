package org.fundacionjala.sevenwonders.core.effect;

import org.fundacionjala.sevenwonders.core.City;
import org.fundacionjala.sevenwonders.core.ResourceType;

import java.util.List;

/**
 * Created by diego on 11/15/2016.
 */
public class ChooseOneEffect implements Effect {

    private List<ResourceType> resourceTypes;

    public ChooseOneEffect(List<ResourceType> resourceTypes) {
        this.resourceTypes = resourceTypes;
    }

    @Override
    public void activate(City city) {

    }
}
