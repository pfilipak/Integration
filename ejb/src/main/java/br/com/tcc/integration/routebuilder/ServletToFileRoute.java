package br.com.tcc.integration.routebuilder;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;


public class ServletToFileRoute extends RouteBuilder {
	
	public void configure() {
		from("servlet:///hello?matchOnUriPrefix=true").process(new Processor() {
		    public void process(Exchange exchange) throws Exception {                    
		        String contentType = exchange.getIn().getHeader(Exchange.CONTENT_TYPE, String.class);
		        String path = exchange.getIn().getHeader(Exchange.HTTP_PATH, String.class);
		        
		        String charsetEncoding = exchange.getIn().getHeader(Exchange.HTTP_CHARACTER_ENCODING, String.class);
		        System.out.println("contentType: " + contentType);
		        
		        exchange.getOut().setHeader(Exchange.CONTENT_TYPE, contentType + "; charset=UTF-8");                        
		        exchange.getOut().setHeader("PATH", path);
		        exchange.getOut().setBody("<b>Hello World</b>");
		    }
		});

		
		
		
//		from("servlet")
//		.log("peguei o arquivo").process(new JAXBProcessor())
//		.to("direct:part1");
//
//
//
//		from("direct:part1").convertBodyTo(String.class)
//		.to("file:///Users/filipak/Documents/workspace/Integration/file/out");
	}
}
