package br.com.tcc.integration.routebuilder.producer;

import org.apache.camel.builder.RouteBuilder;

public class ProduceQueueRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("direct:toQueue")
		.to("jms:queue:testQueue?jmsMessageType=Text");
	}

}
