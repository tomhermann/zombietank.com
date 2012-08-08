package com.zombietank.config.db;

import org.springframework.beans.factory.InitializingBean;

public class DatabaseBootstrap implements InitializingBean {
	private final ScriptRunner scriptRunner;
	private final Scripts scripts;

	public DatabaseBootstrap(Scripts scripts, ScriptRunner scriptRunner) {
		this.scripts = scripts;
		this.scriptRunner = scriptRunner;
	}

	public void executeScripts() {
		for (Script script : scripts) {
			scriptRunner.execute(script);
		}			
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		executeScripts();
	}
}
