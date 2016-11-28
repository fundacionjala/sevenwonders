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

    @Test(expected = NullPointerException.class)
    public void FailToGetDeckFromPlayerTest() {

        GameService gameService = new GameService();

        PlayerModel playerModel = null;
        int gameNumber = 4;

        gameService.getPlayer(gameNumber,playerModel);

        Assert.assertEquals(0, gameService.getGames().size());
    }

    /*
     * Checks if the decks form each player in game has a deck instatiated
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

        for (PlayerModel current: gameService.getPlayers(1)) {
            Assert.assertTrue(current.getDeck() != null);
        }
    }

//    @Test
    public void getDeckFromAsinglePlayerTest(){
        GameService gameService = new GameService();
        GameRoom gameRoom = new GameRoom("Battle", 3);

        PlayerModel firstPlayer = new PlayerModel();
        firstPlayer.setId(1);
        firstPlayer.setUserName("Gicck");
        firstPlayer.setCity(mock(CityModel.class));
        firstPlayer.setWonderModel(mock(WonderModel.class));

        DeckModel deckModel = new DeckModel();

        CardModel civicCard = new CardModel();
        civicCard.setName("it's a civic Card");
        CardModel resourceCard = new CardModel();
        resourceCard.setName("it's a resource Card");
        CardModel scientificCard = new CardModel();
        scientificCard.setName("it's a scientific Card");

        List<CardModel> cardModels = new ArrayList<>();
        cardModels.add(civicCard);
        cardModels.add(resourceCard);
        cardModels.add(scientificCard);

        deckModel.setCards(cardModels);

        firstPlayer.getDeck();
        firstPlayer.setDeck(deckModel);

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

//        Assert.assertTrue(gameService.getPlayers(1).get(1).getDeck().equals(deckModel));
        DeckModel expected = gameService.getPlayers(1).get(0).getDeck();
//        Assert.assertEquals(expected, deckModel);

    }
}
