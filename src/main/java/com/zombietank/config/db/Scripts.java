package com.zombietank.config.db;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class Scripts implements Iterable<Script> {
	private final ScriptLoader scriptLoader;
	private final Set<Script> scripts;
	
	public Scripts() {
		this(new ScriptLoader());
	}
	
	public Scripts(ScriptLoader scriptLoader) {
		this.scripts = new CopyOnWriteArraySet<Script>();
		this.scriptLoader = scriptLoader;
	}
	
	public Scripts addScript(String scriptLocation) throws IOException {
		scripts.add(scriptLoader.loadScript(scriptLocation));
		return this;
	}

	@Override
	public Iterator<Script> iterator() {
		return scripts.iterator();
	}
}
