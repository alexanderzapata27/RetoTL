package co.com.ceiba.infrastructure.repository.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

import co.com.ceiba.domain.dto.person.PersonDTO;
import co.com.ceiba.domain.exception.TLException;
import co.com.ceiba.domain.model.person.Person;

@Component
public class TransformerBetweenPersonDTOAndPerson {

	public Person transformFromPersonDtoToPerson(PersonDTO personDto) throws TLException {
		if(0==personDto.getId()) {
			throw new TLException("No se pudo guardar la persona enviada");
		}
		return new Person(personDto.getIdentification(), personDto.getName(), personDto.getLastname(), transformFromStringToLocalDate(personDto.getDateOfBirth()));
	}
	
	public LocalDate transformFromStringToLocalDate(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return LocalDate.parse(date, formatter);
	}
}
