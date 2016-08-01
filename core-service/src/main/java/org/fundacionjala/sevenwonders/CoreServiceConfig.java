package org.fundacionjala.sevenwonders;

/**
 * Created by ale on 7/17/2016.
 */

import org.apache.camel.spring.javaconfig.CamelConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "org.fundacionjala.sevenwonders")
public class CoreServiceConfig extends CamelConfiguration {

    @Autowired
    private ApplicationContext context;
}