/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */
package org.fundacionjala.sevenwonders.beans;

import com.google.common.base.Preconditions;
import org.fundacionjala.sevenwonders.core.*;
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
    private int currentAge;

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

    public Player getPlayer(int id, PlayerModel playerModel){
        return games.get(id).getPlayers().stream().filter(b ->b.getName().equals(playerModel.getUserName())).findAny()									// If 'findAny' then return found
                .orElse(null);
    }

    public void addChooseCard(ChooseCardModel chooseCardModel) {
        List<Player> currentList = getGame(chooseCardModel.getId()).getPlayers();
        Player current = currentList.stream()
                .filter(itemPlayer ->itemPlayer.getName().equals(chooseCardModel.getNamePlayer()))
                .findAny().get();
        Card cards = current.getDeck().getCards().stream()
                .filter(itemCard -> itemCard.getName().equals(chooseCardModel.getNameCard()))
                .findAny().get();
        currentAge = ((Building) cards).getAge();
        games.get(chooseCardModel.getId()).addChooseCard(current, cards);
    }

    public int getAgeCard() {
        return currentAge;
    }
}
