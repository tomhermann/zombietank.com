package com.zombietank.contact;

import java.util.Collection;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class ContactMessageJdbcRepository implements ContactMessageRepository {
	private final JdbcTemplate jdbcTemplate;
	private final SimpleJdbcInsert insertMessage;

	@Inject
	public ContactMessageJdbcRepository(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.insertMessage = new SimpleJdbcInsert(dataSource).withTableName("contact_messages").usingGeneratedKeyColumns("id");
	}
	
	@Override
	public void save(ContactMessage message) {
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(message);
		Number newId = insertMessage.executeAndReturnKey(parameters);
		message.setId(newId.longValue());
	}
	
	@Override
	public void delete(ContactMessage message) {
		deleteById(message.getId());
	}

	@Override
	public void deleteById(Number id) {
		String sql = "delete from contact_messages where id = ?";
		jdbcTemplate.update(sql, id);
	}

	@Override
	public ContactMessage getMessage(long id) {
		String sql = "select * from contact_messages where id = ?";
		return jdbcTemplate.queryForObject(sql, rowMapper(), id);
	}

	@Override
	public Collection<ContactMessage> getAllMessages() {
		return jdbcTemplate.query("select * from contact_messages", rowMapper());
	}

	private static BeanPropertyRowMapper<ContactMessage> rowMapper() {
		return BeanPropertyRowMapper.newInstance(ContactMessage.class);
	}
}
