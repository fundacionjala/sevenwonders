/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */

package org.fundacionjala.sevenwonders.core;

import com.google.common.base.Preconditions;

import java.util.Map;

/**
 * Author Vania Catorceno.
 */
public class ResourceRequirement implements Requirement {
    private Map<ResourceType, Integer> resourceRequirements;

    public ResourceRequirement(Map<ResourceType, Integer> resourceRequirements) {
        this.resourceRequirements = resourceRequirements;
    }

    public Map<ResourceType, Integer> getRequirements() {
        return resourceRequirements;
    }

    /**
     * This method compare requirements with resources of storage
     * If storage contains resourceType and his quantity is
     * @param city
     * @return boolean value 
     */
    @Override
    public boolean validate(City city) {
        Preconditions.checkNotNull(city, "The city is null!");
        Preconditions.checkNotNull(city.getStorage(), "The storage is null!");
        return resourceRequirements.keySet().stream()
                                            .filter(resourceType -> city.getStorage().getResourceQuantity(resourceType)
                                              >= resourceRequirements.get(resourceType))
                                            .count() == resourceRequirements.size();
    }
}