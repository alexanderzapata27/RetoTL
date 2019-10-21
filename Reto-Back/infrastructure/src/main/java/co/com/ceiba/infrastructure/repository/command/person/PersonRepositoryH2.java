package co.com.ceiba.infrastructure.repository.command.person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.com.ceiba.domain.dto.person.PersonDTO;
import co.com.ceiba.domain.exception.TLException;
import co.com.ceiba.domain.port.person.PersonRepository;
import co.com.ceiba.infrastructure.repository.mapper.MapperResult;
import co.com.ceiba.infrastructure.repository.mapper.TransformerBetweenPersonDTOAndPerson;

@Transactional
@Repository
public class PersonRepositoryH2 implements PersonRepository, MapperResult{

	private static final String INSERT_PERSON_SQL = "INSERT INTO TBL_PERSONS(identification,name,lastname,dateOfBirth) values(?,?,?,?)";
	private static final String UPDATE_PERSON_SQL = "UPDATE TBL_PERSONS SET name=?,lastname=?,dateOfBirth=? WHERE identification=?";
	private static final String DELETE_PERSON_SQL = "DELETE FROM TBL_PERSONS WHERE identification=?";
	private final JdbcTemplate jdbcTemplate;
	private TransformerBetweenPersonDTOAndPerson personTransformer;
	
	@Autowired
	public PersonRepositoryH2(JdbcTemplate jdbcTemplate, TransformerBetweenPersonDTOAndPerson personTransformer) {
		this.jdbcTemplate = jdbcTemplate;
		this.personTransformer = personTransformer;
	}

	@Override
	public PersonDTO save(PersonDTO person) throws TLException {
		KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_PERSON_SQL, Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, person.getIdentification());
				ps.setString(2, person.getName());
				ps.setString(3, person.getLastname());
				ps.setObject(4, personTransformer.transformFromStringToLocalDate(person.getDateOfBirth()));
				return ps;
			}
		}, holder);

		int newUserId = holder.getKey().intValue();
		person.setId(newUserId);
		return person;
		
	}

	@Override
	public void delete(int identification) {
		jdbcTemplate.update(DELETE_PERSON_SQL, identification);
	}
	
	@Override
	public PersonDTO update(PersonDTO person) throws TLException {
		KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(UPDATE_PERSON_SQL, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, person.getName());
				ps.setString(2, person.getLastname());
				ps.setObject(3, personTransformer.transformFromStringToLocalDate(person.getDateOfBirth()));
				ps.setInt(4, person.getIdentification());
				return ps;
			}
		}, holder);

		return person;
	}
}
