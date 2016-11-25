/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */
package org.fundacionjala.sevenwonders.beans;

import com.google.common.base.Preconditions;
import org.fundacionjala.sevenwonders.core.Game;
import org.fundacionjala.sevenwonders.core.calculator.CalculatorType;
import org.fundacionjala.sevenwonders.core.rest.*;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Has the basic functionality that permit to rest service access and generate
 * data without obtain unusable information, this have the principal function
 * of sevenwonders.
 *
 * @author Juan Barahona
 */


@Component
public class GameService {
    private final Map<Integer, Game> games = new TreeMap<>();
    private int autoIncrementId;

    /**
     * Create a game service
     */
    public GameService(){
        autoIncrementId = 1;
    }

    /**
     * Post: Create a game with an identifier
     *
     * @param game
     */
    public void createGame(Game game) {
        Preconditions.checkNotNull(game);
        games.put(autoIncrementId, game);
        autoIncrementId++;
    }

    public PrincipalGameModel getLastCreated(){
        int id = autoIncrementId - 1;
        Game game = games.get(id);
        return convertToGameModel(game, id);
    }

    public List<PlayerModel> getPlayers(int id){
        Game game = games.get(id);
        return convertToGameModel(game, id).getPlayers();
    }

    private PrincipalGameModel convertToGameModel(Game game, int id){
        List<PlayerModel> players = new ArrayList<>();

        PrincipalGameModel gameModel = new PrincipalGameModel();
        gameModel.setId(id);
        game.getPlayers().forEach(player -> {
            PlayerModel playerModel = new PlayerModel();
            List<CardModel> cards = new ArrayList<CardModel>();
            player.getDeck().getCards().stream().forEach( card -> {
                CardModel cardModel = new CardModel();
                cardModel.setName(card.getName());
                cards.add(cardModel);
            });
            DeckModel deckModel = new DeckModel();
            deckModel.setCards(cards);
            playerModel.setDeck(deckModel);

            CityModel city = new CityModel();
            WonderModel wonder = playerModel.getWonderModel();
            city.setName(wonder.getCityName());
            city.setWonder(wonder);
            city.setStoragePoint(new StoragePointModel());
            playerModel.setCity(city);

            playerModel.setUserName(player.getName());
            players.add(playerModel);
        });

        gameModel.setPlayers(players);
        return gameModel;
    }

    /**
     * Get the list games in collection
     * @return collection of the game created
     */
    public Collection<Game> getGames(){
        return games.values();
    }

    /**
     * Get the game by id
     * @param id integer
     * @return a game found by id
     */
    public Game getGame(int id){
        return games.get(id);
    }

    /**
     * Get the points to according to calculator type of an player and game.
     * @param points {@link PointsModel}
     * @return points found by id of the player and game.
     */
    public int getPoints(PointsModel points) {
        Preconditions.checkNotNull(points);
        return  getPlayers(points.getGameId()).stream().filter(b -> b.getId() == points.getPlayerId()).findAny().orElse(null)
                .getCity().getStoragePoint().getPoint((points.convertCalculator()));
    }
}
