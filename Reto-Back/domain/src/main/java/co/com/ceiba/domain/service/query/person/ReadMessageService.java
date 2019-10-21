package co.com.ceiba.domain.service.query.person;

import co.com.ceiba.domain.exception.TLException;
import co.com.ceiba.domain.port.person.Receiver;

public class ReadMessageService {
	
	Receiver receiver;
	
	public ReadMessageService(Receiver receiver) {
		this.receiver = receiver;
	}

	public void executeSendOfPeopleMostOf18() throws TLException {
		receiver.readPeopleMostOf18();
	}
	
	public void executeSendOfPeopleLessOf18() throws TLException {
		receiver.readPeopleLessOf18();
	}
}