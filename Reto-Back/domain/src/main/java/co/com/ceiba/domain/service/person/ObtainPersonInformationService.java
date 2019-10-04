package co.com.ceiba.domain.service.person;

import co.com.ceiba.domain.model.person.Person;
import co.com.ceiba.domain.port.person.PersonDAO;

public class ObtainPersonInformationService {
	PersonDAO personDao;
	
	public ObtainPersonInformationService(PersonDAO personDao) {
		this.personDao = personDao;
	}

	public Person execute(int identification) {
		return personDao.findByIdentification(identification);
	}
}
