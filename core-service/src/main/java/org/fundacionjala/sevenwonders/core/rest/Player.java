package org.fundacionjala.sevenwonders.core.rest;

/**
 * Created by dwits on 05/08/2016.
 */
public class Player {
    private String name;
    private String roomId;

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
