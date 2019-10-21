package co.com.ceiba.infrastructure.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.com.ceiba.domain.port.person.PersonDAO;
import co.com.ceiba.domain.port.person.PersonRepository;
import co.com.ceiba.domain.service.command.person.SavePersonService;

@Configuration
public class BeanSavePersonService {
	
	PersonRepository personRepository;
	PersonDAO personDao;
	
	@Autowired
	BeanSavePersonService(@Qualifier(value = "personRepositoryH2")
	PersonRepository personRepository, @Qualifier(value = "personDaoH2")
	PersonDAO personDao){
		this.personRepository = personRepository;
		this.personDao = personDao;
	}
	
	@Bean
	public SavePersonService savePersonService() {
		return new SavePersonService(this.personRepository,this.personDao);
	}
}
