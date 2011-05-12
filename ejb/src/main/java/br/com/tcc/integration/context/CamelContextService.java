package br.com.tcc.integration.context;

import java.util.Properties;

import javax.jms.ConnectionFactory;
import javax.naming.Context;

import org.apache.camel.CamelContext;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.spi.PackageScanClassResolver;
import org.apachextras.camel.jboss.JBossPackageScanClassResolver;

import br.com.tcc.integration.routebuilder.CardRoute;


public class CamelContextService {

	private static CamelContext context = null; 

	public CamelContextService(){
		//				JndiContext jndicontext = new JndiContext();
		//				jndicontext.bind("bye", new Say());
		try {
			if (context == null) {
				context = new DefaultCamelContext();


				PackageScanClassResolver jbossResolver = new JBossPackageScanClassResolver();
				context.setPackageScanClassResolver(jbossResolver);

				JmsComponent jmsComponent = JmsComponent.jmsComponent();
				Properties p = new Properties( );  
				p.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");  
				p.put(Context.URL_PKG_PREFIXES,	" org.jboss.naming:org.jnp.interfaces");  
				p.put(Context.PROVIDER_URL, "jnp://localhost:1099");  

				Context initialContext = new javax.naming.InitialContext(p);

				ConnectionFactory cf = (ConnectionFactory)initialContext.lookup("/ConnectionFactory");
				jmsComponent.setConnectionFactory(cf);
				context.addComponent("jms", jmsComponent);


				context.addRoutes(new CardRoute()); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public CamelContext getCamelContext(){
		return context;
	}

	public void start(){
		try {
			System.out.println("asfdasfas");
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
