package br.com.tcc.integration.resource;


import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;

import br.com.tcc.integration.context.CamelContextService;
import br.com.tcc.integration.domain.Card;

@Path("/resource/card")
public class CardResource {

	@POST
//	@Consumes(MediaType.APPLICATION_XML)
	public Response execute(@HeaderParam("httprequestid")String httpRequestId) throws Exception{
		CamelContextService service = new CamelContextService();
		CamelContext camelContext = service.getCamelContext();
		
		
			
		ProducerTemplate producer = camelContext.createProducerTemplate();
		final Card card = new Card(1, "2a");
		System.out.println(card);
		producer.send("jms:queue:testQueue?jmsMessageType=Text", new Processor() {
			
			@Override
			public void process(Exchange exchange) throws Exception {
				Message out = exchange.getIn();
				
				out.setBody(card);
				System.out.println("*****************************************");
				System.out.println(card);
			}
		});
		
		return Response.ok().build();	
	}
	
}
