package org.fundacionjala.sevenwonders.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

/**
 * Created by ale on 7/18/2016.
 */
@Component
public class SampleProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        String name = (String) exchange.getIn().getBody();
        exchange.getIn().setBody(name + " Dude");
    }
}
