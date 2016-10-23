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

    public GameService getGameService() {
        return gameService;
    }

    public void setGameService(GameService gameService) {
        this.gameService = gameService;
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
     * GET: Send a list of game rooms
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
     * Update side wonderModel when user chose a side for playing
     * @param id game Room
     * @param playerModel player game
     * @return player with update information about wonder
     */
    public PlayerModel updateSideWonder(int id, PlayerModel playerModel){
        GameRoom gameroom = gameRooms.get(id);
        gameroom.getPlayers().forEach(player -> {
            if(player.getId() == playerModel.getId()) {
                player.setWonderModel(playerModel.getWonderModel());
                return;
            }
        });
        return playerModel;
    }

    /**
     * GET: Get a player of a game room
     *
     * @param id game room identifier
     * @return player
     */
    public Collection<PlayerModel> getPlayers(int id) {
        return gameRooms.get(id).getPlayers();
    }

    /**
     * POST: Add player to a game room
     *
     * @param player
     */
    public void addPlayer(int id, PlayerModel player) {
        GameRoom current = gameRooms.get(id);
        current.addPlayer(player);
    }

    /**
     * Verify if player in the game room
     * @param id game room
     * @param player player of the game
     * @return find player
     */
    public PlayerModel validateGame(int id, PlayerModel player) {
        PlayerModel current = gameRooms.get(id)
                .getPlayers().stream()
                .filter(b -> b.getId() == player.getId())
                .findAny()
                .orElse(null);
        return current;
    }

    /**
     * Method start game, creating whole necessaries for playing
     * @param id game room
     */
    public void startGame(int id) {
        GameRoom current = gameRooms.get(id);
        gameService.createGame(current.createGame());
    }

    /**
     * Verify if in game room the players is complete
     * @param id game room
     * @return value boolean
     */
    public boolean isCompletedPlayers(int id){
        return gameRooms.get(id).getMaxPlayers() == gameRooms.get(id).getPlayers().size();
    }
}
