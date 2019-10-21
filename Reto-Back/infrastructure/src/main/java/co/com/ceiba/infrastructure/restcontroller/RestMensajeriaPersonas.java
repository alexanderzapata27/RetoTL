package co.com.ceiba.infrastructure.restcontroller;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.application.people.HandlerQueueMessages;

@RestController
@RequestMapping("/rabbitmq")
public class RestMensajeriaPersonas {
	
	private HandlerQueueMessages handlerQueueMessages;
	
	@Autowired
	public RestMensajeriaPersonas(HandlerQueueMessages handlerQueueMessages) {
		this.handlerQueueMessages = handlerQueueMessages;
	}
	
	@GetMapping(path = "/sendPeopleMostOf18")
	public void sendPeopleMostOf18() {
		try {
			handlerQueueMessages.executeSendOfPeopleMostOf18();
		} catch (IOException e) {
		} catch (TimeoutException e) {
		}
	}
	
	@GetMapping(path = "/sendPeopleLessOf18")
	public void sendPeopleLessOf18() {
		try {
			handlerQueueMessages.executeSendOfPeopleLessOf18();
		} catch (IOException e) {
		} catch (TimeoutException e) {
		}
	}
	
	@GetMapping(path = "/readPeopleMostOf18")
	public void readPeopleMostOf18() throws IOException, TimeoutException {
		handlerQueueMessages.executeReadOfPeopleMostOf18();
	}
	
	@GetMapping(path = "/readPeopleLessOf18")
	public void readPeopleLessOf18() throws IOException, TimeoutException {
		handlerQueueMessages.executeReadOfPeopleLessOf18();
	}
	

}
