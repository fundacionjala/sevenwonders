/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */
package org.fundacionjala.sevenwonders.core;

import org.junit.Assert;
import org.junit.Test;
import static org.mockito.Mockito.mock;

/**
 * Used to test all the functionality of the {@link Player}.
 *
 * @author Jhonatan Mamani
 */
public class PlayerTest {

    @Test
    public void testGetAndSetDeck() {
        City city = new City(mock(Wonder.class), mock(StoragePoint.class), mock(Storage.class));
        Deck firstDeck = mock(Deck.class);
        Deck secondDeck = mock(Deck.class);
        Player player = new Player(city);

        player.setDeck(firstDeck);
        Assert.assertEquals(firstDeck, player.getDeck());

        player.setDeck(secondDeck);
        Assert.assertEquals(secondDeck, player.getDeck());
    }

    @Test(expected = NullPointerException.class)
    public void testSetDeckWithNull() {
        City city = new City(mock(Wonder.class), mock(StoragePoint.class), mock(Storage.class));
        Player player = new Player(city);

        player.setDeck(null);
        Assert.fail();
    }
}
