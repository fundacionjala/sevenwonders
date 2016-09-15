/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */

package org.fundacionjala.sevenwonders.core.rest;

import java.util.List;

/**
 * Created by Luis Gumucio.
 */
public class GameModel {
    private int id;
    private PlayerModel player;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PlayerModel getPlayer() {
        return player;
    }

    public void setPlayer(PlayerModel player) {
        this.player = player;
    }

}
