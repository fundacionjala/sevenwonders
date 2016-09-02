/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */
package org.fundacionjala.sevenwonders.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.fundacionjala.sevenwonders.core.rest.GameModel;
import org.fundacionjala.sevenwonders.core.rest.GameRoomModel;
import org.fundacionjala.sevenwonders.core.rest.PlayerModel;

/**
 * Created by Luis Gumucio.
 */
public class GameProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        GameModel current = exchange.getIn().getBody(GameModel.class);
        exchange.getIn().setHeader("id", current.getId());
        exchange.getIn().setBody(current.getPlayer(), PlayerModel.class);
    }
}
