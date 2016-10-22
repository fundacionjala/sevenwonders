/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */
package org.fundacionjala.sevenwonders.beans;

import com.google.common.base.Preconditions;
import org.fundacionjala.sevenwonders.core.Game;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

/**
 * Has the basic functionality that permit to rest service access and generate
 * data without obtain unusable information, this have the principal function
 * of sevenwonders.
 *
 * @author Juan Barahona
 */


@Component
public class GameService {
    private final Map<Integer, Game> games = new TreeMap<>();
    private int autoIncrementId;

    /**
     * Create a game service
     */
    public GameService(){
        autoIncrementId = 1;
    }

    /**
     * Post: Create a game with an identifier
     *
     * @param game
     */
    public void createGame(Game game) {
        Preconditions.checkNotNull(game);
        games.put(autoIncrementId, game);
        autoIncrementId++;
    }

    /**
     * Get the list games in collection
     * @return collection of the game created
     */
    public Collection<Game> getGames(){
        return games.values();
    }

    /**
     * Get the game by id
     * @param id integer
     * @return a game found by id
     */
    public Game getGame(int id){
        return games.get(id);
    }

}
