package co.com.ceiba.domain.service.command.person;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import co.com.ceiba.domain.port.person.Publisher;

public class SendMessageService {
	
	Publisher publisher;
	
	public SendMessageService(Publisher publisher) {
		this.publisher = publisher;
	}

	public void executeSendOfPeopleMostOf18() throws IOException, TimeoutException {
		publisher.sendPeopleMostOf18();
	}
	
	public void executeSendOfPeopleLessOf18() throws IOException, TimeoutException {
		publisher.sendPeopleLessOf18();
	}
}