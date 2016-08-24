/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fundacionjala.sevenwonders.core.rest;

/**
 *
 * @author luisgumucio
 */
public class GameMock {

    private int id;
    private String name;
    private int players;

    public GameMock(int id, String name, int players) {
        this.id = id;
        this.name = name;
        this.players = players;
    }

    public GameMock() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPlayers() {
        return players;
    }

    public void setPlayers(int players) {
        this.players = players;
    }
}
