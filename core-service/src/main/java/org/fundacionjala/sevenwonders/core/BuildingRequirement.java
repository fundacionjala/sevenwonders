/*
 *  * Copyright (c) Fundacion Jala. All rights reserved.
 *  * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 *
 */

package org.fundacionjala.sevenwonders.core;

import com.google.common.base.Preconditions;

import java.util.List;
import java.util.Map;

/**
 * @author Gonzalo Calle
 */
public class BuildingRequirement implements Requirement {

    List<Building> buildingRequirements;

    public BuildingRequirement(List<Building> buildingRequirements) {
        this.buildingRequirements = buildingRequirements;
    }

    @Override
    public Map<ResourceType, Integer> getRequirements() {
        return null;
    }

    /**
     * Compares the requirements with the Buildings of the City
     * and checks if the buildings contains all requirements
     * If storage contains resourceType and his quantity is
     *
     * @param city
     * @return boolean value
     */
    @Override
    public boolean validate(City city) {
        Preconditions.checkNotNull(city, "City is null");

        return city.getBuildings().containsAll(buildingRequirements);
    }
}
