package org.fundacionjala.sevenwonders.core.rest;

import java.util.List;

/**
 * Created by dwits on 16/11/2016.
 */
public class DeckModel {
    private List<CardModel> cards;

    public List<CardModel> getCards() {
        return cards;
    }

    public void setCards(List<CardModel> cards) {
        this.cards = cards;
    }
}
