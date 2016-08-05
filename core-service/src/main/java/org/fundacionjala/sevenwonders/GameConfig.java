package org.fundacionjala.sevenwonders;

import org.apache.camel.spring.javaconfig.CamelConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * This contains the basic configuration for the Camel application
 *
 * @author Juan Barahona
 */

@Configuration
@ComponentScan(basePackages = "org.fundacionjala.sevenwonders")
public class GameConfig extends CamelConfiguration {

}
