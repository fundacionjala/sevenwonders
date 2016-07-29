/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */

package org.fundacionjala.sevenwonders.core;

import org.fundacionjala.sevenwonders.core.effect.Effect;

import java.util.List;

/**
 * Created by Vania Catorceno on 6/2/2016.
 */
public class Stage extends Card {
    private boolean built;

    public Stage(List<Requirement> requirements, List<Effect> effects) {
        super(requirements, effects);
    }

    public boolean isBuilt() {
        return built;
    }

    public void setBuildState(boolean state) {
        this.built = state;
    }
}
