/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 *
 */

package org.fundacionjala.sevenwonders.core.builder;

import org.fundacionjala.sevenwonders.core.Building;
import org.fundacionjala.sevenwonders.core.BuildingType;
import org.fundacionjala.sevenwonders.core.Requirement;
import org.fundacionjala.sevenwonders.core.effect.Effect;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Diego Fiengo
 */
public class BuildingBuilder {
    private List<Requirement> requirements;
    private List<Effect> effects;
    private int playersNeeded;
    private int age;
    private String name;
    private BuildingType buildingType;

    public BuildingBuilder() {
        requirements = new ArrayList<>();
    }

    public BuildingBuilder setRequirements(List<Requirement> requirements) {
        this.requirements = requirements;
        return this;
    }

    public BuildingBuilder setEffects(List<Effect> effects) {
        this.effects = effects;
        return this;
    }

    public BuildingBuilder setPlayersNeeded(int playersNeeded) {
        this.playersNeeded = playersNeeded;
        return this;
    }

    public BuildingBuilder setAge(int age) {
        this.age = age;
        return this;
    }

    public BuildingBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public BuildingBuilder setBuildingType(BuildingType buildingType) {
        this.buildingType = buildingType;
        return this;
    }

    public Building createBuilding() {
        return new Building(requirements, effects, playersNeeded, age, name, buildingType);
    }
}