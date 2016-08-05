package org.fundacionjala.sevenwonders.routes;

import org.apache.camel.BeanInject;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.spring.SpringRouteBuilder;
import org.fundacionjala.sevenwonders.beans.GameRoomService;
import org.fundacionjala.sevenwonders.beans.GameService;
import org.fundacionjala.sevenwonders.core.GameBuilder;
import org.fundacionjala.sevenwonders.core.GameRoom;
import org.fundacionjala.sevenwonders.core.Player;
import org.fundacionjala.sevenwonders.core.Wonder;
import org.springframework.stereotype.Component;

/**
 * Created by Juan Manuel Barahona on 04/08/2016.
 */

@Component
public class GameRoomRoute extends SpringRouteBuilder {


    @BeanInject("gameRoomService")
    GameRoomService gameRoomService;


    @Override
    public void configure() throws Exception {


        restConfiguration().component("jetty")
                .bindingMode(RestBindingMode.json)
                .dataFormatProperty("prettyPrint", "true")
                .port(9999);


        rest("/gameRoom").description("Lobby rest service")
                .consumes("application/json").produces("application/json")

                .post().description("Create a new game room").type(org.fundacionjala.sevenwonders.core.rest.GameRoom.class)
                .to("bean:gameRoomService?method=createGameRoom")

                .post("/player").description("Add Player to lobby game").type(Player.class)
                .to("bean:gameRoomService?method=addPlayer")

                .get("/players/{id}").description("Get list of players").outTypeList(Player.class)
                .to("bean:gameRoomService?method=getPlayers(${header.id})")

                .get("/{id}").description("Get a game room").type(GameRoom.class)
                .to("bean:gameRoomService?method=getGameRoom(${header.id})");
    }
}