/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */
package org.fundacionjala.sevenwonders.core;

import java.util.ArrayList;
import java.util.List;
import org.fundacionjala.sevenwonders.exception.NoEnoughPlayersException;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.*;

/**
 * Used to test all the functionality of the {@link Game}.
 *
 * @author Juan Barahona
 */
public class GameBuilderTest {

    @Test
    public void testCreateGame() {
        Storage simpleStorage = mock(Storage.class);
        City simpleCity = new City(mock(Wonder.class), mock(StoragePoint.class), simpleStorage);
        Player simplePlayer = new Player(simpleCity);
        Player simplePlayerOne = new Player(simpleCity);
        Player simplePlayerTwo = new Player(simpleCity);
        Deck simpleDeck = mock(Deck.class);

        Age simpleAge = mock(Age.class);
        when(simpleAge.nextDeck(anyInt(), anyList())).thenReturn(simpleDeck);

        List<Player> players = new ArrayList<>();
        players.add(simplePlayer);
        players.add(simplePlayerOne);
        players.add(simplePlayerTwo);

        List<Age> ages = new ArrayList<>();
        ages.add(simpleAge);

        Game game = new GameBuilder()
                .setAges(ages)
                .setPlayers(players)
                .createGame();

        verify(simpleStorage, times(3)).addResource(ResourceType.COIN, 3);
        verify(simpleAge, times(3)).nextDeck(anyInt(), anyList());
    }

    @Test
    public void testSetUpGame() {
        City simpleCity = new City(mock(Wonder.class), mock(StoragePoint.class), mock(Storage.class));
        Player simplePlayer = new Player(simpleCity);
        Player simplePlayerOne = new Player(simpleCity);
        Player simplePlayerTwo = new Player(simpleCity);

        List<Player> players = new ArrayList<>();
        players.add(simplePlayer);
        players.add(simplePlayerOne);
        players.add(simplePlayerTwo);
        Deck simpleDeck = mock(Deck.class);
        List mockList = mock(List.class);
        when(simpleDeck.filterDeckBy(anyList())).thenReturn(simpleDeck);
        when(simpleDeck.getCards()).thenReturn(mockList);
        when(simpleDeck.size()).thenReturn(3);
        when(mockList.size()).thenReturn(3);

        int numberOfAges = 3;

        Game game = new GameBuilder()
                .setPlayers(players)
                .setDeck(simpleDeck, numberOfAges)
                .createGame();

        Assert.assertEquals(3, game.getPlayers().size());
        Assert.assertEquals(numberOfAges, game.getAges().size());
    }

    @Test(expected = NoEnoughPlayersException.class)
    public void testInitializeGameFail() {
        Player simplePlayer = mock(Player.class);
        List<Player> players = new ArrayList<>();
        players.add(simplePlayer);

        int numberOfAges = 3;
        Game game = new GameBuilder().setDeck(mock(Deck.class), numberOfAges)
                .setPlayers(players)
                .createGame();
        Assert.fail();
    }
}
