package br.com.tcc.integration.routebuilder;

import org.apache.camel.builder.RouteBuilder;

public class FilterRoute extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		from("file:").throttle(3).to("");
		from("").to("");
		from("").marshal().jaxb().to("");
		
	}

}
