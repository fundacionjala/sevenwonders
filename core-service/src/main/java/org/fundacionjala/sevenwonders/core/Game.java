/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */
package org.fundacionjala.sevenwonders.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Adds player, ages and initializes the game.
 *
 * @author Juan Barahona
 */
public class Game {

    private final List<Age> ages;
    private final List<Player> players;
    private Map<Player, Card> chooseCard;

    /**
     * Initialize a new instance of {@link Game}, assigned a {@link Age}.
     *
     * @param players
     * @param ages
     */
    public Game(List<Player> players, List<Age> ages) {
        this.ages = ages;
        this.players = players;
        chooseCard = new HashMap<>();
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
        chooseCard.put(player, card);
    }

    public Map<Player, Card> getChooseCard() {
        return chooseCard;
    }
}
