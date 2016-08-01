/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */
package org.fundacionjala.sevenwonders.core;

import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import org.fundacionjala.sevenwonders.exception.NoEnoughPlayersException;

public class GameBuilder {

    private List<Player> players;
    private List<Age> ages;
    public static final int MIN_PLAYERS = 3;

    /**
     * When initializing the game, the player has 3 initial coins.
     */
    public static final int INITIAL_COINS = 3;

    /**
     * Sets a list of players to {@link GameBuilder}.
     *
     * @param players assigned a list of players.
     * @return GameBuilder
     */
    public GameBuilder setPlayers(List<Player> players) {
        this.players = players;
        return this;
    }

    /**
     * Sets a list of ages to {@link GameBuilder}.
     *
     * @param ages assigned a list of ages.
     * @return GameBuilder
     */
    public GameBuilder setAges(List<Age> ages) {
        this.ages = ages;
        return this;
    }

    /**
     * Sets the deck to according to the number of ages.
     *
     * @param deck assigned a deck.
     * @param numberOfAges assigned a number of ages.
     * @return GameBuilder
     */
    public GameBuilder setDeck(Deck deck, int numberOfAges) {
        Preconditions.checkArgument(deck != null, !deck.getCards().isEmpty());
        List<Age> agesList = new ArrayList<>();
        for (int i = 0; i < numberOfAges; i++) {
            final int age = i + 1;
            List<Predicate<? super Card>> filters = new ArrayList<>();
            filters.add(card -> card instanceof Building);
            filters.add(card -> ((Building) card).getAge() == age);
            agesList.add(new Age(age, deck.filterDeckBy(filters)));
        }
        return this.setAges(agesList);
    }

    /**
     * Creates a game according to the age, filtering cards for criteria, adding
     * to the {@link Deck} of the player and initializing the game.
     *
     * @return Game
     */
    public Game createGame() {
        if (players.size() < MIN_PLAYERS) {
            throw new NoEnoughPlayersException();
        }
        List<Predicate<? super Card>> filters = new ArrayList<>();
        filters.add(card -> card instanceof Building);
        filters.add(card -> ((Building) card).getPlayersNeeded() == players.size());
        filters.add(card -> ((Building) card).getAge() == 1);

        players.stream().forEach(
                player -> {
                    player.getCity().getStorage().addResource(ResourceType.COIN, INITIAL_COINS);
                    player.setDeck(ages.get(0).nextDeck(players.size(), filters));
                }
        );
        return new Game(players, ages);
    }
}
