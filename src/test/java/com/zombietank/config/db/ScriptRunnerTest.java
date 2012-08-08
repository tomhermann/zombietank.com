package com.zombietank.config.db;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;

@RunWith(MockitoJUnitRunner.class)
public class ScriptRunnerTest {
	private ScriptRunner scriptRunner;

	@Mock
	private ScriptHistory scriptHistory;

	@Mock
	private Script script;
	
	@Mock
	private JdbcTemplate jdbcTemplate;
	
	
	@Before
	public void setup() {
		when(script.getContents()).thenReturn(UUID.randomUUID().toString());
		this.scriptRunner = new ScriptRunner(scriptHistory, jdbcTemplate);
	}
	
	@Test
	public void whenScriptHasNotBeenRunItShouldBeExecuted() {
		when(scriptHistory.hasNotBeenRun(script)).thenReturn(true);
		
		scriptRunner.execute(script);
		
		verify(jdbcTemplate).execute(script.getContents());
	}

	@Test
	public void whenNewScriptIsRunItIsRecordedInHistory() {
		when(scriptHistory.hasNotBeenRun(script)).thenReturn(true);
		
		scriptRunner.execute(script);
		
		verify(scriptHistory).recordRun(script);
	}

	@Test
	public void whenSystemScriptIsRunItIsRecordedInHistory() {
		when(script.isSystem()).thenReturn(true);
		
		scriptRunner.execute(script);
		
		verify(scriptHistory).recordRun(script);
	}

	@Test
	public void whenSystemScriptIsRunItIsRecordedInHistoryEvenIfAlreadyRun() {
		when(script.isSystem()).thenReturn(true);
		when(scriptHistory.hasBeenRun(script)).thenReturn(true);
		
		scriptRunner.execute(script);
		
		verify(scriptHistory).recordRun(script);
	}

	@Test
	public void whenScriptIsSystemItShouldAlwaysBeExecuted() {
		when(script.isSystem()).thenReturn(true);
		when(scriptHistory.hasBeenRun(script)).thenReturn(false);
		
		scriptRunner.execute(script);
		
		verify(jdbcTemplate).execute(script.getContents());
	}

	@Test
	public void whenScriptHasBeenRunItShouldNotExecuteAgain() {
		when(scriptHistory.hasNotBeenRun(script)).thenReturn(false);
		
		scriptRunner.execute(script);
		
		verifyZeroInteractions(jdbcTemplate);
	}

}
