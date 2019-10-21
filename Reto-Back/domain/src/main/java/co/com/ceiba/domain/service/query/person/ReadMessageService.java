package co.com.ceiba.domain.service.query.person;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import co.com.ceiba.domain.port.person.Receiver;

public class ReadMessageService {
	
	Receiver receiver;
	
	public ReadMessageService(Receiver receiver) {
		this.receiver = receiver;
	}

	public void executeSendOfPeopleMostOf18() throws IOException, TimeoutException {
		receiver.readPeopleMostOf18();
	}
	
	public void executeSendOfPeopleLessOf18() throws IOException, TimeoutException {
		receiver.readPeopleLessOf18();
	}
}