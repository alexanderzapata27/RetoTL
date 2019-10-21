package co.com.ceiba.infrastructure.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.com.ceiba.domain.port.person.PersonDAO;
import co.com.ceiba.domain.port.person.PersonRepository;
import co.com.ceiba.domain.service.command.person.UpdatePersonService;

@Configuration
public class BeanUpdatePersonService {
	
	PersonRepository personRepository;
	PersonDAO personDao;
	
	@Autowired
	BeanUpdatePersonService(@Qualifier(value = "personRepositoryH2")
	PersonRepository personRepository, @Qualifier(value = "personDaoH2")
	PersonDAO personDao){
		this.personRepository = personRepository;
		this.personDao = personDao;
	}
	
	@Bean
	public UpdatePersonService updatePersonService() {
		return new UpdatePersonService(this.personRepository,this.personDao);
	}
}
