package com.zombietank.config.db;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.ImmutableSet;

@RunWith(MockitoJUnitRunner.class)
public class DatabaseBootstrapTest {
	@InjectMocks private DatabaseBootstrap databaseBootstrap;
	@Mock private Scripts scripts;
	@Mock private ScriptRunner scriptRunner;
	
	@Test
	public void onExecuteScriptsExecuteEachOne() {
		Script script1 = mock(Script.class);
		Script script2 = mock(Script.class);
		// Scripts is an Iterable<Script>; it is implementation specific, but this works. 
		when(scripts.iterator()).thenReturn(ImmutableSet.of(script1, script2).iterator()); 
		
		databaseBootstrap.executeScripts();
		
		verify(scriptRunner).execute(script1);
		verify(scriptRunner).execute(script2);
	}

	@Test
	public void afterPropertiesSetExecutesScripts() throws Exception {
		Script script1 = mock(Script.class);
		Script script2 = mock(Script.class);
		when(scripts.iterator()).thenReturn(ImmutableSet.of(script1, script2).iterator()); 
		
		databaseBootstrap.afterPropertiesSet();
		
		verify(scriptRunner).execute(script1);
		verify(scriptRunner).execute(script2);
	}
}
