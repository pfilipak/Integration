package br.com.tcc.integration.routebuilder.consumer;

import org.apache.camel.builder.RouteBuilder;


public class ConsumerQueueRoute extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		from("jms:queue:testQueue?jmsMessageType=Text")
		.to("file:///home/desenv/workspace/Integration/file/out").to("log:br.com?level=INFO");
	}

}
