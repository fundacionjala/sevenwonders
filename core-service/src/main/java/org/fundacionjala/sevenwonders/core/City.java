/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */
package org.fundacionjala.sevenwonders.core;

import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Juan Barahona on 22/04/2016.
 */
public class City {

    private Wonder wonder;
    private Storage storage;
    private StoragePoint storagePoint;
    private List<Building> buildings;

    public City(Wonder wonder, StoragePoint storagePoint, Storage storage) {
        this.storage = storage;
        this.wonder = wonder;
        this.storagePoint = storagePoint;
        this.buildings = new ArrayList<>();
    }

    /**
     * This method create a stage if validate all requirement are true.
     *
     * @param stage current stage
     */
    public boolean buildStage(Stage stage) {
        Preconditions.checkNotNull(stage, "Stage is null");
        stage.setBuildState(stage.getRequirements().stream()
                .filter(requirement -> requirement.validate(this))
                .count() == stage.getRequirements().size());
        return stage.isBuilt();
    }

    public List<Building> getBuildings() {
        return buildings;
    }

    public Wonder getWonder() {
        return wonder;
    }

    public StoragePoint getStoragePoint() {
        return storagePoint;
    }

    public Storage getStorage() {
        return storage;
    }
}
