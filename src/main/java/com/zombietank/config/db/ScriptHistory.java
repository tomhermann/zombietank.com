package com.zombietank.config.db;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class ScriptHistory {
	private JdbcTemplate jdbcTemplate;

	public ScriptHistory(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public boolean hasBeenRun(Script script) {
		return jdbcTemplate.queryForInt("select count(*) from script_history where checksum = ?") > 0;
	}
	
	public void recordRun(Script script) {
		jdbcTemplate.update("insert into script_history (script_name, checksum) values (?, ?)", script.getPath(), script.getChecksum());
	}
}
