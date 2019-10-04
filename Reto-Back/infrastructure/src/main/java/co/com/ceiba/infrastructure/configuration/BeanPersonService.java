package co.com.ceiba.infrastructure.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.com.ceiba.domain.port.person.PersonDAO;
import co.com.ceiba.domain.service.person.ObtainPersonInformationService;

@Configuration
public class BeanPersonService {
	
	PersonDAO personDao;
	
	@Autowired
	BeanPersonService(@Qualifier(value = "personDaoH2")
	PersonDAO personDao){
		this.personDao = personDao;
	}
	
	@Bean
	public ObtainPersonInformationService personInformationService() {
		return new ObtainPersonInformationService(this.personDao);
	}
}
