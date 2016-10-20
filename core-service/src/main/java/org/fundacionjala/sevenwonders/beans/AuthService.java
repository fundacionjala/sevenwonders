/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */
package org.fundacionjala.sevenwonders.beans;

import org.fundacionjala.sevenwonders.core.rest.PlayerModel;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

/**
 * Created by Juan Manuel Barahona on 24/08/2016.
 */

@Component
public class AuthService {
    private final Map<Integer, PlayerModel> players = new TreeMap<>();
    private int autoIncrementId;


    public AuthService(){
        autoIncrementId = 1;
    }

    public PlayerModel login(PlayerModel playerModel){
        PlayerModel player = new PlayerModel();
        if (playerModel.getUserName() != null && !playerModel.getUserName().isEmpty()) {
                player.setId(autoIncrementId);
                player.setUserName(playerModel.getUserName());
                player.setToken(UUID.randomUUID().toString());
                players.put(autoIncrementId, player);
                autoIncrementId++;
                return player;

        }
        throw new IllegalArgumentException();
    }

    public PlayerModel getPlayerById(int id) {
        return players.get(id);
    }

    public Map<Integer, PlayerModel> getPlayers() {
        return players;
    }
}