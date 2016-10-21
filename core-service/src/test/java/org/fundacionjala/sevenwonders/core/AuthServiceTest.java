/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */
package org.fundacionjala.sevenwonders.core;

import org.fundacionjala.sevenwonders.beans.AuthService;
import org.fundacionjala.sevenwonders.core.rest.PlayerModel;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Diego Fiengo Arnez
 */
public class AuthServiceTest {
    private PlayerModel player;

    public AuthServiceTest() {
        player = new PlayerModel();
        player.setUserName("Diego");
    }

    @Test
    public void testCreateAPlayerAndAdded() {
        AuthService service = new AuthService();
        String expectedName = "Diego";
        service.login(player);

        Assert.assertNotNull(service.getPlayerById(1));
        Assert.assertEquals(expectedName, service.getPlayerById(1).getUserName());
    }

    @Test
    public void testAddSeveralPlayers() {
        AuthService service = new AuthService();
        for (int i = 0; i < 10; i++) {
            service.login(player);
        }
        int expectedPlayersQuantity = 10;

        Assert.assertEquals(expectedPlayersQuantity, service.getPlayers().size());
    }
    @Test (expected = NullPointerException.class)
    public void testAddANewPlayer() {
        AuthService service = new AuthService();
        PlayerModel playerModel = new PlayerModel();
        service.login(playerModel);

        Assert.fail();
    }

    @Test (expected = IllegalArgumentException.class)
    public void testAddAPlayerWithAEmptyName() {
        AuthService service = new AuthService();
        PlayerModel playerModel = new PlayerModel();
        playerModel.setUserName("");
        service.login(playerModel);

        Assert.fail();
    }
}
