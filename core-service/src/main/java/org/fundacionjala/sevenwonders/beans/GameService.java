/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */
package org.fundacionjala.sevenwonders.beans;

import com.google.common.base.Preconditions;
import org.fundacionjala.sevenwonders.core.Game;
import org.fundacionjala.sevenwonders.core.rest.CardModel;
import org.fundacionjala.sevenwonders.core.rest.DeckModel;
import org.fundacionjala.sevenwonders.core.rest.PlayerModel;
import org.fundacionjala.sevenwonders.core.rest.PrincipalGameModel;
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

}
