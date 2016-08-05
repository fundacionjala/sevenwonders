package org.fundacionjala.sevenwonders.beans;

import org.fundacionjala.sevenwonders.core.*;
import org.fundacionjala.sevenwonders.core.GameRoom;
import org.fundacionjala.sevenwonders.core.Player;
import org.fundacionjala.sevenwonders.core.StoragePoint;
import org.fundacionjala.sevenwonders.core.rest.*;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by Juan Manuel Barahona on 04/08/2016.
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

    public org.fundacionjala.sevenwonders.core.rest.GameRoom getGameRoom(String id){
        org.fundacionjala.sevenwonders.core.rest.GameRoom room = new org.fundacionjala.sevenwonders.core.rest.GameRoom();
        room.setMaxPlayers(gameRooms.get(id).getMaxPlayers());
        room.setOwner(gameRooms.get(id).getPlayers().get(0));
        return room;
    }

    public void createGameRoom(org.fundacionjala.sevenwonders.core.rest.GameRoom restGameRoom){
        GameRoom gameRoom = new GameRoom(restGameRoom.getMaxPlayers());
        gameRooms.put(autoIncrementId, gameRoom);
        restGameRoom.getOwner().setRoomId(autoIncrementId);
        autoIncrementId = (Integer.parseInt(autoIncrementId) + 1) + "";
        gameRoom.addPlayer(restGameRoom.getOwner());
    }

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

    public Collection<org.fundacionjala.sevenwonders.core.rest.Player> getPlayers(String id){
        return gameRooms.get(id).getPlayers();
    }

    public void addPlayer(org.fundacionjala.sevenwonders.core.rest.Player player){
        GameRoom current = gameRooms.get(player.getRoomId());
        current.addPlayer(player);

        if(current.getMaxPlayers() == current.getPlayers().size()){
            gameService.createGame(current.createGame());
        }
    }
}
