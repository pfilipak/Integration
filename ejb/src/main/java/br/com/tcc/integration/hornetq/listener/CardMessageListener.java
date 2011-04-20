package br.com.tcc.integration.hornetq.listener;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import br.com.tcc.integration.domain.CardDomain;

public class CardMessageListener implements MessageListener {
	
	@Override
	public void onMessage(Message message) {
		try{
			ObjectMessage messageReceived = (ObjectMessage) message;
			CardDomain card = (CardDomain)messageReceived.getObject();
			System.out.println("Received message: " + card.getId());					
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
