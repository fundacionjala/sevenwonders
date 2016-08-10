package org.fundacionjala.sevenwonders.routes;

import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.spring.SpringRouteBuilder;
import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.fundacionjala.sevenwonders.CoreServiceConfig;
import org.fundacionjala.sevenwonders.beans.SampleBean;
import org.fundacionjala.sevenwonders.processors.SampleProcessor;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

/**
 * Created by ale on 7/18/2016.
 */
public class CoreServiceRouteTest extends CamelSpringTestSupport {

    @EndpointInject(uri = "mock:result")
    protected MockEndpoint resultEndpoint;

    @Produce(uri = "direct:start")
    protected ProducerTemplate template;

    @Test
    public void testRoute() throws Exception {
        template.sendBody("direct:route-test", "Boss");
    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new SpringRouteBuilder() {
            public void configure() {
                from("direct:route-test")
                        .log("Starting...")
                        .process(lookup(SampleProcessor.class))
                        .bean(lookup(SampleBean.class))
                        .log("message:" + body().toString())
                        .to("mock:result");
            }
        };
    }

    @Override
    protected AbstractApplicationContext createApplicationContext() {
        return new AnnotationConfigApplicationContext(CoreServiceConfig.class);
    }
}