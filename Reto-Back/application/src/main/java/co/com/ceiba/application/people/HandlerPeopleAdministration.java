package co.com.ceiba.application.people;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.ceiba.domain.model.person.Person;
import co.com.ceiba.domain.service.person.SavePersonService;

@Component
public class HandlerPeopleAdministration {
	
	SavePersonService savePersonService;
	
	@Autowired
	public HandlerPeopleAdministration(SavePersonService savePersonService) {
		this.savePersonService = savePersonService;
	}
	
	public void execute(Person person) {
		savePersonService.execute(person);
	}
}
