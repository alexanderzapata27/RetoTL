package co.com.ceiba.domain.port.person;

import java.util.List;

import co.com.ceiba.domain.model.person.Person;

public interface PersonDAO {
	public Person findByIdentification(int identification);
	public List<Person> getAll();
}
