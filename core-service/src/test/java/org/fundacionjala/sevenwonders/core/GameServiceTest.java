package org.fundacionjala.sevenwonders.core;

import org.fundacionjala.sevenwonders.beans.GameService;
import org.fundacionjala.sevenwonders.core.rest.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;

/**
 * Used to test all the functionality of the {@link org.fundacionjala.sevenwonders.beans.GameService}.
 *
 * @Author: Vania Catorceno
 */
public class GameServiceTest {
    @Test
    public void createGameRoomAndCompareWithTotallyGameInTheListTest() {
        GameService gameService = new GameService();

        Game game = new Game(mock(List.class), mock(List.class));
        Game oneGame = new Game(mock(List.class), mock(List.class));

        gameService.createGame(game);
        gameService.createGame(oneGame);

        Assert.assertEquals(2, gameService.getGames().size());
    }

    @Test(expected = NullPointerException.class)
    public void failCreateGameRoomSendingNullGameTest() {
        GameService gameService = new GameService();

        Game game = null;
        gameService.createGame(game);

        Assert.assertEquals(0, gameService.getGames().size());
    }

    @Test
    public void getGameByIdTest() {
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
    public void failGetGameByIdTest() {
        GameService gameService = new GameService();

        Game game = new Game(mock(List.class), mock(List.class));
        gameService.createGame(game);

        Assert.assertNull(gameService.getGame(-1));
    }

    /**
     * It tests when the {@link PointsModel} is null an exception {@link NullPointerException} is thrown.
     */
    @Test(expected = NullPointerException.class)
    public void testFailToTheGetPointsOfANullPointsModel() {
        GameService gameService = new GameService();

        PointsModel points = null;
        gameService.getPoints(points);

        Assert.assertEquals(0, gameService.getGames().size());
    }

    /**
     * It tests to get by default points by calculator type.
     */
    @Test
    public void testGetPointsByCalculatorTypeByDefault_0() {
        GameService gameService = new GameService();
        GameRoom room = new GameRoom("None", 3);

        WonderModel firstWonderModel = new WonderModel();
        firstWonderModel.setCityName("Babylon");
        firstWonderModel.setCurrentSide("a");

        CityModel firsCityModel = new CityModel();
        firsCityModel.setName(firstWonderModel.getCityName());
        firsCityModel.setWonder(firstWonderModel);
        firsCityModel.setStoragePoint(new StoragePointModel());

        PlayerModel firstPlayerModel = new PlayerModel();
        firstPlayerModel.setId(1);
        firstPlayerModel.setUserName("Johx");
        firstPlayerModel.setWonderModel(firstWonderModel);
        firstPlayerModel.setCity(firsCityModel);

        WonderModel secondWonderModel = new WonderModel();
        secondWonderModel.setCityName("Efhesos");
        secondWonderModel.setCurrentSide("a");

        CityModel secondCityModel = new CityModel();
        secondCityModel.setName(secondWonderModel.getCityName());
        secondCityModel.setWonder(secondWonderModel);
        secondCityModel.setStoragePoint(new StoragePointModel());

        PlayerModel secondPlayerModel = new PlayerModel();
        secondPlayerModel.setId(2);
        secondPlayerModel.setUserName("Johx1");
        secondPlayerModel.setWonderModel(secondWonderModel);
        secondPlayerModel.setCity(secondCityModel);

        WonderModel thirdWonderModel = new WonderModel();
        thirdWonderModel.setCityName("Rhodos");
        thirdWonderModel.setCurrentSide("a");

        CityModel thirdCityModel = new CityModel();
        thirdCityModel.setName(thirdWonderModel.getCityName());
        thirdCityModel.setWonder(thirdWonderModel);
        thirdCityModel.setStoragePoint(new StoragePointModel());

        PlayerModel thirdPlayerModel = new PlayerModel();
        thirdPlayerModel.setId(3);
        thirdPlayerModel.setUserName("Johx3");
        thirdPlayerModel.setWonderModel(thirdWonderModel);
        thirdPlayerModel.setCity(thirdCityModel);

        room.addPlayer(firstPlayerModel);
        room.addPlayer(secondPlayerModel);
        room.addPlayer(thirdPlayerModel);
        gameService.createGame(room.createGame());

        PointsModel points = new PointsModel();
        points.setPlayerId(1);
        points.setGameId(1);
        points.setCalculatorType(1);

        int expectedPoints = 0;
        int currentPoints = gameService.getPoints(points);

        Assert.assertEquals("The points do not match", expectedPoints, currentPoints);

    }

    /**
     * Tests the creation of {@Link PlayerModel} from a initialization of a {@Link GameService}
     */
    @Test
    public void getPlayerModelsTest() {
        GameService gameService = new GameService();
        GameRoom room = new GameRoom("Battle", 3);

        room.addPlayer(mock(PlayerModel.class));
        room.addPlayer(mock(PlayerModel.class));
        room.addPlayer(mock(PlayerModel.class));

        gameService.createGame(room.createGame());

        List<PlayerModel> expected = gameService.getPlayers(gameService.getLastCreated().getId());
        Assert.assertNotNull(expected);
        for (PlayerModel expectedCurrent : expected) {
            Assert.assertNotNull(expectedCurrent);
        }
    }

    /**
     * Tests the creation of {@Link PlayerModel} by its Id from a initialization of a {@Link GameService}
     */
    @Test
    public void getPlayerModelById() {
        GameService gameService = new GameService();
        GameRoom room = new GameRoom("Battle", 3);

        room.addPlayer(mock(PlayerModel.class));
        room.addPlayer(mock(PlayerModel.class));
        PlayerModel firstPlayerModel = new PlayerModel();
        firstPlayerModel.setId(45);
        room.addPlayer(firstPlayerModel);

        gameService.createGame(room.createGame());

        int expected = gameService.getPlayerModelById(1, 45).getId();
        int result = firstPlayerModel.getId();
        Assert.assertEquals(expected, result);
    }

    /**
     * Tests the creation of {@Link PlayerModel} with a {@Link CityModel} by its Id.
     */
    @Test
    public void getCityFromPlayerModelTest() {
        GameService gameService = new GameService();
        GameRoom room = new GameRoom("Battle", 3);

        room.addPlayer(mock(PlayerModel.class));
        PlayerModel firstPlayerModel = new PlayerModel();
        firstPlayerModel.setId(45);
        firstPlayerModel.setUserName("Gicck");

        room.addPlayer(mock(PlayerModel.class));
        room.addPlayer(firstPlayerModel);

        gameService.createGame(room.createGame());

        CityModel expected = gameService.getPlayerModelById(1, 45).getCity();

        Assert.assertTrue(expected instanceof CityModel);
        Assert.assertNotNull(expected.getName());
        Assert.assertNotNull(expected.getWonder());
        Assert.assertNotNull(expected.getStoragePoint());
    }

    /**
     * Tests the creation of {@Link PlayerModel} with a {@Link CityModel} with a {@Link BuildingModel}.
     */
    @Test
    public void getBuildingsFromPlayerModelTest() {
        GameService gameService = new GameService();
        GameRoom room = new GameRoom("Battle", 3);

        room.addPlayer(mock(PlayerModel.class));
        PlayerModel firstPlayerModel = new PlayerModel();
        firstPlayerModel.setId(45);
        firstPlayerModel.setUserName("Gicck");

        WonderModel wonder = new WonderModel();

        room.addPlayer(mock(PlayerModel.class));
        room.addPlayer(firstPlayerModel);

        gameService.createGame(room.createGame());

        CityModel cityModel = new CityModel();
        List<BuildingModel> buildings = new ArrayList<>();

        BuildingModel civicBuildingModel = new BuildingModel();
        civicBuildingModel.setName("Civic Building");
        civicBuildingModel.setBuildingType(BuildingType.CIVIC);

        BuildingModel resourceBuildingModel = new BuildingModel();
        resourceBuildingModel.setName("Resource Building");
        resourceBuildingModel.setBuildingType(BuildingType.RESOURCE);

        BuildingModel scientificBuildingModel = new BuildingModel();
        scientificBuildingModel.setName("Scientific Building");
        scientificBuildingModel.setBuildingType(BuildingType.SCIENTIFIC);

        buildings.add(civicBuildingModel);
        buildings.add(resourceBuildingModel);
        buildings.add(scientificBuildingModel);

        cityModel.setBuildings(buildings);
        PlayerModel currentPlayerModel = gameService.getPlayerModelById(1, 45);
        currentPlayerModel.setCity(cityModel);

        CityModel expected = currentPlayerModel.getCity();

        Assert.assertTrue(expected != null);
        Assert.assertNotNull(expected.getBuildings());
        Assert.assertTrue(expected.getBuildingsAsEnumMap().containsKey(BuildingType.CIVIC));
        Assert.assertTrue(expected.getBuildingsAsEnumMap().containsKey(BuildingType.RESOURCE));
        Assert.assertTrue(expected.getBuildingsAsEnumMap().containsKey(BuildingType.SCIENTIFIC));
    }


    @Test(expected = NullPointerException.class)
    public void failToGetCityFromPlayerTest() {

        GameService gameService = new GameService();

        PlayerModel playerModel = mock(PlayerModel.class);
        int gameNumber = 4;


        Assert.assertEquals(0, gameService.getPlayer(gameNumber, playerModel));
    }

    /**
     * When {@link PlayerModel} is null it throws an exception
     */
    @Test(expected = NullPointerException.class)
    public void failToGetDeckFromPlayerTest() {

        GameService gameService = new GameService();

        PlayerModel playerModel = null;
        int gameNumber = 4;

        gameService.getPlayer(gameNumber, playerModel);

        Assert.assertEquals(0, gameService.getGames().size());
    }

    /**
     * Checks if the deck form each player in game has a deck instantiated
     */
    @Test
    public void getDeckFromPlayersTest() {
        GameService gameService = new GameService();
        GameRoom gameRoom = new GameRoom("Battle", 3);

        PlayerModel firstPlayer = new PlayerModel();
        firstPlayer.setId(1);
        firstPlayer.setUserName("Gicck");
        firstPlayer.setCity(mock(CityModel.class));
        firstPlayer.setWonderModel(mock(WonderModel.class));
        firstPlayer.setDeck(new DeckModel());

        PlayerModel secondPlayer = new PlayerModel();
        secondPlayer.setId(2);
        secondPlayer.setUserName("Richi");
        secondPlayer.setCity(mock(CityModel.class));
        secondPlayer.setWonderModel(mock(WonderModel.class));
        secondPlayer.setDeck(new DeckModel());

        PlayerModel thirdPlayer = new PlayerModel();
        thirdPlayer.setId(3);
        thirdPlayer.setUserName("Gumu");
        thirdPlayer.setCity(mock(CityModel.class));
        thirdPlayer.setWonderModel(mock(WonderModel.class));
        thirdPlayer.setDeck(new DeckModel());

        gameRoom.addPlayer(firstPlayer);
        gameRoom.addPlayer(secondPlayer);
        gameRoom.addPlayer(thirdPlayer);

        gameService.createGame(gameRoom.createGame());

        for (PlayerModel current : gameService.getPlayers(1)) {
            Assert.assertTrue(current.getDeck() != null);
        }
    }

}
