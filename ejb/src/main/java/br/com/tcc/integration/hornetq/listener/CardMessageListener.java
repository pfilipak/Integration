package br.com.tcc.integration.hornetq.listener;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import br.com.tcc.integration.domain.Card;

public class CardMessageListener implements MessageListener {
	
	@Override
	public void onMessage(Message message) {
		try{
			ObjectMessage messageReceived = (ObjectMessage) message;
			Card card = (Card)messageReceived.getObject();
			System.out.println("Received message: " + card);					
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
