package org.fundacionjala.sevenwonders.core;

import org.fundacionjala.sevenwonders.core.effect.Effect;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dwits on 04/08/2016.
 */
public class CardProvider {

    private List<Card> cards;
    private List<String> nameCards;

    private List<String> initNameCards() {
        List<String> temp = new ArrayList<>();
        temp.add("academy");
        temp.add("altar");
        temp.add("apothecary");
        temp.add("aqueduct");
        temp.add("archeryrange");
        temp.add("arena");
        temp.add("arsenal");
        temp.add("barracks");
        temp.add("baths");
        temp.add("bazar");
        temp.add("brickyard");
        temp.add("buildersguild");
        temp.add("caravansery");
        temp.add("chamberofcommerce");
        temp.add("circus");
        temp.add("claypit");
        temp.add("claypool");
        temp.add("courthouse");
        temp.add("craftsmensguild");
        temp.add("dispensary");
        temp.add("easttradingpost");
        temp.add("excavation");
        temp.add("forestcave");
        temp.add("fortifications");
        temp.add("forum");
        temp.add("foundry");
        temp.add("gardens");
        temp.add("glassworks");
        temp.add("guardtower");
        temp.add("haven");
        temp.add("laboratory");
        temp.add("library");
        temp.add("lighthouse");
        temp.add("lodge");
        temp.add("loom");
        temp.add("lumberyard");
        temp.add("magistratesguild");
        temp.add("marketplace");
        temp.add("mine");
        temp.add("observatory");
        temp.add("orevein");
        temp.add("palace");
        temp.add("pantheon");
        temp.add("pawnshop");
        temp.add("philosophersguild");
        temp.add("press");
        temp.add("quarry");
        temp.add("sawmill");
        temp.add("school");
        temp.add("scientistsguild");
        temp.add("scriptorium");
        temp.add("senate");
        temp.add("shipownersguild");
        temp.add("siegeworkshop");
        temp.add("spiesguild");
        temp.add("stables");
        temp.add("statue");
        temp.add("stockade");
        temp.add("stonepit");
        temp.add("strategistsguild");
        temp.add("study");
        temp.add("tavern");
        temp.add("temple");
        temp.add("theater");
        temp.add("timberyard");
        temp.add("townhall");
        temp.add("tradersguild");
        temp.add("trainingground");
        temp.add("treefarm");
        temp.add("university");
        temp.add("vineyard");
        temp.add("walls");
        temp.add("westtradingpost");
        temp.add("workersguild");
        temp.add("workshop");
        return temp;
    }

    public List<Card> getDefault() {
        cards = new ArrayList<>();
        nameCards = initNameCards();
        for (int i = 0; i < 70; i++) {
            cards.add(new Building(new ArrayList<Requirement>(), new ArrayList<Effect>(), 2, 1, "" + nameCards.get(i), BuildingType.CIVIC));
        }
        return cards;
    }
}
