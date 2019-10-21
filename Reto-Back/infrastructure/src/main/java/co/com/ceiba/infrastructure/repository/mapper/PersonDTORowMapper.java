package co.com.ceiba.infrastructure.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import co.com.ceiba.domain.dto.person.PersonDTO;
import co.com.ceiba.domain.model.person.Person;

public class PersonDTORowMapper implements RowMapper<PersonDTO>{

	@Override
	public PersonDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		Person temporalPerson = new Person(rs.getInt("identification"),rs.getString("name"),rs.getString("lastname"),rs.getDate("dateOfBirth").toLocalDate());
		return new PersonDTO(temporalPerson.getIdentification(),temporalPerson.getName(),temporalPerson.getLastname(),temporalPerson.getDateOfBirth().toString(),temporalPerson.getAgeInYears());
	}
	
}
