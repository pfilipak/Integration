package br.com.tcc.integration.routebuilder;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JaxbDataFormat;

public class Route3 extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		JaxbDataFormat jaxb = new JaxbDataFormat();
		from("activemq:queue:incoming")
		.convertBodyTo(String.class)
		.choice()
		.when().method("helper", "isXml")
		.unmarshal(jaxb)
		.to("activemq:queue:incoming")
		.when().method("helper", "isCsv")
		.unmarshal().csv()
		.beanRef("orderService", "csvToXml")
		.to("activemq:queue:order");
	}

}