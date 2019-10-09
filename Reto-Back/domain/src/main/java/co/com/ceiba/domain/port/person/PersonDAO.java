package co.com.ceiba.domain.port.person;

import java.util.List;

import co.com.ceiba.domain.dto.person.PersonDTO;

public interface PersonDAO {
	public PersonDTO findByIdentification(int identification);
	public List<PersonDTO> getAll();
}
