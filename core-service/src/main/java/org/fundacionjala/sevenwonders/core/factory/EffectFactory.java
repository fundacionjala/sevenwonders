package org.fundacionjala.sevenwonders.core.factory;

import com.fasterxml.jackson.databind.JsonNode;
import org.fundacionjala.sevenwonders.core.ResourceType;
import org.fundacionjala.sevenwonders.core.calculator.CalculatorType;
import org.fundacionjala.sevenwonders.core.effect.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by diego on 11/10/2016.
 */
public class EffectFactory {

    public Effect getEffect(EffectType type, JsonNode dataNode) {
        ResourceType resourceType;
        switch (type) {
            case PRODUCTION:
                resourceType = ResourceType.valueOf(dataNode.path("type").textValue());
                int quantity = dataNode.path("quantity").intValue();
                return new ResourceEffect(resourceType, quantity);
            case VICTORY:
                int points = dataNode.path("points").intValue();
                CalculatorType calculatorType = CalculatorType.valueOf(dataNode.path("type").textValue());
                return new VictoryPointEffect(points, calculatorType);
            case CHOOSEONE:
                List<ResourceType> resourceTypes = new ArrayList<>();
                for (JsonNode current : dataNode) {
                    resourceType = ResourceType.valueOf(current.textValue());
                    resourceTypes.add(resourceType);
                }
                return new ChooseOneEffect(resourceTypes);
            case PLAYTWOLASTCARDS:
                return new PlayTwoLastCardsEffect();
            case SSCIENCE:
                return new SScienceEffect();
            default:
                throw new EnumConstantNotPresentException(EffectType.class, "No supported enum");
        }
    }
}
