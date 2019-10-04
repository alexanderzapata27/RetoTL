package co.com.ceiba.domain.service.person;

import co.com.ceiba.domain.model.person.Person;
import co.com.ceiba.domain.port.person.PersonRepository;

public class SavePersonService {
	
	PersonRepository personRepository;
	
	public SavePersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	public void execute(Person person) {
		personRepository.save(person);
	}
}
