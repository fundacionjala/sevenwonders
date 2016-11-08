/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */

package org.fundacionjala.sevenwonders;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created by diego on 11/7/2016.
 */
public class GameProvider {

    private ObjectMapper mapper;
    private JsonNode rootNode;
    private static String currentDirectory;

    public GameProvider() throws IOException {
        currentDirectory = new File("").getAbsolutePath();
        this.mapper = new ObjectMapper();
    }

    public void readWonders() throws IOException {
        File file = new File(currentDirectory + "/src/main/resources/wonders/Babylon.json");
        rootNode = mapper.readTree(file);
        JsonNode wondersNode = rootNode.path("wonders");
        System.out.println("asdasd");
    }

}
