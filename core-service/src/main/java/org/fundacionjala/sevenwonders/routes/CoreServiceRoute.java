package org.fundacionjala.sevenwonders.routes;

import org.apache.camel.spring.SpringRouteBuilder;
import org.fundacionjala.sevenwonders.beans.SampleBean;
import org.fundacionjala.sevenwonders.processors.CoreServiceExceptionHandler;
import org.fundacionjala.sevenwonders.processors.SampleProcessor;
import org.springframework.stereotype.Component;

@Component
public class CoreServiceRoute extends SpringRouteBuilder {

    @Override
    public void configure() throws Exception {
        onException(Throwable.class).routeId("ExceptionRoute")
                .to("direct:exception-handler")
                .handled(true)
                .end();

        from("direct:exception-handler").routeId("ExceptionHandler")
                .process(lookup(CoreServiceExceptionHandler.class));

        from("direct:start-route").routeId("StartRoute")
                .process(lookup(SampleProcessor.class))
                .bean(lookup(SampleBean.class));

    }
}

