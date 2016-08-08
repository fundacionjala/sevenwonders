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
        cards.add(new Building(new ArrayList<Requirement>(), new ArrayList<Effect>(), 2, 1, "Os", BuildingType.CIVIC));
        cards.add(new Building(new ArrayList<Requirement>(), new ArrayList<Effect>(), 2, 1, "Os", BuildingType.CIVIC));
        cards.add(new Building(new ArrayList<Requirement>(), new ArrayList<Effect>(), 2, 1, "Os", BuildingType.CIVIC));
        cards.add(new Building(new ArrayList<Requirement>(), new ArrayList<Effect>(), 2, 1, "Os", BuildingType.CIVIC));
        cards.add(new Building(new ArrayList<Requirement>(), new ArrayList<Effect>(), 2, 1, "Os", BuildingType.CIVIC));
        cards.add(new Building(new ArrayList<Requirement>(), new ArrayList<Effect>(), 2, 1, "Os", BuildingType.CIVIC));
        cards.add(new Building(new ArrayList<Requirement>(), new ArrayList<Effect>(), 2, 1, "Os", BuildingType.CIVIC));
        cards.add(new Building(new ArrayList<Requirement>(), new ArrayList<Effect>(), 2, 1, "Os", BuildingType.CIVIC));
        cards.add(new Building(new ArrayList<Requirement>(), new ArrayList<Effect>(), 2, 1, "Os", BuildingType.CIVIC));
        cards.add(new Building(new ArrayList<Requirement>(), new ArrayList<Effect>(), 2, 1, "Os", BuildingType.CIVIC));
        cards.add(new Building(new ArrayList<Requirement>(), new ArrayList<Effect>(), 2, 1, "Os", BuildingType.CIVIC));
        cards.add(new Building(new ArrayList<Requirement>(), new ArrayList<Effect>(), 2, 1, "Os", BuildingType.CIVIC));
        cards.add(new Building(new ArrayList<Requirement>(), new ArrayList<Effect>(), 2, 1, "Os", BuildingType.CIVIC));
        cards.add(new Building(new ArrayList<Requirement>(), new ArrayList<Effect>(), 2, 1, "Os", BuildingType.CIVIC));
        cards.add(new Building(new ArrayList<Requirement>(), new ArrayList<Effect>(), 2, 1, "Os", BuildingType.CIVIC));
        cards.add(new Building(new ArrayList<Requirement>(), new ArrayList<Effect>(), 2, 1, "Os", BuildingType.CIVIC));
        cards.add(new Building(new ArrayList<Requirement>(), new ArrayList<Effect>(), 2, 1, "Os", BuildingType.CIVIC));
        cards.add(new Building(new ArrayList<Requirement>(), new ArrayList<Effect>(), 2, 1, "Os", BuildingType.CIVIC));
        cards.add(new Building(new ArrayList<Requirement>(), new ArrayList<Effect>(), 2, 1, "Os", BuildingType.CIVIC));
        cards.add(new Building(new ArrayList<Requirement>(), new ArrayList<Effect>(), 2, 1, "Os", BuildingType.CIVIC));
        return cards;
    }
}
