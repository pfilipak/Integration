package br.com.tcc.integration;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

import br.com.tcc.integration.processor.JAXBProcessor;
import br.com.tcc.integration.routebuilder.FileToJaxbRoute;

import com.blogspot.diegopacheco.camel.hornetq.component.HornetQCamelConnectionFactory;
import com.blogspot.diegopacheco.camel.hornetq.component.HornetQComponent;

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

		HornetQComponent hornetQComponent = new HornetQComponent();
		hornetQComponent.setConnectionFactory(new HornetQCamelConnectionFactory());


		CamelContext context = new DefaultCamelContext(); 
		context.addComponent("hornetq", hornetQComponent);
		context.addRoutes(new FileToJaxbRoute()); 
		System.out.println("start");
		context.start();
		context.resume();
		Thread.sleep(3000); 
		System.out.println("stop");
		context.stop();


	}
}

