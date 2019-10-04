package co.com.ceiba.domain.port.person;

import co.com.ceiba.domain.model.person.Person;

public interface PersonRepository {
	public void save(Person person);
	public void delete(int identification);
}
