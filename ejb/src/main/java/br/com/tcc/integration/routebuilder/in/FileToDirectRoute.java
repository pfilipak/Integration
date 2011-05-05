package br.com.tcc.integration.routebuilder.in;

import org.apache.camel.builder.RouteBuilder;

import br.com.tcc.integration.processor.JAXBProcessor;

public class FileToDirectRoute extends RouteBuilder {
	
	public void configure() { 
		from("file:///Users/filipak/Documents/workspace/Integration/file/in?noop=true")
		.log("peguei o arquivo")
		.process(new JAXBProcessor())
		.to("direct:toQueue");
	}
}