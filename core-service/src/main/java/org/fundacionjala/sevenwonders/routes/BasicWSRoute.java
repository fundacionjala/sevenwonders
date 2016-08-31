/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */
package org.fundacionjala.sevenwonders.routes;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.spring.SpringRouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by Luis Gumucio on 8/25/2016.
 */
@Component
public class BasicWSRoute extends SpringRouteBuilder {

    static Logger logger = LoggerFactory.getLogger(BasicWSRoute.class);

    private final String CONNECTION_URI = "//localhost:9292/basic";

    @Override
    public void configure() throws Exception {

        from("websocket:" + CONNECTION_URI)
                .routeId("mainRoute")
                .log(LoggingLevel.DEBUG, ">> msg recieved : ${body}")
                // .delay(2000)
                .unmarshal().json(JsonLibrary.Jackson, WsMessage.class)
                .process(new Processor() {

                    @Override
                    public void process(Exchange exchange) throws Exception {
                        // how to get the inbound message
                        WsMessage info = exchange.getIn().getBody(WsMessage.class);
                        logger.info("data from client: " + info);

                        WsMessage response = new WsMessage();
                        response.setHeader("msg");
                        response.setSender("server");

                        exchange.getIn().setBody(response, WsMessage.class);

                    }
                })
                .marshal().json(JsonLibrary.Jackson, WsMessage.class)
                .log(LoggingLevel.DEBUG, ">> msg response : ${body}")
                .to("websocket:" + CONNECTION_URI + "?sendToAll=true");
    }

    String getConnectionUri() {
        return CONNECTION_URI;
    }
}
