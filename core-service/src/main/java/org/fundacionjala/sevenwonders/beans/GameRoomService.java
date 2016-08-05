/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */
package org.fundacionjala.sevenwonders.beans;

import org.fundacionjala.sevenwonders.core.*;
import org.fundacionjala.sevenwonders.core.GameRoom;
import org.fundacionjala.sevenwonders.core.Player;
import org.fundacionjala.sevenwonders.core.StoragePoint;
import org.fundacionjala.sevenwonders.core.rest.*;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Has the basic functionality that permit to rest service access and generate
 * data without obtain unusable information about game room data.
 *
 * @author Juan Barahona
 */

@Component
public class GameRoomService {
    private final Map<String, GameRoom> gameRooms = new TreeMap<>();
    private GameService gameService;
    private String autoIncrementId;

    public GameRoomService(){
        autoIncrementId = 1 + "";
        gameService = new GameService();
    }


    /**
     * POST: Create a game room with the information sent in post petition.
     *
     * @param restGameRoom
     */
    public void createGameRoom(org.fundacionjala.sevenwonders.core.rest.GameRoom restGameRoom){
        GameRoom gameRoom = new GameRoom(restGameRoom.getMaxPlayers());
        gameRooms.put(autoIncrementId, gameRoom);
        restGameRoom.getOwner().setRoomId(autoIncrementId);
        autoIncrementId = (Integer.parseInt(autoIncrementId) + 1) + "";
        gameRoom.addPlayer(restGameRoom.getOwner());
    }

    /**
     * GET: Send a game room
     *
     * @param id identifier of game room
     * @return game room with owner and max of players
     */
    public org.fundacionjala.sevenwonders.core.rest.GameRoom getGameRoom(String id){
        org.fundacionjala.sevenwonders.core.rest.GameRoom room = new org.fundacionjala.sevenwonders.core.rest.GameRoom();
        room.setMaxPlayers(gameRooms.get(id).getMaxPlayers());
        room.setOwner(gameRooms.get(id).getPlayers().get(0));
        return room;
    }

    /**
     * Get: Send a list of game rooms
     *
     * @return GameRooms
     */
    public Collection<org.fundacionjala.sevenwonders.core.rest.GameRoom> listGameRooms(){

        List<org.fundacionjala.sevenwonders.core.rest.GameRoom> currentGameRooms = new ArrayList<>();
        gameRooms.values().stream().forEach(gameRoom -> {
            org.fundacionjala.sevenwonders.core.rest.GameRoom room = new org.fundacionjala.sevenwonders.core.rest.GameRoom();
            room.setMaxPlayers(gameRoom.getMaxPlayers());
            room.setOwner(gameRoom.getPlayers().get(1));
            currentGameRooms.add(room);
        });

        return currentGameRooms;
    }

    /**
     * Get: Get a player of a game room
     *
     * @param id game room identifier
     * @return player
     */
    public Collection<org.fundacionjala.sevenwonders.core.rest.Player> getPlayers(String id){
        return gameRooms.get(id).getPlayers();
    }

    /**
     * Post: Add player to a game room
     *
     * @param player
     */

    public void addPlayer(org.fundacionjala.sevenwonders.core.rest.Player player){
        GameRoom current = gameRooms.get(player.getRoomId());
        current.addPlayer(player);

        if(current.getMaxPlayers() == current.getPlayers().size()){
            gameService.createGame(current.createGame());
        }
    }
}
