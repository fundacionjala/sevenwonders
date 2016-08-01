/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */
package org.fundacionjala.sevenwonders.core;

import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import org.fundacionjala.sevenwonders.exception.NoEnoughCardsException;

/**
 * Builds a deck according to the number of age and gets current {@link Age}.
 *
 * @author Juan Barahona
 */
public class Age {

    private final int currentAge;
    private final Deck deck;

    /**
     * Initialize a new instance of {@link Age}, assigned a number of age and an
     * initial {@link Deck}.
     *
     * @param numberOfAge assigned number of age.
     * @param initialDeck assigned an initial deck.
     */
    public Age(int numberOfAge, Deck initialDeck) {
        deck = initialDeck;
        currentAge = numberOfAge;
    }

    /**
     * Gets the current {@link Age}.
     *
     * @return current age.
     */
    public int getCurrentAge() {
        return currentAge;
    }

    /**
     * Builds a next deck with random cards according to the length and filters.
     *
     * @param length assigned the length of the cards.
     * @param filters assigned filter criteria.
     * @return a Deck.
     */
    public Deck nextDeck(int length, List<Predicate<? super Card>> filters) {
        Preconditions.checkArgument(length > 1,
                "The length of the filter list is less than 1");
        Preconditions.checkNotNull(filters, "The filters list is null");
        Preconditions.checkArgument(!filters.isEmpty(),
                "The filters list is empty");
        Random random = new Random();
        List<Card> filterResult = deck.filterDeckBy(filters).getCards();
        List<Card> result = new ArrayList<>();
        if (filterResult.size() < length) {
            throw new NoEnoughCardsException();
        }
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(filterResult.size() - 1);
            result.add(filterResult.get(randomIndex));
            filterResult.remove(randomIndex);
        }
        deck.removeCards(result);
        return new Deck(result);
    }
}
