package org.fundacionjala.sevenwonders.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.fundacionjala.sevenwonders.core.rest.ChooseCardModel;

/**
 * Created by Luis Gumucio.
 */
public class ChooseCardProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        ChooseCardModel current = exchange.getIn().getBody(ChooseCardModel.class);
        exchange.getIn().setHeader("id", current.getId());
        exchange.getIn().setBody(current);
    }
}
