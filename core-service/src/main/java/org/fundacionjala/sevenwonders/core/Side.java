package org.fundacionjala.sevenwonders.core;

import com.google.common.base.Preconditions;
import org.fundacionjala.sevenwonders.core.effect.Effect;

import java.util.List;

/**
 * Created by Luis Gumucio
 */
public class Side {
    private Effect effect;
    private List<StageType> stages;

    public Side(List<StageType> stages, Effect effect) {
        Preconditions.checkNotNull(stages, "the stages is null");
        Preconditions.checkNotNull(effect, "the effect is null");
        this.stages = stages;
        this.effect = effect;
    }

    public Effect getEffect() {
        return effect;
    }

    public List<StageType> getStages() {
        return stages;
    }
}
