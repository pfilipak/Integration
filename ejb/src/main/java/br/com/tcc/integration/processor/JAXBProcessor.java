package br.com.tcc.integration.processor;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.component.file.GenericFile;

import br.com.tcc.integration.domain.Card;

public class JAXBProcessor implements Processor{

	@Override
	public void process(Exchange exchange) throws Exception {
		Message stream = exchange.getIn(Message.class);
		String body = ((GenericFile<?>)stream.getBody()).getAbsoluteFilePath();
		JAXBContext context = JAXBContext.newInstance(Card.class) ;
		Unmarshaller unmarshaller = context.createUnmarshaller() ;
		Card client = (Card)unmarshaller.unmarshal (new File(body));
		exchange.getIn().setBody(client, Card.class);
	}
}

