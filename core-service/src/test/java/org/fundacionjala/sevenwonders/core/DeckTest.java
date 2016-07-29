/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */
package org.fundacionjala.sevenwonders.core;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.junit.Assert;
import org.junit.Test;

/**
 * Used to test all the functionality of the {@link Deck}.
 *
 * @author Juan Barahona
 */
public class DeckTest {

    private final List<Card> cards;

    public DeckTest() {
        cards = new ArrayList<>();
        Building building = new Building(new ArrayList<>(), new ArrayList<>(),
                2, 1, "Generic", BuildingType.CIVIC);
        Building firstBuilding = new Building(new ArrayList<>(), new ArrayList<>(),
                3, 1, "Generic", BuildingType.CIVIC);
        Building secondBuilding = new Building(new ArrayList<>(), new ArrayList<>(),
                4, 1, "Generic", BuildingType.CIVIC);
        Building thirdBuilding = new Building(new ArrayList<>(), new ArrayList<>(),
                5, 1, "Generic", BuildingType.CIVIC);

        cards.add(building);
        cards.add(building);
        cards.add(building);
        cards.add(building);
        cards.add(firstBuilding);
        cards.add(firstBuilding);
        cards.add(firstBuilding);
        cards.add(secondBuilding);
        cards.add(thirdBuilding);
        cards.add(thirdBuilding);
    }

    @Test
    public void testGetCardsByAge() {
        Deck simpleDeck = new Deck(cards);
        List<Predicate<? super Card>> filters = new ArrayList<>();
        filters.add(card -> card instanceof Building);
        filters.add(card -> ((Building) card).getAge() == 1);

        Deck result = simpleDeck.filterDeckBy(filters);

        List<Card> expected = cards.stream()
                .filter(card -> card instanceof Building && ((Building) card).getAge() == 1)
                .collect(Collectors.toList());

        Assert.assertArrayEquals(result.getCards().toArray(), expected.toArray());
    }

    @Test
    public void testGetCardsByPlayers() {
        Deck simpleDeck = new Deck(cards);
        List<Predicate<? super Card>> filters = new ArrayList<>();
        filters.add(card -> card instanceof Building);
        filters.add(card -> ((Building) card).getPlayersNeeded() == 2);

        Deck result = simpleDeck.filterDeckBy(filters);

        List<Card> expected = cards.stream()
                .filter(card -> card instanceof Building && ((Building) card).getPlayersNeeded() == 2)
                .collect(Collectors.toList());

        Assert.assertArrayEquals(result.getCards().toArray(), expected.toArray());
    }

    @Test
    public void testGetCardsByAgeAndPlayers() {
        Deck simpleDeck = new Deck(cards);
        List<Predicate<? super Card>> filters = new ArrayList<>();
        filters.add(card -> card instanceof Building);
        filters.add(card -> ((Building) card).getPlayersNeeded() == 2);
        filters.add(card -> ((Building) card).getAge() == 1);

        Deck result = simpleDeck.filterDeckBy(filters);

        List<Card> expected = cards.stream()
                .filter(card -> card instanceof Building
                        && ((Building) card).getPlayersNeeded() == 2
                        && ((Building) card).getAge() == 1)
                .collect(Collectors.toList());

        Assert.assertArrayEquals(result.getCards().toArray(), expected.toArray());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetCardsByZeroArguments() {
        Deck simpleDeck = new Deck(cards);
        List<Predicate<? super Card>> filters = new ArrayList<>();

        simpleDeck.filterDeckBy(filters);
        Assert.fail();
    }

    @Test(expected = NullPointerException.class)
    public void TestInvalidGetCardsBy() {
        Deck simpleDeck = new Deck(cards);

        simpleDeck.filterDeckBy(null);
        Assert.fail();
    }

    @Test
    public void TestRemoveCards() {
        Deck simpleDeck = new Deck(cards);
        int initialSize = simpleDeck.size();
        List<Card> subList = cards.subList(0, 4);
        simpleDeck.removeCards(subList);
        int finalSize = simpleDeck.size();

        Assert.assertNotEquals(initialSize, finalSize);
    }
}
