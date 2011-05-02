package br.com.tcc.integration;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

import br.com.tcc.integration.routebuilder.FileToJaxbRoute;
import br.com.tcc.integration.routebuilder.ServletToFileRoute;

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
//		context.addComponent("hornetq", hornetQComponent);
		context.addRoutes(new FileToJaxbRoute()); 
		context.addRoutes(new ServletToFileRoute()); 
		
		System.out.println("start");
		context.start();
		Thread.sleep(100000); 
		System.out.println("stop");
		context.stop();


	}
}

