/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */
package org.fundacionjala.sevenwonders.core;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import org.fundacionjala.sevenwonders.exception.NoEnoughCardsException;
import org.junit.Assert;
import org.junit.Test;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Used to test all the functionality of the {@link Age}.
 *
 * @author Juan Barahona
 */
public class AgeTest {

    private final List<Card> cards;
    private final List<Card> noEnoughcards;

    public AgeTest() {

        cards = new ArrayList<>();
        noEnoughcards = new ArrayList<>();
        Building building = new Building(new ArrayList<>(), new ArrayList<>(),
                2, 1, "Generic", BuildingType.CIVIC);
        cards.add(building);
        cards.add(building);
        cards.add(building);
        cards.add(building);
        cards.add(building);
        cards.add(building);

        noEnoughcards.add(building);
        noEnoughcards.add(building);
        noEnoughcards.add(building);
    }

    @Test
    public void testNextDeck() {
        int numberOfAge = 1;
        int quantity = 4;

        Deck deck = mock(Deck.class);
        when(deck.filterDeckBy(anyList())).thenReturn(new Deck(cards));

        Age simpleAge = new Age(numberOfAge, deck);

        List<Predicate<? super Card>> filters = new ArrayList<>();
        filters.add(card -> card instanceof Building);
        filters.add(card -> ((Building) card).getPlayersNeeded() == 2);
        filters.add(card -> ((Building) card).getAge() == 1);

        Deck result = simpleAge.nextDeck(quantity, filters);

        Assert.assertEquals(quantity, result.size());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextGetDeckWithInvalidArgument() {
        int numberOfAge = 1;
        int quantity = -4;

        Deck deck = mock(Deck.class);
        Age simpleAge = new Age(numberOfAge, deck);
        List<Predicate<? super Card>> filters = new ArrayList<>();

        Deck result = simpleAge.nextDeck(quantity, filters);
        Assert.fail();

    }

    @Test(expected = NoEnoughCardsException.class)
    public void testNextDeckWithoutEnoughCards() {
        int numberOfAge = 1;
        int quantity = 4;

        Deck deck = mock(Deck.class);
        when(deck.filterDeckBy(anyList())).thenReturn(new Deck(noEnoughcards));

        Age simpleAge = new Age(numberOfAge, deck);

        List<Predicate<? super Card>> filters = new ArrayList<>();
        filters.add(card -> card instanceof Building);
        filters.add(card -> ((Building) card).getPlayersNeeded() == 2);
        filters.add(card -> ((Building) card).getAge() == 1);

        Deck result = simpleAge.nextDeck(quantity, filters);

        Assert.fail();
    }
}
