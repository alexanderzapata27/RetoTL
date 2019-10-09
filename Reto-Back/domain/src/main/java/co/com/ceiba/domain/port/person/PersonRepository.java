package co.com.ceiba.domain.port.person;

import co.com.ceiba.domain.dto.person.PersonDTO;
import co.com.ceiba.domain.model.person.Person;

public interface PersonRepository {
	public Person save(PersonDTO person) throws Exception;
	public void delete(int identification);
}
