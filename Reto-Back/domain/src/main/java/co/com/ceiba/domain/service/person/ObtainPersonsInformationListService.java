package co.com.ceiba.domain.service.person;

import java.util.List;
import co.com.ceiba.domain.model.person.Person;
import co.com.ceiba.domain.port.person.PersonDAO;

public class ObtainPersonsInformationListService {
	PersonDAO personDao;
	
	public ObtainPersonsInformationListService(PersonDAO personDao) {
		this.personDao = personDao;
	}

	public List<Person> execute() {
		return personDao.getAll();
	}
}
