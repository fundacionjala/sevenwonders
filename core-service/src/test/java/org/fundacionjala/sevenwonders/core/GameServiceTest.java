package org.fundacionjala.sevenwonders.core;

import org.fundacionjala.sevenwonders.beans.GameService;
import org.fundacionjala.sevenwonders.core.calculator.CalculatorType;
import org.fundacionjala.sevenwonders.core.rest.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
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

    /**
     * It tests when the {@link PointsModel} is null an exception {@link NullPointerException} is thrown.
     */
    @Test (expected = NullPointerException.class)
    public void testFailToTheGetPointsOfANullPointsModel(){
        GameService gameService = new GameService();

        PointsModel points = null;
        gameService.getPoints(points);

        Assert.assertEquals(0, gameService.getGames().size());
    }

    /**
     *
     */
    @Test
    public void testGetPointsByCalculatorTypeByDefault_0() {
        GameService gameService = new GameService();
        Wonder wonder = new Wonder("Rhodos");
        Player player = new Player("Johx", new City(wonder, new StoragePoint(), new Storage()));
        List<Player> players = new ArrayList<>();
        Game game= new Game(players, new ArrayList<>());

        GameRoom room = new GameRoom("None", 3);

        WonderModel wonderModel = new WonderModel();
        wonderModel.setCityName("Babylon");
        wonderModel.setCurrentSide("a");

        CityModel cityModel = new CityModel();
        cityModel.setName(wonderModel.getCityName());
        cityModel.setWonder(wonderModel);
        cityModel.setStoragePoint(new StoragePointModel());

        PlayerModel playerModel = new PlayerModel();
        playerModel.setId(1);
        playerModel.setUserName("Johx");
        playerModel.setWonderModel(wonderModel);
        playerModel.setCity(cityModel);

        WonderModel wonderModel1 = new WonderModel();
        wonderModel1.setCityName("Efhesos");
        wonderModel1.setCurrentSide("a");

        CityModel cityModel1= new CityModel();
        cityModel1.setName(wonderModel.getCityName());
        cityModel1.setWonder(wonderModel);
        cityModel1.setStoragePoint(new StoragePointModel());

        PlayerModel playerModel1 = new PlayerModel();
        playerModel1.setId(2);
        playerModel1.setUserName("Johx1");
        playerModel1.setWonderModel(wonderModel);
        playerModel1.setCity(cityModel);

        WonderModel wonderModel2 = new WonderModel();
        wonderModel2.setCityName("Rhodos");
        wonderModel2.setCurrentSide("a");

        CityModel cityModel2 = new CityModel();
        cityModel2.setName(wonderModel.getCityName());
        cityModel2.setWonder(wonderModel);
        cityModel2.setStoragePoint(new StoragePointModel());

        PlayerModel playerModel2 = new PlayerModel();
        playerModel2.setId(3);
        playerModel2.setUserName("Johx3");
        playerModel2.setWonderModel(wonderModel);
        playerModel2.setCity(cityModel);

        room.addPlayer(playerModel);
        room.addPlayer(playerModel1);
        room.addPlayer(playerModel2);
        gameService.createGame(room.createGame());

        PointsModel points = new PointsModel();
        points.setPlayerId(1);
        points.setGameId(1);
        points.setCalculatorType(1);

        int expectedPoints = 0;
        int currentPoints = gameService.getPoints(points);

        Assert.assertEquals("The points do not match" ,expectedPoints, currentPoints);

    }
    /**
     *of points when added by a type.
     */
    public void testGetPointsWhenAddedByCalculatorTypeInStoragePoints() {
        GameService gameService = new GameService();

        GameRoom gameRoom = new GameRoom("Battle wonders", 3);
        Game game = gameRoom.createGame();
        gameService.createGame(game);

        PrincipalGameModel gameModel = gameService.getLastCreated();
        List<PlayerModel> players = new ArrayList<>();
        PlayerModel player = new PlayerModel();

        CityModel city = new CityModel();
        city.setWonder(new WonderModel());

        StoragePointModel storage = new StoragePointModel();
        storage.addPointsType(CalculatorType.CIVIC, 5);

        city.setStoragePoint(storage);

        player.setCity(city);
        players.add(1, player);

        gameModel.setPlayers(players);

        PointsModel points = new PointsModel();
        points.setPlayerId(1);
        points.setGameId(1);
        points.setCalculatorType(1);

        int expectedPoints = 0;
        int currentPoints = gameService.getPoints(points);

        Assert.assertEquals("The points do not match" ,expectedPoints, currentPoints);

    }
}
