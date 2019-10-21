package co.com.ceiba.application.people;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.ceiba.domain.exception.TLException;
import co.com.ceiba.domain.service.command.person.SendMessageService;
import co.com.ceiba.domain.service.query.person.ReadMessageService;

@Component
public class HandlerQueueMessages {
	SendMessageService sendMessageService;
	ReadMessageService readMessageService;
	
	@Autowired
	public HandlerQueueMessages(SendMessageService sendMessageService, ReadMessageService readMessageService) {
		this.sendMessageService = sendMessageService;
		this.readMessageService = readMessageService;
	}
	
	public void executeSendOfPeopleMostOf18() throws TLException {
		sendMessageService.executeSendOfPeopleMostOf18();
	}
	
	public void executeSendOfPeopleLessOf18() throws TLException {
		sendMessageService.executeSendOfPeopleLessOf18();
	}
	
	public void executeReadOfPeopleMostOf18() throws TLException {
		readMessageService.executeSendOfPeopleMostOf18();
	}
	
	public void executeReadOfPeopleLessOf18() throws TLException {
		readMessageService.executeSendOfPeopleLessOf18();
	}
}
