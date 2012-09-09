package com.zombietank.audio;

import java.io.File;

public class SongFile {
	private final File file;
	private PersistableTag tag;
	private Throwable error;
	
	public SongFile(File file) {
		this.file = file;
	}

	public PersistableTag getTag() {
		return tag;
	}

	public void setTag(PersistableTag tag) {
		this.tag = tag;
	}
	
	public String getFilename() {
		return file.getName();
	}

	public String getPath() {
		return file.getAbsolutePath();
	}

	public File getFile() {
		return file;
	}
	
	public boolean hasError() {
		return error != null;
	}
	
	public Throwable getError() {
		return error;
	}

	public void setError(Throwable error) {
		this.error = error;
	}
}
