package org.fundacionjala.sevenwonders.core.factory;

import org.fundacionjala.sevenwonders.core.ResourceType;
import org.fundacionjala.sevenwonders.core.calculator.CalculatorType;
import org.fundacionjala.sevenwonders.core.effect.Effect;
import org.fundacionjala.sevenwonders.core.effect.EffectType;
import org.fundacionjala.sevenwonders.core.effect.ResourceEffect;
import org.fundacionjala.sevenwonders.core.effect.VictoryPointEffect;

import java.util.List;

/**
 * Created by diego on 11/10/2016.
 */
public class EffectFactory {

    public Effect getEffect(EffectType type, List<Object> data) {
        switch (type) {
            case PRODUCTION:
                return new ResourceEffect((ResourceType) data.get(0), (int) data.get(1));
            case VICTORY:
                return new VictoryPointEffect((int) data.get(0), (CalculatorType) data.get(1));
            default:
                throw new EnumConstantNotPresentException(EffectType.class, "No supported enum");
        }
    }
}
