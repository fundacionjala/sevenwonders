/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */
package org.fundacionjala.sevenwonders;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spring.javaconfig.CamelConfiguration;
import org.fundacionjala.sevenwonders.routes.AuthServiceRoute;
import org.fundacionjala.sevenwonders.routes.ChooseWonderWSRoute;
import org.fundacionjala.sevenwonders.routes.GameWSRoute;
import org.fundacionjala.sevenwonders.routes.GameRoomRoute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

/**
 * This contains the basic configuration for the Camel application
 *
 * @author Juan Barahona
 */

@Configuration
@ComponentScan(basePackages = "org.fundacionjala.sevenwonders")
public class GameConfig extends CamelConfiguration {

    @Autowired
    private AuthServiceRoute authServiceRoute;

    @Autowired
    private GameRoomRoute gameRoomRoute;

    @Autowired
    private GameWSRoute gameWSRoute;

    @Autowired
    private ChooseWonderWSRoute chooseWonderWSRoute;
    @Override
    public List<RouteBuilder> routes() {
        return Arrays.asList(authServiceRoute, gameRoomRoute, gameWSRoute, chooseWonderWSRoute);
    }
}
