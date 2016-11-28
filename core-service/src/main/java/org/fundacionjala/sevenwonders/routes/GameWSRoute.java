/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */

package org.fundacionjala.sevenwonders.routes;

import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.spring.SpringRouteBuilder;
import org.fundacionjala.sevenwonders.core.rest.GameModel;
import org.fundacionjala.sevenwonders.core.rest.PlayerModel;
import org.fundacionjala.sevenwonders.processors.GameProcessor;
import org.springframework.stereotype.Component;

/**
 * Created by Luis Gumucio.
 */
@Component
public class GameWSRoute extends SpringRouteBuilder {
    private GameProcessor gameProcessor = new GameProcessor();
    @Override
    public void configure() throws Exception {
        from("websocket://localhost:9295/game")
                .log("join websocket")
                .unmarshal().json(JsonLibrary.Jackson, GameModel.class)
                .process(gameProcessor)
                .log("${body}")
                .to("direct:sendMessageGame");

        from("direct:sendMessageGame")
                .choice()
                .when(method("gameRoomService", "validateGame(${header.id}, ${body})").isNotNull())
                .to("websocket://localhost:9295/game?sendToAll=true")
                .to("direct:roomCompleted")
                .endChoice();

        from("direct:roomCompleted")
                .choice()
                .when(method("gameRoomService", "isCompletedPlayers(${header.id})").isEqualTo(true))
                .to("direct:getGameRoom");

        from("direct:getGameRoom")
                .to("direct:sendMessageRoom")
                .to("bean:gameRoomService?method=getGameRoom(${header.id})")
                .to("bean:gameRoomService?method=updateGameRoom(${header.id})")
                .to("websocket://localhost:9291/lobby?sendToAll=true");

    }
}
