package co.com.ceiba.infrastructure.repository.person;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.com.ceiba.domain.model.person.Person;
import co.com.ceiba.domain.port.person.PersonDAO;
import co.com.ceiba.infrastructure.repository.mapper.PersonRowMapper;

@Transactional
@Repository
public class PersonDaoH2 implements PersonDAO{

	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public PersonDaoH2(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Person> getAll() {
		String sql = "SELECT identification, name, lastname, age FROM TBL_PERSONS";
		RowMapper<Person> rowMapper = new PersonRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public Person findByIdentification(int identification) {
		String sql = "SELECT identification, name, lastname, age FROM TBL_PERSONS where identification = :identification";
		RowMapper<Person> rowMapper = new PersonRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper).stream().findFirst().get();
	}

}
