package br.com.tcc.integration;

import java.util.Properties;

import javax.jms.ConnectionFactory;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

import br.com.tcc.integration.domain.Card;

public class Main {

	public static void main(String[] args) throws Exception {
		////		PackageScanClassResolver jbossResolver = new JBossPackageScanClassResolver();
		//		 	 			
		//		HornetQComponent hornetQComponent = new HornetQComponent();
		//		hornetQComponent.setUseNettyDefaults(true);
		////		hornetQComponent.setConnectionFactory(new HornetQConnectionFactory());
		//		
		//		CamelContext context = new DefaultCamelContext();
		////		context.setPackageScanClassResolver(jbossResolver);
		////		context.addComponent("hornetq", hornetQComponent);
		//		context.addRoutes(new RouteJboss());
		//		System.out.println("start");
		//		context.start();
		//Thread.sleep(100000); context.stop();

//		HornetQComponent hornetQComponent = new HornetQComponent();
//		hornetQComponent.setConnectionFactory(new HornetQCamelConnectionFactory());


		CamelContext context = new DefaultCamelContext(); 
		JmsComponent jmsComponent = JmsComponent.jmsComponent();
		Context initialContext = getInitialContext();
		ConnectionFactory cf = (ConnectionFactory)initialContext.lookup("/ConnectionFactory");
		jmsComponent.setConnectionFactory(cf);
		context.addComponent("jms", jmsComponent);
//		context.addRoutes(new FileToDirectRoute()); 
//		context.addRoutes(new ProduceQueueRoute()); 
//		context.addRoutes(new ConsumerQueueRoute()); 
		
		
		context.start();
		ProducerTemplate producer = context.createProducerTemplate();
		final Card card = new Card(1, "2a");
		System.out.println(card);
		producer.send("jms:queue:testQueue?jmsMessageType=Text", new Processor() {
			
			@Override
			public void process(Exchange exchange) throws Exception {
				Message out = exchange.getIn();
				
				out.setBody("card");
				System.out.println("*****************************************");
				System.out.println(card);
			}
		});
		Thread.sleep(100000); 
		System.out.println("stop");
		context.stop();


	}
	
	private static InitialContext getInitialContext() throws javax.naming.NamingException {  
		Properties p = new Properties( );  
		p.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");  
		p.put(Context.URL_PKG_PREFIXES,	" org.jboss.naming:org.jnp.interfaces");  
		p.put(Context.PROVIDER_URL, "jnp://localhost:1099");  
		return new javax.naming.InitialContext(p);  
	} 
}

