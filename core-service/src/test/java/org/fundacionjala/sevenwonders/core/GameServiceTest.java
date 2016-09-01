package org.fundacionjala.sevenwonders.core;

import org.fundacionjala.sevenwonders.beans.GameService;
import org.fundacionjala.sevenwonders.core.rest.GameRoomModel;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.mockito.Mockito.*;

/**
 * Used to test all the functionality of the {@link org.fundacionjala.sevenwonders.beans.GameService}.
 * @Author: Vania Catorceno
 */
public class GameServiceTest {
    @Test
    public void createGameRoomAndCompareWithTotallyGameInTheListTest(){
        GameService gameService = new GameService();

        Game game = new Game(mock(List.class), mock(List.class));
        Game oneGame = new Game(mock(List.class), mock(List.class));

        gameService.createGame(game);
        gameService.createGame(oneGame);

        Assert.assertEquals(2, gameService.getGames().size());
    }

    @Test (expected = NullPointerException.class)
    public void failCreateGameRoomSendingNullGameTest(){
        GameService gameService = new GameService();

        Game game = null;
        gameService.createGame(game);

        Assert.assertEquals(0, gameService.getGames().size());
    }

    @Test
    public void getGameByIdTest(){
        GameService gameService = new GameService();
        GameRoomModel gameRoomModel = new GameRoomModel();
        gameRoomModel.setId(2);

        Game game = new Game(mock(List.class), mock(List.class));
        Game oneGame = new Game(mock(List.class), mock(List.class));

        gameService.createGame(game);
        gameService.createGame(oneGame);

        Assert.assertEquals(oneGame, gameService.getGame(gameRoomModel.getId()));
    }

    @Test
    public void failGetGameByIdTest(){
        GameService gameService = new GameService();

        Game game = new Game(mock(List.class), mock(List.class));
        gameService.createGame(game);

        Assert.assertNull(gameService.getGame(-1));
    }
}
