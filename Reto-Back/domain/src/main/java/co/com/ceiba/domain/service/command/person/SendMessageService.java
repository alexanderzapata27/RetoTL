package co.com.ceiba.domain.service.command.person;

import co.com.ceiba.domain.exception.TLException;
import co.com.ceiba.domain.port.person.Publisher;

public class SendMessageService {
	
	Publisher publisher;
	
	public SendMessageService(Publisher publisher) {
		this.publisher = publisher;
	}

	public void executeSendOfPeopleMostOf18() throws TLException {
		publisher.sendPeopleMostOf18();
	}
	
	public void executeSendOfPeopleLessOf18() throws TLException {
		publisher.sendPeopleLessOf18();
	}
}