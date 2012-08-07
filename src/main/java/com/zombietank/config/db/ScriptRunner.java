package com.zombietank.config.db;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

public class ScriptRunner {
	private final JdbcTemplate jdbcTemplate;

	public ScriptRunner(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public void execute(Script script) throws DataAccessException {
		if(!scriptHasRun(script)) {
			jdbcTemplate.execute(script.getContents());
		}
	}

	private boolean scriptHasRun(Script script) {
		return false;
	}
}
