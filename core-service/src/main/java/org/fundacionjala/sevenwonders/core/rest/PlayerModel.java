/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */
package org.fundacionjala.sevenwonders.core.rest;

/**
 * Created by Juan Manuel Barahona on 05/08/2016.
 */
public class PlayerModel {
    private int id;
    private String userName;
    private int roomId;
    private String token;
    private WebSocketConnection webSocketConnection;
    public int getRoomId() {
        return roomId;
    }

    public WebSocketConnection getWebSocketConnection() {
        return webSocketConnection;
    }

    public void setWebSocketConnection(WebSocketConnection webSocketConnection) {
        this.webSocketConnection = webSocketConnection;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
