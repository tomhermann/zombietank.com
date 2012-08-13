package com.zombietank.config.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

public class ScriptRunner {
	private static final Logger logger = LoggerFactory.getLogger(ScriptRunner.class);
	private final ScriptHistory scriptHistory;
	private final JdbcTemplate jdbcTemplate;

	public ScriptRunner(ScriptHistory scriptHistory, JdbcTemplate jdbcTemplate) {
		this.scriptHistory = scriptHistory;
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public void execute(Script script) throws DataAccessException {
		if(script.isSystem() || scriptHistory.hasNotBeenRun(script)) {
			run(script);
		} else {
			logger.info("Not running {}", script);
		}
	}
	
	private void run(Script script) {
		logger.info("Executing script: {}", script);
		jdbcTemplate.execute(script.getContents());
		scriptHistory.recordRun(script);
	}
}
