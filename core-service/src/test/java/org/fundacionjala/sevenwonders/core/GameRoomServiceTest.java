/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */
package org.fundacionjala.sevenwonders.core;

import org.fundacionjala.sevenwonders.beans.GameRoomService;
import org.fundacionjala.sevenwonders.core.rest.GameRoomModel;
import org.fundacionjala.sevenwonders.core.rest.PlayerModel;
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
        GameRoomModel gameRoomModel = new GameRoomModel();
        PlayerModel player = new PlayerModel();

        player.setUserName("Juan");
        gameRoomModel.setMaxPlayers(3);
        gameRoomModel.setOwner(player);

        gameRoomService.createGameRoom(gameRoomModel);

        Assert.assertNotNull(gameRoomService.getGameRoom(1).getOwner());
    }

    @Test (expected = NullPointerException.class)
    public void failCreateGameRoomWhenOwnerPlayerIsNullTest(){
        GameRoomService gameRoomService = new GameRoomService();
        GameRoomModel gameRoomModel = new GameRoomModel();

        gameRoomModel.setMaxPlayers(3);
        gameRoomModel.setOwner(null);

        gameRoomService.createGameRoom(gameRoomModel);

        Assert.fail();
    }

    @Test (expected = NullPointerException.class)
    public void failedPostGameRoomModelForCreateGameRoom(){
        GameRoomService gameRoomService = new GameRoomService();

        gameRoomService.createGameRoom(null);

        Assert.fail();
    }

    @Test
    public void addPlayerToGameRoomTest(){
        GameRoomService gameRoomService = new GameRoomService();
        GameRoomModel gameRoomModel = new GameRoomModel();
        PlayerModel player = new PlayerModel();
        player.setUserName("Juan");

        gameRoomModel.setMaxPlayers(3);
        gameRoomModel.setOwner(player);

        gameRoomModel = gameRoomService.createGameRoom(gameRoomModel);

        PlayerModel playerOne = new PlayerModel();
        playerOne.setUserName("Dwits");

        gameRoomService.addPlayer(gameRoomModel.getId(),playerOne);

        Assert.assertEquals(2, gameRoomService.getGameRoom(1).getPlayers().size());
    }

    @Test (expected = Exception.class)
    public void FailAddPlayerWhenMaxPlayersIsThreeGameRoomTest(){
        GameRoomService gameRoomService = new GameRoomService();
        GameRoomModel gameRoomModel = new GameRoomModel();
        PlayerModel playerOwner = new PlayerModel();
        playerOwner.setUserName("Rosario");

        gameRoomModel.setMaxPlayers(3);
        gameRoomModel.setOwner(playerOwner);

        gameRoomModel = gameRoomService.createGameRoom(gameRoomModel);

        PlayerModel onePlayer = new PlayerModel();
        onePlayer.setUserName("Marco");

        gameRoomService.addPlayer(gameRoomModel.getId(), onePlayer);

        PlayerModel secondPlayer = new PlayerModel();
        secondPlayer.setUserName("Miguel");

        PlayerModel thirdPlayer = new PlayerModel();
        thirdPlayer.setUserName("jose");

        gameRoomService.addPlayer(gameRoomModel.getId(), secondPlayer);
        gameRoomService.addPlayer(gameRoomModel.getId(), thirdPlayer);

        Assert.fail();
    }

    @Test (expected = Exception.class)
    public void failAddNullPlayerTest(){
        GameRoomService gameRoomService = new GameRoomService();
        GameRoomModel gameRoomModel = new GameRoomModel();
        PlayerModel playerOwner = new PlayerModel();
        playerOwner.setUserName("Rosario");

        gameRoomModel.setMaxPlayers(3);
        gameRoomModel.setOwner(playerOwner);

        gameRoomModel = gameRoomService.createGameRoom(gameRoomModel);

        gameRoomService.addPlayer(gameRoomModel.getId(), null);

        Assert.fail();
    }

    @Test
    public void validatePlayerIntoGameRoomWithJoin(){
        GameRoomService gameRoomService = new GameRoomService();
        GameRoomModel gameRoomModel = new GameRoomModel();
        PlayerModel playerOwner = new PlayerModel();
        playerOwner.setUserName("Rosario");
        playerOwner.setId(2);

        gameRoomModel.setMaxPlayers(3);
        gameRoomModel.setOwner(playerOwner);

        gameRoomModel = gameRoomService.createGameRoom(gameRoomModel);
        gameRoomService.addPlayer(gameRoomModel.getId(), playerOwner);

        PlayerModel onePlayer = new PlayerModel();
        onePlayer.setUserName("Matias");
        onePlayer.setId(3);

        gameRoomService.addPlayer(gameRoomModel.getId(), onePlayer);

        Assert.assertEquals(onePlayer, gameRoomService.validateGame(gameRoomModel.getId(), onePlayer));
    }

    @Test
    public void validatePlayerIntoRoomWithOutJoin(){
        GameRoomService gameRoomService = new GameRoomService();
        GameRoomModel gameRoomModel = new GameRoomModel();
        PlayerModel playerOwner = new PlayerModel();
        playerOwner.setUserName("Rosario");
        playerOwner.setId(2);

        gameRoomModel.setMaxPlayers(3);
        gameRoomModel.setOwner(playerOwner);

        gameRoomModel = gameRoomService.createGameRoom(gameRoomModel);
        gameRoomService.addPlayer(gameRoomModel.getId(), playerOwner);

        PlayerModel onePlayer = new PlayerModel();
        onePlayer.setUserName("Matias");
        onePlayer.setId(3);

        Assert.assertNull(gameRoomService.validateGame(gameRoomModel.getId(), onePlayer));
    }

    @Test
    public void updateInformationWonderForPlayer(){
        GameRoomService gameRoomService = new GameRoomService();
        GameRoomModel gameRoomModel = new GameRoomModel();

        PlayerModel playerOwner = new PlayerModel();
        playerOwner.setUserName("Rosario");
        playerOwner.setId(2);

        gameRoomModel.setMaxPlayers(3);
        gameRoomModel.setOwner(playerOwner);

        gameRoomModel = gameRoomService.createGameRoom(gameRoomModel);
        gameRoomService.addPlayer(gameRoomModel.getId(), playerOwner);

        PlayerModel onePlayer = new PlayerModel();
        onePlayer.setUserName("Matias");
        onePlayer.setId(3);

        gameRoomService.addPlayer(gameRoomModel.getId(), onePlayer);

        Assert.assertEquals(onePlayer, gameRoomService.validateGame(gameRoomModel.getId(), onePlayer));

    }

    @Test
    public void startGameWhenFullRoomTest(){
        GameRoomService gameRoomService = new GameRoomService();
        GameRoomModel gameRoomModel = new GameRoomModel();
        PlayerModel player = new PlayerModel();
        player.setUserName("Juan");

        gameRoomModel.setMaxPlayers(3);
        gameRoomModel.setOwner(player);

        gameRoomService.createGameRoom(gameRoomModel);

        PlayerModel playerOne = new PlayerModel();
        playerOne.setUserName("Dwits");

        gameRoomService.addPlayer(gameRoomModel.getId(), playerOne);

        Assert.assertEquals(0, gameRoomService.getGameService().getGames().size());

        PlayerModel playerTwo = new PlayerModel();
        playerTwo.setUserName("Lucero");

        gameRoomService.addPlayer(gameRoomModel.getId(), playerTwo);

        gameRoomService.startGame(gameRoomModel.getId());

        Assert.assertEquals(1, gameRoomService.getGameService().getGames().size());
    }

    @Test
    public void updateSideWonder(){
        GameRoomService gameRoomService = new GameRoomService();
        GameRoomModel gameRoomModel = new GameRoomModel();
        PlayerModel player = new PlayerModel();
        player.setUserName("Juan");

        gameRoomModel.setMaxPlayers(3);
        gameRoomModel.setOwner(player);

        gameRoomService.createGameRoom(gameRoomModel);

        PlayerModel playerOne = new PlayerModel();
        playerOne.setUserName("Dwits");
        playerOne.setId(2);

        gameRoomService.addPlayer(gameRoomModel.getId(), playerOne);

        Assert.assertEquals(0, gameRoomService.getGameService().getGames().size());

        PlayerModel playerTwo = new PlayerModel();
        playerTwo.setUserName("Lucero");

        gameRoomService.addPlayer(gameRoomModel.getId(), playerTwo);

        gameRoomService.startGame(gameRoomModel.getId());

        playerOne.getWonderModel().setCurrentSide("B");
        Assert.assertEquals(playerOne.getWonderModel().getCurrentSide(),gameRoomService.
                                                                        updateSideWonder(gameRoomModel.getId(), playerOne).
                                                                        getWonderModel().getCurrentSide());
    }

    @Test
    public void falseIsCompletePlayerIsListPlayersIsNotComplete(){
        GameRoomService gameRoomService = new GameRoomService();
        GameRoomModel gameRoomModel = new GameRoomModel();

        PlayerModel player = new PlayerModel();
        player.setUserName("Lucero");

        gameRoomModel.setMaxPlayers(3);
        gameRoomModel.setOwner(player);

        gameRoomService.createGameRoom(gameRoomModel);

        PlayerModel playerOne = new PlayerModel();
        playerOne.setUserName("Daniel");

        gameRoomService.addPlayer(gameRoomModel.getId(), playerOne);

        Assert.assertFalse(gameRoomService.isCompletedPlayers(gameRoomModel.getId()));
    }

    @Test
    public void testVerifyQuantityOfPlayers() {
        GameRoomService gameRoomService = new GameRoomService();
        GameRoomModel gameRoomModel = new GameRoomModel();
        PlayerModel player = new PlayerModel();
        player.setUserName("Diego");

        gameRoomModel.setMaxPlayers(5);
        gameRoomModel.setOwner(player);

        gameRoomService.createGameRoom(gameRoomModel);

        PlayerModel playerTwo = new PlayerModel();
        playerTwo.setUserName("Dwits");

        gameRoomService.addPlayer(gameRoomModel.getId(), playerTwo);

        Assert.assertFalse(gameRoomService.isCompletedPlayers(gameRoomModel.getId()));

        PlayerModel playerThree = new PlayerModel();
        playerThree.setUserName("Lucho");

        gameRoomService.addPlayer(gameRoomModel.getId(), playerThree);

        Assert.assertFalse(gameRoomService.isCompletedPlayers(gameRoomModel.getId()));

        PlayerModel playerFour = new PlayerModel();
        playerFour.setUserName("Vania");

        gameRoomService.addPlayer(gameRoomModel.getId(), playerFour);

        Assert.assertFalse(gameRoomService.isCompletedPlayers(gameRoomModel.getId()));

        PlayerModel playerFive = new PlayerModel();
        playerFive.setUserName("Ale");

        gameRoomService.addPlayer(gameRoomModel.getId(), playerFive);

        Assert.assertTrue(gameRoomService.isCompletedPlayers(gameRoomModel.getId()));


    }
}
