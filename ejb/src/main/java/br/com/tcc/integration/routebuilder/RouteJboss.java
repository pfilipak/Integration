package br.com.tcc.integration.routebuilder;

import org.apache.camel.builder.RouteBuilder;

public class RouteJboss extends RouteBuilder {

	@Override
	public void configure() throws Exception {
//		from("jms:queue:testQueue").to("jms:queue:outraFila");
//		from("file:///Users/filipak/Documents/workspace/integration/file/in").log("asdf").sethto("jms:queue:testeQueue");
	}
	
	public static void main(String[] args) throws Exception {
//		JAXBContext context = JAXBContext.newInstance(Client.class);
//		Marshaller marshaller = context.createMarshaller();
//		Client client = new Client();
//		client.setId("id");
//		client.setName("name");
//		marshaller.marshal(client, new File("oi.xml"));
		
//		JAXBContext context = JAXBContext.newInstance() ;
//		Unmarshaller unmarshaller = context.createUnmarshaller() ;
//		Client client = (Client)unmarshaller.unmarshal (new File("/Users/filipak/Documents/workspace/integration/file/in/data.xml"));
//		System.out.println(client);
	}

}
