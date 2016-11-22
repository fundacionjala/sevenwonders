package org.fundacionjala.sevenwonders.core.rest;

import java.util.List;

/**
 * Created by Juan Manuel Barahona on 16/11/2016.
 */
public class PrincipalGameModel {
    private int id;
    private List<PlayerModel> players;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<PlayerModel> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerModel> players) {
        this.players = players;
    }
}
