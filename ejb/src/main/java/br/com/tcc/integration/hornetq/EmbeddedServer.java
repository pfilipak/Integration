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

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.naming.InitialContext;

/**
 * A simple JMS Queue example that creates a producer and consumer on a queue and sends then receives a message.
 *
 * @author <a href="ataylor@redhat.com">Andy Taylor</a>
 */
public class EmbeddedServer extends HornetQExample
{
	public static void main(final String[] args)
	{
		new EmbeddedServer().run(args);
	}

	@Override
	public boolean runExample() throws Exception
	{
		Connection connection = null;
		InitialContext initialContext = null;
		try
		{
			// Step 1. Create an initial context to perform the JNDI lookup.
			initialContext = getContext(0);

			// Step 2. Perfom a lookup on the queue
			Queue queue = (Queue)initialContext.lookup("/queue/ExampleQueue");

			// Step 3. Perform a lookup on the Connection Factory
			ConnectionFactory cf = (ConnectionFactory)initialContext.lookup("/ConnectionFactory");

			// Step 4.Create a JMS Connection
			connection = cf.createConnection();

			// Step 5. Create a JMS Session
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			// Step 6. Create a JMS Message Producer
			MessageProducer producer = session.createProducer(queue);

			// Step 7. Create a Text Message
//			for (int i = 0; i < 500; i++){
//				CardModel cardModel = new CardModel(i,"card" + String.valueOf(i));
//				ObjectMessage message = (ObjectMessage) session.createObjectMessage(cardModel);
//				System.out.println("Sent message: " + ((CardModel)message.getObject()).getNumber());
//				// Step 8. Send the Message
//				producer.send(message);
//				Thread.sleep(3000);
//			}



			connection.start();
			final MessageConsumer messageConsumer = session.createConsumer(queue);
			messageConsumer.setMessageListener(new MessageListener(){

				@Override
				public void onMessage(Message message) {
					try{
						ObjectMessage messageReceived = (ObjectMessage) message;
						messageReceived = (ObjectMessage)messageConsumer.receive();
						// TODO Auto-generated catch block
						System.out.println("Received message: " + ((CardModel)messageReceived.getObject()).getId());					
					} catch (Exception e) {
						e.printStackTrace();
					}
				}});

			return true;
		}
		finally
		{
			// Step 12. Be sure to close our JMS resources!
			if (initialContext != null)
			{
				initialContext.close();
			}
			if (connection != null)
			{
				connection.close();
			}
		}
	}

	private boolean isToFinish() {
		// TODO Auto-generated method stub
		return false;
	}

}
