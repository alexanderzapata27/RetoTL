package co.com.ceiba.domain.service.person;

import co.com.ceiba.domain.port.person.PersonRepository;

public class DeletePersonService {
	
	PersonRepository personRepository;
	
	public DeletePersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	public void execute(int identification) throws Exception {
		personRepository.delete(identification);
	}
}
