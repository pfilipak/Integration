package br.com.tcc.integration.routebuilder;

import org.apache.camel.builder.RouteBuilder;

public class QueueToFileRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
//		String queue = "hornetq:queue:testQueue?jmsMessageType=Text";
		String queue = "hornetq:queue:/queue/testQueue";
		System.out.println("file -> queue");
//		from("file:///Users/filipak/Documents/workspace/integration/file/in?delete=true").log("asdf").getTrace();//to(queue);
		System.out.println("queue -> file");
		from(queue).to("file:///Users/filipak/Documents/workspace/integration/file/out");
	}

}
