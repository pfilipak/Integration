package br.com.tcc.integration.context;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

import br.com.tcc.integration.routebuilder.ServletToFileRoute;


public class CamelContextService {

	private static CamelContext context = null; 
	
	public CamelContextService(){
		try {
			if (context == null) {
				context = new DefaultCamelContext();
//				context.addRoutes(new FileToJaxbRoute()); 
				context.addRoutes(new ServletToFileRoute()); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void start(){
		try {
			context.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void stop(){
		try {
			context.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
