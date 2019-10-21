package co.com.ceiba.domain.service.command.person;

import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

import co.com.ceiba.domain.dto.person.ErrorDTO;
import co.com.ceiba.domain.dto.person.PersonDTO;
import co.com.ceiba.domain.exception.TLException;
import co.com.ceiba.domain.port.person.PersonDAO;
import co.com.ceiba.domain.port.person.PersonRepository;

public class SavePersonService {
	private final Logger LOGGER = Logger.getLogger("SavePersonService");
	PersonRepository personRepository;
	PersonDAO personDao;
	
	public SavePersonService(PersonRepository personRepository,PersonDAO personDao) {
		this.personRepository = personRepository;
		this.personDao = personDao;
	}

	public PersonDTO execute(PersonDTO person) throws TLException {
		PersonDTO response = null;
		try{
			PersonDTO existentPerson = personDao.findByIdentification(person.getIdentification());
			if(null!=existentPerson) {
				response = createErrorObject("El usuario se encuentra repetido");
			}
		}catch(NoSuchElementException e) {
			response = personRepository.save(person);
			LOGGER.log(Level.INFO,"El usuario no estaba repetido y se crea",e);
		}
		return response;
	}
	
	public PersonDTO createErrorObject(String message) {
		return new PersonDTO(new ErrorDTO(405, message));		
	}
}
