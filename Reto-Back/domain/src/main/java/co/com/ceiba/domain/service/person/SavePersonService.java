package co.com.ceiba.domain.service.person;

import co.com.ceiba.domain.dto.person.PersonDTO;
import co.com.ceiba.domain.model.person.Person;
import co.com.ceiba.domain.port.person.PersonRepository;

public class SavePersonService {
	
	PersonRepository personRepository;
	
	public SavePersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	public Person execute(PersonDTO person) throws Exception {
		return personRepository.save(person);
	}
}
