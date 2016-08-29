/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */
package org.fundacionjala.sevenwonders.core;

/**
 * Used to test all the functionality of the {@link org.fundacionjala.sevenwonders.beans.GameRoomService}.
 *
 * @author Juan Barahona
 */
public class GameRoomModelServiceTest {
/*
    @Test
    public void postAndGetGameRoomTest(){
        GameRoomService gameRoomService = new GameRoomService();
        GameRoomModel gameRoomModel = new GameRoomModel();
        PlayerModel player = new PlayerModel();

        player.setName("Juan");
        gameRoomModel.setMaxPlayers(3);
        gameRoomModel.setOwner(player);

        gameRoomService.createGameRoom(gameRoomModel);

        Assert.assertEquals("1", gameRoomService.getGameRoom(1).getOwner().getRoomId());
    }

    @Test
    public void addPlayerToGameRoomTest(){
        GameRoomService gameRoomService = new GameRoomService();
        GameRoomModel gameRoomModel = new GameRoomModel();
        PlayerModel player = new PlayerModel();
        player.setName("Juan");

        gameRoomModel.setMaxPlayers(3);
        gameRoomModel.setOwner(player);

        gameRoomService.createGameRoom(gameRoomModel);

        PlayerModel playerOne = new PlayerModel();
        playerOne.setName("Dwits");
        playerOne.setRoomId(1);

        gameRoomService.addPlayer(playerOne);

        Assert.assertEquals(2, gameRoomService.getGameRoom(1).getPlayers().size());
    }

    @Test
    public void automaticCreateGameWhenFullRoom(){
        GameRoomService gameRoomService = new GameRoomService();
        GameRoomModel gameRoomModel = new GameRoomModel();
        PlayerModel player = new PlayerModel();
        player.setName("Juan");

        gameRoomModel.setMaxPlayers(3);
        gameRoomModel.setOwner(player);

        gameRoomService.createGameRoom(gameRoomModel);

        PlayerModel playerOne = new PlayerModel();
        playerOne.setName("Dwits");
        playerOne.setRoomId(1);

        gameRoomService.addPlayer(playerOne);

        Assert.assertEquals(0, gameRoomService.getGameService().getGames().size());

        PlayerModel playerTwo = new PlayerModel();
        playerTwo.setName("Lucero");
        playerTwo.setRoomId(1);

        gameRoomService.addPlayer(playerTwo);

        Assert.assertEquals(1, gameRoomService.getGameService().getGames().size());

    }
    */
}
