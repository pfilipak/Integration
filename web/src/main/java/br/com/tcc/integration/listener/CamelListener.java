package br.com.tcc.integration.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import br.com.tcc.integration.context.CamelContextService;

public class CamelListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		new CamelContextService().stop();
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		new CamelContextService().start();
	}

}