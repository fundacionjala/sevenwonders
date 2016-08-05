package org.fundacionjala.sevenwonders.core.rest;

/**
 * Created by dwits on 05/08/2016.
 */
public class GameRoom {
    private int maxPlayers;
    private Player owner;

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }
}
