/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */
package org.fundacionjala.sevenwonders.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.spring.SpringRouteBuilder;
import org.fundacionjala.sevenwonders.core.GameRoom;
import org.fundacionjala.sevenwonders.core.Player;
import org.fundacionjala.sevenwonders.core.rest.GameRoomModel;
import org.fundacionjala.sevenwonders.core.rest.PlayerModel;
import org.fundacionjala.sevenwonders.processors.GameProcessor;
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
                .route()
                .to("bean:gameRoomService?method=addPlayer(${header.id}, ${body})")
                .to("direct:sendMessageGame")
                .to("direct:roomCompleted")
                .endRest()

                .get("{id}/players").description("Get list of players").outTypeList(Player.class)
                .to("bean:gameRoomService?method=getPlayers(${header.id})")

                .get("/{id}").description("Get a game room").type(GameRoom.class)
                .to("bean:gameRoomService?method=getGameRoom(${header.id})").verb("options").route()

                .setHeader("Access-Control-Allow-Origin", constant("*"))
                .setHeader("Access-Control-Allow-Methods", constant("GET, HEAD, POST, PUT, DELETE, OPTIONS"))
                .setHeader("Access-Control-Allow-Headers", constant("Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers"))
                .setHeader("Allow", constant("GET, HEAD, POST, PUT, DELETE, OPTIONS"));

        from("direct:sendMessage")
                .to("websocket://localhost:9291/lobby?sendToAll=true");

        from("direct:roomCompleted")
                .choice()
                .when(method("gameRoomService", "isCompletedPlayers(${header.id})").isEqualTo(true))
                .to("direct:getGameRoom");

        from("direct:getGameRoom")
                .to("bean:gameRoomService?method=getGameRoom(${header.id})")
                .to("websocket://localhost:9291/lobby?sendToAll=true");

    }
}
