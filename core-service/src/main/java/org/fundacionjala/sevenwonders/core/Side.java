package org.fundacionjala.sevenwonders.core;

import com.google.common.base.Preconditions;
import org.fundacionjala.sevenwonders.core.effect.Effect;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Luis Gumucio
 */
public class Side {

    private String nameSide;
    private Effect effect;
    private List<StageType> stages;

    public Side(List<StageType> stages, Effect effect) {
        this.stages = stages;
        this.effect = effect;
    }

    public Effect getEffect() {
        return effect;
    }

    public void setEffect(Effect effect) {
        this.effect = effect;
    }

    public List<StageType> getStages() {
        return stages;
    }

    public void setStages(List<StageType> stages) {
        this.stages = stages;
    }
}
