package br.com.tcc.integration.routebuilder;

import org.apache.camel.builder.RouteBuilder;


public class HttpToQueueRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("jetty:http://localhost:8080/")
		.inOnly("activemq:queue:incoming")
		.transform()
		.constant("OK");
	}

}