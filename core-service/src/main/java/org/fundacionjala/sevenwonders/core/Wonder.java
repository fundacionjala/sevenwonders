/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */

package org.fundacionjala.sevenwonders.core;

import com.google.common.base.Preconditions;
import java.util.List;
import java.util.stream.Collectors;
import org.fundacionjala.sevenwonders.core.effect.Effect;

/**
 * Created by Vania Catorceno on 6/2/2016.
 */
public class Wonder {
    private List<Stage> stages;
    private Effect effect;
    private String name;

    public Wonder(List<Stage> stages) {
        Preconditions.checkNotNull(stages, "The stages is null");
        this.stages = stages;
    }

    public Effect getEffect() {
        return effect;
    }

    public String getName() {
        return name;
    }

    public List<Stage> getStages() {
        return stages;
    }

    /**
     * filter stages constructed
     * @return List of stages contructed
     */
    public List<Stage> getBuildingsStages() {
        return stages.stream().
                filter(s -> s.isBuilt()).collect(Collectors.toList());
    }
}
