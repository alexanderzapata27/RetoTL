package co.com.ceiba.application.people;

import java.util.List;

import org.springframework.stereotype.Component;

import co.com.ceiba.domain.dto.person.PersonDTO;
import co.com.ceiba.domain.port.person.PersonDAO;

@Component
public class HandlerPeopleInformation {
	PersonDAO personDao;
	
	public HandlerPeopleInformation(PersonDAO personDao) {
		this.personDao = personDao;
	}
	
	public PersonDTO execute(int identification) {
		return personDao.findByIdentification(identification);
	}
	
	public List<PersonDTO> execute() {
		return personDao.getAll();
	}
}
