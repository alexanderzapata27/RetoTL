package co.com.ceiba.domain.service.command.person;

import co.com.ceiba.domain.dto.person.PersonDTO;
import co.com.ceiba.domain.exception.TLException;
import co.com.ceiba.domain.port.person.PersonDAO;
import co.com.ceiba.domain.port.person.PersonRepository;

public class UpdatePersonService {
	
	PersonRepository personRepository;
	PersonDAO personDao;
	
	public UpdatePersonService(PersonRepository personRepository,PersonDAO personDao) {
		this.personRepository = personRepository;
		this.personDao = personDao;
	}

	public PersonDTO execute(PersonDTO person) throws TLException {
		return personRepository.update(person);
	}
}
