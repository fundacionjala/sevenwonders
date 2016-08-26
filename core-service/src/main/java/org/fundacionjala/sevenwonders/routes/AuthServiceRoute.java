package org.fundacionjala.sevenwonders.routes;

import org.apache.camel.BeanInject;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.model.rest.RestPropertyDefinition;
import org.apache.camel.spring.SpringRouteBuilder;
import org.fundacionjala.sevenwonders.beans.AuthService;
import org.fundacionjala.sevenwonders.core.rest.PlayerModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Juan Manuel Barahona on 24/08/2016.
 */

@Component
public class AuthServiceRoute extends SpringRouteBuilder {


    @BeanInject("authService")
    private AuthService authService;


    @Override
    public void configure() throws Exception {

        restConfiguration().component("jetty")
                .bindingMode(RestBindingMode.json)
                .dataFormatProperty("prettyPrint", "true")
                .enableCORS(true)
                .port(9999);


        rest("/login").description("Login rest service")
                .consumes("application/json").produces("application/json")

                .post().description("Create a player").type(PlayerModel.class)
                .to("bean:authService?method=login")

                .verb("options").route()

                .setHeader("Access-Control-Allow-Origin", constant("*"))
                .setHeader("Access-Control-Allow-Methods", constant("GET, HEAD, POST, PUT, DELETE, OPTIONS"))
                .setHeader("Access-Control-Allow-Headers", constant("Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers"))
                .setHeader("Allow", constant("GET, HEAD, POST, PUT, DELETE, OPTIONS"));
    }
}