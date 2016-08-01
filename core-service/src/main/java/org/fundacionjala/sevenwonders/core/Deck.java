/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */
package org.fundacionjala.sevenwonders.core;

import com.google.common.base.Preconditions;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Contains all cards, gets cards, removes cards and filters by criteria.
 *
 * @author Juan Barahona
 */
public class Deck {

    private final List<Card> cards;

    /**
     * Initialize a new instance of {@link Deck}, assigned a list of cards.
     *
     * @param cards assigned to list of cards.
     */
    public Deck(List<Card> cards) {
        this.cards = cards;
    }

    /**
     * Filters of deck by a list of cards.
     *
     * @param filters assigned list of cards filtered.
     * @return Deck.
     */
    public Deck filterDeckBy(List<Predicate<? super Card>> filters) {
        Preconditions.checkNotNull(filters, "The filters list is null");
        Preconditions.checkArgument(!filters.isEmpty(),
                "The size of the filter list is less equal than 0");
        List<Card> cardResult = cards;
        for (Predicate<? super Card> filter : filters) {
            cardResult = cardResult.stream().filter(filter).collect(Collectors.toList());
        }
        return new Deck(cardResult);
    }

    /**
     * Gets the cards.
     *
     * @return list of cards.
     */
    public List<Card> getCards() {
        return cards;
    }

    /**
     * Gets the size.
     *
     * @return the size of the cards.
     */
    public int size() {
        return cards.size();
    }

    /**
     * Removes cards according a list of cards.
     *
     * @param cards assigned to remove.
     */
    public void removeCards(List<Card> cards) {
        this.cards.removeAll(cards);
    }
}
