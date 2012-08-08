package com.zombietank.config.db;

import static org.mockito.Mockito.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.ImmutableSet;

@RunWith(MockitoJUnitRunner.class)
public class DatabaseBootstrapTest {
	private DatabaseBootstrap databaseBootstrap;
	private Set<Script> scriptCollection;
	@Mock private Scripts scripts;
	@Mock private ScriptRunner scriptRunner;
	
	@Before
	public void setup() {
		scriptCollection = new HashSet<Script>();
		this.databaseBootstrap = new DatabaseBootstrap(scripts, scriptRunner);
	}
	
	
	@Test
	public void onExecuteScriptsExecuteEach() {
		Script script1 = mock(Script.class);
		Script script2 = mock(Script.class);
		scriptCollection.addAll(ImmutableSet.of(script1, script2));
		when(scripts.getScripts()).thenReturn(scriptCollection);
		// maybe this iterable Scripts business was a bad idea.
		when(scripts.iterator()).thenReturn(scriptCollection.iterator()); 
		
		databaseBootstrap.executeScripts();
		
		verify(scriptRunner).execute(script1);
		verify(scriptRunner).execute(script2);
	}

}
