package co.com.ceiba.infrastructure.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.com.ceiba.domain.port.person.PersonRepository;
import co.com.ceiba.domain.service.person.SavePersonService;

@Configuration
public class BeanSavePersonService {
	
	PersonRepository personRepository;
	
	@Autowired
	BeanSavePersonService(@Qualifier(value = "personRepositoryH2")
	PersonRepository personRepository){
		this.personRepository = personRepository;
	}
	
	@Bean
	public SavePersonService savePersonService() {
		return new SavePersonService(this.personRepository);
	}
}
