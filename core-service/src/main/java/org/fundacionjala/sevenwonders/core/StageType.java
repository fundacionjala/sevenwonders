package org.fundacionjala.sevenwonders.core;

import org.fundacionjala.sevenwonders.core.effect.Effect;

import java.util.List;

/**
 * Created by Jorge on 16/11/2016.
 */
public class StageType {
    private List<Requirement> requirements;
    private List<Effect>effects;

    public StageType(List<Requirement> requirements, List<Effect> effects)
    {
        this.requirements = requirements;
        this.effects = effects;
    }

    public List<Requirement> getRequirements() {
        return requirements;
    }

    public List<Effect> getEffects() {
        return effects;
    }
}
