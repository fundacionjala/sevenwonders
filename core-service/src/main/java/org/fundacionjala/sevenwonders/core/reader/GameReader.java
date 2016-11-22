/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */

package org.fundacionjala.sevenwonders.core.reader;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.fundacionjala.sevenwonders.core.*;
import org.fundacionjala.sevenwonders.core.builder.BuildingBuilder;
import org.fundacionjala.sevenwonders.core.effect.Effect;
import org.fundacionjala.sevenwonders.core.effect.EffectType;
import org.fundacionjala.sevenwonders.core.factory.EffectFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by diego on 11/7/2016.
 */
public class GameReader {

    private ObjectMapper mapper;
    private JsonNode rootNode;
    private EffectFactory effectFactory;

    public GameReader() throws IOException {
        this.mapper = new ObjectMapper();
        this.effectFactory = new EffectFactory();
    }

    public Wonder readWonder(File file) throws IOException {
        rootNode = mapper.readTree(file);
        String name = rootNode.path("name").textValue();
        JsonNode sidesNode = rootNode.path("sides");
        List<Side> sides = new ArrayList<>();
        for (JsonNode currentSide : sidesNode) {
            String stageName = currentSide.path("name").textValue();
            List<Effect> effects = readEffects(currentSide.path("effect"));
            List<StageType> stages = readStages(currentSide.path("stages"));
            sides.add(new Side( stages, effects));
        }
        Wonder wonder = new Wonder(name);
        wonder.setSide("a", sides.get(0));
        wonder.setSide("b", sides.get(0));
        return wonder;
    }

    public Building readBuilding(File file, String folderName) throws IOException {
        rootNode = mapper.readTree(file);
        String cardName = rootNode.path("name").textValue();
        BuildingType buildingType = BuildingType.valueOf(folderName);
        JsonNode requirementsNode = rootNode.path("requirements");
        List<Requirement> requirements = readRequirements(requirementsNode);
        int age = rootNode.path("age").intValue();
        int playersNeeded = rootNode.path("playersNeeded").intValue();
        JsonNode effectsNode = rootNode.path("effects");
        List<Effect> effects = readEffects(effectsNode);
        Building building = new BuildingBuilder()
                .setName(cardName)
                .setBuildingType(buildingType)
                .setAge(age)
                .setPlayersNeeded(playersNeeded)
                .setEffects(effects)
                .setRequirements(requirements)
                .createBuilding();

        return building;
    }

    private List<Effect> readEffects(JsonNode effectsNode) {
        List<Effect> effects = new ArrayList<>();
        for (JsonNode currentEffect : effectsNode) {
            Effect effect = null;
            EffectType type = EffectType.valueOf(currentEffect.path("type").textValue());
            JsonNode dataNode = currentEffect.path("data");
            effect = effectFactory.getEffect(type, dataNode);
            effects.add(effect);
        }
        return effects;
    }

    private List<Requirement> readRequirements(JsonNode requirementsNode) {
        List<Requirement> requirementList = new ArrayList<>();
        if (requirementsNode.size() != 0) {
            for (JsonNode currentNode : requirementsNode) {
                if ("RESOURCE".equals(currentNode.path("type").textValue())) {
                    JsonNode dataNode = currentNode.path("data");
                    Map<ResourceType, Integer> map = new HashMap<>();
                    ResourceType type = ResourceType.valueOf(dataNode.path("type").textValue().toUpperCase());
                    map.put(type, dataNode.path("quantity").intValue());
                    requirementList.add(new ResourceRequirement(map));
                }
            }
        }
        return requirementList;
    }

    private List<StageType> readStages(JsonNode stagesNode) {
        List<StageType> result = new ArrayList<>();
        for (JsonNode currentStage : stagesNode) {
            List<Effect> effects = readEffects(currentStage.path("effects"));
            List<Requirement> requirements = readRequirements(currentStage.path("requirements"));
            StageType stage = new StageType(requirements, effects);
            result.add(stage);
        }
        return result;
    }

}
