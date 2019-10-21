package co.com.ceiba.infrastructure.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.com.ceiba.domain.port.person.PersonRepository;
import co.com.ceiba.domain.service.command.person.DeletePersonService;

@Configuration
public class BeanDeletePersonService {
	
	PersonRepository personRepository;
	
	@Autowired
	BeanDeletePersonService(@Qualifier(value = "personRepositoryH2")
	PersonRepository personRepository){
		this.personRepository = personRepository;
	}
	
	@Bean
	public DeletePersonService deletePersonService() {
		return new DeletePersonService(this.personRepository);
	}
}
