package com.zombietank.config.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

public class DatabaseBootstrap implements InitializingBean {
	private static final Logger logger = LoggerFactory.getLogger(DatabaseBootstrap.class);
	private final ScriptRunner scriptRunner;
	private final Scripts scripts;

	public DatabaseBootstrap(Scripts scripts, ScriptRunner scriptRunner) {
		this.scripts = scripts;
		this.scriptRunner = scriptRunner;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		for (Script script : scripts) {
			logger.info("Executing {}, checksum: {}", script.getPath(), script.getChecksum());
			scriptRunner.execute(script);
			logger.info("Execution complete.");
		}			
	}
}
