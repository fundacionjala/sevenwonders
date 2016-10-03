/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */
package org.fundacionjala.sevenwonders.core;

import com.google.common.base.Preconditions;
import org.fundacionjala.sevenwonders.core.rest.PlayerModel;
import org.fundacionjala.sevenwonders.core.rest.WonderModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Has the basic functionality for add players, set some settings and start the game when
 * the game room is full.
 *
 * @author Juan Barahona
 */
public class GameRoom {
    public static final int NUMBER_OF_AGES = 3;

    private WonderProvider wonderProvider;
    private CardProvider cardProvider;
    private List<PlayerModel> players;
    private int maxPlayers;
    private String name;

    public GameRoom(String name, int maxPlayers) {
        wonderProvider = new WonderProvider();
        cardProvider = new CardProvider();
        players = new ArrayList<>();
        this.maxPlayers = maxPlayers;
        this.name = name;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    /**
     * Gets a list of rest players
     *
     * @return list of {@link org.fundacionjala.sevenwonders.core.rest.PlayerModel}
     */
    public List<PlayerModel> getPlayers(){
        return players;
    }

    /**
     * Add players to game room
     *
     * @param player assigned a item of players.
     */
    public void addPlayer(PlayerModel player) {
        Preconditions.checkArgument(maxPlayers != players.size());
        this.players.add(player);
    }

    /**
     * Initialize players with {@link Wonder}, {@link Storage} and {@link StoragePoint}
     * @return list of {@link Player}
     */
    private List<Player> initializePlayers() {
        List<Player> gamePlayers = new ArrayList<>();
        List<Wonder> wonders = wonderProvider.getDefault();
        Random random = new Random();
        players.stream().forEach(item -> {
            Wonder currentWonder = wonders.remove(random.nextInt(wonders.size()));
            StoragePoint storagePoint = new StoragePoint();
            Storage storage = new Storage();
            City city = new City(currentWonder, storagePoint, storage);
            Player player = new Player(item.getUserName(), city);
            WonderModel wonderModel = new WonderModel();
            wonderModel.setCurrentSide("A");
            wonderModel.setCityName(currentWonder.getName());
            item.setWonderModel(wonderModel);
            gamePlayers.add(player);
        });

        return gamePlayers;
    }


    /**
     * Create a game with basic values
     *
     * @return Game
     */
    public Game createGame(){
        List<Player> initPlayers = initializePlayers();
        GameBuilder builder = new GameBuilder();
        return builder.setPlayers(initPlayers)
                      .setDeck(new Deck(cardProvider.getDefault()), NUMBER_OF_AGES)
                      .createGame();
    }

    public String getName() {
        return name;
    }
}
