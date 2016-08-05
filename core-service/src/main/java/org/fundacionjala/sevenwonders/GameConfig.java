package org.fundacionjala.sevenwonders;

import org.apache.camel.spring.javaconfig.CamelConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Juan Manuel Barahona on 04/08/2016.
 */


@Configuration
@ComponentScan(basePackages = "org.fundacionjala.sevenwonders")
public class GameConfig extends CamelConfiguration {

}
