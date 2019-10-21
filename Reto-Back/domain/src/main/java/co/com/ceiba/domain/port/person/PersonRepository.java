package co.com.ceiba.domain.port.person;

import co.com.ceiba.domain.dto.person.PersonDTO;
import co.com.ceiba.domain.exception.TLException;

public interface PersonRepository {
	public PersonDTO save(PersonDTO person) throws TLException;
	public void delete(int identification);
	public PersonDTO update(PersonDTO person) throws TLException;
}
