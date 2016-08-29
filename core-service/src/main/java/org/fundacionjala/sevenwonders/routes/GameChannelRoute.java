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
import org.fundacionjala.sevenwonders.core.rest.PlayerModel;
import org.springframework.stereotype.Component;

/**
 * Created by Luis Gumucio.
 */
@Component
public class GameChannelRoute extends SpringRouteBuilder {
    @BeanInject("gameRoomService")
    GameRoomService gameRoomService;
    @Override
    public void configure() throws Exception {
        from("websocket://localhost:9291/gameChannel")
                .log("${body}")
                .unmarshal().json(JsonLibrary.Jackson, PlayerModel.class)
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        PlayerModel current = exchange.getIn().getBody(PlayerModel.class);
                        boolean isGameChannel = gameRoomService.getGameRoom(current.getRoomId())
                                .getPlayers()
                                .stream()
                                .filter(b ->b.getuserName().equals(current.getuserName()))
                                .count() > 0;
                        if (isGameChannel){
                            exchange.getIn().setBody(gameRoomService.getGameRoom(current.getRoomId()), GameRoomService.class);
                        }
                        exchange.getIn().setBody("no access channel game", String.class);
                    }
                })
                .to("websocket://localhost:9291/gameChannel?sendToAll=true");
    }
}
