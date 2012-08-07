package com.zombietank.config.db;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

public class ScriptRunner {
	private static final Logger logger = LoggerFactory.getLogger(ScriptRunner.class);
	private final ScriptHistory scriptHistory;
	private final JdbcTemplate jdbcTemplate;

	public ScriptRunner(ScriptHistory scriptHistory, DataSource dataSource) {
		this.scriptHistory = scriptHistory;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public void execute(Script script) throws DataAccessException {
		if(script.isSystem() || scriptHistory.hasNotBeenRun(script)) {
			run(script);
		} else {
			logger.info("Not running {}", script.getPath());
		}
	}
	
	private void run(Script script) {
		logger.info("Executing script: {}", script);
		jdbcTemplate.execute(script.getContents());
		scriptHistory.recordRun(script);
	}
}
