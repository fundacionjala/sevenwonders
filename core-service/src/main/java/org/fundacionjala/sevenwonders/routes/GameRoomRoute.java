/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */
package org.fundacionjala.sevenwonders.routes;

import org.apache.camel.spring.SpringRouteBuilder;
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

    @Override
    public void configure() throws Exception {

        rest("/games").description("Lobby rest service")
                .consumes("application/json").produces("application/json")

                .post().description("Create a new game room").type(GameRoomModel.class)
                .route()
                .to("bean:gameRoomService?method=createGameRoom")
                .to("direct:sendMessage")
                .endRest()

                .get().description("Get all gamerooms").typeList(GameRoomModel.class)
                .to("bean:gameRoomService?method=listGameRooms")

                .post("{id}/players").description("Add Player to lobby game").type(PlayerModel.class)
                .to("bean:gameRoomService?method=addPlayer(${header.id}, ${body})")

                .get("{id}/players").description("Get list of players").outTypeList(Player.class)
                .to("bean:gameRoomService?method=getPlayers(${header.id})")

                .get("/{id}").description("Get a game room").type(GameRoom.class)
                .to("bean:gameRoomService?method=getGameRoom(${header.id})");

        from("direct:sendMessage")
                .to("websocket://localhost:9291/lobby?sendToAll=true");
    }
}
