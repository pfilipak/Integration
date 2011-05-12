package br.com.tcc.integration.routebuilder.in;

import org.apache.camel.builder.RouteBuilder;

import br.com.tcc.integration.processor.JAXBProcessor;

public class FileToDirectRoute extends RouteBuilder {
	
	public void configure() { 
		from("file:///home/desenv/workspace/Integration/file/in?noop=true")
		.to("log:br.com?level=INFO")
		.process(new JAXBProcessor()).convertBodyTo(String.class)
		.to("direct:toQueue");
	}
}