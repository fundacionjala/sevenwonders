package org.fundacionjala.sevenwonders.beans;

import org.fundacionjala.sevenwonders.core.Game;
import org.fundacionjala.sevenwonders.core.rest.GameRoomModel;
import org.fundacionjala.sevenwonders.core.rest.PlayerModel;
import org.fundacionjala.sevenwonders.core.rest.WonderModel;

/**
 * Created by Dell on 10/9/2016.
 */
public class GameServiceMock {
    private GameRoomService gameRoomService;
    private GameRoomModel gameRoomModel;
    private PlayerModel playerOne;
    private PlayerModel playerTwo;
    private PlayerModel playerThree;
    private WonderModel wonderOne;
    private WonderModel wonderTwo;
    private WonderModel wonderThree;
    public GameServiceMock() {
        initGame();
    }

    private void initGame() {
        initGameRoomModel();
        gameRoomService = new GameRoomService();
        gameRoomService.createGameRoom(gameRoomModel);
        gameRoomService.addPlayer(1, playerTwo);
        gameRoomService.addPlayer(1, playerThree);
        gameRoomService.startGame(1);
    }

    private void initGameRoomModel() {
        wonderOne.setCityName("Babylon");
        wonderOne.setCurrentSide("A");
        wonderTwo.setCityName("Rodhos");
        wonderTwo.setCurrentSide("A");
        wonderThree.setCityName("Ephesos");
        wonderThree.setCurrentSide("A");
        playerOne.setToken("123hjk");
        playerOne.setUserName("pedro");
        playerOne.setId(1);
        playerOne.setWonderModel(wonderOne);
        playerOne.setToken("123hsjk");
        playerOne.setUserName("juan");
        playerOne.setId(2);
        playerOne.setWonderModel(wonderOne);
        playerOne.setToken("13hjk");
        playerOne.setUserName("lucas");
        playerOne.setId(3);
        playerOne.setWonderModel(wonderOne);
        gameRoomModel = new GameRoomModel();
        gameRoomModel.setRoomName("babilOne");
        gameRoomModel.setId(1);
        gameRoomModel.setChannel("ch1");
        gameRoomModel.setMaxPlayers(3);
        gameRoomModel.setOwner(playerOne);
    }

    public GameService getGameService() {
        return  gameRoomService.getGameService();
    }

}
