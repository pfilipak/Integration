package br.com.tcc.integration;

import java.util.Properties;

import javax.jms.ConnectionFactory;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.camel.CamelContext;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

import br.com.tcc.integration.routebuilder.consumer.ConsumerQueueRoute;
import br.com.tcc.integration.routebuilder.in.FileToDirectRoute;
import br.com.tcc.integration.routebuilder.producer.ProduceQueueRoute;

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
		context.addRoutes(new FileToDirectRoute()); 
		context.addRoutes(new ProduceQueueRoute()); 
		context.addRoutes(new ConsumerQueueRoute()); 
		
		System.out.println("start");
		context.start();
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

