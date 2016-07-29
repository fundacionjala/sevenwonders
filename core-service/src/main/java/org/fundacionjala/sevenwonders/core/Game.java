/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */
package org.fundacionjala.sevenwonders.core;

import java.util.List;

/**
 * Adds player, ages and initializes the game.
 *
 * @author Juan Barahona
 */
public class Game {

    private final List<Age> ages;
    private final List<Player> players;

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
}
