/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */
package org.fundacionjala.sevenwonders.core.rest;

import org.fundacionjala.sevenwonders.core.Building;
import org.fundacionjala.sevenwonders.core.BuildingType;

/**
 * Builds a Building model for {@link CityModel}.
 *
 * @author Gonzalo Calle
 */
public class BuildingModel {
    private String name;
    private BuildingType buildingType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BuildingType getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(BuildingType buildingType) {
        this.buildingType = buildingType;
    }

}
