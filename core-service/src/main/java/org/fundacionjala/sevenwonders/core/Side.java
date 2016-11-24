package org.fundacionjala.sevenwonders.core;

import com.google.common.base.Preconditions;
import org.fundacionjala.sevenwonders.core.effect.Effect;

import java.util.List;

/**
 * Created by Luis Gumucio
 */
public class Side {
    private List<Effect> effects;
    private List<StageType> stages;

    public Side(List<StageType> stages, List<Effect> effects) {
        Preconditions.checkNotNull(stages, "the stages is null");
        Preconditions.checkNotNull(effects, "the effect is null");
        this.stages = stages;
        this.effects = effects;
    }

    public List<Effect> getEffects() {
        return effects;
    }

    public List<StageType> getStages() {
        return stages;
    }
}
