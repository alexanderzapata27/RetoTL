package co.com.ceiba.infrastructure.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.application.people.HandlerQueueMessages;
import co.com.ceiba.domain.exception.TLException;

@RestController
@RequestMapping("/rabbitmq")
public class RestMensajeriaPersonas {
	
	private HandlerQueueMessages handlerQueueMessages;
	
	@Autowired
	public RestMensajeriaPersonas(HandlerQueueMessages handlerQueueMessages) {
		this.handlerQueueMessages = handlerQueueMessages;
	}
	
	@GetMapping(path = "/sendPeopleMostOf18")
	public void sendPeopleMostOf18() throws TLException {
		handlerQueueMessages.executeSendOfPeopleMostOf18();
	}
	
	@GetMapping(path = "/sendPeopleLessOf18")
	public void sendPeopleLessOf18() throws TLException {
		handlerQueueMessages.executeSendOfPeopleLessOf18();
	}
	
	@GetMapping(path = "/readPeopleMostOf18")
	public void readPeopleMostOf18() throws TLException {
		handlerQueueMessages.executeReadOfPeopleMostOf18();
	}
	
	@GetMapping(path = "/readPeopleLessOf18")
	public void readPeopleLessOf18() throws TLException {
		handlerQueueMessages.executeReadOfPeopleLessOf18();
	}
}
