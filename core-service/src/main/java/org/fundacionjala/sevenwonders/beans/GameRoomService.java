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
    private  GameRoom gameRoom;
    private PlayerModel playerModel;


    public GameRoomService(){
        autoIncrementId = 1;
        gameService = new GameService();
        playerModel = new PlayerModel();
        playerModel.setUserName("luis");
        playerModel.setRoomId(1);
        gameRoom = new GameRoom("new game", 7);
        gameRoom.addPlayer(playerModel);
        gameRooms.put(autoIncrementId, gameRoom);
    }


    /**
     * POST: Create a game room with the information sent in post petition.
     *
     * @param restGameRoomModel
     */
    public void createGameRoom(GameRoomModel restGameRoomModel){
        GameRoom gameRoom = new GameRoom(restGameRoomModel.getName(),
                restGameRoomModel.getMaxPlayers());
        gameRooms.put(autoIncrementId, gameRoom);
        restGameRoomModel.getOwner().setRoomId(autoIncrementId);
        autoIncrementId++;
        gameRoom.addPlayer(restGameRoomModel.getOwner());
    }

    /**
     * GET: Send a game room
     *
     * @param id identifier of game room
     * @return game room with owner and max of players
     */
    public GameRoomModel getGameRoom(int id){
        GameRoomModel room = new GameRoomModel();
        room.setMaxPlayers(gameRooms.get(id).getMaxPlayers());
        room.setOwner(gameRooms.get(id).getPlayers().get(0));
        room.setPlayers(gameRooms.get(id).getPlayers());
        return room;
    }

    /**
     * Get: Send a list of game rooms
     *
     * @return GameRooms
     */
    public Collection<GameRoomModel> listGameRooms(){

        List<GameRoomModel> currentGameRoomModels = new ArrayList<>();
        for (int i = 1; i <= gameRooms.size(); i++) {
            GameRoomModel room = new GameRoomModel();
            room.setName(gameRooms.get(i).getName());
            room.setMaxPlayers(gameRooms.get(i).getMaxPlayers());
            room.setOwner(gameRooms.get(i).getPlayers().get(0));
            room.setPlayers(gameRooms.get(i).getPlayers());
            currentGameRoomModels.add(room);
        }

        return currentGameRoomModels;
    }

    /**
     * Get: Get a player of a game room
     *
     * @param id game room identifier
     * @return player
     */
    public Collection<PlayerModel> getPlayers(int id){
        return gameRooms.get(id).getPlayers();
    }

    /**
     * Post: Add player to a game room
     *
     * @param player
     */

    public void addPlayer(PlayerModel player){
        GameRoom current = gameRooms.get(player.getRoomId());
        current.addPlayer(player);

        if(current.getMaxPlayers() == current.getPlayers().size()){
            gameService.createGame(current.createGame());
        }
    }

    public GameService getGameService() {
        return gameService;
    }

    public void setGameService(GameService gameService) {
        this.gameService = gameService;
    }
}
