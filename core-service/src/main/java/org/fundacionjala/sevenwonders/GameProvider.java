/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */

package org.fundacionjala.sevenwonders;

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
public class GameProvider {

    private ObjectMapper mapper;
    private JsonNode rootNode;
    private EffectFactory effectFactory;
    private static String currentDirectory;
    private static String resourcesDirectory;

    public GameProvider() throws IOException {
        this.currentDirectory = new File("").getAbsolutePath();
        this.mapper = new ObjectMapper();
        this.effectFactory = new EffectFactory();
        this.resourcesDirectory = currentDirectory + "/src/main/resources";
    }

    public void readWonders() throws IOException {
        List<Wonder> result = new ArrayList<>();
        File folder = new File(resourcesDirectory + "/wonders");
        File[] listofFiles = folder.listFiles();
        for (File currentFile : listofFiles) {
            rootNode = mapper.readTree(currentFile);
            JsonNode sides = rootNode.path("sides");
        }
    }

    public Deck readCards() throws IOException {
        List<Card> cardList = new ArrayList<>();
        File folder = new File(resourcesDirectory + "/cards/buildings");
        File[] listofFolders = folder.listFiles();
        for (File currentFolder : listofFolders) {
            String folderName = currentFolder.getName().toUpperCase();
            File[] listofFiles = currentFolder.listFiles();
            for (File currentFile : listofFiles) {
                rootNode = mapper.readTree(currentFile);
                int quantity = rootNode.path("quantity").intValue();
                for (int i = 0; i < quantity; i++) {
                    String cardName = rootNode.path("name").textValue();
                    BuildingType buildingType = BuildingType.valueOf(folderName);
                    JsonNode requirementsNode = rootNode.path("requirements");
                    List<Requirement> requirements = readRequirements(requirementsNode);
                    int age = rootNode.path("age").intValue();
                    int playersNeeded = rootNode.path("playersNeeded").intValue();
                    JsonNode effectsNode = rootNode.path("effects");
                    List<Effect> effects = readEffects(effectsNode, folderName);
                    Building building = new BuildingBuilder()
                            .setName(cardName)
                            .setBuildingType(buildingType)
                            .setAge(age)
                            .setPlayersNeeded(playersNeeded)
                            .setEffects(effects)
                            .setRequirements(requirements)
                            .createBuilding();
                    cardList.add(building);
                }
            }
        }
        return new Deck(cardList);
    }

    private List<Effect> readEffects(JsonNode effectsNode, String folderName) {
        List<Effect> effects = new ArrayList<>();
        for (JsonNode currentEffect : effectsNode) {
            Effect effect = null;
            List<Object> valuesList = new ArrayList<>();
            EffectType type = EffectType.valueOf(currentEffect.path("type").textValue());
            JsonNode dataNode = currentEffect.path("data");
            switch (folderName) {
                case "RESOURCE":
                    ResourceType resourceType = ResourceType.valueOf(dataNode.path("type").textValue());
                    valuesList.add(resourceType);
                    valuesList.add(dataNode.path("quantity").intValue());
                    effect = effectFactory.getEffect(type, valuesList);
                    break;
                case "CIVIC":
                    valuesList.add(dataNode.path("points").intValue());
                    effect = effectFactory.getEffect(type, valuesList);
                    break;
            }
            effects.add(effect);
        }
        return effects;
    }

    private List<Requirement> readRequirements(JsonNode requirementsNode) {
        List<Requirement> requirementList = new ArrayList<>();
        if (requirementsNode.size() != 0) {
            for (JsonNode currentNode : requirementsNode) {
                Map<ResourceType, Integer> map = new HashMap<>();
                ResourceType type = ResourceType.valueOf(currentNode.path("type").textValue().toUpperCase());
                map.put(type, currentNode.path("quantity").intValue());
                requirementList.add(new ResourceRequirement(map));
            }
        }
        return requirementList;
    }

}
