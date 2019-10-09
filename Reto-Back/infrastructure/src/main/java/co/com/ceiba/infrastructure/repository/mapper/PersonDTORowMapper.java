package co.com.ceiba.infrastructure.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import co.com.ceiba.domain.dto.person.PersonDTO;

public class PersonDTORowMapper implements RowMapper<PersonDTO>{

	@Override
	public PersonDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new PersonDTO(rs.getInt("identification"),rs.getString("name"),rs.getString("lastname"),rs.getDate("dateOfBirth").toString());
	}
	
}
