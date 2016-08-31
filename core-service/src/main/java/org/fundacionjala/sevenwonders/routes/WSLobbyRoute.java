/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */
package org.fundacionjala.sevenwonders.routes;

import org.apache.camel.BeanInject;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.spring.SpringRouteBuilder;
import org.fundacionjala.sevenwonders.beans.GameRoomService;
import org.fundacionjala.sevenwonders.core.rest.GameModel;
import org.springframework.stereotype.Component;

/**
 * Created by Juan Manuel Barahona on 29/08/2016.
 */

@Component
public class WSLobbyRoute extends SpringRouteBuilder {

    @BeanInject("gameRoomService")
    GameRoomService gameRoomService;

    @Override
    public void configure() throws Exception {
        from("websocket://localhost:9291/lobby")
                .log("${body}")
                .unmarshal().json(JsonLibrary.Jackson, GameModel.class)
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        exchange.getIn().setBody(gameRoomService.listGameRooms(), GameModel.class);
                    }
                })
                .to("websocket://localhost:9291/lobby?sendToAll=true");

    }
}
