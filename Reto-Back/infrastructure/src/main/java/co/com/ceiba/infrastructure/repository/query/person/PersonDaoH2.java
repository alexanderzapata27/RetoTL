package co.com.ceiba.infrastructure.repository.query.person;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.com.ceiba.domain.dto.person.PersonDTO;
import co.com.ceiba.domain.port.person.PersonDAO;
import co.com.ceiba.infrastructure.repository.mapper.PersonDTORowMapper;

@Transactional
@Repository
public class PersonDaoH2 implements PersonDAO{

	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public PersonDaoH2(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<PersonDTO> getAll() {
		String sql = "SELECT identification, name, lastname, dateofbirth FROM TBL_PERSONS";
		RowMapper<PersonDTO> rowMapper = new PersonDTORowMapper();
		return this.jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public PersonDTO findByIdentification(int identification) {
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		mapSqlParameterSource.addValue("identification", identification);
		String sql = "SELECT identification, name, lastname, dateofbirth FROM TBL_PERSONS where identification = ?";
		RowMapper<PersonDTO> rowMapper = new PersonDTORowMapper();
		return this.jdbcTemplate.query(sql, rowMapper, identification).stream().findFirst().get();
	}

}
