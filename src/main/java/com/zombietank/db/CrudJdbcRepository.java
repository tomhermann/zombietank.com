package com.zombietank.db;

import java.util.Collection;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import com.google.common.base.Preconditions;

public abstract class CrudJdbcRepository<T extends Persistable> implements CrudRepository<T> {
	private final JdbcTemplate jdbcTemplate;
	private final String tableName;
	private final BeanPropertyRowMapper<T> rowMapper;
	private final SimpleJdbcInsert insert;

	public CrudJdbcRepository(Class<T> clazz, DataSource dataSource, String tableName) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.tableName = tableName;
		this.rowMapper = BeanPropertyRowMapper.newInstance(clazz);
		this.insert = new SimpleJdbcInsert(jdbcTemplate).withTableName(tableName).usingGeneratedKeyColumns(getIdFieldName());
	}
	
	@Override
	public void save(T persistable) {
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(persistable);
		Number newId = insert.executeAndReturnKey(parameters);
		persistable.setId(newId.longValue());
	}
	
	@Override
	public void delete(T persistable) {
		Preconditions.checkNotNull(persistable);
		deleteById(persistable.getId());
	}

	@Override
	public void deleteById(long id) {
		String sql = "delete from " + tableName + " where " + getIdFieldName() + " = ?";
		jdbcTemplate.update(sql, id);
	}

	@Override
	public T get(long id) {
		String sql = "select * from " + tableName + " where " + getIdFieldName() + " = ?";
		return jdbcTemplate.queryForObject(sql, rowMapper, id);
	}

	@Override
	public Collection<T> getAll() {
		return jdbcTemplate.query("select * from " + tableName, rowMapper);
	}
	
	protected String getIdFieldName() {
		return "id";
	}
}
