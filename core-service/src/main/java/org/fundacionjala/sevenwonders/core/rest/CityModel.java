/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */
package org.fundacionjala.sevenwonders.core.rest;

import com.google.common.base.Preconditions;
import org.fundacionjala.sevenwonders.core.Building;
import org.fundacionjala.sevenwonders.core.BuildingType;

import java.util.Collections;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Generates a City model according to the name, {@link WonderModel} and {@link StoragePointModel}.
 *
 * @author Jhonatan Mamani
 */
public class CityModel {

    private String name;
    private WonderModel wonder;
    private StoragePointModel storagePoint;
    private List<BuildingModel> buildings;

    public CityModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StoragePointModel getStoragePoint() {
        return storagePoint;
    }

    public void setStoragePoint(StoragePointModel storagePoint) {
        this.storagePoint = storagePoint;
    }

    public WonderModel getWonder() {
        return wonder;
    }

    public void setWonder(WonderModel wonder) {
        this.wonder = wonder;
    }

    public EnumMap<BuildingType, BuildingModel> getBuildingsAsEnumMap() {
        EnumMap<BuildingType, BuildingModel> map = new EnumMap<BuildingType, BuildingModel>(BuildingType.class);
        for (BuildingModel current : buildings) {
            map.put(current.getBuildingType(), current);
        }
        return map;
    }

    public List<BuildingModel> getBuildings() {
        return buildings;
    }

    public void setBuildings(List<BuildingModel> buildings) {
        this.buildings = buildings;
    }
}
