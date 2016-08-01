package org.fundacionjala.sevenwonders.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

/**
 * Created by ale on 7/18/2016.
 */
@Component
public class CoreServiceExceptionHandler implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        Throwable caused = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Throwable.class);
        String routeId = exchange.getProperty(Exchange.FAILURE_ROUTE_ID, String.class);
        String endpoint = exchange.getProperty(Exchange.FAILURE_ENDPOINT, String.class);

        final String message = String.format("Error in route: %s, endpoint: %s : %s", routeId, endpoint, caused);
        System.out.println(message);
    }
}
