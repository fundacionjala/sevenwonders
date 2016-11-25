package org.fundacionjala.sevenwonders.routes;

import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.spring.SpringRouteBuilder;
import org.fundacionjala.sevenwonders.core.rest.ChooseCardModel;
import org.fundacionjala.sevenwonders.processors.ChooseCardProcessor;
import org.springframework.stereotype.Component;

/**
 * Created by Luis Gumucio.
 */

@Component
public class NotifiedChooseCardWSRoute extends SpringRouteBuilder {
    private ChooseCardProcessor chooseCardProcessor = new ChooseCardProcessor();
    @Override
    public void configure() throws Exception {
        from("websocket://localhost:9295/chooseCard")
                .to("websocket://localhost:9295/chooseCard?sendToAll=false");

        from("direct:isFullCard")
                .log("choose card")
                .process(chooseCardProcessor)
                .choice()
                .when(method("gameRoomService","isFullChooseCard(${header.id})").isEqualTo(true))
                .to("bean:gameRoomService?method=getChooseCardModel(${body})")
                .to("websocket://localhost:9295/chooseCard?sendToAll=true")
                .otherwise()
                .to("bean:gameRoomService?method=getChooseCardModel(${body})")
                .endChoice();
    }
}
