package br.com.tcc.integration.hornetq;
/*
 * Copyright 2009 Red Hat, Inc.
 * Red Hat licenses this file to you under the Apache License, version
 * 2.0 (the "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *    http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.  See the License for the specific language governing
 * permissions and limitations under the License.
 */

import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;

import br.com.tcc.integration.domain.Card;
import br.com.tcc.integration.hornetq.listener.CardMessageListener;

/**
 * A simple JMS Queue example that creates a producer and consumer on a queue and sends then receives a message.
 *
 * @author <a href="ataylor@redhat.com">Andy Taylor</a>
 */
public class CardSender  {
	public static void main(final String[] args) throws Exception {
		new CardSender().run();
	}

	public boolean run() throws Exception {
		Connection connection = null;
		InitialContext initialContext = null;
		try	{
			initialContext = getInitialContext();
			Queue queue = (Queue)initialContext.lookup("/queue/testQueue");

			ConnectionFactory cf = (ConnectionFactory)initialContext.lookup("/ConnectionFactory");
			connection = cf.createConnection();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			MessageProducer producer = session.createProducer(queue);

			MessageConsumer messageConsumer = session.createConsumer(queue);
			messageConsumer.setMessageListener(new CardMessageListener());

			connection.start();

			for (int i = 0; i < 500; i++){
				Card card = new Card(i, "card_" + i);
				ObjectMessage message = (ObjectMessage) session.createObjectMessage(card);
				System.out.println("Sent message: " + card);
				producer.send(message);
				Thread.sleep(3000);
			}
			return true;
		}
		finally {
			if (initialContext != null)	{
				initialContext.close();
			}
			if (connection != null)	{
				connection.close();
			}
		}
	}
	
	private static InitialContext getInitialContext() throws javax.naming.NamingException {  
		Properties p = new Properties( );  
		p.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");  
		p.put(Context.URL_PKG_PREFIXES,	" org.jboss.naming:org.jnp.interfaces");  
		p.put(Context.PROVIDER_URL, "jnp://localhost:1099");  
		return new javax.naming.InitialContext(p);  
	}    

}
