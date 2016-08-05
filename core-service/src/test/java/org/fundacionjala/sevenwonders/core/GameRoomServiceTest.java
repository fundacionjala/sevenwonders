/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */
package org.fundacionjala.sevenwonders.core;

import org.fundacionjala.sevenwonders.beans.GameRoomService;
import org.fundacionjala.sevenwonders.core.rest.*;
import org.fundacionjala.sevenwonders.core.rest.GameRoom;
import org.fundacionjala.sevenwonders.core.rest.Player;
import org.junit.Assert;
import org.junit.Test;

/**
 * Used to test all the functionality of the {@link org.fundacionjala.sevenwonders.beans.GameRoomService}.
 *
 * @author Juan Barahona
 */
public class GameRoomServiceTest {

    @Test
    public void postAndGetGameRoomTest(){
        GameRoomService gameRoomService = new GameRoomService();
        org.fundacionjala.sevenwonders.core.rest.GameRoom gameRoom = new GameRoom();
        org.fundacionjala.sevenwonders.core.rest.Player player = new Player();

        player.setName("Juan");
        gameRoom.setMaxPlayers(3);
        gameRoom.setOwner(player);

        gameRoomService.createGameRoom(gameRoom);

        Assert.assertEquals("1", gameRoomService.getGameRoom("1").getOwner().getRoomId());
    }

    @Test
    public void addPlayerToGameRoomTest(){
        GameRoomService gameRoomService = new GameRoomService();
        org.fundacionjala.sevenwonders.core.rest.GameRoom gameRoom = new GameRoom();
        org.fundacionjala.sevenwonders.core.rest.Player player = new Player();
        player.setName("Juan");

        gameRoom.setMaxPlayers(3);
        gameRoom.setOwner(player);

        gameRoomService.createGameRoom(gameRoom);

        org.fundacionjala.sevenwonders.core.rest.Player playerOne = new Player();
        playerOne.setName("Dwits");
        playerOne.setRoomId("1");

        gameRoomService.addPlayer(playerOne);

        Assert.assertEquals(2, gameRoomService.getGameRoom("1").getPlayers().size());
    }

    @Test
    public void automaticCreateGameWhenFullRoom(){
        GameRoomService gameRoomService = new GameRoomService();
        org.fundacionjala.sevenwonders.core.rest.GameRoom gameRoom = new GameRoom();
        org.fundacionjala.sevenwonders.core.rest.Player player = new Player();
        player.setName("Juan");

        gameRoom.setMaxPlayers(3);
        gameRoom.setOwner(player);

        gameRoomService.createGameRoom(gameRoom);

        org.fundacionjala.sevenwonders.core.rest.Player playerOne = new Player();
        playerOne.setName("Dwits");
        playerOne.setRoomId("1");

        gameRoomService.addPlayer(playerOne);

        Assert.assertEquals(0, gameRoomService.getGameService().getGames().size());

        org.fundacionjala.sevenwonders.core.rest.Player playerTwo = new Player();
        playerTwo.setName("Lucero");
        playerTwo.setRoomId("1");

        gameRoomService.addPlayer(playerTwo);

        Assert.assertEquals(1, gameRoomService.getGameService().getGames().size());

    }
}
