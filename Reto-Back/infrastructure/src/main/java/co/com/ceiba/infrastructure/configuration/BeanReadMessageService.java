package co.com.ceiba.infrastructure.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.com.ceiba.domain.port.person.Receiver;
import co.com.ceiba.domain.service.query.person.ReadMessageService;

@Configuration
public class BeanReadMessageService {
	
	Receiver receiver;
	
	@Autowired
	BeanReadMessageService(@Qualifier(value = "receiverRabbitMQ")
	Receiver receiver){
		this.receiver = receiver;
	}
	
	@Bean
	public ReadMessageService readMessageService() {
		return new ReadMessageService(this.receiver);
	}	
}
