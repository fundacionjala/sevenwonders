package org.fundacionjala.sevenwonders.core;

import org.fundacionjala.sevenwonders.core.effect.Effect;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dwits on 04/08/2016.
 */
public class CardProvider {

    private List<Card> cards;

    public List<Card> getDefault() {
        cards = new ArrayList<>();
        for (int i = 0; i < 70; i++) {
            cards.add(new Building(new ArrayList<Requirement>(), new ArrayList<Effect>(), 2, 1, "Card_" + i, BuildingType.CIVIC));
        }
        return cards;
    }
}
