package co.com.ceiba.application.people;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.ceiba.domain.dto.person.PersonDTO;
import co.com.ceiba.domain.exception.TLException;
import co.com.ceiba.domain.service.command.person.DeletePersonService;
import co.com.ceiba.domain.service.command.person.SavePersonService;
import co.com.ceiba.domain.service.command.person.UpdatePersonService;

@Component
public class HandlerPeopleAdministration {
	
	SavePersonService savePersonService;
	UpdatePersonService updatePersonService;
	DeletePersonService deletePersonService;
	
	@Autowired
	public HandlerPeopleAdministration(SavePersonService savePersonService, DeletePersonService deletePersonService,UpdatePersonService updatePersonService) {
		this.savePersonService = savePersonService;
		this.deletePersonService = deletePersonService;
		this.updatePersonService = updatePersonService;
	}
	
	public PersonDTO executeCreation(PersonDTO person) throws TLException {
		return savePersonService.execute(person);
	}
	
	public void executeDelete(int identification){
		deletePersonService.execute(identification);
	}

	public PersonDTO executeUpdate(PersonDTO person) throws TLException {
		return updatePersonService.execute(person);
	}
}
