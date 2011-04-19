package br.com.tcc.integration.hornetq;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;


public class Producer {

	private static AtomicInteger cont = new AtomicInteger();
	
	public void example() throws Exception  
	{  
		String destinationName = "queue/Integration";  

		Context ic = null;  
		ConnectionFactory cf = null;  
		Connection connection =  null;  

		try  
		{           
			ic = getInitialContext();  

			cf = (ConnectionFactory)ic.lookup("/ConnectionFactory");  
			Queue queue = (Queue)ic.lookup(destinationName);  

			connection = cf.createConnection();  
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);  
			MessageProducer publisher = session.createProducer(queue);  
			MessageConsumer consumer = session.createConsumer(queue);

			connection.start();  

//			for (int i = 0; i < 1000; i++) {
				TextMessage message = session.createTextMessage("-> Message " + cont.incrementAndGet());  
//				System.out.println("Sending ... [" + message.getText() + "]");
				publisher.send(message);  
			
				Thread.sleep(1000);
				System.out.println("Peguei " + ((TextMessage)consumer.receive()).getText());
//			}

		}  
		finally  
		{           
			if(ic != null)  
			{  
				try  
				{  
					ic.close();  
				}  
				catch(Exception e)  
				{  
					throw e;  
				}  
			}  

			closeConnection(connection);  
		}  
	}  
	private void closeConnection(Connection con)  
	{        
		try  
		{  
			if (con != null)  
			{  
				con.close();  
			}           
		}  
		catch(JMSException jmse)  
		{  
			System.out.println("Could not close connection " + con +" exception was " + jmse);  
		}  
	}  


	public static void main(String[] args) throws Exception  
	{  
		new Producer().example(); 
	}  
	
	public static Context getInitialContext( )  
	throws javax.naming.NamingException {  

		Properties p = new Properties( );  
		p.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");  
		p.put(Context.URL_PKG_PREFIXES,	" org.jboss.naming:org.jnp.interfaces");  
		p.put(Context.PROVIDER_URL, "jnp://localhost:1099");  
		return new javax.naming.InitialContext(p);  
	}    
}
