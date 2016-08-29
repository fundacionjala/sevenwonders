/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */
package org.fundacionjala.sevenwonders.routes;

import org.apache.camel.BeanInject;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.spring.SpringRouteBuilder;
import org.fundacionjala.sevenwonders.beans.GameRoomService;
import org.fundacionjala.sevenwonders.core.GameRoom;
import org.fundacionjala.sevenwonders.core.Player;
import org.fundacionjala.sevenwonders.core.rest.GameRoomModel;
import org.fundacionjala.sevenwonders.core.rest.PlayerModel;
import org.springframework.stereotype.Component;

/**
 * This registry the principal routes(GET, SET, PUT, DELETE) of game rooms
 *
 * @author Juan Barahona
 */

@Component
public class GameRoomRoute extends SpringRouteBuilder {


    @BeanInject("gameRoomService")
    GameRoomService gameRoomService;


    @Override
    public void configure() throws Exception {

        rest("/gameRoom").description("Lobby rest service")
                .consumes("application/json").produces("application/json")

                .post().description("Create a new game room").type(GameRoomModel.class)
                .to("bean:gameRoomService?method=createGameRoom")
                .to("websocket://localhost:9291/lobby")

                .get().description("Get all gamerooms").typeList(GameRoomModel.class)
                .to("bean:gameRoomService?method=listGameRooms")

                .post("/player").description("Add Player to lobby game").type(PlayerModel.class)
                .to("bean:gameRoomService?method=addPlayer")

                .get("/players/{id}").description("Get list of players").outTypeList(Player.class)
                .to("bean:gameRoomService?method=getPlayers(${header.id})")

                .get("/{id}").description("Get a game room").type(GameRoom.class)
                .to("bean:gameRoomService?method=getGameRoom(${header.id})");
    }
}
