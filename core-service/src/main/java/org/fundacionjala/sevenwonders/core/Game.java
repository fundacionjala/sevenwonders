/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */
package org.fundacionjala.sevenwonders.core;

import com.google.common.base.Preconditions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Adds player, ages and initializes the game.
 *
 * @author Juan Barahona
 */
public class Game {

    private final List<Age> ages;
    private final List<Player> players;
    private final Map<String, Card>  chooseCard = new TreeMap<>();

    /**
     * Initialize a new instance of {@link Game}, assigned a {@link Age}.
     *
     * @param players
     * @param ages
     */
    public Game(List<Player> players, List<Age> ages) {
        this.ages = ages;
        this.players = players;
    }

    /**
     * Gets a list of ages.
     *
     * @return ages.
     */
    public List<Age> getAges() {
        return ages;
    }

    /**
     * Gets a list of players.
     *
     * @return players.
     */
    public List<Player> getPlayers() {
        return players;
    }

    public void addChooseCard(Player player, Card card){
        Preconditions.checkNotNull(player, "The player is null");
        Preconditions.checkNotNull(card, "The card is null");
        chooseCard.put(player.getName(), card);
    }

    public Map<String, Card> getChooseCard() {
        return chooseCard;
    }
}
