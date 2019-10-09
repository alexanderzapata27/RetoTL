package co.com.ceiba.application.people;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.ceiba.domain.dto.person.PersonDTO;
import co.com.ceiba.domain.model.person.Person;
import co.com.ceiba.domain.service.person.DeletePersonService;
import co.com.ceiba.domain.service.person.SavePersonService;

@Component
public class HandlerPeopleAdministration {
	
	SavePersonService savePersonService;
	DeletePersonService deletePersonService;
	
	@Autowired
	public HandlerPeopleAdministration(SavePersonService savePersonService, DeletePersonService deletePersonService) {
		this.savePersonService = savePersonService;
		this.deletePersonService = deletePersonService;
	}
	
	public Person executeCreation(PersonDTO person) throws Exception {
		return savePersonService.execute(person);
	}
	
	public void executeDelete(int identification) throws Exception {
		deletePersonService.execute(identification);
	}
}
