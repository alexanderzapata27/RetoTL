package co.com.ceiba.infrastructure.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import co.com.ceiba.domain.model.person.Person;

public class PersonRowMapper implements RowMapper<Person>{

	@Override
	public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
		Person personDto = new Person(rs.getInt("identification"),rs.getString("name"),rs.getString("lastname"),rs.getTimestamp("age").toLocalDateTime().toLocalDate());
		return personDto;
	}
	
}
