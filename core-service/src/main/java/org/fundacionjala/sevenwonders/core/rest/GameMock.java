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
    private String type;
    private int players;

    public GameMock(int id, String name, String type, int players) {
        this.id = id;
        this.name = name;
        this.players = players;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPlayers() {
        return players;
    }

    public void setPlayers(int players) {
        this.players = players;
    }
}
