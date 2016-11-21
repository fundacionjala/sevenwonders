package org.fundacionjala.sevenwonders.routes;

import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.stereotype.Component;

/**
 * Created by Luis Gumucio.
 */

@Component
public class NotifiedChooseCardWSRoute extends SpringRouteBuilder {

    @Override
    public void configure() throws Exception {
        from("websocket://localhost:9295/chooseCard")
                .to("websocket://localhost:9295/chooseCard?sendToAll=false");
    }
}
