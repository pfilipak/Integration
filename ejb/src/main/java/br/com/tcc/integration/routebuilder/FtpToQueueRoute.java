package br.com.tcc.integration.routebuilder;

import org.apache.camel.builder.RouteBuilder;


public class FtpToQueueRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("ftp:user@rider.com?password=secret").to("hornetq");
	}

}
