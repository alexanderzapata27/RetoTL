package co.com.ceiba.infrastructure.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.com.ceiba.domain.port.person.Publisher;
import co.com.ceiba.domain.service.command.person.SendMessageService;

@Configuration
public class BeanSendMessageService {
	
	Publisher publisher;
	
	@Autowired
	BeanSendMessageService(Publisher publisher){
		this.publisher = publisher;
	}
	
	@Bean
	public SendMessageService sendMessageService() {
		return new SendMessageService(this.publisher);
	}	
}
