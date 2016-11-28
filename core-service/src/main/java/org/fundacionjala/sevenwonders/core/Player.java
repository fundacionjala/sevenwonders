/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */
package org.fundacionjala.sevenwonders.core;

import com.google.common.base.Preconditions;

/**
 * Has a reference of a {@link City}, adds resource according to type of
 * resource and quantity, sets deck, gets deck.
 *
 * @author Juan Barahona
 */
public class Player {
    private String name;
    private Deck deck;
    private City city;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Initialize a new instance of {@link Player}, assigned a {@link City}.
     *
     * @param city
     */
    public Player(City city) {
        this.city = city;
    }

    /**
     * Initialize a new instance of {@link Player}, assigned a {@link City} and name.
     * @param name name of player
     * @param city city of player
     */
    public Player(String name, City city) {
        this.name = name;
        this.city = city;
    }

    /**
     * Sets the deck.
     *
     * @param deck assigned to set deck.
     */
    public void setDeck(Deck deck) {
        Preconditions.checkNotNull(deck, "The Deck is null");
        this.deck = deck;
    }

    /**
     * Gets the deck.
     *
     * @return Deck.
     */
    public Deck getDeck() {
        return deck;
    }

    /**
     * Gets the city.
     *
     * @return City.
     */
    public City getCity() {
        return city;
    }


    public String getName() {
        return name;
    }

}
