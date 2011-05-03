package br.com.tcc.integration.routebuilder;

import org.apache.camel.builder.RouteBuilder;

import br.com.tcc.integration.processor.JAXBProcessor;

public class FileToJaxbRoute extends RouteBuilder {
	
	public void configure() { 
		from("file:///Users/filipak/Documents/workspace/Integration/file/in?noop=true")
		.log("peguei o arquivo").process(new JAXBProcessor())
		.to("direct:part1");



		from("direct:part1").convertBodyTo(String.class)
		.to("jms:queue:testQueue?jmsMessageType=Text");
		
		from("jms:queue:testQueue?jmsMessageType=Text")
		.to("file:///Users/filipak/Documents/workspace/Integration/file/out");
	}
}