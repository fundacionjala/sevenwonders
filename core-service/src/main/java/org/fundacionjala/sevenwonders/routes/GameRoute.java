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
import org.fundacionjala.sevenwonders.core.rest.*;
import org.fundacionjala.sevenwonders.processors.GameProcessor;
import org.springframework.stereotype.Component;

/**
 * This registry the principal routes(GET, SET, PUT, DELETE) of game rooms
 *
 * @author Juan Barahona
 */

@Component
public class GameRoute extends SpringRouteBuilder {

    @Override
    public void configure() throws Exception {

        rest("/game").description("Gameboard rest service")
                .consumes("application/json").produces("application/json")

                .get("last").description("Get last game ").outTypeList(PrincipalGameModel.class)
                .to("bean:gameService?method=getLastCreated()")

                .get("{id}/players").description("Get list of players").typeList(PlayerModel.class)
                .to("bean:gameService?method=getPlayers(${header.id})")

                .get("/points").description("Get points of a player").type(PointsModel.class)
                .to("bean:gameService?method=getPoints(${body})")

                .get("{gameId}/buildings/{playerId}").description("Get buildings of a player").type(BuildingModel.class)
                .to("bean:gameService?method=getPlayerModelById(${gameId},${playerId})")

                .verb("options")
                .route()

                .setHeader("Access-Control-Allow-Origin", constant("*"))
                .setHeader("Access-Control-Allow-Methods", constant("GET, HEAD, POST, PUT, DELETE, OPTIONS"))
                .setHeader("Access-Control-Allow-Headers", constant("Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers"))
                .setHeader("Allow", constant("GET, HEAD, POST, PUT, DELETE, OPTIONS"));
    }
}

