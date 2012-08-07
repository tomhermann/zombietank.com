package com.zombietank.config.db;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

public class ScriptRunner {
	private final ScriptHistory scriptHistory;
	private final JdbcTemplate jdbcTemplate;

	public ScriptRunner(ScriptHistory scriptHistory, DataSource dataSource) {
		this.scriptHistory = scriptHistory;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public void execute(Script script) throws DataAccessException {
		if(script.isSystem() || scriptHistory.hasNotBeenRun(script)) {
			run(script);
		}
	}
	
	private void run(Script script) {
		jdbcTemplate.execute(script.getContents());
		scriptHistory.recordRun(script);
	}
}
