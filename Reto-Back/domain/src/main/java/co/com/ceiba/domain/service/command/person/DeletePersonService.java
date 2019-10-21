package co.com.ceiba.domain.service.command.person;

import co.com.ceiba.domain.port.person.PersonRepository;

public class DeletePersonService {
	
	PersonRepository personRepository;
	
	public DeletePersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	public void execute(int identification){
		personRepository.delete(identification);
	}
}
