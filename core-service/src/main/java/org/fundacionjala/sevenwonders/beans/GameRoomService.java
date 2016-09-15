/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */
package org.fundacionjala.sevenwonders.beans;

import org.fundacionjala.sevenwonders.core.GameRoom;
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
    private final Map<Integer, GameRoom> gameRooms = new TreeMap<>();
    private GameService gameService;
    private int autoIncrementId;

    public GameRoomService() {
        autoIncrementId = 1;
        gameService = new GameService();
    }


    /**
     * POST: Create a game room with the information sent in post petition.
     *
     * @param restGameRoomModel
     */
    public GameRoomModel createGameRoom(GameRoomModel restGameRoomModel) {
        GameRoom gameRoom = new GameRoom(restGameRoomModel.getRoomName(), restGameRoomModel.getMaxPlayers());
        gameRooms.put(autoIncrementId, gameRoom);
        restGameRoomModel.setId(autoIncrementId);
        restGameRoomModel.setChannel("game-" + autoIncrementId);
        autoIncrementId++;
        gameRoom.addPlayer(restGameRoomModel.getOwner());
        return restGameRoomModel;
    }

    /**
     * GET: Send a game room
     *
     * @param id identifier of game room
     * @return game room with owner and max of players
     */
    public GameRoomModel getGameRoom(int id) {
        GameRoomModel room = new GameRoomModel();
        room.setRoomName(gameRooms.get(id).getName());
        room.setMaxPlayers(gameRooms.get(id).getMaxPlayers());
        room.setOwner(gameRooms.get(id).getPlayers().get(0));
        room.setPlayers(gameRooms.get(id).getPlayers());
        room.setChannel("game-" + id);
        room.setId(id);
        return room;
    }

    /**
     * Get: Send a list of game rooms
     *
     * @return GameRooms
     */
    public Collection<GameRoomModel> listGameRooms() {
        List<GameRoomModel> currentGameRoomModels = new ArrayList<>();
        gameRooms.entrySet().stream().forEach(entry -> {
            GameRoomModel room = new GameRoomModel();
            room.setRoomName(entry.getValue().getName());
            room.setMaxPlayers(entry.getValue().getMaxPlayers());
            room.setOwner(entry.getValue().getPlayers().get(0));
            room.setPlayers(entry.getValue().getPlayers());
            room.setChannel("game-" + entry.getKey());
            room.setId(entry.getKey());
            currentGameRoomModels.add(room);
        });

        return currentGameRoomModels;
    }

    /**
     * Get: Get a player of a game room
     *
     * @param id game room identifier
     * @return player
     */
    public Collection<PlayerModel> getPlayers(int id) {
        return gameRooms.get(id).getPlayers();
    }

    /**
     * Post: Add player to a game room
     *
     * @param player
     */
    public void addPlayer(int id, PlayerModel player) {
        GameRoom current = gameRooms.get(id);
        current.addPlayer(player);
    }

    public GameService getGameService() {
        return gameService;
    }

    public void setGameService(GameService gameService) {
        this.gameService = gameService;
    }

    public PlayerModel validateGame(int id, PlayerModel player) {
        PlayerModel current = gameRooms.get(id)
                .getPlayers().stream()
                .filter(b -> b.getId() == player.getId())
                .findAny()
                .orElse(null);
        return current;
    }

    public void startGame(int id) {
        GameRoom current = gameRooms.get(id);
        gameService.createGame(current.createGame());
    }

    public boolean isCompletedPlayers(int id){
        return gameRooms.get(id).getMaxPlayers() == gameRooms.get(id).getPlayers().size();
    }
}
