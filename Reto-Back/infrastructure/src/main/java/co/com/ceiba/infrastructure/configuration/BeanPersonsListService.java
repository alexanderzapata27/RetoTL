package co.com.ceiba.infrastructure.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.com.ceiba.domain.port.person.PersonDAO;
import co.com.ceiba.domain.service.person.ObtainPersonsInformationListService;

@Configuration
public class BeanPersonsListService {
	
	PersonDAO personDao;
	
	@Autowired
	BeanPersonsListService(@Qualifier(value = "personDaoH2")
	PersonDAO personDao){
		this.personDao = personDao;
	}
	
	@Bean
	public ObtainPersonsInformationListService personInformationListService() {
		return new ObtainPersonsInformationListService(this.personDao);
	}
}
