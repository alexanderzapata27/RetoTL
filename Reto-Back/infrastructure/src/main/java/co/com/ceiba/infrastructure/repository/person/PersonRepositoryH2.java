package co.com.ceiba.infrastructure.repository.person;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.com.ceiba.domain.model.person.Person;
import co.com.ceiba.domain.port.person.PersonDAO;
import co.com.ceiba.domain.port.person.PersonRepository;
import co.com.ceiba.infrastructure.repository.mapper.PersonRowMapper;

@Transactional
@Repository
public class PersonRepositoryH2 implements PersonRepository{

	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public PersonRepositoryH2(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void save(Person person) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int identification) {
		// TODO Auto-generated method stub
		
	}
}
